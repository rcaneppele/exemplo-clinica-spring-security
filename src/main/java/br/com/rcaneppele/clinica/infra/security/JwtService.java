package br.com.rcaneppele.clinica.infra.security;

import br.com.rcaneppele.clinica.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    private final String issuer;
    private final String secret;

    public JwtService(@Value("${app.security.token.issuer}") String issuer, @Value("${app.security.token.secret}") String secret) {
        this.issuer = issuer;
        this.secret = secret;
    }

    public String gerarJwt(Usuario usuario) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getId().toString())
                    .withClaim("role", usuario.getRole())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar JWT", exception);
        }
    }

    public DadosUsuario dadosDoUsuario(String jwt) {
        try {
            var verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build()
                    .verify(jwt);
            return new DadosUsuario(Long.parseLong(verifier.getSubject()), verifier.getClaim("role").asString());
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("JWT inválido ou expirado:" +jwt);
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

}

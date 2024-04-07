package br.com.rcaneppele.clinica.infra.security;

import br.com.rcaneppele.clinica.domain.medico.Medico;
import br.com.rcaneppele.clinica.domain.paciente.Paciente;
import br.com.rcaneppele.clinica.domain.usuario.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public SecurityFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwt = getJwtDoHeader(request);

        if (jwt != null) {
            var usuario = getUsuario(jwt);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtDoHeader(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        return null;
    }

    private Usuario getUsuario(String jwt) {
        var dados = jwtService.dadosDoUsuario(jwt);

        if (dados.role().equals("ROLE_MEDICO")) {
            return new Medico(dados.id());
        }

        return new Paciente(dados.id());
    }

}

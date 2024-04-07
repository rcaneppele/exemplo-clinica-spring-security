package br.com.rcaneppele.clinica.web.controller;

import br.com.rcaneppele.clinica.domain.usuario.Usuario;
import br.com.rcaneppele.clinica.infra.security.JwtService;
import br.com.rcaneppele.clinica.web.dto.JwtDto;
import br.com.rcaneppele.clinica.web.dto.LoginDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final JwtService jwtService;

    public AutenticacaoController(AuthenticationManager manager, JwtService jwtService) {
        this.manager = manager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<JwtDto> efetuarLogin(@RequestBody @Valid LoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var jwt = jwtService.gerarJwt((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtDto(jwt));
    }

}
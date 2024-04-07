package br.com.rcaneppele.clinica.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("/medicos Response");
    }

}

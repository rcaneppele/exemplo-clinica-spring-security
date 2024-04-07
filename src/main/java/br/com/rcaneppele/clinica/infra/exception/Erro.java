package br.com.rcaneppele.clinica.infra.exception;

import org.springframework.validation.FieldError;

public record Erro(String campo, String mensagem) {
    public Erro(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}

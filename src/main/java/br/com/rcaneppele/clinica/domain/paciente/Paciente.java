package br.com.rcaneppele.clinica.domain.paciente;

import br.com.rcaneppele.clinica.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {

    private static final String ROLE = "ROLE_PACIENTE";

    private String nome;
    private String email;
    private String cpf;

    public Paciente() {
    }

    public Paciente(Long id) {
        this.id = id;
    }

    public Paciente(String nome, String email, String cpf, String senha) {
        super(cpf, senha);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cpf, paciente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

}

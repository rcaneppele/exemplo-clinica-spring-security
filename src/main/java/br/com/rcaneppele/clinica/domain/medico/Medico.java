package br.com.rcaneppele.clinica.domain.medico;

import br.com.rcaneppele.clinica.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "medicos")
public class Medico extends Usuario {

    private static final String ROLE = "ROLE_MEDICO";

    private String nome;
    private String email;
    private String crm;

    public Medico() {
    }

    public Medico(Long id) {
        this.id = id;
    }

    public Medico(String nome, String email, String crm, String senha) {
        super(crm, senha);
        this.nome = nome;
        this.email = email;
        this.crm = crm;
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(crm, medico.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crm);
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

}

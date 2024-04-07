package br.com.rcaneppele.clinica.domain.usuario;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String login;
    private String senha;
    private Boolean bloqueado;

    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.bloqueado = false;
        this.login = login;
        this.senha = senha;
    }

    public String getRole() {
        return "ROLE_ADMIN";
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !bloqueado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

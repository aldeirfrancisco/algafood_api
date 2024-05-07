package com.algafood.algafoodapi.core.security.authorizationsserver;

import java.util.Collection;

import com.algafood.algafoodapi.domain.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private long id;
    private String fullName;
    private String password;
    private String nomeCompleto;
    private Collection<GrantedAuthority> authorities;

    public AuthUser(Usuario usuario, Collection<GrantedAuthority> authorities) {
        this.fullName = usuario.getEmail();
        this.password = usuario.getSenha();
        this.id = usuario.getId();
        this.nomeCompleto = usuario.getNome();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return fullName;
    }

    public long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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

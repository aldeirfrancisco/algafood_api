package com.algafood.algafoodapi.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurit {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuario() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("id_usuario");
    }

}

package com.algafood.algafoodapi.core.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSegurity {

    public @interface Cozinhas {
        @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME) // vai ser lida em tempo de execução
        @Target(METHOD) // AONDE VAI SER USADO A Annotation, no caso so método
        public @interface PodeEditar {
        }

        @PreAuthorize("isAuthenticated")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }
    }
}

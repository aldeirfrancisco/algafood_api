package com.algafood.algafoodapi.domain.service;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {

    void envio(Mensagem ms);

    @Builder
    @Getter
    class Mensagem {

        @Singular
        private Set<String> destinatarios;
        @NonNull
        private String assunto;
        private String corpo;
    }
}

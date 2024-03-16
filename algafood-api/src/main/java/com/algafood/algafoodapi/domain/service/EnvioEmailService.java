package com.algafood.algafoodapi.domain.service;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public interface EnvioEmailService {

    void envio(Mensagem ms);

    @Setter
    @Getter
    class Mensagem {
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
    }
}

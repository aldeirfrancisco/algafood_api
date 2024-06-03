package com.algafood.algafoodapi.infrasTructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.algafood.algafoodapi.domain.service.EnvioEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {

    @Autowired
    private ProcessadoEmailTemplate procesadorEmailTemplate;

    @Override
    public void envio(Mensagem mensagem) {

        String corpo = procesadorEmailTemplate.processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }

}

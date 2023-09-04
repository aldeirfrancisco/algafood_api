package com.algafood.algafoodapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.algafoodapi.notificacao.NotificadoarEmail;
import com.algafood.algafoodapi.notificacao.NotificadoarMSN;
import com.algafood.algafoodapi.service.AtivacaoClienteService;

//@Configuration
public class BeanConfig {

    @Bean
    public AtivacaoClienteService getAtivacaoClienteService() {
        // NotificadoarEmail notificador = new NotificadoarEmail();
        return new AtivacaoClienteService();
    }

    @Bean
    public NotificadoarEmail getNotificadoarEmail() {
        return new NotificadoarEmail();
    }

    @Bean
    public NotificadoarMSN getNotificadoarMSN() {
        return new NotificadoarMSN();
    }
}

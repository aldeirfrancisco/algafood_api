package com.algafood.algafoodapi.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

    private String hostServidor;

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public String getHostServidor() {
        return hostServidor;
    }
}

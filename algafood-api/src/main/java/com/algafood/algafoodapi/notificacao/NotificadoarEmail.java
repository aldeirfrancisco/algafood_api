package com.algafood.algafoodapi.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadoarEmail implements Notificador {

    @Value("${notificador.email.host-servidor}")
    private String rotEmail;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("rotEmail %s \n", rotEmail);

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}

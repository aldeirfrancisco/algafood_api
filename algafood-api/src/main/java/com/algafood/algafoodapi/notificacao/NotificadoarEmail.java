package com.algafood.algafoodapi.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadoarEmail implements Notificador {

    @Autowired
    private NotificadorProperties notificadorProperties;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("rotEmail %s \n", notificadorProperties.getHostServidor());

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}

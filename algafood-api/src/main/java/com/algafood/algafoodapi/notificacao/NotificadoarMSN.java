package com.algafood.algafoodapi.notificacao;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadoarMSN implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do MSN %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}

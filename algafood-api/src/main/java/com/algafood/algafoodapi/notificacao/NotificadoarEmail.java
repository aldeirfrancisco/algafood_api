package com.algafood.algafoodapi.notificacao;

import com.algafood.algafoodapi.modelo.Cliente;

public class NotificadoarEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}

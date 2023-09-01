package com.algafood.algafoodapi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.algafood.algafoodapi.modelo.Cliente;
import com.algafood.algafoodapi.notificacao.Notificador;

public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;

    // public AtivacaoClienteService(Notificador notificador) {
    // this.notificador = notificador;
    // }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        if (notificador != null) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        } else {
            System.out.println("Não existe notificador, mas cliente foi ativado");
        }
    }
}

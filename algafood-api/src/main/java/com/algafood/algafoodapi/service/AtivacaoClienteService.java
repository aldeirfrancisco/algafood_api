package com.algafood.algafoodapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.modelo.Cliente;
import com.algafood.algafoodapi.notificacao.NivelUrgencia;
import com.algafood.algafoodapi.notificacao.Notificador;
import com.algafood.algafoodapi.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        if (notificador != null) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        } else {
            System.out.println("Não existe notificador, mas cliente foi ativado");
        }
    }
}

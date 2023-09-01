package com.algafood.algafoodapi.notificacao;

import com.algafood.algafoodapi.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);

}

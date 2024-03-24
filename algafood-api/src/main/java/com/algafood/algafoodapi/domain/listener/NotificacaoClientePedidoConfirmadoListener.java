package com.algafood.algafoodapi.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.domain.event.PedidoConfirmadoEvent;
import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.service.EnvioEmailService;
import com.algafood.algafoodapi.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
    @Autowired
    private EnvioEmailService envioEmail;

    // sempre que o pedido for comfirmado, dispara um evento e
    // quando o evento for disparado o spring chama esse método com a instacia do
    // evento disparado
    @EventListener // marcar o método com listener de evento
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();
        var msn = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();
        envioEmail.envio(msn);
    }
}

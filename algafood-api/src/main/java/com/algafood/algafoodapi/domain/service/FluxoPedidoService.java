package com.algafood.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private EnvioEmailService envioEmail;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmado();
        var msn = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome())
                .corpo("O pedido de código <strong>" + pedido.getId() + "</strong> foi confirmado")
                .destinatario(pedido.getCliente().getEmail())
                .build();
        envioEmail.envio(msn);
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();

    }
}
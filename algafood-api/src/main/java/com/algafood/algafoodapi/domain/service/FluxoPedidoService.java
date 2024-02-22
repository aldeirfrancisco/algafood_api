package com.algafood.algafoodapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.exception.NegocioException;
import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

    @Autowired
    EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {

            throw new NegocioException(
                    String.format("Status do pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
package com.algafood.algafoodapi.domain.event;

import com.algafood.algafoodapi.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCancelarEvent {
    private Pedido pedido;
}

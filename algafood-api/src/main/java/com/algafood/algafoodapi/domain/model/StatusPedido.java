package com.algafood.algafoodapi.domain.model;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}

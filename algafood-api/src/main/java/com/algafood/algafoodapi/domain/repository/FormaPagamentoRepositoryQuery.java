package com.algafood.algafoodapi.domain.repository;

import java.util.List;

import com.algafood.algafoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepositoryQuery {

    List<FormaPagamento> todas();

    FormaPagamento adicionar(FormaPagamento formaPagamento);
}

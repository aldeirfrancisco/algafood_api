package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository
        extends CustomJpaRepository<FormaPagamento, Long>, FormaPagamentoRepositoryQuery {

}

package com.algafood.algafoodapi.domain.repository;

import java.time.OffsetDateTime;

import com.algafood.algafoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository
                extends CustomJpaRepository<FormaPagamento, Long>, FormaPagamentoRepositoryQuery {

        OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);

}

package com.algafood.algafoodapi.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.algafoodapi.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}

package com.algafood.algafoodapi.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.algafoodapi.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}

package com.algafood.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algafood.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome,
            BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}

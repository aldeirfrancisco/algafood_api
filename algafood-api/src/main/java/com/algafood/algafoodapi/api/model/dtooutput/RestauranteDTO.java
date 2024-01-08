package com.algafood.algafoodapi.api.model.dtooutput;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}
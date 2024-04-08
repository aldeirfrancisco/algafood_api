package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteResumoDTO extends RepresentationModel<RestauranteResumoDTO> {

    private Long id;
    private String nome;
}

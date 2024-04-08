package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoDTO extends RepresentationModel<CidadeResumoDTO> {

    private Long id;
    private String nome;
    private String estado;
}

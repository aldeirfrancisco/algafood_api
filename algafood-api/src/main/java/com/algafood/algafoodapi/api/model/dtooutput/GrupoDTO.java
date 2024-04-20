package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoDTO extends RepresentationModel<GrupoDTO> {
    private Long id;
    private String nome;

}

package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long id;

    private String nome;

    private String email;

}

package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO> {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Minas gerais")
    private String nome;

}

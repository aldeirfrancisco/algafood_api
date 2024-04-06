package com.algafood.algafoodapi.api.model.dtooutput;

import org.springframework.hateoas.RepresentationModel; // É um contenier para coleção de link, tem metodo para adicionar link.

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;
    @ApiModelProperty(example = "Uberlândia")
    private String nome;
    private EstadoDTO estado;
}

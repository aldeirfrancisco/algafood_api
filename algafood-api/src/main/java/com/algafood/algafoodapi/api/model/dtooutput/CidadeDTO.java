package com.algafood.algafoodapi.api.model.dtooutput;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeDTO {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;
    @ApiModelProperty(example = "Uberlândia")
    private String nome;
    private EstadoDTO estado;
}

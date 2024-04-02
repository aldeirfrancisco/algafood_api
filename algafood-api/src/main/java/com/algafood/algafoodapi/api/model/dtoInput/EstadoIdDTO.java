package com.algafood.algafoodapi.api.model.dtoInput;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdDTO {
    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}

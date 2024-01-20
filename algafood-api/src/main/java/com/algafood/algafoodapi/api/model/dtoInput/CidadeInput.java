package com.algafood.algafoodapi.api.model.dtoInput;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}

package com.algafood.algafoodapi.api.model.mixin;

import java.util.List;

import com.algafood.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}

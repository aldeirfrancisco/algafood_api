package com.algafood.algafoodapi.core.jackson;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.mixin.CozinhaMixin;
import com.algafood.algafoodapi.api.model.mixin.RestauranteMixin;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}

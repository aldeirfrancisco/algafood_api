package com.algafood.algafoodapi.core.jackson;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.mixin.CidadeMixin;
import com.algafood.algafoodapi.api.model.mixin.CozinhaMixin;
import com.algafood.algafoodapi.domain.model.Cidade;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}

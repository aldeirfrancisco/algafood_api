package com.algafood.algafoodapi.api.asswmbler;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtoInput.RestauranteInput;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}

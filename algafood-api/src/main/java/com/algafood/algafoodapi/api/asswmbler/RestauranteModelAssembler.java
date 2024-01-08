package com.algafood.algafoodapi.api.asswmbler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtooutput.CozinhaDTO;
import com.algafood.algafoodapi.api.model.dtooutput.RestauranteDTO;
import com.algafood.algafoodapi.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {
    public RestauranteDTO toModel(Restaurante restaurante) {
        CozinhaDTO cozinhaModel = new CozinhaDTO();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteModel = new RestauranteDTO();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);
        return restauranteModel;
    }

    public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}

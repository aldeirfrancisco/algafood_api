package com.algafood.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.Exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.model.Restaurante;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = this.cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }
}

package com.algafood.algafoodapi.domain.repository;

import java.util.List;

import com.algafood.algafoodapi.domain.model.Cozinha;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha porId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}

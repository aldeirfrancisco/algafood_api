package com.algafood.algafoodapi.domain.repository;

import java.util.List;

import com.algafood.algafoodapi.domain.model.Cidade;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade porId(Long id);

    Cidade adicionar(Cidade cidade);

    void remover(Cidade cidade);

}

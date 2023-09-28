package com.algafood.algafoodapi.domain.repository;

import java.util.List;

import com.algafood.algafoodapi.domain.model.Cidade;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cidade);

    void remover(Long cidadeId);

}

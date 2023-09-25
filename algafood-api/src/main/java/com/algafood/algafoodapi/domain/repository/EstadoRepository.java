package com.algafood.algafoodapi.domain.repository;

import java.util.List;

import com.algafood.algafoodapi.domain.model.Estado;

public interface EstadoRepository {

    List<Estado> listar();

    Estado porId(Long id);

    Estado adicionar(Estado estado);

    void remover(Estado estado);

}

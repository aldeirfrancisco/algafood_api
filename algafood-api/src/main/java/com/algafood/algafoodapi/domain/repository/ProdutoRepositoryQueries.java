package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);
}

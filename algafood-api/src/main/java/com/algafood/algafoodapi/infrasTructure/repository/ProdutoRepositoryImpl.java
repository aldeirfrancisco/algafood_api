package com.algafood.algafoodapi.infrasTructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.algafood.algafoodapi.domain.model.FotoProduto;
import com.algafood.algafoodapi.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

}

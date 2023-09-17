package com.algafood.algafoodapi.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Cozinha;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Transactional // faz com que o metod seja executado dentro de uma transação
    public Cozinha adicionar(Cozinha cozinha) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(cozinha);
    }
}

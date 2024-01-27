package com.algafood.algafoodapi.infrasTructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.algafood.algafoodapi.domain.model.FormaPagamento;

import com.algafood.algafoodapi.domain.repository.FormaPagamentoRepositoryQuery;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> todas() {
        return manager.createQuery("from Cozinha", FormaPagamento.class)
                .getResultList();
    }

    // e que essas transaçoes so garate que operações que deve acontece juntas só
    // sejam comfirmadas se todas obtiverem sucesso,
    // se uma de errado todas as outras vão ser desfeitas garantido a integridade do
    // banco de dados.
    @Transactional
    @Override
    public FormaPagamento adicionar(FormaPagamento formaPagamento) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(formaPagamento);
    }

}

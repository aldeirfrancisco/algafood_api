package com.algafood.algafoodapi.infrasTructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override()
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);

    }

    // e que essas transaçoes so garate que operações que deve acontece juntas só
    // sejam comfirmadas se todas obtiverem sucesso,
    // se uma de errado todas as outras vão ser desfeitas garantido a integridade do
    // banco de dados.
    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(cozinha);
    }

    @Transactional // faz com que o metod seja executado dentro de uma transação
    @Override
    public void remover(Cozinha cozinha) {
        cozinha = this.buscar(cozinha.getId());
        manager.remove(cozinha);
    }
}

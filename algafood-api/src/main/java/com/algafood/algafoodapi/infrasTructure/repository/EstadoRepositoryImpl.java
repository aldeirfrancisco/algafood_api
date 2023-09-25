package com.algafood.algafoodapi.infrasTructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Estado;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    public Estado porId(Long id) {
        return manager.find(Estado.class, id);

    }

    // e que essas transaçoes so garate que operações que deve acontece juntas só
    // sejam comfirmadas se todas obtiverem sucesso,
    // se uma de errado todas as outras vão ser desfeitas garantido a integridade do
    // banco de dados.
    @Transactional
    @Override
    public Estado adicionar(Estado estado) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(estado);
    }

    @Transactional // faz com que o metod seja executado dentro de uma transação
    @Override
    public void remover(Estado estado) {
        estado = this.porId(estado.getId());
        manager.remove(estado);
    }
}

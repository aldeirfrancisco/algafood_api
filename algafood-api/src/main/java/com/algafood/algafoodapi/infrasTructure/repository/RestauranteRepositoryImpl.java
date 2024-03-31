package com.algafood.algafoodapi.infrasTructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Restaurante;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class, id);

    }

    // e que essas transaçoes so garate que operações que deve acontece juntas só
    // sejam comfirmadas se todas obtiverem sucesso,
    // se uma de errado todas as outras vão ser desfeitas garantido a integridade do
    // banco de dados.
    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(restaurante);
    }

    @Transactional // faz com que o metod seja executado dentro de uma transação
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = this.buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}

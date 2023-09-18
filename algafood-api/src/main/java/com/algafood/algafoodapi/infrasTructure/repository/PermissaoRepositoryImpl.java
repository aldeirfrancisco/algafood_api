package com.algafood.algafoodapi.infrasTructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.model.Permissao;
import com.algafood.algafoodapi.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao porId(Long id) {
        return manager.find(Permissao.class, id);

    }

    // e que essas transaçoes so garate que operações que deve acontece juntas só
    // sejam comfirmadas se todas obtiverem sucesso,
    // se uma de errado todas as outras vão ser desfeitas garantido a integridade do
    // banco de dados.
    @Transactional
    @Override
    public Permissao adicionar(Permissao permissao) {
        // o método merge não autera a instancia atribuido a ele.
        return manager.merge(permissao);
    }

    @Transactional // faz com que o metod seja executado dentro de uma transação
    @Override
    public void remover(Permissao permissao) {
        permissao = this.porId(permissao.getId());
        manager.remove(permissao);
    }
}

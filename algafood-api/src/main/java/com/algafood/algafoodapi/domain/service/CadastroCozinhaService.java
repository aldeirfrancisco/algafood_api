package com.algafood.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.Exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.Exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long id) {
        try {

            cozinhaRepository.remover(id);

        } catch (EmptyResultDataAccessException e) {

            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com o código %d ", id));
        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                    String.format("Cozinha de  código %d não pode ser removida, pois está em uso", id));
        }
    }
}

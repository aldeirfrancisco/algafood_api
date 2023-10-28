package com.algafood.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.Exception.CozinhaNaoEncontradaException;
import com.algafood.algafoodapi.domain.Exception.EntidadeEmUsoException;
import com.algafood.algafoodapi.domain.Exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
    /**
     *
     */
    private static final String MSN_COZINHA_EM_USO = "Cozinha de  código %d não pode ser removida, pois está em uso";
    /**
     *
     */

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id) {
        try {

            cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            throw new CozinhaNaoEncontradaException(id);

        } catch (DataIntegrityViolationException e) {

            throw new EntidadeEmUsoException(
                    String.format(MSN_COZINHA_EM_USO, id));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}

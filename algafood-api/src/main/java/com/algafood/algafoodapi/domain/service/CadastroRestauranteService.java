package com.algafood.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.algafoodapi.domain.Exception.RestauranteNaoEncontradoException;
import com.algafood.algafoodapi.domain.model.Cidade;
import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.model.FormaPagamento;
import com.algafood.algafoodapi.domain.model.Restaurante;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroFormaPagamento cadastroFormaPagamento;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = this.cadastroCozinha.buscarOuFalhar(cozinhaId);
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return (Restaurante) restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        restaurante.inativar();
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = this.cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        restaurante.removeFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormapagamento(long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = this.cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return this.restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.fechar();
    }
}

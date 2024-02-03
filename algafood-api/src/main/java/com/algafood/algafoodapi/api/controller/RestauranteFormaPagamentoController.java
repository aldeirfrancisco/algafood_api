package com.algafood.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoModelAssembler;

import com.algafood.algafoodapi.api.model.dtooutput.FormaPagamentoDTO;

import com.algafood.algafoodapi.domain.model.Restaurante;

import com.algafood.algafoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return this.formaPagamentoModelAssembler.toCollectionDTO(restaurante.getFormasPagamento());
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarFormaPagamento(@PathVariable long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);

    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarFormapagamento(@PathVariable long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.associarFormapagamento(restauranteId, formaPagamentoId);

    }
}

package com.algafood.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoModelAssembler;

import com.algafood.algafoodapi.api.model.dtooutput.FormaPagamentoDTO;
import com.algafood.algafoodapi.core.security.CheckSecurity.Restaurantes.PodeConsultar;
import com.algafood.algafoodapi.core.security.CheckSecurity.Restaurantes.PodeEditar;
import com.algafood.algafoodapi.domain.model.Restaurante;

import com.algafood.algafoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @PodeConsultar
    @GetMapping
    public CollectionModel<FormaPagamentoDTO> listar(@PathVariable long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        CollectionModel<FormaPagamentoDTO> formasPagamentoModel = formaPagamentoModelAssembler
                .toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(algaLinks.linkToRestauranteFormasPagamento(restauranteId))
                .add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

        formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
            formaPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
                    restauranteId, formaPagamentoModel.getId(), "desassociar"));

        });

        return formasPagamentoModel;
    }

    @PodeEditar
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable long restauranteId,
            @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @PodeEditar
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarFormapagamento(@PathVariable long restauranteId,
            @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.associarFormapagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}

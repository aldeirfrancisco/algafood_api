package com.algafood.algafoodapi.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoInputDissasembler;
import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.FormaPagamentoInput;
import com.algafood.algafoodapi.api.model.dtooutput.FormaPagamentoDTO;
import com.algafood.algafoodapi.core.security.CheckSecurity.FormasPagamento.PodeConsultar;
import com.algafood.algafoodapi.core.security.CheckSecurity.FormasPagamento.PodeEditar;
import com.algafood.algafoodapi.domain.model.FormaPagamento;
import com.algafood.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.algafood.algafoodapi.domain.service.CadastroFormaPagamento;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDissasembler formaPagamentoInputDissasembler;

    @Autowired
    private CadastroFormaPagamento cadastroFormaPagamento;

    @PodeConsultar
    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request) {

        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoDTO> formasPagamentosModel = formaPagamentoModelAssembler
                .toCollectionModel(todasFormasPagamentos);
        return ResponseEntity.ok().body(formasPagamentosModel);
    }

    @PodeConsultar
    @GetMapping(value = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId,
            ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataAtualizacao = formaPagamentoRepository
                .getDataAtualizacaoById(formaPagamentoId);

        if (dataAtualizacao != null) {
            eTag = String.valueOf(dataAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        FormaPagamentoDTO formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoModel);
    }

    @PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDissasembler.toDomainObject(formaPagamentoInput);
        return cadastroFormaPagamento.salvar(formaPagamento);
    }

    @PodeEditar
    @DeleteMapping("/formaPagamentoId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);
    }

    @PodeEditar
    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDissasembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }
}

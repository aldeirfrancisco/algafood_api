package com.algafood.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoInputDissasembler;
import com.algafood.algafoodapi.api.asswmbler.FormaPagamentoModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.FormaPagamentoInput;
import com.algafood.algafoodapi.api.model.dtooutput.FormaPagamentoDTO;
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

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return formaPagamentoModelAssembler.toCollectionDTO(formaPagamentoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDissasembler.toDomainObject(formaPagamentoInput);
        return cadastroFormaPagamento.salvar(formaPagamento);
    }

    @DeleteMapping("/formaPagamentoId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDissasembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toDTO(formaPagamentoAtual);
    }
}

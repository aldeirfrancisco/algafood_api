package com.algafood.algafoodapi.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.domain.service.CadastroCozinhaService;

import lombok.extern.slf4j.Slf4j;

import com.algafood.algafoodapi.api.asswmbler.CozinhaInputDisassembler;
import com.algafood.algafoodapi.api.asswmbler.CozinhaModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.CozinhaInput;
import com.algafood.algafoodapi.api.model.dtooutput.CozinhaDTO;
import com.algafood.algafoodapi.domain.model.Cozinha;

//@ResponseBody garante que a resposta do metodo vai ser o corpo da requeiscao
//@Controller
@Slf4j
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @SuppressWarnings("rawtypes")
    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        log.info("Aldeir {}", pageable.getPageSize());
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
        @SuppressWarnings("unchecked")
        PagedModel<CozinhaDTO> cozinhaPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelAssembler);

        return cozinhaPagedModel;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cadastroCozinha.salvar(cozinha);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
            @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }

}

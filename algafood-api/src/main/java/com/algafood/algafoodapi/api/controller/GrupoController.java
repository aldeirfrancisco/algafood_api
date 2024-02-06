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

import com.algafood.algafoodapi.api.asswmbler.GrupoInputDissassembler;
import com.algafood.algafoodapi.api.asswmbler.GrupoModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.GrupoInput;
import com.algafood.algafoodapi.api.model.dtooutput.GrupoDTO;
import com.algafood.algafoodapi.domain.model.Grupo;
import com.algafood.algafoodapi.domain.repository.GrupoRepository;
import com.algafood.algafoodapi.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;
    @Autowired
    private GrupoInputDissassembler grupoInputDissassembler;

    @Autowired
    private CadastroGrupoService grupoService;

    @GetMapping
    public List<GrupoDTO> listar() {
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        return grupoModelAssembler.toDTO(grupoService.buscarOuFalhar(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDissassembler.toDomainObject(grupoInput);

        return grupoModelAssembler.toDTO(grupoService.salvar(grupo));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        grupoInputDissassembler.copyToDomainObject(grupoInput, grupo);

        grupo = grupoService.salvar(grupo);
        return grupoModelAssembler.toDTO(grupo);
    }
}

package com.algafood.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.asswmbler.UsuarioInputDisassembler;
import com.algafood.algafoodapi.api.asswmbler.UsuarioModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.SenhaInput;
import com.algafood.algafoodapi.api.model.dtoInput.UsuarioInput;
import com.algafood.algafoodapi.api.model.dtooutput.UsuarioDTO;
import com.algafood.algafoodapi.domain.model.Usuario;
import com.algafood.algafoodapi.domain.repository.UsuarioRepository;
import com.algafood.algafoodapi.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public CollectionModel<UsuarioDTO> lista() {
        return this.usuarioModelAssembler.toCollectionModel(this.usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.buscarOuFalhar(usuarioId));
    }

    @PostMapping
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = this.usuarioInputDisassembler.toDomainObject(usuarioInput);
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = this.cadastroUsuarioService.buscarOuFalhar(usuarioId);
        this.usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);
        return this.usuarioModelAssembler.toModel(this.cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        this.cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}

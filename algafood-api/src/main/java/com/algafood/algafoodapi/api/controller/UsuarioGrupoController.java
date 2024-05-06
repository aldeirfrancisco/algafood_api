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
import com.algafood.algafoodapi.api.asswmbler.GrupoModelAssembler;
import com.algafood.algafoodapi.api.model.dtooutput.GrupoDTO;
import com.algafood.algafoodapi.core.security.CheckSecurity.UsuariosGruposPermissoes.PodeConsultar;
import com.algafood.algafoodapi.core.security.CheckSecurity.UsuariosGruposPermissoes.PodeEditar;
import com.algafood.algafoodapi.domain.model.Usuario;
import com.algafood.algafoodapi.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @PodeConsultar
    @GetMapping
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        CollectionModel<GrupoDTO> grupoDTOs = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "Associar"));
        grupoDTOs.getContent().forEach(grupoDTO -> grupoDTO
                .add(algaLinks.linkToUsuarioGrupoDesassociacao(usuarioId, grupoDTO.getId(), "desassociar")));

        return grupoDTOs;

    }

    @PodeEditar
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @PodeEditar
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}

package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.GrupoController;
import com.algafood.algafoodapi.api.model.dtooutput.GrupoDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurit algaSecurity;

    public GrupoModelAssembler() {
        super(GrupoController.class, GrupoDTO.class);

    }

    @Override
    public GrupoDTO toModel(Grupo grupo) {
        GrupoDTO grupoDTO = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoDTO);
        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            grupoDTO.add(algaLinks.linkToGrupos("grupos"));

            grupoDTO.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        }
        return grupoDTO;

    }

    @Override
    public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
        CollectionModel<GrupoDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToGrupos());
        }

        return collectionModel;
    }

}

package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.CidadeController;

import com.algafood.algafoodapi.api.model.dtooutput.CidadeDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurit algaSecurity;

    @Override
    public CidadeDTO toModel(Cidade cidade) {
        CidadeDTO cidadeModel = modelMapper.map(cidade, CidadeDTO.class);
        if (algaSecurity.podeConsultarCidades()) {
            cidadeModel.add(algaLinks.linkToCidades("cidades"));
        }

        if (algaSecurity.podeConsultarEstados()) {
            cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
        }

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarCidades()) {
            collectionModel.add(algaLinks.linkToCidades());
        }

        return collectionModel;
    }

}

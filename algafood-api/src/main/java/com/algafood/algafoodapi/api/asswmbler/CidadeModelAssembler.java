package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.controller.CidadeController;
import com.algafood.algafoodapi.api.controller.EstadoController;
import com.algafood.algafoodapi.api.model.dtooutput.CidadeDTO;
import com.algafood.algafoodapi.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CidadeDTO toModel(Cidade cidade) {
        CidadeDTO cidadeDTO = modelMapper.map(cidade, CidadeDTO.class);
        cidadeDTO.add(linkTo(methodOn(CidadeController.class).buscar(cidadeDTO.getId())).withSelfRel());
        cidadeDTO.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
        cidadeDTO.getEstado().add(
                linkTo(methodOn(EstadoController.class).buscar(cidadeDTO.getEstado().getId())).withSelfRel());
        return cidadeDTO;
    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
    }

}

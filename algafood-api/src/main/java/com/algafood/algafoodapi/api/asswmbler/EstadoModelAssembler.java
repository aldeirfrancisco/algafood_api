package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.controller.EstadoController;
import com.algafood.algafoodapi.api.model.dtooutput.EstadoDTO;
import com.algafood.algafoodapi.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EstadoDTO toModel(Estado estado) {

        EstadoDTO estadoDTO = modelMapper.map(estado, EstadoDTO.class);
        estadoDTO.add(linkTo(methodOn(EstadoController.class).buscar(estado.getId())).withSelfRel());
        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> estado) {
        return super.toCollectionModel(estado).add(linkTo(EstadoController.class).withSelfRel());
    }

}

package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
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
    @Autowired
    private AlgaLinks algaLinks;

    @Override
    public EstadoDTO toModel(Estado estado) {

        EstadoDTO estadoDTO = modelMapper.map(estado, EstadoDTO.class);
        estadoDTO.add(algaLinks.linkToEstados("estados"));
        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> estado) {
        return super.toCollectionModel(estado).add(algaLinks.linkToEstados());
    }

}

package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.CozinhaController;
import com.algafood.algafoodapi.api.model.dtooutput.CozinhaDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

    public CozinhaModelAssembler() {
        super(CozinhaController.class, CozinhaDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLink;

    @Autowired
    private AlgaSecurit algaSecurity;

    @Override
    public CozinhaDTO toModel(Cozinha cozinha) {
        CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);

        if (algaSecurity.podeConsultarCozinhas()) {
            cozinhaDTO.add(algaLink.linkToCozinhas("cozinhas"));
        }
        return cozinhaDTO;
    }

    @Override
    public CollectionModel<CozinhaDTO> toCollectionModel(Iterable<? extends Cozinha> cozinhas) {
        return super.toCollectionModel(cozinhas).add(linkTo(CozinhaController.class).withSelfRel());
    }
}

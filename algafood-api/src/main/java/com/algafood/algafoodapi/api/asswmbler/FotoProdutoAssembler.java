package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.RestauranteProdutoFotoController;
import com.algafood.algafoodapi.api.model.dtooutput.FotoProdutoDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurit algaSecurity;

    public FotoProdutoAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoDTO.class);
    }

    @Override
    public FotoProdutoDTO toModel(FotoProduto foto) {
        FotoProdutoDTO fotoProdutoDTO = createModelWithId(foto.getId(), foto);
        modelMapper.map(foto, fotoProdutoDTO);
        if (algaSecurity.podeConsultarRestaurantes()) {
            fotoProdutoDTO.add(this.algaLinks.linkToFotoProduto(foto.getRestauranteId(), foto.getProduto().getId()));
            fotoProdutoDTO.add(algaLinks.linkToProduto(
                    foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
        }
        return fotoProdutoDTO;
    }

}

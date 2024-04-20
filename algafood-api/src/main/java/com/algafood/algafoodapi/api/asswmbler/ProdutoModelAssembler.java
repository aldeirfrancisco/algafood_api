package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.RestauranteProdutoController;
import com.algafood.algafoodapi.api.model.dtooutput.ProdutoDTO;
import com.algafood.algafoodapi.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

    @Autowired
    private AlgaLinks algaLinks;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoDTO.class);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProdutoDTO toModel(Produto produto) {
        ProdutoDTO produtoModel = createModelWithId(
                produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoModel);

        produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        return produtoModel;
    }

}

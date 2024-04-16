package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.controller.PedidoController;
import com.algafood.algafoodapi.api.controller.UsuarioController;
import com.algafood.algafoodapi.api.model.dtooutput.PedidoResumoDTO;
import com.algafood.algafoodapi.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);

        // pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos1"));
        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", VariableType.REQUEST_PARAM),
                new TemplateVariable("size", VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", VariableType.REQUEST_PARAM));

        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
                new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        pedidoModel.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables.concat(filtroVariables)), "pedidos"));
        pedidoModel.getRestaurante()
                .add(linkTo(methodOn(PedidoController.class).buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModel.getCliente().add(
                linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoModel;
    }

    public CollectionModel<PedidoResumoDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos).add(linkTo(PedidoController.class).withSelfRel());
    }
}

package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.controller.CidadeController;
import com.algafood.algafoodapi.api.controller.FormaPagamentoController;
import com.algafood.algafoodapi.api.controller.PedidoController;
import com.algafood.algafoodapi.api.controller.RestauranteController;
import com.algafood.algafoodapi.api.controller.RestauranteProdutoController;
import com.algafood.algafoodapi.api.controller.UsuarioController;
import com.algafood.algafoodapi.api.model.dtooutput.PedidoDTO;
import com.algafood.algafoodapi.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTO toModel(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
        pedidoDTO.add(linkTo(methodOn(PedidoController.class, pedidoDTO).buscar(pedidoDTO.getId())).withSelfRel());
        pedidoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class, pedidoDTO.getRestaurante())
                .buscar(pedidoDTO.getRestaurante().getId())).withSelfRel());

        pedidoDTO.getCliente().add(
                linkTo(methodOn(UsuarioController.class, pedidoDTO.getCliente()).buscar(pedidoDTO.getCliente().getId()))
                        .withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoDTO.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class, pedidoDTO.getFormaPagamento())
                .buscar(pedidoDTO.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class,
                pedidoDTO.getEnderecoEntrega().getCidade()).buscar(pedidoDTO.getEnderecoEntrega().getCidade().getId()))
                .withSelfRel());

        pedidoDTO.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoDTO.getRestaurante().getId(), item.getProdutoId())).withRel("produto"));
        });
        return pedidoDTO;
    }

    public CollectionModel<PedidoDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos).add(linkTo(PedidoController.class).withSelfRel());
    }
}

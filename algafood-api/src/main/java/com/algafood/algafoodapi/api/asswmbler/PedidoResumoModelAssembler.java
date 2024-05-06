package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;
import com.algafood.algafoodapi.api.controller.PedidoController;
import com.algafood.algafoodapi.api.model.dtooutput.PedidoResumoDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurit algaSecurity;

    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoModel = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoModel);

        if (algaSecurity.podePesquisarPedidos()) {
            pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getRestaurante().add(
                    algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
        }

        return pedidoModel;
    }

    public CollectionModel<PedidoResumoDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos).add(linkTo(PedidoController.class).withSelfRel());
    }
}

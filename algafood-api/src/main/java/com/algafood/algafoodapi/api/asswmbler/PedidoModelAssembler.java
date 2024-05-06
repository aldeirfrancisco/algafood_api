package com.algafood.algafoodapi.api.asswmbler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.AlgaLinks;

import com.algafood.algafoodapi.api.controller.PedidoController;

import com.algafood.algafoodapi.api.model.dtooutput.PedidoDTO;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoDTO.class);

    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaSecurit algaSecurity;

    @Autowired
    private AlgaLinks algaLinks;

    public PedidoDTO toModel(Pedido pedido) {
        PedidoDTO pedidoModel = modelMapper.map(pedido, PedidoDTO.class);
        if (algaSecurity.podePesquisarPedidos()) {
            pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
        }

        if (algaSecurity.podeGerenciarPedidos(pedido.getId())) {
            if (pedido.podeSerConfirmado()) {
                pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getId(), "confirmar"));
            }

            if (pedido.podeSerCancelado()) {
                pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getId(), "cancelar"));
            }

            if (pedido.podeSerEntregue()) {
                pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getId(), "entregar"));
            }
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getRestaurante().add(
                    algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModel.getCliente().add(
                    algaLinks.linkToUsuario(pedido.getCliente().getId()));
        }

        if (algaSecurity.podeConsultarFormasPagamento()) {
            pedidoModel.getFormaPagamento().add(
                    algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        }

        if (algaSecurity.podeConsultarCidades()) {
            pedidoModel.getEnderecoEntrega().getCidade().add(
                    algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        }

        // Quem pode consultar restaurantes, também pode consultar os produtos dos
        // restaurantes
        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getItens().forEach(item -> {
                item.add(algaLinks.linkToProduto(
                        pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
            });
        }
        return pedidoModel;
    }

    public CollectionModel<PedidoDTO> toCollectionModel(Iterable<? extends Pedido> pedidos) {
        return super.toCollectionModel(pedidos).add(linkTo(PedidoController.class).withSelfRel());
    }
}

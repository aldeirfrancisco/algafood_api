package com.algafood.algafoodapi.api.model.dtooutput;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens;

}

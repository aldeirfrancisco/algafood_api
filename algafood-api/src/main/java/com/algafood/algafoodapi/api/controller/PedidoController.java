package com.algafood.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.asswmbler.PedidoInputDisassembler;
import com.algafood.algafoodapi.api.asswmbler.PedidoModelAssembler;
import com.algafood.algafoodapi.api.asswmbler.PedidoResumoModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.PedidoInput;
import com.algafood.algafoodapi.api.model.dtooutput.PedidoDTO;
import com.algafood.algafoodapi.api.model.dtooutput.PedidoResumoDTO;
import com.algafood.algafoodapi.core.data.PageWrapper;
import com.algafood.algafoodapi.core.data.PageableTranslator;
import com.algafood.algafoodapi.core.security.AlgaSecurit;
import com.algafood.algafoodapi.core.security.CheckSecurity.Pedidos.PodeBuscar;
import com.algafood.algafoodapi.core.security.CheckSecurity.Pedidos.PodeCriar;
import com.algafood.algafoodapi.core.security.CheckSecurity.Pedidos.PodePesquisar;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.exception.NegocioException;
import com.algafood.algafoodapi.domain.filtro.PedidoFiltro;
import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.model.Usuario;
import com.algafood.algafoodapi.domain.repository.PedidoRepository;
import com.algafood.algafoodapi.domain.service.EmissaoPedidoService;
import com.algafood.algafoodapi.infrasTructure.repository.spec.PedidoSpec;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @Autowired
    private AlgaSecurit algaSecurit;

    @PodePesquisar
    @SuppressWarnings("unchecked")
    @GetMapping
    public PagedModel<PedidoResumoDTO> pesquisar(PedidoFiltro filtro, @PageableDefault(size = 10) Pageable pageable) {

        Pageable pageableTraduzido = traduzirPageble(pageable);
        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpec.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        PagedModel<PedidoResumoDTO> pedidoResumo = pagedResourcesAssembler.toModel(pedidosPage,
                pedidoResumoModelAssembler);
        return pedidoResumo;
    }

    @PodeBuscar
    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PodeCriar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(algaSecurit.getUsuarioId());

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageble(Pageable pageable) {
        var mapeamento = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal");
        return PageableTranslator.translate(pageable, mapeamento);
    }
}

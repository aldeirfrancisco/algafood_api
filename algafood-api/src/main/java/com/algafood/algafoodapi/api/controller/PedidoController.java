package com.algafood.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.exception.NegocioException;
import com.algafood.algafoodapi.domain.model.Pedido;
import com.algafood.algafoodapi.domain.model.Usuario;
import com.algafood.algafoodapi.domain.repository.PedidoRepository;
import com.algafood.algafoodapi.domain.repository.filter.PedidoFiltro;
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

    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(PedidoFiltro filtro, @PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> todosPedidosPage = pedidoRepository.findAll(PedidoSpec.usandoFiltro(filtro), pageable);
        List<PedidoResumoDTO> todosPedidos = pedidoResumoModelAssembler
                .toCollectionModel(todosPedidosPage.getContent());
        return new PageImpl<>(todosPedidos, pageable, todosPedidosPage.getTotalElements());
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}

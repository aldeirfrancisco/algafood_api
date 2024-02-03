package com.algafood.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.SmartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.algafoodapi.api.asswmbler.RestauranteInputDisassembler;
import com.algafood.algafoodapi.api.asswmbler.RestauranteModelAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.RestauranteInput;
import com.algafood.algafoodapi.api.model.dtooutput.RestauranteDTO;
import com.algafood.algafoodapi.domain.Exception.CidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.Exception.CozinhaNaoEncontradaException;

import com.algafood.algafoodapi.domain.Exception.NegocioException;

import com.algafood.algafoodapi.domain.model.Restaurante;
import com.algafood.algafoodapi.domain.repository.RestauranteRepository;
import com.algafood.algafoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    SmartValidator validator;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteDTO> listar() {
        return restauranteModelAssembler.toCollectionDTO(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {

        try {

            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toDTO(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId,
            @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            // Restaurante restaurante =
            // restauranteInputDisassembler.toDomainObject(restauranteInput);

            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
            // BeanUtils.copyProperties(restaurante, restauranteAtual,
            // "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteModelAssembler.toDTO(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        cadastroRestaurante.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        cadastroRestaurante.fechar(restauranteId);
    }

    // @PatchMapping("/{restauranteId}")
    // public Restaurante atualizarParcial(@PathVariable Long restauranteId,
    // @RequestBody Map<String, Object> campos, HttpServletRequest request) {
    // Restaurante restauranteAtual =
    // cadastroRestaurante.buscarOuFalhar(restauranteId);
    // merge(campos, restauranteAtual, request);
    // validate(restauranteAtual, "restaurante");
    // return atualizar(restauranteId, restauranteAtual);
    // }

    // private void validate(Restaurante restaurante, String objectName) {
    // BeanPropertyBindingResult bindingResult = new
    // BeanPropertyBindingResult(restaurante, objectName);
    // this.validator.validate(restaurante, bindingResult);
    // if (bindingResult.hasErrors()) {
    // throw new ValidacaoException(bindingResult);
    // }
    // }

    // private void merge(Map<String, Object> dadosOrigem, Restaurante
    // restauranteDestino, HttpServletRequest request) {

    // ServletServerHttpRequest serverHttpRequest = new
    // ServletServerHttpRequest(request);
    // try {

    // ObjectMapper objectMapper = new ObjectMapper();
    // objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
    // true);
    // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
    // true);

    // Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,
    // Restaurante.class);

    // dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
    // Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
    // field.setAccessible(true);
    // Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
    // ReflectionUtils.setField(field, restauranteDestino, novoValor);
    // });
    // } catch (IllegalArgumentException e) {
    // Throwable rooCause = ExceptionUtils.getRootCause(e); // pega a causa
    // principal da exception lancada.
    // throw new HttpMessageNotReadableException(e.getMessage(), rooCause,
    // serverHttpRequest);// relancando um
    // // exception
    // }
    // }

}

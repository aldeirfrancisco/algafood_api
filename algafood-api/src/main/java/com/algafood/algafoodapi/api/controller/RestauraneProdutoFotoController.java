package com.algafood.algafoodapi.api.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.algafoodapi.api.asswmbler.FotoProdutoAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.FotoProdutoInput;
import com.algafood.algafoodapi.api.model.dtooutput.FotoProdutoDTO;
import com.algafood.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.algafoodapi.domain.model.FotoProduto;
import com.algafood.algafoodapi.domain.model.Produto;
import com.algafood.algafoodapi.domain.service.CadastroProdutoService;
import com.algafood.algafoodapi.domain.service.CatalogoFotoProdutoService;
import com.algafood.algafoodapi.domain.service.FotoStorageService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauraneProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;
    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private FotoProdutoAssembler fotoProdutoAssembler;

    @Autowired
    private FotoStorageService fotoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable long produtoId,
            @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        Produto produto = this.cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getName());

        FotoProduto fotoSalva = this.catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
        return this.fotoProdutoAssembler.toDto(fotoSalva);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable long produtoId) {
        FotoProduto foto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return this.fotoProdutoAssembler.toDto(foto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId,
            @PathVariable long produtoId) {
        try {

            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
            InputStream inputStream = this.fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
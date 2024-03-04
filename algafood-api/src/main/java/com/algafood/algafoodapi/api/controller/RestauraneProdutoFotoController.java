package com.algafood.algafoodapi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.algafoodapi.api.asswmbler.FotoProdutoAssembler;
import com.algafood.algafoodapi.api.model.dtoInput.FotoProdutoInput;
import com.algafood.algafoodapi.api.model.dtooutput.FotoProdutoDTO;
import com.algafood.algafoodapi.domain.model.FotoProduto;
import com.algafood.algafoodapi.domain.model.Produto;
import com.algafood.algafoodapi.domain.service.CadastroProdutoService;
import com.algafood.algafoodapi.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauraneProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;
    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private FotoProdutoAssembler fotoProdutoAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable long produtoId,
            @Valid FotoProdutoInput fotoProdutoInput) {
        Produto produto = this.cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getName());

        FotoProduto fotoSalva = this.catalogoFotoProdutoService.salvar(foto);
        return this.fotoProdutoAssembler.toDto(fotoSalva);
    }
}

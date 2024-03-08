package com.algafood.algafoodapi.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.exception.FotoProdutoNaoEncontradaException;

import com.algafood.algafoodapi.domain.model.FotoProduto;

import com.algafood.algafoodapi.domain.repository.ProdutoRepository;
import com.algafood.algafoodapi.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private FotoStorageService fotoStorageService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Optional<FotoProduto> fotoExistente = this.produtoRepository.findFotoById(foto.getRestauranteId(),
                foto.getProduto().getId());

        String nomeArquivoExistente = null;
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        foto.setNomeArquivo(nomeNovoArquivo);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivo).build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, long produtoId) {

        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));

    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);

        produtoRepository.delete(foto);
        produtoRepository.flush();

        fotoStorageService.remover(foto.getNomeArquivo());
    }
}

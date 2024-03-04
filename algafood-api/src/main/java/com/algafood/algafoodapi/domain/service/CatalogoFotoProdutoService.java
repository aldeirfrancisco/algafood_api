package com.algafood.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.algafoodapi.domain.model.FotoProduto;
import com.algafood.algafoodapi.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        Optional<FotoProduto> fotoExistente = this.produtoRepository.findFotoById(foto.getRestauranteId(),
                foto.getProduto().getId());

        if (fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(foto);
    }
}

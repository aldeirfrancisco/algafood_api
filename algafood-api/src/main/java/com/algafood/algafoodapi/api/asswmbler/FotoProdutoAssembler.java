package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtooutput.FotoProdutoDTO;

import com.algafood.algafoodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDTO toDto(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoDTO.class);
    }

}

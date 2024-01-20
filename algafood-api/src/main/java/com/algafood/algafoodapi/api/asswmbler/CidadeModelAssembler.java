package com.algafood.algafoodapi.api.asswmbler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtooutput.CidadeDTO;
import com.algafood.algafoodapi.domain.model.Cidade;

@Component
public class CidadeModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toDto(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toDto(cidade))
                .collect(Collectors.toList());
    }
}

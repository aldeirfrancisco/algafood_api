package com.algafood.algafoodapi.api.asswmbler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtoInput.GrupoInput;

import com.algafood.algafoodapi.domain.model.Grupo;

@Component
public class GrupoInputDissassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo Grupo) {
        modelMapper.map(grupoInput, Grupo);
    }
}

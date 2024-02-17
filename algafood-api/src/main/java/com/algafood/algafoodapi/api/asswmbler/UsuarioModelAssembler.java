package com.algafood.algafoodapi.api.asswmbler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.algafoodapi.api.model.dtooutput.UsuarioDTO;
import com.algafood.algafoodapi.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> toCollectionModel(Collection<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toDto(usuario))
                .collect(Collectors.toList());
    }
}

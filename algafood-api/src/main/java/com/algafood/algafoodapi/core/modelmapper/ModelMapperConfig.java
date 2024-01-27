package com.algafood.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.algafoodapi.api.model.dtooutput.EnderecoDTO;
import com.algafood.algafoodapi.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        // customizando o mapeamento de propriedade com ModelMapper
        // var modelMapper = new ModelMapper();
        // modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
        // .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setprecoFrete);
        // return modelMapper;
        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoDTO.class);
        enderecoToEnderecoModelTypeMap.<String>addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value));
        return modelMapper;
    }
}

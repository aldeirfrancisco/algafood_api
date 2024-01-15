package com.algafood.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.algafoodapi.api.model.dtooutput.RestauranteDTO;
import com.algafood.algafoodapi.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // customizando o mapeamento de propriedade com ModelMapper
        // var modelMapper = new ModelMapper();
        // modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
        // .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setprecoFrete);
        // return modelMapper;
        return new ModelMapper();
    }
}

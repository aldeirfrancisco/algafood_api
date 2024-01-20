package com.algafood.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
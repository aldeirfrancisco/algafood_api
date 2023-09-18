package com.algafood.algafoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import com.algafood.algafoodapi.domain.model.*;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.AlgafoodApiApplication;

public class ConsultaCozinhaMan {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
        List<Cozinha> todaCozinha = cozinhas.todas();
        for (Cozinha cozinha : todaCozinha) {
            System.out.println(cozinha.getNome());
        }
    }
}

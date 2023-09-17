package com.algafood.algafoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import com.algafood.algafoodapi.domain.model.*;
import com.algafood.algafoodapi.AlgafoodApiApplication;

public class ConsultaConzinhaMan {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        List<Cozinha> cozinha = cadastroCozinha.listar();
        for (Cozinha cozinha2 : cozinha) {
            System.out.println(cozinha2.getNome());
        }
    }
}

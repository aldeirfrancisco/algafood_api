package com.algafood.algafoodapi.api.model.dtooutput;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private long id;
    private String logradouro;
    private BigDecimal numero;
    private String complemento;
    private String bairro;
    private CidadeResumoDTO cidade;

}

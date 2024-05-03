package com.algafood.algafoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso_nao_encontrado", "recurso_nao_encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}

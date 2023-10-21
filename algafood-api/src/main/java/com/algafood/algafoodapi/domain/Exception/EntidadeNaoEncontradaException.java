package com.algafood.algafoodapi.domain.Exception;

//@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}

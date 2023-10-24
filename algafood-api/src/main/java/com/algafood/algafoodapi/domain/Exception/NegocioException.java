package com.algafood.algafoodapi.domain.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    // Throwable classe pai de todas as exception.
    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}

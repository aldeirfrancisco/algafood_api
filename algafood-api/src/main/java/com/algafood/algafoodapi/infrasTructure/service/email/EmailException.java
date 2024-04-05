package com.algafood.algafoodapi.infrasTructure.service.email;

public class EmailException extends RuntimeException {

    public EmailException(String msn, Throwable couse) {
        super(msn, couse);
    }

    public EmailException(String msn) {
        super(msn);
    }
}

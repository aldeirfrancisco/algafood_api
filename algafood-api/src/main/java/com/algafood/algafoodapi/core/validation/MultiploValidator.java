package com.algafood.algafoodapi.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.var;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
    private int numeroMultiplo;

    @Override
    public void initialize(Multiplo constrainAnotation) {
        this.numeroMultiplo = constrainAnotation.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean valido = true;
        if (value != null) {
            var valorDecimal = BigDecimal.valueOf(value.doubleValue());
            var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
            var resto = valorDecimal.remainder(multiploDecimal);
            valido = BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return valido;
    }

}
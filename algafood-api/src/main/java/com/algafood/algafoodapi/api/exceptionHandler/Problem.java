package com.algafood.algafoodapi.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL) // só vai incluir na representação json se não estiver null.
@Getter
@Builder
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;

}

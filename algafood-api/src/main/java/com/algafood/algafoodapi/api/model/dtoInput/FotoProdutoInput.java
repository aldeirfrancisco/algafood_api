package com.algafood.algafoodapi.api.model.dtoInput;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.algafood.algafoodapi.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max = "62KB")
    private MultipartFile arquivo;
    @NotBlank
    private String descricao;
}

package com.algafood.algafoodapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.service.CadastroCozinhaService;
import static org.assertj.core.api.Assertions.assertThat;
import javax.validation.ConstraintViolationException;

//@RunWith(SpringRunner.class) levanta o contexto do spring na hora de rodar o teste
//@SpringBootTest fornece as funcionalidade do spring boot no teste.
@SpringBootTest
public class CadastroConzinhaIntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_quandoCadastroCozinhaComDadosCorretos() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_quandoCadastroCozinhaSemNome() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        // ação
        ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            cadastroCozinha.salvar(novaCozinha);
        });

        // validação
        assertThat(erroEsperado).isNotNull();
    }
}

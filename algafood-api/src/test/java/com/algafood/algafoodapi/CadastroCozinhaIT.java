package com.algafood.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.aspectj.lang.annotation.Before;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT =>  vai levantar um servidor web para os teste da API.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    // teste de callBaack será executado antes dos testes ser executados.
    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {

        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); se a
        // quando a validação falhar, vai mostra no log da requisição enviada e a
        // responta.
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter4Cozinha_QuandoConsultarCozinhas() {

        given()
                .basePath("/cozinhas")
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(4))
                .body("nome", hasItems("Indiana", "Tailandesa"));
        // Matchers biblioteca de escrever expressões com regra de correspondencia entre
        // objetos.
    }

}
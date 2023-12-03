package com.algafood.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT =>  vai levantar um servidor web para os teste da API.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); se a
        // quando a validação falhar, vai mostra no log da requisição enviada e a
        // responta.
        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter4Cozinha_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cozinhas")
                .port(port)
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
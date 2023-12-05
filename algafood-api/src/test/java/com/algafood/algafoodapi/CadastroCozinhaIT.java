package com.algafood.algafoodapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algafood.algafoodapi.domain.model.Cozinha;
import com.algafood.algafoodapi.domain.repository.CozinhaRepository;
import com.algafood.algafoodapi.util.DatabaseCleaner;

//webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT =>  vai levantar um servidor web para os teste da API.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner; // responsalvel por limpa a tabela do banco

    @Autowired
    private CozinhaRepository cozinhaRepository;

    // @Autowired
    // private Flyway flyway;

    // teste de callBaack será executado antes de cada métodos de testes ser
    // executados.
    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";
        databaseCleaner.clearTables();

        prepararDados();
        // flyway.migrate(); // garatindo a integridade do banco, quando um método de
        // teste roda o flyway vai
        // roda e voltando os dados original no banco.
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
                .body("", hasSize(2))
                .body("nome", hasItems("Americana", "Tailandesa"));
        // Matchers biblioteca de escrever expressões com regra de correspondencia entre
        // objetos.
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinhas() {
        given()
                .body("{\"nome\": \"Chinesa\"}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);
    }
}
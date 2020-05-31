package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import br.com.marketcode.model.Cliente;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class ClienteTest {

    @Test
    public void testHelloEndpoint() {
        Cliente cliente = new Cliente();
        cliente.nome = "Gustavo Faria";
        cliente.cpf = "12345678902";
        cliente.cvcCartao = "210";
        cliente.eMail = "gustavo@gmail.com";
        cliente.nomeCartao = "Gutsavo faria";
        cliente.numCartao = "2537483927";
        cliente.telefone = "33881303";
        cliente.validadeCartao = "05/27";
        cliente.senha = "123456";
        given()
          .contentType(ContentType.JSON)
          .when()
          .body(cliente)
          .post("/api/cliente/v1/")
          .then()
             .statusCode(201);
    }

}
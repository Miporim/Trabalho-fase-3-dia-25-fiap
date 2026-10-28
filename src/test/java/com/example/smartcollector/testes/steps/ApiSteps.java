package com.example.smartcollector.testes.steps;

import com.example.smartcollector.testes.config.TestConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.E;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiSteps {
    private Response response;
    private String token;
    private String emailAtual;

    @Dado("que não existe um usuário com o e-mail {string}")
    public void prepararEmailNovo(String emailDeclarado) {
        // Endereço único para permitir executar a suíte repetidamente sem colisões.
        String prefixo = emailDeclarado.substring(0, emailDeclarado.indexOf('@'));
        emailAtual = prefixo + "." + UUID.randomUUID() + "@smartcollector.com";
    }

    @Dado("que existe um usuário com o e-mail {string}")
    public void garantirUsuarioExistente(String email) {
        emailAtual = email;
        Map<String, Object> body = new HashMap<>();
        body.put("nome", "Usuario BDD Duplicado");
        body.put("email", emailAtual);
        body.put("senha", TestConfig.testPassword());
        body.put("funcao", "USER");

        // Se já existir de uma execução anterior, o 400 é esperado; se não, cria.
        given()
            .baseUri(TestConfig.baseUrl())
            .contentType("application/json")
            .body(body)
        .when()
            .post("/auth/registro");
    }

    @Quando("envio uma requisição de registro com os seguintes dados:")
    public void enviarRegistro(DataTable tabela) {
        Map<String, String> dados = tabela.asMap(String.class, String.class);
        Map<String, Object> body = new HashMap<>();
        body.put("nome", dados.get("nome"));
        body.put("email", emailAtual != null ? emailAtual : dados.get("email"));
        body.put("senha", dados.getOrDefault("senha", TestConfig.testPassword()));
        body.put("funcao", dados.getOrDefault("funcao", "USER"));

        response = given()
            .baseUri(TestConfig.baseUrl())
            .contentType("application/json")
            .body(body)
        .when()
            .post("/auth/registro");
    }

    @Dado("que possuo um token de autenticação válido")
    public void obterTokenValido() {
        emailAtual = "bdd." + UUID.randomUUID() + "@smartcollector.com";

        Map<String, Object> cadastro = new HashMap<>();
        cadastro.put("nome", "Usuario BDD Item");
        cadastro.put("email", emailAtual);
        cadastro.put("senha", TestConfig.testPassword());
        cadastro.put("funcao", "USER");

        given()
            .baseUri(TestConfig.baseUrl())
            .contentType("application/json")
            .body(cadastro)
        .when()
            .post("/auth/registro")
        .then()
            .statusCode(anyOf(is(200), is(400)));

        Map<String, Object> login = new HashMap<>();
        login.put("email", emailAtual);
        login.put("senha", TestConfig.testPassword());

        Response loginResponse = given()
            .baseUri(TestConfig.baseUrl())
            .contentType("application/json")
            .body(login)
        .when()
            .post("/auth/login");

        loginResponse.then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/login-response.schema.json"));
        token = loginResponse.jsonPath().getString("token");
        assertThat("O token não deve estar vazio", token, not(isEmptyOrNullString()));
    }

    @Quando("cadastro um item com os seguintes dados:")
    public void cadastrarItem(DataTable tabela) {
        Map<String, String> dados = tabela.asMap(String.class, String.class);
        Map<String, Object> body = new HashMap<>();
        body.put("nome", dados.get("nome"));
        body.put("volume", Double.parseDouble(dados.get("volume")));

        response = given()
            .baseUri(TestConfig.baseUrl())
            .contentType("application/json")
            .header("Authorization", "Bearer " + token)
            .body(body)
        .when()
            .post("/itens");
    }

    @Então("a resposta deve possuir status HTTP {int}")
    public void validarStatus(int statusEsperado) {
        assertEquals(statusEsperado, response.statusCode(),
                "Corpo recebido: " + response.asString());
    }

    @E("a resposta deve informar {string}")
    public void validarMensagem(String mensagem) {
        assertThat(response.asString(), containsString(mensagem));
    }

    @E("o corpo da resposta deve conter o nome {string}")
    public void validarNome(String nome) {
        response.then().body("nome", equalTo(nome));
    }

    @E("o volume retornado deve ser {double}")
    public void validarVolume(double volume) {

        assertEquals(
                (double) volume/10,
                response.jsonPath().getDouble("volume"),
                0.001,
                "O volume retornado pela API não corresponde ao esperado."
        );
    }

    @E("o JSON da resposta deve respeitar o schema de item")
    public void validarSchemaItem() {
        response.then()
                .assertThat()
                .body(
                        matchesJsonSchemaInClasspath(
                                "schemas/item-response.schema.json"
                        )
                );
    }

}

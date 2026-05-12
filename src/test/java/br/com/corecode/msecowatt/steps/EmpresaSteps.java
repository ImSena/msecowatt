package br.com.corecode.msecowatt.steps;

import io.cucumber.java.pt.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class EmpresaSteps extends CucumberSpringConfiguration {

    @LocalServerPort
    private int port;

    @Autowired
    private JwtDecoder jwtDecoder;

    private RequestSpecification request;
    private Response response;

    @Dado("que a API de empresas está pronta")
    public void que_a_api_de_empresas_esta_pronta() {
        Jwt jwt = Jwt.withTokenValue("token-mock-para-teste")
                .header("alg", "none")
                .claim("sub", "admin-teste")
                .build();

        Mockito.when(jwtDecoder.decode(Mockito.anyString())).thenReturn(jwt);

        request = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/api/v1")
                .header("Authorization", "Bearer token-mock-para-teste")
                .contentType(ContentType.JSON);
    }

    @Dado("eu monto um payload para a empresa {string} com CNPJ {string}")
    public void eu_monto_um_payload_para_a_empresa_com_cnpj(String nome, String cnpj) {
        String payload = """
            {
                "name": "%s",
                "cnpj": "%s",
                "phoneNumber": "11999999999",
                "street": "Avenida Paulista",
                "city": "Sao Paulo",
                "state": "SP"
            }
            """.formatted(nome, cnpj);

        request.body(payload);
    }

    @Dado("eu monto um payload de empresa faltando o CNPJ")
    public void eu_monto_um_payload_de_empresa_faltando_o_cnpj() {
        String payload = """
            {
                "name": "Empresa Fantasma",
                "cnpj": "",
                "phoneNumber": "11999999999",
                "street": "Avenida Paulista",
                "city": "Sao Paulo",
                "state": "SP"
            }
            """;
        request.body(payload);
    }

    @Quando("a requisicao POST for enviada para {string}")
    public void a_requisicao_post_for_enviada_para(String endpoint) {
        response = request.log().all().post(endpoint);
        response.then().log().all();
    }

    @Quando("a requisicao GET for enviada para {string}")
    public void a_requisicao_get_for_enviada_para(String endpoint) {
        response = request.log().all().get(endpoint);
        response.then().log().all();
    }

    @Entao("o status code da empresa deve ser {int}")
    public void o_status_code_da_empresa_deve_ser(Integer statusCodeEsperado) {
        response.then().statusCode(statusCodeEsperado);
    }

    @Entao("a resposta da empresa deve conter um {string} gerado")
    public void a_resposta_da_empresa_deve_conter_um_gerado(String campo) {
        response.then().body(campo, notNullValue());
    }

    @Entao("a resposta da empresa deve conter o nome {string}")
    public void a_resposta_da_empresa_deve_conter_o_nome(String nomeEsperado) {
        response.then().body("name", equalTo(nomeEsperado));
    }

    @Entao("a lista de empresas deve respeitar o contrato JSON Schema")
    public void a_lista_de_empresas_deve_respeitar_o_contrato() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/company-array-schema.json"));
    }
}
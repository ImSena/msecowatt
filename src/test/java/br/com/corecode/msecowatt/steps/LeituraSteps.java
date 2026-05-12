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

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LeituraSteps extends CucumberSpringConfiguration {

    @LocalServerPort
    private int port;

    @Autowired
    private JwtDecoder jwtDecoder;

    private RequestSpecification request;
    private Response response;

    private static String idEmpresaLocal;
    private static String idLeituraLocal;

    @Dado("que a API de leituras está pronta")
    public void que_a_api_de_leituras_esta_pronta() {
        Jwt jwt = Jwt.withTokenValue("token-mock").header("alg", "none").claim("sub", "user").build();
        Mockito.when(jwtDecoder.decode(Mockito.anyString())).thenReturn(jwt);

        if (idEmpresaLocal == null) {
            String cnpjAleatorio = UUID.randomUUID().toString().substring(0, 14);

            Response respEmpresa = given()
                    .baseUri("http://localhost").port(port).basePath("/api/v1")
                    .header("Authorization", "Bearer token-mock")
                    .contentType(ContentType.JSON)
                    .body("{\"name\":\"Empresa Teste\",\"cnpj\":\""+cnpjAleatorio+"\",\"phoneNumber\":\"119999\",\"street\":\"Rua T\",\"city\":\"SP\",\"state\":\"SP\"}")
                    .post("/companies");

            if (respEmpresa.statusCode() == 201) {
                idEmpresaLocal = respEmpresa.jsonPath().getString("id");
            } else {
                idEmpresaLocal = given()
                        .baseUri("http://localhost").port(port).basePath("/api/v1")
                        .header("Authorization", "Bearer token-mock")
                        .get("/companies").jsonPath().getString("[0].id");
            }
        }

        request = given()
                .baseUri("http://localhost")
                .port(port)
                .basePath("/api/v1")
                .header("Authorization", "Bearer token-mock")
                .contentType(ContentType.JSON);
    }

    @Dado("eu monto um payload para a empresa {string} com consumo de {double} e data {string}")
    public void eu_monto_um_payload_para_a_empresa_com_consumo_e_data(String fakeId, Double consumo, String data) {
        String payload = """
            {
                "companyId": "%s",
                "readingDate": "%s",
                "consumptionKwh": %s
            }
            """.formatted(idEmpresaLocal, data, consumo);
        request.body(payload);
    }

    @Dado("eu monto um payload vazio sem a identificacao da empresa")
    public void eu_monto_um_payload_vazio() {
        request.body("{\"companyId\": \"\", \"readingDate\": \"2026-05-11\", \"consumptionKwh\": null}");
    }

    @Quando("eu envio um POST para {string}")
    public void eu_envio_um_post_para(String endpoint) {
        response = request.log().all().post(endpoint);
        response.then().log().all();
        if (response.statusCode() == 201) {
            idLeituraLocal = response.jsonPath().getString("id");
        }
    }

    @Quando("eu envio um GET para {string}")
    public void eu_envio_um_get_para(String endpoint) {
        String path = endpoint;
        if (endpoint.equals("/energy-readings") && idLeituraLocal != null) {
            path = "/energy-readings/" + idLeituraLocal;
        }
        response = request.log().all().get(path);
        response.then().log().all();
    }

    @Entao("o status code deve ser {int}")
    public void o_status_code_deve_ser(Integer code) {
        response.then().statusCode(code);
    }

    @Entao("a resposta deve conter um {string} gerado")
    public void a_resposta_deve_conter_um_gerado(String campo) {
        response.then().body(campo, notNullValue());
    }
}
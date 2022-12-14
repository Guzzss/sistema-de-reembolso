package aceitacao.service;

import aceitacao.dto.financeiro.FinanceiroPagarDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FinanceiroService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/financeiro";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjcwLCJyb2xlcyI6WyJST0xFX0ZJTkFOQ0VJUk8iXSwiaWF0IjoxNjYxMTc2MjY5LCJleHAiOjE2NjEyNjI2Njl9.LTR6pRwC_WSTQuBGVBIMNzgGgeU54b-lf8Fe46tN6Kg";

    public FinanceiroPagarDTO pagarFinanceiroComSucesso(Integer idReembolso, String statusPagamento) {
        String url = baseUrl + "/pagar/" + idReembolso + "?pagar="+ statusPagamento;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(FinanceiroPagarDTO.class);
    }

    public Response pagarFinanceiroComIdInexistente(Integer idReembolso, String statusPagamento) {
        String url = baseUrl + "/pagar/" + idReembolso + "?pagar="+ statusPagamento;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(404) // Extração do resultado
                .extract().response();
    }
}

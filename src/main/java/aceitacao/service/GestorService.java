package aceitacao.service;

import aceitacao.dto.ReembolsoDTO;
import aceitacao.dto.gestor.GestorAprovarReembolsoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GestorService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/gestor";

    //todo add token de gestor
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjYwODU4OTMyLCJleHAiOjE2NjA5NDUzMzJ9.7KbK2KKKj51rIijkfUuSPoZsqkJ7Rx1r9J6WDrXc_gA";

    public GestorAprovarReembolsoDTO avaliarReembolsoComSucesso(Integer idReembolso, String status) {
        String url = baseUrl + "/aprovar/" + idReembolso +"?aprovado=" + status;
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
                .extract().as(GestorAprovarReembolsoDTO.class);
    }

    public Response avaliarReembolsoComIdInexistente(Integer idReembolso, String status) {
        String url = baseUrl + "/aprovar/" + idReembolso +"?aprovado=" + status;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().response();
    }

}

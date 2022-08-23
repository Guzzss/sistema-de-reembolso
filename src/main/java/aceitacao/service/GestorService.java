package aceitacao.service;

import aceitacao.dto.gestor.GestorAprovarReembolsoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GestorService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/gestor";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjY5LCJyb2xlcyI6WyJST0xFX0dFU1RPUiJdLCJpYXQiOjE2NjEyMTk4NDcsImV4cCI6MTY2MTQ3OTA0N30.EE6ZvVQ6GLUICBSvOECl_AmUUp3WGQnsgCGBQXuFTg8";

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
                .statusCode(404) // Extração do resultado
                .extract().response();
    }

}

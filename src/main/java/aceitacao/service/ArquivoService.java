package aceitacao.service;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.io.File;

import static io.restassured.RestAssured.given;

public class ArquivoService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/upload";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjYwODU4OTMyLCJleHAiOjE2NjA5NDUzMzJ9.7KbK2KKKj51rIijkfUuSPoZsqkJ7Rx1r9J6WDrXc_gA";

    public Response updateFile(Integer idUsuario){
        String url = baseUrl + "/foto";
        Response res = given()
                .header("Authorization", token)
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart(new File("src/test/resources/Regina.jpg"))
                .log().all()
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return res;
    }
    public Response updateAnexo(Integer idReembolso){
        String url = baseUrl + "/anexo?idReembolso=" + idReembolso;
        Response res = given()
                .header("Authorization", token)
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart(new File("src/test/resources/Regina.jpg"))
                .log().all()
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return res;
    }
}

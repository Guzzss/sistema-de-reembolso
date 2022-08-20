package aceitacao.service;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.io.File;

import static io.restassured.RestAssured.given;

public class ArquivoService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/upload";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjI0LCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTAyNDY3NywiZXhwIjoxNjYxMTExMDc3fQ.pyJw8e7QdvL-U9LjQqkc6KJXooZjRbI588D2OIWh95k";

    public Response updateFile(Integer idUsuario){
        String url = baseUrl + "/foto";
        Response res = given()
                .header("Authorization", token)
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart("file", new File("src/test/resources/Regina.png"), "image/png")
                .log().all()
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return res;
    }
    public Response updateAnexo(Integer idReembolso, Integer idUsuario){
        String url = baseUrl + "/anexo/reembolso/usuario?idReembolso=" + idReembolso + "&idUsuario=" + idUsuario;
        Response res = given()
                .header("Authorization", token)
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart("file", new File("src/test/resources/Regina.png"), "image/png")
                .when()
                .post(url)
                .then()
                .log().all()
                .extract().response();
        return res;
    }
}

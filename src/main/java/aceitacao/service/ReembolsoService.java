package aceitacao.service;

import aceitacao.dto.PageReembolsoDTO;
import aceitacao.dto.ReembolsoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReembolsoService {

    //esse token
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQ09MQUJPUkFET1IiXSwiaWF0IjoxNjYwNjc4MDk1LCJleHAiOjE2NjA3NjQ0OTV9.0BXD_vpZNWUF-8W-Md1AR2jR_mTHMI3HGSkHkhPrm64";
    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/reembolso";


    public ReembolsoDTO criarReembolsoComSucesso(String jsonBody) {
        String url = baseUrl + "/create";
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(ReembolsoDTO.class);
    }

    public Response criarReembolsoSemPassarValor(String jsonBody) {
        String url = baseUrl + "/create";
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response criarReembolsoSemPassarTitulo(String jsonBody) {
        String url = baseUrl + "/create";
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public ReembolsoDTO editarReembolsoComSucesso(Integer idReembolso, String jsonBody) {
        String url = baseUrl + "/logged/update/" + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(ReembolsoDTO.class);
    }

    public Response editarReembolsoSemPassarValor(Integer idReembolso, String jsonBody) {
        String url = baseUrl + "/logged/update/" + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response editarReembolsoSemPassarTitulo(Integer idReembolso, String jsonBody) {
        String url = baseUrl + "/logged/update/" + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .put(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response deletarReembolsoComSucesso(Integer idReembolso) {
        String url = baseUrl + "/logged/delete/" + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .delete(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response deletarReembolsoComIdInexistente(Integer idReembolso) {
        String url = baseUrl + "/logged/delete/" + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .delete(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().response();
    }

    public PageReembolsoDTO listarReembolsoDoUsuarioLogadoComSucesso(String statusReembolso, Integer paginas, Integer registros) {
        String url = baseUrl + "/logged/list/status?statusReembolso=" + statusReembolso + "&pagina=" + paginas + "&quantidadeDeRegistros=" + registros;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(PageReembolsoDTO.class);
    }

    public PageReembolsoDTO listarReembolsoDosUsuariosComSucesso(String statusReembolso, Integer paginas, Integer registros) {
        String url = baseUrl + "/list/status?statusReembolso=" + statusReembolso + "&pagina=" + paginas + "&quantidadeDeRegistros=" + registros;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(PageReembolsoDTO.class);
    }
}


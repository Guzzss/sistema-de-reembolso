package aceitacao.service;

import aceitacao.dto.PageReembolsoDTO;
import aceitacao.dto.ReembolsoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReembolsoService {

    //esse token
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjYwODU4OTMyLCJleHAiOjE2NjA5NDUzMzJ9.7KbK2KKKj51rIijkfUuSPoZsqkJ7Rx1r9J6WDrXc_gA";
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
                .statusCode(400) // Extração do resultado
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
                .statusCode(400) // Extração do resultado
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
                .statusCode(202) // Extração do resultado
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
                .statusCode(400) // Extração do resultado
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
                .statusCode(400) // Extração do resultado
                .extract().response();
    }

    public Response deletarReembolsoComSucesso(Integer idReembolso, Integer pagina, Integer registros) {
        String url = baseUrl + "/logged/delete/" + idReembolso + "?pagina=" + pagina + "&quantidadeDeRegistros=" + registros;
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

    public PageReembolsoDTO listarReembolsoPorNomeEStatusComSucesso(String nome, String statusReembolso, Integer paginas, Integer registros) {
        String url = baseUrl + "/list/nome/status?nome=" + nome + "&statusReembolso=" + statusReembolso + "&pagina=" + paginas + "&quantidadeDeRegistros=" + registros;
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

    public ReembolsoDTO getReembolsoByIdComSucesso(Integer idReembolso) {
        String url = baseUrl + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(ReembolsoDTO.class);
    }

    public Response getReembolsoByIdComIdInexistente(Integer idReembolso) {
        String url = baseUrl + idReembolso;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(404) // Extração do resultado
                .extract().response();
    }
}


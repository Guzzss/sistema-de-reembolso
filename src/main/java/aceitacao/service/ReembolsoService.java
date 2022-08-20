package aceitacao.service;

import aceitacao.dto.PageReembolsoDTO;
import aceitacao.dto.ReembolsoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReembolsoService {

    //esse token
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQ09MQUJPUkFET1IiXSwiaWF0IjoxNjYxMDI1MTE5LCJleHAiOjE2NjExMTE1MTl9.9rFcjcaR_zPYwDZjhv2Fty6ugz7dRa-H6gzWAWqqB0g";
    String tokenAdm = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjI0LCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTAyNDY3NywiZXhwIjoxNjYxMTExMDc3fQ.pyJw8e7QdvL-U9LjQqkc6KJXooZjRbI588D2OIWh95k";
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

    public ReembolsoDTO editarReembolsoComSucesso(Integer idReembolso, Integer idUsuario, String jsonBody) {
        String url = baseUrl + "/update/" + idReembolso + "/usuario/" + idUsuario;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAdm)
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

    public Response editarReembolsoSemPassarValor(Integer idReembolso, Integer idUsuario, String jsonBody) {
        String url = baseUrl + "/update/" + idReembolso + "/usuario/" + idUsuario;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAdm)
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

    public Response editarReembolsoSemPassarTitulo(Integer idReembolso, Integer idUsuario, String jsonBody) {
        String url = baseUrl + "/update/" + idReembolso + "/usuario/" + idUsuario;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAdm)
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

    public Response deletarReembolsoComSucesso(Integer idReembolso, Integer idUsuario) {
        String url = baseUrl + "/delete/" + idReembolso + "/usuario/" + idUsuario;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAdm)
                .log().all()
                .when() // Quando
                .delete(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response deletarReembolsoComIdInexistente(Integer idReembolso, Integer idUsuario) {
        String url = baseUrl + "/delete/" + idReembolso + "usuario/" + idUsuario;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAdm)
                .log().all()
                .when() // Quando
                .delete(url)
                .then() // Então
                .log()
                .all()
                .statusCode(404) // Extração do resultado
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
                .header("Authorization", tokenAdm)
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
                .header("Authorization", tokenAdm)
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(PageReembolsoDTO.class);
    }

    public ReembolsoDTO getReembolsoByIdComSucesso(Integer idReembolso) {
        String url = baseUrl + "/" + idReembolso;
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


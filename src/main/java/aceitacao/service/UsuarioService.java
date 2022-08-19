package aceitacao.service;

import aceitacao.dto.usuarioDTO.NovoUsuarioDTO;
import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.usuarioDTO.PageUsuarioDTO;
import aceitacao.dto.usuarioDTO.UsuarioDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/usuario";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjYsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjYwODU4OTMyLCJleHAiOjE2NjA5NDUzMzJ9.7KbK2KKKj51rIijkfUuSPoZsqkJ7Rx1r9J6WDrXc_gA";

    public NovoUsuarioDTO cadastroUsuario(String jsonBody) {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(NovoUsuarioDTO.class);
    }

    public Response cadastroUsuarioComEmailExistente(String jsonBody) {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
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

    public Response cadastroUsuarioComEmailInvalido(String jsonBody) {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
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

    public ResponseErroDTO cadastroUsuarioSemSenhaEEmail(String jsonBody) {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().as(ResponseErroDTO.class);
    }

    public ResponseErroDTO cadastroUsuarioSemNome(String jsonBody) {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().as(ResponseErroDTO.class);
    }

    public Response cadastroUsuarioSemCampos() {
        String url = baseUrl + "/cadastro";
        return given() // Dado
                .contentType(ContentType.JSON)
                .log().all()
                .body("{}")
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().response();
    }

    public Response loginComSucesso(String jsonBody) {
        String url = baseUrl + "/login";
        return given() // Dado
                .contentType(ContentType.JSON)
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

    public Response loginComEmailInvalido(String jsonBody) {
        String url = baseUrl + "/login";
        return given() // Dado
                .contentType(ContentType.JSON)
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

    public Response loginComSenhaInvalida(String jsonBody) {
        String url = baseUrl + "/login";
        return given() // Dado
                .contentType(ContentType.JSON)
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


    public UsuarioDTO getUsuarioLogado() {
        String url = baseUrl + "/logged";
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(UsuarioDTO.class);
    }

    public Response deleteUsuario(Integer idUsuario) {
        String url = baseUrl + "/delete" + "/" + idUsuario;
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

    public PageUsuarioDTO listarPessoasComSucesso(Integer pagina, Integer registros) {
        String url = baseUrl + "/listar?pagina=" + pagina + "&quantidadeDeRegistros=" + registros;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .get(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().as(PageUsuarioDTO.class);
    }
}
package aceitacao.service;

import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.UsuarioDTO;
import aceitacao.dto.UsuarioLogadoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/usuario";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjcsInJvbGVzIjpbXSwiaWF0IjoxNjYwNDI1MTY1LCJleHAiOjE2NjA1MTE1NjV9.aGWaAT-u12au-7SzObS65eXHdhQA_NRuU8tSf8uZIoM";
    public UsuarioDTO cadastroUsuario(String jsonBody) {
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
                .extract().as(UsuarioDTO.class);
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

    public UsuarioDTO editarUsuarioComSucesso(String jsonBody) {
        String url = baseUrl + "/update";
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
                .extract().as(UsuarioDTO.class);
    }

    public Response editarUsuarioComEmailExistente(String jsonBody) {
        String url = baseUrl + "/update";
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

    public UsuarioLogadoDTO getUsuarioLogado() {
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
                .extract().as(UsuarioLogadoDTO.class);
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

    public Response ativarUsuario(Integer idUsuario, String ATIVADO) {
        String url = baseUrl + "/ativar-desativar-usuario" + "/" + idUsuario + "?ativarDesativarUsuario=" + ATIVADO;
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
                .extract().response();
    }

    public Response ativarUsuarioComIdInexistente(Integer idUsuario, String ATIVADO) {
        String url = baseUrl + "/ativar-desativar-usuario" + "/" + idUsuario + "?ativarDesativarUsuario=" + ATIVADO;
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

    public Response desativarUsuario(Integer idUsuario, String DESATIVADO) {
        String url = baseUrl + "/ativar-desativar-usuario" + "/" + idUsuario + "?ativarDesativarUsuario=" + DESATIVADO;
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
                .extract().response();
    }

    public Response desativarUsuarioComIdInexistente(Integer idUsuario, String DESATIVADO) {
        String url = baseUrl + "/ativar-desativar-usuario" + "/" + idUsuario + "?ativarDesativarUsuario=" + DESATIVADO;
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

    public Response definirRoleComSucesso(Integer idUsuario, String role) {
        String url = baseUrl + "/role?idUsuario=" + idUsuario + "&role=" + role;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(200) // Extração do resultado
                .extract().response();
    }

    public Response definirRoleComIdInexistente(Integer idUsuario, String role) {
        String url = baseUrl + "/role?idUsuario=" + idUsuario + "&role=" + role;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(400) // Extração do resultado
                .extract().response();
    }

    public Response listarPessoasComSucesso() {
        String url = baseUrl + "/listar";
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
                .extract().response();
    }
}
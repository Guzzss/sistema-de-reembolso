package aceitacao.service;

import aceitacao.dto.usuario.UsuarioDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AdminService {


    String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzaXN0ZW1hLWRlLXJlZW1ib2xzby1hcGkiLCJqdGkiOjI0LCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY2MTIxOTkxNSwiZXhwIjoxNjYxNDc5MTE1fQ.UwLYppO3ewfXGXhGzXkRrFKme7TOnAzzk5pRQPG0ezM";

    String baseUrl = "https://sistema-de-reembolso-dev.herokuapp.com/admin";

    public UsuarioDTO cadastrarAdminComSucesso(String role, String jsonBody) {
            String url = baseUrl + "/cadastro?role=" + role;
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
                    .extract().as(UsuarioDTO.class);
        }

    public Response cadastrarAdminSemPassarNome(String role, String jsonBody) {
        String url = baseUrl + "/cadastro?role=" + role;
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

    public Response cadastrarAdminSemPassarEmail(String role, String jsonBody) {
        String url = baseUrl + "/cadastro?role=" + role;
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

    public Response cadastrarAdminSemPassarSenha(String role, String jsonBody) {
        String url = baseUrl + "/cadastro?role=" + role;
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

    public Response cadastrarAdminComEmailExistente(String role, String jsonBody) {
        String url = baseUrl + "/cadastro?role=" + role;
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

    public Response cadastrarAdminComTodosCamposEmBranco(String role) {
        String url = baseUrl + "/cadastro?role=" + role;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
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

    public UsuarioDTO definirRoleComSucesso(Integer idUsuario, String role) {
        String url = baseUrl + "/atribuir/role?idUsuario=" + idUsuario + "&role=" + role;
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
                .extract().as(UsuarioDTO.class);
    }

    public Response definirRoleComIdInexistente(Integer idUsuario, String role) {
        String url = baseUrl + "/atribuir/role?idUsuario=" + idUsuario + "&role=" + role;
        return given() // Dado
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .log().all()
                .when() // Quando
                .post(url)
                .then() // Então
                .log()
                .all()
                .statusCode(404) // Extração do resultado
                .extract().response();
    }
}



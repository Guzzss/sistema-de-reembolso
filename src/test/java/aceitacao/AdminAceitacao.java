package aceitacao;

import aceitacao.dto.usuarioDTO.NovoUsuarioDTO;
import aceitacao.dto.usuarioDTO.UsuarioDTO;
import aceitacao.service.AdminService;
import aceitacao.service.UsuarioService;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AdminAceitacao {

    AdminService adminService = new AdminService();

    UsuarioService usuarioService = new UsuarioService();

    UsuarioAceitacao usuarioAceitacao = new UsuarioAceitacao();
    public String lerJson(String caminhojson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }

    Faker faker = new Faker();

    @Test
    public void cadastrarAdminComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/adminJsons/Admin.json");

        JSONObject jsonObject = new JSONObject(json);
        String nome = faker.name().fullName();
        String email = faker.name().firstName().toLowerCase() + "@dbccompany.com.br";
        String senha = faker.internet().password(8, 16, true, true)+"#";

        jsonObject.put("nome", nome);
        jsonObject.put("email", email);
        jsonObject.put("senha", senha);

        UsuarioDTO resultService = adminService.cadastrarAdminComSucesso("ADMINISTRADOR", jsonObject.toString());

        Assert.assertEquals(resultService.getNome(), nome);
        Assert.assertEquals(resultService.getEmail(), email);
        Assert.assertEquals(resultService.getIdUsuario(), resultService.getIdUsuario());
        Assert.assertEquals(resultService.getValorTotal(), Double.valueOf(0.0));
        Assert.assertEquals(resultService.getFotoDTO(), null);
        usuarioService.deleteUsuario(resultService.getIdUsuario());
    }

    @Test
    public void cadastrarAdminSemPassarNome() throws IOException {
        String json = lerJson("src/test/resources/data/adminJsons/AdminSemNome.json");
        Response resultService = adminService.cadastrarAdminSemPassarNome("ADMINISTRADOR", json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void cadastrarAdminSemPassarEmail() throws IOException {
        String json = lerJson("src/test/resources/data/adminJsons/AdminSemEmail.json");
        Response resultService = adminService.cadastrarAdminSemPassarEmail("ADMINISTRADOR", json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void cadastarAdminSemPassarSenha() throws IOException {
        String json = lerJson("src/test/resources/data/adminJsons/AdminSemSenha.json");
        Response resultService = adminService.cadastrarAdminSemPassarSenha("ADMINISTRADOR", json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void cadastrarAdminComEmailExistente() throws IOException {
        String json = lerJson("src/test/resources/data/adminJsons/AdminEmailExistente.json");
        Response resultService = adminService.cadastrarAdminComEmailExistente("ADMINISTRADOR", json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void CadastrarAdminComTodosCamposEmBranco() {
        Response resultService = adminService.cadastrarAdminComTodosCamposEmBranco("ADMINISTRADOR");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

        @Test
    public void definirRoleComSucesso() throws IOException {
        NovoUsuarioDTO usuarioDTO = usuarioAceitacao.addPessoaPeloJson();
        UsuarioDTO resultService = adminService.definirRoleComSucesso(usuarioDTO.getIdUsuario(), "GESTOR");
        Assert.assertEquals(resultService.getIdUsuario(), usuarioDTO.getIdUsuario());
        usuarioService.deleteUsuario(usuarioDTO.getIdUsuario());
    }

    @Test
    public void definirRoleComIdInexistente() {
        Response resultService = adminService.definirRoleComIdInexistente(5467575, "GESTOR");
        Assert.assertEquals(resultService.getStatusCode(), 404);
    }

 }

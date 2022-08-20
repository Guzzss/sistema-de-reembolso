package aceitacao;

import aceitacao.dto.usuarioDTO.NovoUsuarioDTO;
import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.usuarioDTO.PageUsuarioDTO;
import aceitacao.dto.usuarioDTO.UsuarioDTO;
import aceitacao.service.UsuarioService;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAceitacao {

    UsuarioService usuarioService = new UsuarioService();
    Faker faker = new Faker();

    public String lerJson(String caminhojson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }

    @Test
    public void cadastroUsuarioComSucesso() throws IOException {
        NovoUsuarioDTO resultService = addPessoaPeloJson();
        String token = resultService.getToken();
        Assert.assertEquals(resultService.getRole(), "ROLE_COLABORADOR");
        Assert.assertEquals(resultService.getToken(), token);
        usuarioService.deleteUsuario(resultService.getIdUsuario());
    }

    @Test
    public void cadastroUsuarioComEmailExistente() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioJsons/usuarioEmailExistente.json");

        Response resultService = usuarioService.cadastroUsuarioComEmailExistente(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void cadastroUsuarioComEmailInválido() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioJsons/usuarioComEmailInvalido.json");

        Response resultService = usuarioService.cadastroUsuarioComEmailInvalido(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }
    @Test
    public void cadastroUsuarioSemSenhaEEmail() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioJsons/usuarioSemSenhaEEmail.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("senha: Insira uma senha válida"));
        errors.add("email: Insira um email");


        ResponseErroDTO resultService = usuarioService.cadastroUsuarioSemSenhaEEmail(json);
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors().get(0), "senha: Insira uma senha válida");
        Assert.assertEquals(resultService.getErrors().get(1), "email: Insira um email");

    }

    @Test
    public void cadastroUsuarioSemNome() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioJsons/usuarioSemNome.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("nome: Insira o nome do usuário"));

        ResponseErroDTO resultService = usuarioService.cadastroUsuarioSemNome(json);
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors().get(0), "nome: Insira o nome do usuário");
    }

    @Test
    public void cadastroUsuarioSemCampos() {
        Response resultService = usuarioService.cadastroUsuarioSemCampos();
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void loginComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/loginJsons/Login.json");
        Response resultService = usuarioService.loginComSucesso(json);
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void loginComEmailInvalido() throws IOException {
        String json = lerJson("src/test/resources/data/loginJsons/LoginComEmailInvalido.json");
        Response resultService = usuarioService.loginComEmailInvalido(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void loginComSenhaInvalida() throws IOException {
        String json = lerJson("src/test/resources/data/loginJsons/LoginComSenhaInvalida.json");
        Response resultService = usuarioService.loginComSenhaInvalida(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void getUsuarioLogado() {
        UsuarioDTO resultService = usuarioService.getUsuarioLogado();
        Assert.assertEquals(resultService.getNome(), "Gustavo");
        Assert.assertEquals(resultService.getEmail(), "gustavo.teichmann@dbccompany.com.br");
        Assert.assertNotNull(resultService.getIdUsuario());
    }

    @Test
    public void deleteUsuario() throws IOException {
        NovoUsuarioDTO usuarioDTO = addPessoaPeloJson();
        Response resultService = usuarioService.deleteUsuario(usuarioDTO.getIdUsuario());
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void listarPessoasComSucesso() {
        PageUsuarioDTO resultService = usuarioService.listarPessoasComSucesso(0, 1);
        Assert.assertEquals(resultService.getPage(), Integer.valueOf(0));
        Assert.assertNotNull(resultService.getContent());
        Assert.assertEquals(resultService.getTotalElements() != 0, true);
    }

    public NovoUsuarioDTO addPessoaPeloJson() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioJsons/usuario.json");
        JSONObject jsonObject = new JSONObject(json);

        jsonObject.put("nome", faker.name().fullName());
        jsonObject.put("email", faker.name().firstName().toLowerCase() + "@dbccompany.com.br");
        jsonObject.put("senha", faker.internet().password(8, 16, true, true)+"#");

        return usuarioService.cadastroUsuario(jsonObject.toString());
    }
}


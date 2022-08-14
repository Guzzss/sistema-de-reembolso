package aceitacao;

import aceitacao.dto.ResponseErroDTO;
import aceitacao.dto.UsuarioDTO;
import aceitacao.dto.UsuarioLogadoDTO;
import aceitacao.service.UsuarioService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ImagemBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAceitacao {

    UsuarioService usuarioService = new UsuarioService();

    public String lerJson(String caminhojson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }

    @Test
    public void cadastroUsuarioComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/usuario.json");

        UsuarioDTO resultService = usuarioService.cadastroUsuario(json);
        Assert.assertEquals(resultService.getNome(), "Gustavo");
        Assert.assertEquals(resultService.getEmail(), "seuemail100@dbccompany.com.br");
        Assert.assertNotNull(resultService.getIdUsuario());
        usuarioService.deleteUsuario(resultService.getIdUsuario());
    }

    @Test
    public void cadastroUsuarioComEmailExistente() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioEmailExistente.json");

        Response resultService = usuarioService.cadastroUsuarioComEmailExistente(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void cadastroUsuarioSemSenhaEEmail() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioSemSenhaEEmail.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("senha: must not be blank"));
        errors.add("senha: must not be blank");


        ResponseErroDTO resultService = usuarioService.cadastroUsuarioSemSenhaEEmail(json);
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors().get(0), "senha: must not be blank");
        Assert.assertEquals(resultService.getErrors().get(1), "email: must not be null");

    }

    @Test
    public void cadastroUsuarioSemNome() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioSemNome.json");
        Object status = 400;
        List<String> errors = new ArrayList<>(List.of("nome: must not be blank"));

        ResponseErroDTO resultService = usuarioService.cadastroUsuarioSemNome(json);
        Assert.assertEquals(resultService.getStatus(), status);
        Assert.assertEquals(resultService.getErrors().get(0), "nome: must not be blank");

    }

    @Test
    public void cadastroUsuarioSemCampos() {
        Response resultService = usuarioService.cadastroUsuarioSemCampos();
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }


    @Test
    public void loginComEmailInvalido() throws IOException {
        String json = lerJson("src/test/resources/data/LoginComEmailInvalido.json");
        Response resultService = usuarioService.loginComEmailInvalido(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void loginComSenhaInvalida() throws IOException {
        String json = lerJson("src/test/resources/data/LoginComSenhaInvalida.json");
        Response resultService = usuarioService.loginComSenhaInvalida(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void EditarUsuarioComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/usuarioEditado.json");

        UsuarioDTO resultService = usuarioService.editarUsuarioComSucesso(json);
        Assert.assertEquals(resultService.getNome(), "GustavoEditado");
        Assert.assertEquals(resultService.getEmail(), "tester@dbccompany.com.br");
        Assert.assertNotNull(resultService.getIdUsuario());
    }

    @Test
    public void EditarUsuarioComEmailExistente() throws IOException {
        String json = lerJson("src/test/resources/data/UsuarioEditadoComEmailExistente.json");
        Object status = 400;
        Response resultService = usuarioService.editarUsuarioComEmailExistente(json);

        Assert.assertEquals(resultService.getStatusCode(), status);
    }

    @Test
    public void getJogadorLogado() {
        UsuarioLogadoDTO resultService = usuarioService.getUsuarioLogado();
        Assert.assertEquals(resultService.getNome(), "GustavoEditado");
        Assert.assertEquals(resultService.getEmail(), "tester@dbccompany.com.br");
        Assert.assertNotNull(resultService.getIdUsuario());
    }

    @Test
    public void deleteJogador() throws IOException {
        UsuarioDTO usuarioDTO = addPessoaPeloJson();
        Response resultService = usuarioService.deleteUsuario(usuarioDTO.getIdUsuario());
        Assert.assertEquals(resultService.getStatusCode(), 200);
    }

    @Test
    public void ativarUsuario() throws IOException {
        UsuarioDTO usuarioDTO = addPessoaPeloJson();
        Response resultService = usuarioService.ativarUsuario(usuarioDTO.getIdUsuario(), "ATIVAR");
        Assert.assertEquals(resultService.getStatusCode(), 200);
        usuarioService.deleteUsuario(usuarioDTO.getIdUsuario());
    }

    @Test
    public void ativarUsuarioComIdInexistente() throws IOException {
        Response resultService = usuarioService.ativarUsuarioComIdInexistente(3434343, "ATIVAR");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void desativarUsuario() throws IOException {
        UsuarioDTO usuarioDTO = addPessoaPeloJson();
        Response resultService = usuarioService.desativarUsuario(usuarioDTO.getIdUsuario(), "DESATIVAR");
        Assert.assertEquals(resultService.getStatusCode(), 200);
        usuarioService.deleteUsuario(usuarioDTO.getIdUsuario());
    }

    @Test
    public void desativarUsuarioComIdInexistente() {
        Response resultService = usuarioService.desativarUsuarioComIdInexistente(3434343, "DESATIVAR");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    public UsuarioDTO addPessoaPeloJson() throws IOException {
        String json = lerJson("src/test/resources/data/usuario.json");
        UsuarioDTO resultService = usuarioService.cadastroUsuario(json);
        return resultService;
    }


}


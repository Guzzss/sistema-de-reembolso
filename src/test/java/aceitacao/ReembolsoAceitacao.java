package aceitacao;

import aceitacao.dto.AnexoDTO;
import aceitacao.dto.PageReembolsoDTO;
import aceitacao.dto.ReembolsoDTO;
import aceitacao.service.ReembolsoService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReembolsoAceitacao {

    ReembolsoService reembolsoService = new ReembolsoService();

    public String lerJson(String caminhojson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhojson)));
    }

    @Test
    public void criarReembolsoComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/Reembolso.json");
        ReembolsoDTO resultService = reembolsoService.criarReembolsoComSucesso(json);
        Assert.assertEquals(resultService.getTitulo(), "Transporte para reunião com cliente");
        Assert.assertEquals(resultService.getValor(), Double.valueOf(10));
        Assert.assertEquals(resultService.getStatusDoReembolso(), "aberto");
        Assert.assertEquals(resultService.getIdReembolso(), resultService.getIdReembolso());
        Assert.assertEquals(resultService.getUsuario().getIdUsuario(), Integer.valueOf(6));
        Assert.assertEquals(resultService.getUsuario().getNome(), "Gustavo");
        Assert.assertEquals(resultService.getUsuario().getEmail(), "gustavo.teichmann@dbccompany.com.br");
        reembolsoService.deletarReembolsoComSucesso(resultService.getIdReembolso(), 0, 5);
    }

    @Test
    public void criarReembolsoSemPassarValor() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/ReembolsoSemValor.json");
        Response resultService = reembolsoService.criarReembolsoSemPassarValor(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void criarReembolsoSemPassarTitulo() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/ReembolsoSemValor.json");
        Response resultService = reembolsoService.criarReembolsoSemPassarTitulo(json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void editarReembolsoComSucesso() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/ReembolsoEditado.json");
        ReembolsoDTO reembolsoDTO = addReembolso();
        ReembolsoDTO resultService = reembolsoService.editarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), json);
        Assert.assertEquals(resultService.getTitulo(), "Transporte para reunião com um novo cliente");
        Assert.assertEquals(resultService.getValor(), Double.valueOf(100));
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void editarReembolsoSemPassarValor() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/ReembolsoSemValor.json");
        ReembolsoDTO reembolsoDTO = addReembolso();
        Response resultService = reembolsoService.editarReembolsoSemPassarValor(reembolsoDTO.getIdReembolso(), json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void editarReembolsoSemPassarTitulo() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/ReembolsoSemTitulo.json");
        ReembolsoDTO reembolsoDTO = addReembolso();
        Response resultService = reembolsoService.editarReembolsoSemPassarTitulo(reembolsoDTO.getIdReembolso(), json);
        Assert.assertEquals(resultService.getStatusCode(), 400);
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void deletarReembolsoComSucesso() throws IOException {
        ReembolsoDTO reembolsoDTO = addReembolso();
        Response resultService = reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
        Assert.assertEquals(resultService.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void deletarReembolsoComIdInexistente() {
        Response resultService = reembolsoService.deletarReembolsoComIdInexistente(777777777);
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void listarReembolsoDoUsuarioLogadoComSucesso() {
        PageReembolsoDTO resultService = reembolsoService.listarReembolsoDoUsuarioLogadoComSucesso("ABERTO", 0, 10);
        Assert.assertEquals(resultService.getContent().size(), 10);
        Assert.assertEquals(resultService.getPage(), Integer.valueOf(0));
        Assert.assertNotNull(resultService.getContent());
    }

    @Test
    public void listarReembolsoDosUsuariosComSucesso() {
        PageReembolsoDTO resultService = reembolsoService.listarReembolsoDosUsuariosComSucesso("ABERTO", 0, 10);
        Assert.assertEquals(resultService.getContent().size(), 10);
        Assert.assertEquals(resultService.getPage(), Integer.valueOf(0));
        Assert.assertNotNull(resultService.getContent());
    }

    @Test
    public void listarReembolsosPorNomeEStatus() {
        PageReembolsoDTO resultService = reembolsoService.listarReembolsoPorNomeEStatusComSucesso("Gustavo", "ABERTO", 0, 10);
        Assert.assertEquals(resultService.getContent().size(), 10);
        Assert.assertEquals(resultService.getPage(), Integer.valueOf(0));
        Assert.assertNotNull(resultService.getContent());
    }

    @Test
    public void getReembolsoById() throws IOException {
        ReembolsoDTO reembolsoDTO = addReembolso();
        ReembolsoDTO resultService = reembolsoService.getReembolsoByIdComSucesso(reembolsoDTO.getIdReembolso());
        reembolsoService.deletarReembolsoComSucesso(resultService.getIdReembolso(), 0, 5);
    }

    @Test
    public void getReembolsoByIdInexistente() throws IOException {
        Response resultService = reembolsoService.getReembolsoByIdComIdInexistente(4564654);
        Assert.assertEquals(resultService.getStatusCode(), 404);

    }

    public ReembolsoDTO addReembolso() throws IOException {
        String json = lerJson("src/test/resources/data/reembolsoJsons/Reembolso.json");
        return reembolsoService.criarReembolsoComSucesso(json);

    }

}

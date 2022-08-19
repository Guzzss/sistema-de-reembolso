package aceitacao;

import aceitacao.dto.ReembolsoDTO;
import aceitacao.dto.gestor.GestorAprovarReembolsoDTO;
import aceitacao.service.GestorService;
import aceitacao.service.ReembolsoService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GestorAceitacao {

    GestorService gestorService = new GestorService();
    ReembolsoAceitacao reembolsoAceitacao = new ReembolsoAceitacao();
    ReembolsoService reembolsoService = new ReembolsoService();

    @Test
    public void aprovarReembolsoComSucesso() throws IOException {
        ReembolsoDTO reembolsoDTO = reembolsoAceitacao.addReembolso();
        GestorAprovarReembolsoDTO resultService =  gestorService.avaliarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), "true");
        Assert.assertEquals(resultService.getIdReembolso(), reembolsoDTO.getIdReembolso());
        Assert.assertEquals(resultService.getStatusDoReembolso(), "aprovado gestor");
        Assert.assertEquals(resultService.getValor(), reembolsoDTO.getValor());
        Assert.assertEquals(resultService.getTitulo(), reembolsoDTO.getTitulo());
        Assert.assertEquals(resultService.getAnexoDTO(), reembolsoDTO.getAnexoDTO());
        Assert.assertEquals(resultService.getUsuario(), reembolsoDTO.getUsuario());
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void aprovarReembolsoComIdInexistente() {
        Response resultService =  gestorService.avaliarReembolsoComIdInexistente(343434343, "true");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void reprovarReembolsoComSucesso() throws IOException {
        ReembolsoDTO reembolsoDTO = reembolsoAceitacao.addReembolso();
        GestorAprovarReembolsoDTO resultService =  gestorService.avaliarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), "false");
        Assert.assertEquals(resultService.getIdReembolso(), reembolsoDTO.getIdReembolso());
        Assert.assertEquals(resultService.getStatusDoReembolso(), "reprovado gestor");
        Assert.assertEquals(resultService.getValor(), reembolsoDTO.getValor());
        Assert.assertEquals(resultService.getTitulo(), reembolsoDTO.getTitulo());
        Assert.assertEquals(resultService.getAnexoDTO(), reembolsoDTO.getAnexoDTO());
        Assert.assertEquals(resultService.getUsuario(), reembolsoDTO.getUsuario());
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(),0 , 5);
    }

    @Test
    public void reprovarReembolsoComIdInexistente() {
        Response resultService =  gestorService.avaliarReembolsoComIdInexistente(343434343, "false");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }
}

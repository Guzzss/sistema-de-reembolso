package aceitacao;

import aceitacao.dto.ReembolsoDTO;
import aceitacao.dto.financeiro.FinanceiroPagarDTO;
import aceitacao.service.FinanceiroService;
import aceitacao.service.ReembolsoService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FinanceiroAceitacao {

    FinanceiroService financeiroService = new FinanceiroService();
    ReembolsoAceitacao reembolsoAceitacao = new ReembolsoAceitacao();
    ReembolsoService reembolsoService = new ReembolsoService();

    @Test
    public void aprovarPagamentoComSucesso() throws IOException {
        ReembolsoDTO reembolsoDTO = reembolsoAceitacao.addReembolso();
        FinanceiroPagarDTO resultService = financeiroService.pagarFinanceiroComSucesso(reembolsoDTO.getIdReembolso(), "true");
        Assert.assertEquals(resultService.getIdReembolso(), reembolsoDTO.getIdReembolso());
        Assert.assertEquals(resultService.getStatusDoReembolso(), "fechado(pago)");
        Assert.assertEquals(resultService.getValor(), reembolsoDTO.getValor());
        Assert.assertEquals(resultService.getTitulo(), reembolsoDTO.getTitulo());
        Assert.assertEquals(resultService.getAnexoDTO(), reembolsoDTO.getAnexoDTO());
        Assert.assertEquals(resultService.getUsuario(), reembolsoDTO.getUsuario());
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void aprovarPagamentoComIdInexistente() {
        Response resultService = financeiroService.pagarFinanceiroComIdInexistente(343434343, "true");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }

    @Test
    public void reprovarPagamentoComSucesso() throws IOException {
        ReembolsoDTO reembolsoDTO = reembolsoAceitacao.addReembolso();
        FinanceiroPagarDTO resultService = financeiroService.pagarFinanceiroComSucesso(reembolsoDTO.getIdReembolso(), "false");
        Assert.assertEquals(resultService.getIdReembolso(), reembolsoDTO.getIdReembolso());
        Assert.assertEquals(resultService.getStatusDoReembolso(), "reprovado financeiro");
        Assert.assertEquals(resultService.getValor(), reembolsoDTO.getValor());
        Assert.assertEquals(resultService.getTitulo(), reembolsoDTO.getTitulo());
        Assert.assertEquals(resultService.getAnexoDTO(), reembolsoDTO.getAnexoDTO());
        Assert.assertEquals(resultService.getUsuario(), reembolsoDTO.getUsuario());
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(), 0, 5);
    }

    @Test
    public void reprovarPagamentoComIdInexistente() {
        Response resultService = financeiroService.pagarFinanceiroComIdInexistente(343434343, "false");
        Assert.assertEquals(resultService.getStatusCode(), 400);
    }
 }

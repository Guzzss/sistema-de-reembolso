package aceitacao;

import aceitacao.dto.reembolso.ReembolsoDTO;
import aceitacao.dto.usuario.NovoUsuarioDTO;
import aceitacao.service.ArquivoService;
import aceitacao.service.ReembolsoService;
import aceitacao.service.UsuarioService;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class ArquivoAceitacao {

    ArquivoService arquivoService = new ArquivoService();
    UsuarioAceitacao usuarioAceitacao = new UsuarioAceitacao();
    UsuarioService usuarioService = new UsuarioService();
    ReembolsoAceitacao reembolsoAceitacao = new ReembolsoAceitacao();

    ReembolsoService reembolsoService = new ReembolsoService();
    @Test
    public void fileUpdate() throws IOException {
        NovoUsuarioDTO usuario = usuarioAceitacao.addPessoaPeloJson();
        Response arquivo = arquivoService.updateFile(usuario.getIdUsuario());
        Assert.assertEquals(arquivo.getStatusCode(), HttpStatus.SC_OK);
        usuarioService.deleteUsuario(usuario.getIdUsuario());
    }

    @Test
    public void anexoUpdate() throws IOException {
        ReembolsoDTO reembolsoDTO = reembolsoAceitacao.addReembolso();
        Response arquivo = arquivoService.updateAnexo(reembolsoDTO.getIdReembolso(), reembolsoDTO.getUsuario().getIdUsuario());
        Assert.assertEquals(arquivo.getStatusCode(), HttpStatus.SC_OK);
        reembolsoService.deletarReembolsoComSucesso(reembolsoDTO.getIdReembolso(),reembolsoDTO.getUsuario().getIdUsuario());
    }
}

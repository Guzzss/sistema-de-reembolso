package aceitacao.dto.reembolso;

import aceitacao.dto.arquivos.AnexoDTO;
import aceitacao.dto.usuario.UsuarioReembolsoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class ReembolsoDTO {
    private Integer idReembolso;
    private Date dataEntrada;
    private Date dataUltimaAlteracao;
    private Double valor;
    private String statusDoReembolso;
    private String titulo;
    private AnexoDTO anexoDTO;
    private UsuarioReembolsoDTO usuario;
}

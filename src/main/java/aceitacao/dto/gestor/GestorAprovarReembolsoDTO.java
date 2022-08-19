package aceitacao.dto.gestor;

import aceitacao.dto.AnexoDTO;
import aceitacao.dto.usuarioDTO.UsuarioReembolsoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class GestorAprovarReembolsoDTO {
    private Integer idReembolso;
    private Date dataEntrada;
    private Date dataUltimaAlteracao;
    private Double valor;
    private String statusDoReembolso;
    private String titulo;
    private AnexoDTO anexoDTO;
    private UsuarioReembolsoDTO usuario;
}

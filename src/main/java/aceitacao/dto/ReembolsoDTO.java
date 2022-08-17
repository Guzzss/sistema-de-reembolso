package aceitacao.dto;

import aceitacao.dto.usuarioDTO.UsuarioDTO;
import aceitacao.dto.usuarioDTO.UsuarioReembolsoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonIgnoreProperties
public class ReembolsoDTO {
    private Integer idReembolso;
    private Date data;
    private Double valor;
    private String statusDoReembolso;
    private String titulo;
    private AnexoDTO anexoDTO;
    private UsuarioReembolsoDTO usuario;
}

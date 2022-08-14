package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UsuarioLogadoDTO {
    private String nome;
    private String email;
    private Integer idUsuario;
    private byte[] foto;
}

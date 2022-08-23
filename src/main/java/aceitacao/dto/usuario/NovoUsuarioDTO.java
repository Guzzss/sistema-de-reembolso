package aceitacao.dto.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class NovoUsuarioDTO {
    private Integer idUsuario;
    private String token;
    private String role;
}

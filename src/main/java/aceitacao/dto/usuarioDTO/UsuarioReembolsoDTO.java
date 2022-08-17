package aceitacao.dto.usuarioDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UsuarioReembolsoDTO {
    private Integer idUsuario;
    private String nome;
    private String email;
}

package aceitacao.dto.usuarioDTO;

import aceitacao.dto.FotoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class UsuarioDTO {
    private String nome;
    private String email;
    private Integer idUsuario;
    private Double valorTotal;
    private FotoDTO fotoDTO;
}

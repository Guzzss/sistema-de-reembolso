package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class UsuarioDTO {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private byte[] foto;
}

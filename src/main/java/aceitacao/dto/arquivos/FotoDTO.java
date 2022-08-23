package aceitacao.dto.arquivos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class FotoDTO {
    private Integer idFotos;
    private String nome;
    private String tipo;
    private Byte[] data;
}

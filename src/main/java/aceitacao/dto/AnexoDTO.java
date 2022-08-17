package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AnexoDTO {
    private Integer idAnexo;
    private String nome;
    private String tipo;
    private byte[] data;
}

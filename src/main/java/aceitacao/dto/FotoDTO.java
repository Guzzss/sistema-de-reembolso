package aceitacao.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class FotoDTO {
    private Integer idArquivos;
    private String nome;
    private String tipo;
    private List<String> data;
}

package aceitacao.dto.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class PageUsuarioDTO<UsuarioDTO> {
    private Long totalElements;
    private Integer totalPages;
    private Integer page;
    private Integer size;
    private List<UsuarioDTO> content;
}

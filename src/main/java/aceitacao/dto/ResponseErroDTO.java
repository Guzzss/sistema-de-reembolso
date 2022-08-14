package aceitacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class ResponseErroDTO {
	private List<String> errors;
	private String timestamp;
	private Integer status;
}
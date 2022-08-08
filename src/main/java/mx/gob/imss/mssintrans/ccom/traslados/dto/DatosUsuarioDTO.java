package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosUsuarioDTO {
	@JsonProperty
	public String rol;

	@JsonProperty
	public String matricula;
	
	@JsonProperty
	public Integer IDOOAD;
	@JsonProperty
	public Integer idUnidadAdscripcion;
}

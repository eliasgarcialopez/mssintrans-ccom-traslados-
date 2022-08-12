package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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

package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "unidades-administracion")
public class UnidadesRespuesta {
	@JsonProperty
	private Integer idUnidad;
	
	@JsonProperty
	private String nomUnidadAdscripcion;
}

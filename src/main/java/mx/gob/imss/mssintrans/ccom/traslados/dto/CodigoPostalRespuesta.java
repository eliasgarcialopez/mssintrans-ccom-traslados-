package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "codigo-postal")
public class CodigoPostalRespuesta {
	@JsonProperty
	private Integer idCodigoPostal;

	@JsonProperty
	private String codigoPostal;
	
	@JsonProperty
	private Integer idMunicipio;

	@JsonProperty
	private String nomMunicipio;
	
	@JsonProperty
	private Integer idEstado;

	@JsonProperty
	private String nomEstado;
	
}

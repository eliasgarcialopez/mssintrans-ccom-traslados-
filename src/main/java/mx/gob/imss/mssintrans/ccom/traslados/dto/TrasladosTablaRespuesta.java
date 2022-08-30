package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "traslados")
public class TrasladosTablaRespuesta {
	@JsonProperty
	private Integer idSolicitud;
	@JsonProperty
	private String fecSolicitud;
	@JsonProperty
	private String timTransmision;
	@JsonProperty
	private String umSolicitante;
	@JsonProperty
	private String umfAdscripcion;
	@JsonProperty
	private String desNomPaciente;
	@JsonProperty
	private String desnsPaciente;
}

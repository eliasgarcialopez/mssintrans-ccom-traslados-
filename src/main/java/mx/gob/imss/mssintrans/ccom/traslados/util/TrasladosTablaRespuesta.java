package mx.gob.imss.mssintrans.ccom.traslados.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrasladosTablaRespuesta {
	private Integer idSolicitud;
	private String fechaSolicitud;
	private String umSolicitante;
	private Double umfAdscripcion;
	private String nombrePaciente;
}

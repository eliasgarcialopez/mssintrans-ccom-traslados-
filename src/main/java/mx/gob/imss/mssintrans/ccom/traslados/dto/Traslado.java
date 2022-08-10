package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "traslado")
public class Traslado {

	@JsonProperty
	private Integer idSolicitud;
	@JsonProperty
	private String fecSolicitud;
	@JsonProperty
	private String timSolicitud;
	@JsonProperty
	private Integer idUnidadSolicitante;
	@JsonProperty
	private Integer idUnidadAdscripcion;
	@JsonProperty
	private String desnsPaciente;
	@JsonProperty
	private String desDiagnostico;
	@JsonProperty
	private Integer indOxigeno;
	@JsonProperty
	private Integer indIncubadora;
	@JsonProperty
	private Integer indCapsula;
	@JsonProperty
	private Integer numTipoServicio;
	@JsonProperty
	private Integer numPosturaPaciente;
	@JsonProperty
	private String fecTransmision;
	@JsonProperty
	private String timTransmision;
	@JsonProperty
	private Integer cveOrigen;
	@JsonProperty
	private Integer cveAreaOrigen;
	@JsonProperty
	private Integer numCamaOrigen;
	@JsonProperty
	private Integer cveDestino;

	@JsonProperty
	private Integer cveAreaDestino;
	@JsonProperty
	private Integer numCamaDestino;
	@JsonProperty
	private Integer idCodigoPostal;
	@JsonProperty
	private Integer idMunicipio;
	@JsonProperty
	private String desColonia;
	@JsonProperty
	private String desCalle;
	@JsonProperty
	private String numExterior;
	@JsonProperty
	private String numInterior;
	@JsonProperty
	private String desReferencia;
	@JsonProperty
	private Integer numTelDestino;
	@JsonProperty
	private String nomFamiliar;
	@JsonProperty
	private Integer telFamiliar;
	@JsonProperty
	private String cveMatriculaRecibe;
	@JsonProperty
	private String numFolioAceptacion;
	@JsonProperty
	private String numMaticulaAutoriza;
	@JsonProperty
	private Integer idEstatusSolicitud;
	@JsonProperty
	private String desmotivoCancelacion;
	@JsonProperty
	private String cveMatricula;

}

package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Traslado {

	private Integer idSolicitud;
	private String fecSolicitud;
	private String timSolicitud;
	private Integer idUnidadSolicitante;
	private Integer idUnidadAdscripcion;
	private String desnsPaciente;
	private String desDiagnostico;
	private Integer indOxigeno;
	private Integer indIncubadora;
	private Integer indCapsula;
	private Integer numTipoServicio;
	private Integer numPosturaPaciente;
	private String fecTransmision;
	private String timTransmision;
	private Integer cveOrigen;
	private Integer cveAreaOrigen;
	private Integer numCamaOrigen;
	private Integer cveDestino;
	private Integer cveAreaDestino;
	private Integer numCamaDestino;
	private Integer idCodigoPostal;
	private Integer idMunicipio;
	private String desColonia;
	private String desCalle;
	private String numExterior;
	private String numInterior;
	private String desReferencia;
	private Integer numTelDestino;
	private String nomFamiliar;
	private Integer telFamiliar;
	private String cveMatriculaRecibe;
	private String numFolioAceptacion;
	private String numMaticulaAutoriza;
	private Integer idEstatusSolicitud;
	private String desmotivoCancelacion;
	private String cveMatricula;

}
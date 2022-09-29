package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrasladoResponse {
	private Integer idSolicitud;
	private String fecSolicitud;
	private String timSolicitud;
	private Integer idUnidadSolicitante;
	private Integer idUnidadAdscripcion;
	private String desnsPaciente;
	private String desNomPaciente;
	private String desDiagnostico;
	private Integer indOxigeno;
	private Integer indIncubadora;
	private Integer indCapsula;
	private Integer desTipoServicio;
	private Integer desPosturaPaciente;
	private String fecTransmision;
	private String timTransmision;
	private Integer cveOrigen;
	private String desAreaOrigen;
	private Integer numCamaOrigen;
	private Integer cveDestino;
	private String desAreaDestino;
	private Integer numCamaDestino;
	//private Integer idCodigoPostal;
	private String codigoPostal;
	//private Integer idMunicipio;
	private String nomMunicipio;
	private String nomEstado;
	private String desColonia;
	private String desCalle;
	private String numExterior;
	private String numInterior;
	private String desReferencia;
	private String numTelDestino;
	private String nomFamiliar;
	private String telFamiliar;
	private String numMatriculaRecibe;
	private String desNomDoctorRecibe;
	private String numFolioAceptacion;
	private String numMatriculaAutoriza;
	private String desNomDoctorAutoriza;
	private String desEstatusSolicitud;
	private String desmotivoCancelacion;
	private String desMunicipio;
	private String desEstado;
	private String desCiudad;
	private String cveCodPostal;
	private String desFirma;
}

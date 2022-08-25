package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANST_SOLICITUD_TRASLADO")
public class TrasladoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SOLICITUD", unique = false, nullable = true)
	private Integer idSolicitud;
	
	@Column(name = "FEC_SOLICITUD", unique = false, nullable = true)
	private String fecSolicitud;
	
	@Column(name = "TIM_SOLICITUD", unique = false, nullable = true)
	private String timSolicitud;
	
	@Column(name = "ID_UNIDAD_SOLICITANTE", unique = false, nullable = true)
	private Integer idUnidadSolicitante;
	
	@Column(name = "ID_UNIDAD_ADSCRIPCION", unique = false, nullable = true)
	private Integer idUnidadAdscripcion;
	
	@Column(name = "DES_NSS_PACIENTE", unique = false, nullable = true)
	private String desnsPaciente;

	@Column(name = "DES_NOM_PACIENTE", unique = false, nullable = true)
	private String desNomPaciente;
	
	@Column(name = "DES_DIAGNOSTICO", unique = false, nullable = true)
	private String desDiagnostico;
	
	@Column(name = "IND_OXIGENO", unique = false, nullable = true)
	private Integer indOxigeno;
	
	@Column(name = "IND_INCUBADORA", unique = false, nullable = true)
	private Integer indIncubadora;
	
	@Column(name = "IND_CAPSULA", unique = false, nullable = true)
	private Integer indCapsula;
	
	@Column(name = "DES_TIPO_SERVICIO", unique = false, nullable = true)
	private Integer desTipoServicio;
	
	@Column(name = "DES_POSTURA_PACIENTE", unique = false, nullable = true)
	private Integer desPosturaPaciente;
	
	@Column(name = "FEC_TRANSMISION", unique = false, nullable = true)
	private String fecTransmision;
	
	@Column(name = "TIM_TRANSMISION", unique = false, nullable = true)
	private String timTransmision;
	
	@Column(name = "CVE_ORIGEN", unique = false, nullable = true)
	private Integer cveOrigen;
	
	@Column(name = "DES_AREA_ORIGEN", unique = false, nullable = true)
	private String desAreaOrigen;
	
	@Column(name = "NUM_CAMA_ORIGEN", unique = false, nullable = true)
	private Integer numCamaOrigen;
	
	@Column(name = "CVE_DESTINO", unique = false, nullable = true)
	private Integer cveDestino;
	
	@Column(name = "DES_AREA_DESTINO", unique = false, nullable = true)
	private String desAreaDestino;
	
	@Column(name = "NUM_CAMA_DESTINO", unique = false, nullable = true)
	private Integer numCamaDestino;
	
	@Column(name = "ID_CODIGO_POSTAL", unique = false, nullable = true)
	private Integer idCodigoPostal;
	
	@Column(name = "ID_MUNICIPIO", unique = false, nullable = true)
	private Integer idMunicipio;
	
	@Column(name = "DES_COLONIA", unique = false, nullable = true)
	private String desColonia;
	
	@Column(name = "DES_CALLE", unique = false, nullable = true)
	private String desCalle;
	
	@Column(name = "NUM_EXTERIOR", unique = false, nullable = true)
	private String numExterior;
	
	@Column(name = "NUM_INTERIOR", unique = false, nullable = true)
	private String numInterior;
	
	@Column(name = "DES_REFERENCIA", unique = false, nullable = true)
	private String desReferencia;
	
	@Column(name = "NUM_TEL_DESTINO", unique = false, nullable = true)
	private Integer numTelDestino;
	
	@Column(name = "NOM_FAMILIAR", unique = false, nullable = true)
	private String nomFamiliar;
	
	@Column(name = "TEL_FAMILIAR", unique = false, nullable = true)
	private Integer telFamiliar;
	
	@Column(name = "CVE_MATRICULA_DOCTOR_RECIBE", unique = false, nullable = true)
	private String numMatriculaRecibe;

	@Column(name = "NUM_FOLIO_ACEPTACION", unique = false, nullable = true)
	private String numFolioAceptacion;
	
	@Column(name = "CVE_MATRICULA_DOCTOR_AUTORIZA", unique = false, nullable = true)
	private String numMatriculaAutoriza;
	
	@Column(name = "DES_ESTATUS_SOLICITUD", unique = false, nullable = true)
	private String desEstatusSolicitud;
	
	@Column(name = "DES_MOTIVO_CANCELACION", unique = false, nullable = true)
	private String desmotivoCancelacion;
	
	@Column(name = "CVE_MATRICULA", unique = false, nullable = true)
	private String cveMatricula;
	
	@Column(name = "FEC_ALTA", unique = false, nullable = true)
	private String fecAlta;

	@Column(name = "FEC_ACTUALIZACION", unique = false, nullable = true)
	private String fecActualizacion;

	@Column(name = "FEC_BAJA", unique = false, nullable = true)
	private String fecBaja;
	
	@Column(name = "IND_ACTIVO", unique = false, nullable = true)
	private Integer indActivo;

	@Column(name = "IND_SISTEMA", unique = false, nullable = true)
	private Integer indSistema; 
}

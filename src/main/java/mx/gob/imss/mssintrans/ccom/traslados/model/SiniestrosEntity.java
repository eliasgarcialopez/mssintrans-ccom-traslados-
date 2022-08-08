package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANST_SINIESTROS")
public class SiniestrosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SINIESTRO", unique = false, nullable = true)
	private Integer idSiniestro;
	
	@Column(name = "NUM_FOLIO", unique = false, nullable = true)
	private Integer numFolio;
	
	@Column(name = "FEC_REPORTE", unique = false, nullable = true)
	private String fecReporte;

	@Column(name = "FEC_SINIESTRO", unique = false, nullable = true)
	private String fecSiniestro;
	
	/*@Column(name = "ID_VEHICULO", unique = false, nullable = true)
	private Integer idVehiculo;*/

	@OneToOne
	@JoinColumn( name="ID_VEHICULO", unique = false, nullable = true)
	private VehiculosPropiosEntity vehiculo;
	
	@Column(name = "ID_REASIGNACION", unique = false, nullable = true)
	private Integer idReAsignacion;
	
	
	/*@Column(name = "ID_CHOFER", unique = false, nullable = true)
	private Integer idChofer;
	
	@Column(name = "ID_ARRENDADORA", unique = false, nullable = true)
	private Integer idArrendadora;
	
	@Column(name = "ID_ASEGURADORA", unique = false, nullable = true)
	private Integer idAseguradora;*/
	
	@Column(name = "FEC_INGRESO_TALLER", unique = false, nullable = true)
	private String fecIngresoTaller;
	
	@Column(name = "FEC_SALIDA_TALLER", unique = false, nullable = true)
	private String fecSalidaTaller;
	
	@Column(name = "OBSERVACIONES", unique = false, nullable = true, length = 150)
	private String observaciones;
	
	@Column(name = "DES_RUTA_DECLARACION", unique = false, nullable = true)
	private String desRutaDeclaracion;
	
	@Column(name = "DES_RUTA_REPORTE_ACCID", unique = false, nullable = true)
	private String desRutaReproteAcc;
	
	@Column(name = "DES_RUTA_REPORTE_FOTO", unique = false, nullable = true)
	private String desRutaReporteFoto;
	
	@Column(name = "CANT_PORC_PERDIDA", unique = false, nullable = true)
	private Integer cantidadPorcPerdidad;
	
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

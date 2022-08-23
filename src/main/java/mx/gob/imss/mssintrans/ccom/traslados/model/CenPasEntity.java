package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANST_CENSO_PACIENTES")
public class CenPasEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CENSO", unique = false, nullable = true) 
	private Integer idCenso;
	
	@Column(name = "DES_NSS", unique = false, nullable = true) 
	private String desNss;
	
	@Column(name = "NOMBRE_PACIENTE", unique = false, nullable = true) 
	private String nombre;
	
	@Column(name = "DES_ESTATUS", unique = false, nullable = true) 
	private String desEstatus;
	
	@Column(name = "IND_LUNES", unique = false, nullable = true)
	private Integer lunes;
	
	@Column(name = "IND_MARTES", unique = false, nullable = true)
	private Integer martes;
	
	@Column(name = "IND_MIERCOLES", unique = false, nullable = true)
	private Integer miercoles;
	
	@Column(name = "IND_JUEVES", unique = false, nullable = true)
	private Integer jueves;
	
	@Column(name = "IND_VIERNES", unique = false, nullable = true)
	private Integer viernes;
	
	@Column(name = "IND_SABADO", unique = false, nullable = true)
	private Integer sabado;
	
	@Column(name = "IND_DOMINGO", unique = false, nullable = true)
	private Integer domingo;
	
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

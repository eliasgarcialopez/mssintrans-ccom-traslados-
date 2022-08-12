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
@Table(name = "SINTRANST_CENSO_DOCTORES")
public class CenDocEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CENSO", unique = false, nullable = true) 
	private Integer idCenso;
	
	@Column(name = "CVE_MATRICULA", unique = false, nullable = true) 
	private Integer cveMatricula;
	
	@Column(name = "ID_UNIDAD", unique = false, nullable = true) 
	private Integer idUnidad;
	
	@Column(name = "DES_ESTATUS", unique = false, nullable = true) 
	private String desEstatus;
	
	@Column(name = "CVE_MATRICULA_AUDITORIA", unique = false, nullable = true) 
	private String cveMatriculaAud;
	
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

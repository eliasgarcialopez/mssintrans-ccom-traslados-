package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANST_VEHICULOS")
public class VehiculosArrendadosEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEHICULO", unique = false, nullable = true)
	private Integer idVehiculo;
	
	@Column(name = "CVE_ECCO", unique = false, nullable = true)
	private String cveEcco;
	
	@Column(name = "NUM_PLACAS", unique = false, nullable = true)
	private String numPlacas;
	
	@Column(name = "DES_ESTATUS_VEHICULO", unique = false, nullable = true)
	private String desEstatusVehiculo;

	@Column(name = "NOM_ARRENDADORA", unique = false, nullable = true)
	private String nomArrendadora;

	@Column(name = "DES_NOMBRE_ASEGURADORA", unique = false, nullable = true)
	private String desNombreAseguradora;
	
	@Column(name = "ID_REASIGNACION", unique = false, nullable = true)
	private Integer idReAsignacion;
	

	@Column(name = "NOM_CHOFER", unique = false, nullable = true)
	private String nombreChofer;
	
	
}


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
@Table(name = "SINTRANST_VEHICULOS")
public class VehiculosPropiosEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEHICULO", unique = false, nullable = true)
	private Integer idVehiculo;
	
	@Column(name = "CVE_ECCO", unique = false, nullable = true)
	private String cveEcco;
	
	@Column(name = "NUM_INVENTARIO", unique = false, nullable = true)
	private String numInventario;
	
	@Column(name = "NUM_SERIE", unique = false, nullable = true)
	private String numSerie;
	
	@Column(name = "NUM_TARJETON", unique = false, nullable = true)
	private String numTarjeton;
	
	@Column(name = "DES_TIPO_VEHICULO", unique = false, nullable = true)
	private String desTipoVehiculo;
	
	@Column(name = "DES_CLASIF_CONUEE", unique = false, nullable = true)
	private String desClasifConuee;
	
	@Column(name = "DES_TIPO_SERVICIO", unique = false, nullable = true)
	private String desTipoServicio;
	
	@Column(name = "DES_VERSION_VEHICULO", unique = false, nullable = true)
	private String desVersionVehiculo;
	
	@Column(name = "DES_MARCA", unique = false, nullable = true)
	private String desMarca;
	
	@Column(name = "DES_CLASE", unique = false, nullable = true)
	private String desClase;
	
	@Column(name = "DES_SUBMARCA", unique = false, nullable = true)
	private String desSubmarca;
	
	@Column(name = "DES_MODELO", unique = false, nullable = true)
	private Integer desModelo;
	
	@Column(name = "DES_COMBUSTIBLE", unique = false, nullable = true)
	private String desCombustible;
	
	@Column(name = "DES_COMBUSTIBLE_X_LTO", unique = false, nullable = true)
	private Integer desCombustibleXLitro;
	
	@Column(name = "CAN_CAPACIDAD_PERSONAS", unique = false, nullable = true)
	private Integer canCapacidadPersonas;
	
	@Column(name = "CAN_TONELADAS", unique = false, nullable = true)
	private Double canToneladas;
	
	@Column(name = "CAN_CILINDROS", unique = false, nullable = true)
	private Integer canCilindros;
	
	@Column(name = "NUM_MOTOR", unique = false, nullable = true)
	private String numMotor;
	
	@Column(name = "IMP_VALOR_CONTABLE", unique = false, nullable = true)
	private String impValorContable;
	
	@Column(name = "NUM_PLACAS", unique = false, nullable = true)
	private String numPlacas;
	
	@Column(name = "NUM_LICENCIA_COFEPRIS", unique = false, nullable = true)
	private String numLicenciaCofepris;
	
	@Column(name = "FEC_VENCIMIENTO_COFEPRIS", unique = false, nullable = true)
	private String fecVencimientoCofepris;
	
	@Column(name = "DES_TIPO_REGIMEN", unique = false, nullable = true)
	private String desTipoRegimen;
	
	@Column(name = "ID_UNIDAD_ADSCRIPCION", unique = false, nullable = true)
	private Integer idUnidadAdscripcion;
	
	@Column(name = "NOM_RESPONSABLE_BIENES", unique = false, nullable = true)
	private String nomResponsableBienes;
	
	@Column(name = "DES_ESTATUS_VEHICULO", unique = false, nullable = true)
	private String desEstatusVehiculo;
	
	@Column(name = "FEC_BAJA_VEHICULO", unique = false, nullable = true)
	private String fecBajaVehiculo;
	
	@Column(name = "DES_MOTIVO_BAJA", unique = false, nullable = true)
	private String desMotivoBaja;
	
	@Column(name = "DES_ESTATUS_ENAJENACION", unique = false, nullable = true)
	private String desEstatusEnajenacion;
	
	@Column(name = "ID_ASEGURADORA", unique = false, nullable = true)
	private Integer idAseguradora;
	
	@Column(name = "NUM_POLIZA", unique = false, nullable = true)
	private String numPoliza;
	
	@Column(name = "DES_RUTA_ARCHIVO__TJETA_CIRC", unique = false, nullable = true)
	private String desRutaArchivoTjetaCirc;
	
	@Column(name = "DES_RUTA_VERIFICACION", unique = false, nullable = true)
	private String desRutaVerificacion;
	
	@Column(name = "DES_RUTA_POLIZA_SEGURO", unique = false, nullable = true)
	private String desRutaPolizaSeguro;
	
	@Column(name = "DES_RUTA_FOTO_FRENTE", unique = false, nullable = true)
	private String desRutaFotoFrente;
	
	@Column(name = "DES_RUTA_FOTO_LATERAL_IZQ", unique = false, nullable = true)
	private String desRutaFotoLateralIzq;
	
	@Column(name = "DES_RUTA_FOTO_LATERAL_DER", unique = false, nullable = true)
	private String desRutaFotoLateralDer;
	
	@Column(name = "DES_RUTA_FOTO_TRASERA", unique = false, nullable = true)
	private String desRutaFotoTrasera;
	
	@Column(name = "FEC_VENC_TARJETA_CIRCULACION", unique = false, nullable = true)
	private String fecVencTarjetaCirculacion;
	
	@Column(name = "FEC_PROX_VERIFICACION", unique = false, nullable = true)
	private String fecProxVerificacion;
	
	@Column(name = "FEC_VENC_POLIZA", unique = false, nullable = true)
	private String fecVencPoliza;
	
	@Column(name = "ID_ARRENDATARIO", unique = false, nullable = true)
	private Integer idArrentadario;
	
	@Column(name = "NUM_AUXILIAR_CONTABLE", unique = false, nullable = true)
	private String numAuxiliar;
	
	@Column(name = "IND_SUSTITUTO", unique = false, nullable = true)
	private Integer indSustituto;
	
	@Column(name = "IND_ARRENDADO", unique = false, nullable = true)
	private Integer indArrendado;
	
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

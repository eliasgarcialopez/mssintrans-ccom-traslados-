package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "siniestro")
public class SiniestroResponse {
	@JsonProperty
	private Integer idSiniestro;
	
	@JsonProperty
	private Integer numFolio;
	
	@JsonProperty
	private String fecReporte;
	
	@JsonProperty
	private String fecSiniestro;
	
	@JsonProperty
	private VehiculoResponse vehiculo;
	
	@JsonProperty
	private Integer idReAsignacion;
	
	@JsonProperty
	private String fecIngresoTaller;
	
	@JsonProperty
	private String fecSalidaTaller;
	
	@JsonProperty
	private String observaciones;
	
	@JsonProperty
	private String desRutaDeclaracion;
	
	@JsonProperty
	private String desRutaReproteAcc;
	
	@JsonProperty
	private String desRutaReporteFoto;
	
	@JsonProperty
	private Integer cantidadPorcPerdidad;
	
	@JsonProperty
	private String cveMatricula;
	
	/*@JsonProperty
	private String fecAlta;*/

	/*@JsonProperty
	private String fecActualizacion;

	@JsonProperty
	private String fecBaja;*/

	/*@JsonProperty
	private Integer indActivo;*/
	
	/*@JsonProperty
	private Integer indSistema; */

}

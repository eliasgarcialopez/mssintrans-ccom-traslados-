package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "censoDoctores")
public class CenPasResponse {
	
	@JsonProperty
	private Integer idCenso;

	@JsonProperty
	private String desNss;
	
	@JsonProperty 
	private String desEstatus;
	
	@JsonProperty
	private Integer lunes;
	
	@JsonProperty
	private Integer martes;
	
	@JsonProperty
	private Integer miercoles;
	
	@JsonProperty
	private Integer jueves;
	
	@JsonProperty
	private Integer viernes;
	
	@JsonProperty
	private Integer sabado;
	
	@JsonProperty
	private Integer domingo;
	
	@JsonProperty 
	private String cveMatricula;
	
	@JsonProperty 
	private String fecAlta;
	
	@JsonProperty 
	private String fecActualizacion;
	
	@JsonProperty 
	private String fecBaja;
	
	@JsonProperty
	private Integer indActivo;
	
	@JsonProperty
	private Integer indSistema;

}

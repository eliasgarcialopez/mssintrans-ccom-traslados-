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
public class CenDocResponse {
	
	@JsonProperty
	private Integer idCenso;

	@JsonProperty
	private Integer cveMatricula;
	
	@JsonProperty
	private Integer idUnidad;
	
	@JsonProperty 
	private String desEstatus;
	
	@JsonProperty
	private String cveMatriculaAud;
	
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

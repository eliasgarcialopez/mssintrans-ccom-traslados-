package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

	@SerializedName("MATRICULA")
	private Integer matricula;
	
	@SerializedName("CVEBAJA")
	private String cveBaja;

	@SerializedName("DEL")
	private Integer del;
	
	@SerializedName("FECHABAJA")
	private String fechaBaja;
	
	@SerializedName("CVEPUESTO")
	private Integer cvePuesto;
		
	@SerializedName("NSS")
	private String nss;
	
	@SerializedName("CURP")
	private String curp;
	
	@SerializedName("CONTRATACION")
	private Integer contratacion;
	
	@SerializedName("DESCRIPCIONDEPTO")
	private String descripcionDepto;
		
	@SerializedName("ESTATUS")
	private Integer estatus;
	
	@SerializedName("CVEDEPTO")
	private String cveDepto;
	
	@SerializedName("DECRIPCIONPUESTO")
	private String descripcionPuesto;
	
	@SerializedName("NOMBRE")
	private String nombre;
}

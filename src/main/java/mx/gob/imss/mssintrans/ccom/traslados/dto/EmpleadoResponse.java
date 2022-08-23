package mx.gob.imss.mssintrans.ccom.traslados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponse {
	
	private Integer idCenso;

	private String matriculaDoctor;
	
	private String nombreDoctor;

}

package mx.gob.imss.mssintrans.ccom.traslados.service;

import mx.gob.imss.mssintrans.ccom.traslados.dto.EmpleadoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;

public interface SiapService {

	public Respuesta<EmpleadoResponse> buscarPorMat(String matricula);
	
}

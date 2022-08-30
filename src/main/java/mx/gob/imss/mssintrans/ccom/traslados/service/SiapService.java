package mx.gob.imss.mssintrans.ccom.traslados.service;

import java.util.List;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Empleado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.EmpleadoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;

public interface SiapService {

	public Respuesta<EmpleadoResponse> buscarPorMat(String matricula);
	
	public Respuesta<List<EmpleadoResponse>> buscarMat();
	
	public Respuesta<Empleado> buscarSiapPorMat(String matricula);
	
}

package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Empleado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.EmpleadoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.ConsultaMatriculaService;
import mx.gob.imss.mssintrans.ccom.traslados.service.SiapService;

@Slf4j
@Service
public class SiapServiceImpl implements SiapService {
	
	@Autowired
	ConsultaMatriculaService consultaMatriculaService;

	@Override
	public Respuesta<EmpleadoResponse> buscarPorMat(String matricula) {
		
		Respuesta<EmpleadoResponse> respuesta = new Respuesta<>();
		Empleado response;
		EmpleadoResponse empleado = new EmpleadoResponse();
		
		response = consultaMatriculaService.consultaMatricula(matricula);
		
		log.info("Resultado: " + response);
		
		if(response==null) {
			
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje("No se encontro el registro.");
			
		}else {
			
			empleado.setMatricula(response.getMatricula());
			empleado.setNombre(response.getNombre());
			
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos(empleado);
		}
		
		return respuesta;
	}

}

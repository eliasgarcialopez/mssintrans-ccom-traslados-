package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Empleado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.EmpleadoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenDocRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.ConsultaMatriculaService;
import mx.gob.imss.mssintrans.ccom.traslados.service.SiapService;
import mx.gob.imss.mssintrans.ccom.traslados.util.CenDocMapper;

@Slf4j
@Service
public class SiapServiceImpl implements SiapService {
	
	@Autowired
	private CenDocRepository cenDocRepository;
	
	@Autowired
	private ConsultaMatriculaService consultaMatriculaService;

	@Override
	public Respuesta<EmpleadoResponse> buscarPorMat(String matricula) {
		
		Respuesta<EmpleadoResponse> respuesta = new Respuesta<>();
		EmpleadoResponse empleado = new EmpleadoResponse();
		
		CenDocEntity registro = cenDocRepository.consultaPorMat(matricula);
		
		log.info("Resultado: " + registro);
		
		if(registro==null) {
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Médico no encontrado en el Censo.");
			return respuesta;
		}
		
		empleado = CenDocMapper.INSTANCE.entityAMat(registro);
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos(empleado);
		
		return respuesta;
	}

	@Override
	public Respuesta<List<EmpleadoResponse>> buscarMat() {
		Respuesta<List<EmpleadoResponse>> respuesta = new Respuesta<>();
		List<EmpleadoResponse> lista;
		List<CenDocEntity> matriculas;
		
		matriculas = cenDocRepository.consultaMat();
		lista = CenDocMapper.INSTANCE.entityAMat(matriculas);
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos(lista);
		
		return respuesta;
	}

	@Override
	public Respuesta<Empleado> buscarSiapPorMat(String matricula) {
		Respuesta<Empleado> respuesta = new Respuesta<>();
		Empleado empleado = consultaMatriculaService.consultaMatricula(matricula);
		
		log.info("Resultado: " + empleado);
		
		if(empleado==null) {
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Médico no encontrado en el Siap.");
			return respuesta;
		}
		
		String nombre = empleado.getNombre();
		empleado.setNombre( nombre.replace("&", "Ñ") );
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos(empleado);
		
		return respuesta;
	}

}

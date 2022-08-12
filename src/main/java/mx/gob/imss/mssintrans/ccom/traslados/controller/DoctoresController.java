package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenDocService;

@Slf4j
@RestController
@RequestMapping("/censo-doctores")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DoctoresController {
	
	@Autowired
	private CenDocService cenDocService;
	
	@PostMapping
	public ResponseEntity<Respuesta<?>> crear(
			@RequestParam Integer cveMatricula,
			@RequestParam Integer idUnidad,
			@RequestParam String desEstatus
			) {
		
		Respuesta<?> response;
		
		/**
		 * Validacion de seguridad del usuario
		 */
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (usuario.equals("denegado")) {
			response = denegado(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		
		/**
		 * Llamado al funcionamiento del servicio
		 */
		CenDocEntity cenDocEntity = new CenDocEntity();
		cenDocEntity.setCveMatricula(cveMatricula);
		cenDocEntity.setIdUnidad(idUnidad);
		cenDocEntity.setDesEstatus(desEstatus);
		cenDocEntity.setCveMatriculaAud( datosUsuarios.matricula );
		
		log.info(cenDocEntity.toString());
		
		response = cenDocService.crear(cenDocEntity);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<?> actualizar(
			@RequestParam Integer idCenso,
			@RequestParam Integer cveMatricula,
			@RequestParam Integer idUnidad,
			@RequestParam String desEstatus
			) {
		
		Respuesta<?> response;
		
		/**
		 * Validacion de seguridad del usuario
		 */
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (usuario.equals("denegado")) {
			response = denegado(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		
		/**
		 * Llamado al funcionamiento del servicio
		 */
		CenDocEntity cenDocEntity = new CenDocEntity();
		cenDocEntity.setIdCenso(idCenso);
		cenDocEntity.setCveMatricula(cveMatricula);
		cenDocEntity.setIdUnidad(idUnidad);
		cenDocEntity.setDesEstatus(desEstatus);
		cenDocEntity.setCveMatriculaAud( datosUsuarios.matricula );
		
		log.info(cenDocEntity.toString());
		
		response = cenDocService.actualizar(cenDocEntity);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/{idCenso}")
	public ResponseEntity<?> obtener(@PathVariable Integer idCenso) {
		
		Respuesta<?> response;
		
		/**
		 * Validacion de seguridad del usuario
		 */
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (usuario.equals("denegado")) {
			response = denegado(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		/**
		 * Llamado al funcionamiento del servicio
		 */
		
		response = cenDocService.consultaPorId(idCenso);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public Respuesta<?> denegado( String usuario ){
		
		Respuesta<?> response = new Respuesta<>();
		response.setError(false);
		response.setCodigo(HttpStatus.UNAUTHORIZED.value());
		response.setMensaje(usuario);
		
		return response;
	}

}

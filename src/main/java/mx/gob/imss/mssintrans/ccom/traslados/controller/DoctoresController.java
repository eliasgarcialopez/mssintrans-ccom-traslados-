package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenDocService;
import mx.gob.imss.mssintrans.ccom.traslados.service.SiapService;

@Slf4j
@RestController
@RequestMapping("/censo-doctores")
public class DoctoresController {
	
	@Autowired
	private CenDocService cenDocService;
	
	@Autowired
	private SiapService siapService;
	
	@PostMapping
	public ResponseEntity<Respuesta<?>> crear(
			@RequestParam String matricula,
			@RequestParam String nombre,
			@RequestParam(required=false) Integer idUnidad,
			@RequestParam(required=false) String desEstatus
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
		cenDocEntity.setMatriculaDoctor(matricula);
		cenDocEntity.setNombreDoctor(nombre);
		cenDocEntity.setIdUnidad(idUnidad);
		cenDocEntity.setDesEstatus(desEstatus);
		cenDocEntity.setCveMatricula( datosUsuarios.matricula );
		
		log.info(cenDocEntity.toString());
		
		response = cenDocService.crear(cenDocEntity);
		
		ResponseEntity<Respuesta<?>> responseEntity;
		
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	
	@PutMapping
	public ResponseEntity<?> actualizar(
			@RequestParam Integer idCenso,
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
		cenDocEntity.setIdUnidad(idUnidad);
		cenDocEntity.setDesEstatus(desEstatus);
		cenDocEntity.setCveMatricula( datosUsuarios.matricula );
		
		log.info(cenDocEntity.toString());
		
		response = cenDocService.actualizar(cenDocEntity);
		
		ResponseEntity<?> responseEntity;
		
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
		
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
		
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	public Respuesta<?> denegado( String usuario ){
		
		Respuesta<?> response = new Respuesta<>();
		response.setError(false);
		response.setCodigo(HttpStatus.UNAUTHORIZED.value());
		response.setMensaje(usuario);
		
		return response;
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<?> obtenerMatricula(@PathVariable String matricula) {
		
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
		
		response = siapService.buscarPorMat(matricula);
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/matricula")
	public ResponseEntity<?> obtenerMatriculas() {
		
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
		
		response = siapService.buscarMat();
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@DeleteMapping("/{idCenso}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idCenso) {
		
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
		
		response = cenDocService.eliminar(idCenso);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/matricula/siap/{matricula}")
	public ResponseEntity<?> obtenerSiapMatricula(@PathVariable String matricula) {
		
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
		
		response = siapService.buscarSiapPorMat(matricula);
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@GetMapping("/matricula/censodoctores")
	public ResponseEntity<?> obtenerCensoDoctores(@RequestParam(defaultValue = "0") Integer pagina,
			@RequestParam(defaultValue = "10") Integer tamanio, @RequestParam(defaultValue = "ID_CENSO") String columna,
			@RequestParam(defaultValue = "ASC") String orden) {
		Respuesta<?> response;
		Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by(columna).descending());
		
		if(orden.equals("ASC")|| orden.equals("asc")) {
			pageable = PageRequest.of(pagina, tamanio, Sort.by(columna).ascending());
		}
		
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
		
		response = cenDocService.obtenerCensoDoctores(pageable, datosUsuarios);
		
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

}

package mx.gob.imss.mssintrans.ccom.traslados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenPasService;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CenPasResponse;

@Slf4j
@RestController
@RequestMapping("/censo-pacientes")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class PacientesController {
	
	@Autowired
	private CenPasService cenPasService;
	
	@PostMapping
	public ResponseEntity<Respuesta<?>> crear(
			@RequestParam String desNss,
			@RequestParam String nombre,
			@RequestParam(required=false) String desEstatus,
			@RequestParam(required=false) Integer lunes,
			@RequestParam(required=false) Integer martes,
			@RequestParam(required=false) Integer miercoles,
			@RequestParam(required=false) Integer jueves,
			@RequestParam(required=false) Integer viernes,
			@RequestParam(required=false) Integer sabado,
			@RequestParam(required=false) Integer domingo
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
		CenPasEntity cenPasEntity = new CenPasEntity();
		cenPasEntity.setDesNss(desNss);
		cenPasEntity.setNombre(nombre);
		cenPasEntity.setDesEstatus(desEstatus);
		cenPasEntity.setLunes(lunes);
		cenPasEntity.setMartes(martes);
		cenPasEntity.setMiercoles(miercoles);
		cenPasEntity.setJueves(jueves);
		cenPasEntity.setViernes(viernes);
		cenPasEntity.setSabado(sabado);
		cenPasEntity.setDomingo(domingo);
		cenPasEntity.setCveMatricula( datosUsuarios.matricula );
		
		log.info(cenPasEntity.toString());
		
		response = cenPasService.crear(cenPasEntity);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<?> actualizar(
			@RequestParam Integer idCenso,
			@RequestParam String desEstatus,
			@RequestParam Integer lunes,
			@RequestParam Integer martes,
			@RequestParam Integer miercoles,
			@RequestParam Integer jueves,
			@RequestParam Integer viernes,
			@RequestParam Integer sabado,
			@RequestParam Integer domingo
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
		CenPasEntity cenPasEntity = new CenPasEntity();
		cenPasEntity.setIdCenso(idCenso);
		cenPasEntity.setDesEstatus(desEstatus);
		cenPasEntity.setLunes(lunes);
		cenPasEntity.setMartes(martes);
		cenPasEntity.setMiercoles(miercoles);
		cenPasEntity.setJueves(jueves);
		cenPasEntity.setViernes(viernes);
		cenPasEntity.setSabado(sabado);
		cenPasEntity.setDomingo(domingo);
		cenPasEntity.setCveMatricula( datosUsuarios.matricula );
		
		log.info(cenPasEntity.toString());
		
		response = cenPasService.actualizar(cenPasEntity);
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
		
		response = cenPasService.consultaPorId(idCenso);
		
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
		
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		/**
		 * Llamado al funcionamiento del servicio
		 */
		
		response = cenPasService.eliminar(idCenso, datosUsuarios.matricula);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/nss/{desNss}")
	public ResponseEntity<?> obtenerNss(@PathVariable String desNss) {
		
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
		
		response = cenPasService.consultaPorNss(desNss);
		
		ResponseEntity<?> responseEntity;
		
		if(response.isError()) {
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/buscar")
	public ResponseEntity<Respuesta<?>> consulta(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "sort", defaultValue = "desc") String sort, 
			@RequestParam(value = "columna", defaultValue = "id") String columna) {
		
		Respuesta<List<CenPasResponse>> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		
		String nombreColumna;
		switch(columna)
		{
		   case "nss" : 
			   nombreColumna = "DES_NSS";			      
		       break;
		   case "nombre" : 
			   nombreColumna = "NOM_PACIENTE";			      
			   break; 
		   case "estatus" : 
			   nombreColumna = "DES_ESTATUS";			      
			   break; 
		   default:
			   nombreColumna = "ID_CENSO";
		}

		Pageable pageable = PageRequest.of(pagina, 10, Sort.by(nombreColumna).descending());
		if(sort.equals("ASC")|| sort.equals("asc")) {
			pageable = PageRequest.of(pagina, 10, Sort.by(nombreColumna).ascending());
		}
		
		response = cenPasService.consultaGeneral(pageable);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigo()));
		
	}
	
	public Respuesta<?> denegado( String usuario ){
		
		Respuesta<?> response = new Respuesta<>();
		response.setError(false);
		response.setCodigo(HttpStatus.UNAUTHORIZED.value());
		response.setMensaje(usuario);
		
		return response;
	}

}

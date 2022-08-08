package mx.gob.imss.mssintrans.ccom.traslados.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Siniestro;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.SiniestroServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/siniestros")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class SiniestroController {

	private final SiniestroServiceImpl siniestroServiceImpl;
	private static final Logger log = LoggerFactory.getLogger(SiniestroController.class);


	/**
	 * Consulta general filtrada
	 *
	 * @param pagina
	 * @param tamanio
	 * @return
	 */
	

	@GetMapping("/buscar")
	public ResponseEntity<Respuesta<?>> consultarSiniestros(@RequestParam Integer pagina,
			@RequestParam(defaultValue = "10") Integer tamanio,
			@RequestParam(value = "sort", defaultValue = "ID_SINIESTRO,desc") String[] sort) {
				//PageRequest.of(numeroPagina, sizePage,Sort.by(Direction.fromString(tipoOrden),order);
		Respuesta<?> response= new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
		
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		Pageable pageable=null;
		try {
			
			 pageable = PageRequest.of(pagina, tamanio, Sort.by(SiniestroController.convertSort(sort)));
			 response = siniestroServiceImpl.consultaGeneral(pageable,datosUsuarios);
		} catch (Exception e) {
			// TODO: handle exception
			response.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setError(true);
			response.setDatos(null);

			
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	private static List<Sort.Order> convertSort(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String string : sort) {
                String[] strings = string.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(strings[1]), strings[0]));
            }
        } else {
        	String []busqueda=filtroBusqueda(sort);
            
        	orders.add(new Sort.Order(Sort.Direction.fromString(busqueda[1]),busqueda[0]));
        }
        return orders;
    }
	
	private static String[] filtroBusqueda(String [] sort) {
		String []busqueda= new String[2];
    	switch(sort[0]) {
    	case "idSiniestro":
    		sort[0]="ID_SINIESTRO";
    		break;
    	case "numFolio":
    		sort[0]="NUM_FOLIO";
    		break;
    	default:
    			sort[0]="ID_SINIESTRO";
    			
    		break;
    	}
    	
    	if (sort.length==1) {
			busqueda[0]= sort[0];
			busqueda[1]="desc";
		}else {
			busqueda[0]= sort[0];
			busqueda[1]=sort[1];
		}
    
    	return busqueda;
	}

	/**
	 * busqueda por ecco de vehiculo siniestrado
	 * 
	 * @param pagina
	 * @param tamanio
	 * @param ordenar
	 * @param ecco
	 * @return
	 */
	@GetMapping("/buscar/{ecco}")
	public ResponseEntity<Respuesta<?>> consultarSiniestrosPorEcco(@RequestParam Integer pagina,
			@RequestParam(defaultValue = "10") Integer tamanio,
			@RequestParam(value = "sort", defaultValue = "ID_SINIESTRO,desc") String[] sort,
			@PathVariable String ecco) {
		Respuesta<?> response= new Respuesta<>();

		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
	
		Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by(SiniestroController.convertSort(sort)));
		response = siniestroServiceImpl.consultarSiniestroPorEcco(ecco, pageable,datosUsuarios);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarSiniestrosPorId(@PathVariable Integer id) {
		Respuesta<?> respuesta = siniestroServiceImpl.consultarSiniestroPorId(id);
		Respuesta<?> response= new Respuesta<>();

		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Respuesta<?>> crearSiniestro(@RequestParam Integer numFolio, @RequestParam String fechReporte,
			@RequestParam String fechSiniestro, @RequestParam Integer idReAsignacion, @RequestParam Integer idVehiculo,
			@RequestParam(required = false, defaultValue = "") String fechIngreso,
			@RequestParam(required = false, defaultValue = "") String fechSalida, @RequestParam String observaciones,
			@RequestParam Integer porcPerdida, @RequestParam String cveMatricula,
			@RequestParam("declaracion") MultipartFile declaracion, @RequestParam("reportAcc") MultipartFile reportAcc,
			@RequestParam("reporFoto") MultipartFile reporFoto) {
		Respuesta<?> response= new Respuesta<>();

		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		Siniestro siniestro = new Siniestro();

		siniestro.setNumFolio(numFolio);
		siniestro.setFecReporte(fechReporte);
		siniestro.setFecSiniestro(fechSiniestro);
		siniestro.setIdReAsignacion(idReAsignacion);
		siniestro.setIdVehiculo(idVehiculo);
		siniestro.setFecIngresoTaller(fechIngreso.equalsIgnoreCase("") ? null : fechIngreso);
		siniestro.setFecSalidaTaller(fechSalida.equalsIgnoreCase("") ? null : fechSalida);
		siniestro.setObservaciones(observaciones);
		siniestro.setCantidadPorcPerdidad(porcPerdida);
		siniestro.setCveMatricula(cveMatricula);
		siniestro.setDesRutaDeclaracion("");
		siniestro.setDesRutaReproteAcc("");
		siniestro.setDesRutaReporteFoto("");
		response = siniestroServiceImpl.guardarNuevoRegistro(siniestro, declaracion, reportAcc, reporFoto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{idSiniestro}")
	public ResponseEntity<Respuesta<?>> editarSiniestro(@PathVariable Integer idSiniestro,
			@RequestParam(required = false, defaultValue = "") String fechIngreso,
			@RequestParam(required = false, defaultValue = "") String fechSalida, @RequestParam String observaciones,
			@RequestParam Integer porcPerdida, @RequestParam( name = "declaracion", required = false) MultipartFile declaracion,
			@RequestParam(name="reportAcc", required = false) MultipartFile reportAcc, @RequestParam(name="reporFoto", required = false) MultipartFile reporFoto) {
		Respuesta<?> response= new Respuesta<>();

		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);

		
		Siniestro siniestro = new Siniestro();
		siniestro.setIdSiniestro(idSiniestro);
		siniestro.setFecIngresoTaller(fechIngreso.equalsIgnoreCase("") ? null : fechIngreso);
		siniestro.setFecSalidaTaller(fechSalida.equalsIgnoreCase("") ? null : fechSalida);
		siniestro.setObservaciones(observaciones);
		siniestro.setCantidadPorcPerdidad(porcPerdida);
		siniestro.setDesRutaDeclaracion("");
		siniestro.setDesRutaReproteAcc("");
		siniestro.setDesRutaReporteFoto("");

		response = siniestroServiceImpl.actualizarRegistro(siniestro, declaracion, reportAcc, reporFoto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Respuesta> delete(@PathVariable Integer id) {
		Respuesta<?> response= new Respuesta<>();

		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);

		response = siniestroServiceImpl.eliminarSiniestro(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Respuesta<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
		final Respuesta<Map<String, String>> response = new Respuesta<>();
		response.setMensaje("Hay errores en los datos, favor de revisar la informacion");
		response.setError(true);
		response.setCodigo(HttpStatus.BAD_REQUEST.value());
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String campo = ((FieldError) error).getField();
			String mensaje = error.getDefaultMessage();
			errors.put(campo, mensaje);
		});
		return response;
	}
	
	public Respuesta<?> denegado( String usuario ){

        Respuesta<?> response = new Respuesta<>();
        response.setError(false);
        response.setCodigo(HttpStatus.UNAUTHORIZED.value());
        response.setMensaje(usuario);

        return response;
    }

}

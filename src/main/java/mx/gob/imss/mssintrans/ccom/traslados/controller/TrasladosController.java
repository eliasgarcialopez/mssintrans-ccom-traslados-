package mx.gob.imss.mssintrans.ccom.traslados.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestBody;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.TrasladosTablaRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.TrasladoServiceImpl;

@RestController
@RequestMapping("/traslados")
public class TrasladosController {
	@Autowired
	private TrasladoServiceImpl trasladoServiceImpl;

	@SuppressWarnings("unchecked")
	@GetMapping("/buscar")
	public ResponseEntity<Respuesta<?>> consultaGeneral(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "tamanio", defaultValue = "10") int tamanio,
			@RequestParam(value = "sort", defaultValue = "desc") String sort,
			@RequestParam(defaultValue = "idSolicitud") String columna
			) {
		//Respuesta<?> response = new Respuesta<>();
		Respuesta<Page<TrasladosTablaRespuesta>> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		String nombreColumna =  nombreColumna(columna); 

		Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by(nombreColumna).descending());
		
		if(sort.equals("ASC")|| sort.equals("asc")) {
			pageable = PageRequest.of(pagina, tamanio, Sort.by(nombreColumna).ascending());
		}
	
		response = trasladoServiceImpl.consultaGeneral(pageable, datosUsuarios.getRol(),datosUsuarios.getIDOOAD());
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigo()));
		//return new ResponseEntity<>(HttpStatus.OK);
	}

	
	
	private String nombreColumna(String columna) {
		// TODO Auto-generated method stub

		String nombreColumna = null;
		try {
			
			switch(columna)
			{
				case "idSolicitud" : nombreColumna="ID_SOLICITUD";			      
				break; 
				
			   case "fecSolicitud" : nombreColumna="FEC_SOLICITUD";			      
			      break; 

			   case "timTransmision" : nombreColumna="TIM_SOLICITUD";			      
			   break; 

			   case "umSolicitante" : nombreColumna="ID_UNIDAD_SOLICITANTE";			      
			   break; 

			   case "umfAdscripcion" : nombreColumna="ID_UNIDAD_ADSCRIPCION";			      
			   break; 
			   
			   case "nombrePaciente":nombreColumna="DES_NOM_PACIENTE";			       
			      break;

			   case "desnsPaciente":nombreColumna="DES_NSS_PACIENTE";			       
			   break;
			      			    
			   default : 
			     nombreColumna="ID_SOLICITUD";
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}

		return nombreColumna;
	}
	
	@GetMapping("/{idTraslado}")
	public ResponseEntity<Respuesta<?>>consultarTraslado(@PathVariable Integer idTraslado ){
		Respuesta<?> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		response = trasladoServiceImpl.consultaPorId(idTraslado);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Respuesta<?>> crearTraslado(@RequestBody Traslado traslado) {
		Respuesta<?> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		
		response = trasladoServiceImpl.guardarNuevoRegistro(traslado,datosUsuarios.getMatricula());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{idSolicitud}")
	public ResponseEntity<Respuesta<?>> actualizarTraslado(@PathVariable Integer idSolicitud,
			@RequestBody Traslado traslado) {
		Respuesta<?> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		
		traslado.setIdSolicitud(idSolicitud);
		response = trasladoServiceImpl.actualizarRegistro(traslado,datosUsuarios.getMatricula());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{idSolicitud}")
	public ResponseEntity<Respuesta<?>> eliminarSolicituda(@PathVariable Integer idSolicitud) {
		Respuesta<?> response = new Respuesta<>();
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario.equals("denegado")) {
			response.setError(false);
			response.setCodigo(HttpStatus.UNAUTHORIZED.value());
			response.setMensaje(usuario);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		Gson gson = new Gson();
		DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		response = trasladoServiceImpl.eliminarTraslado(idSolicitud,datosUsuarios.getMatricula());
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

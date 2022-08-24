package mx.gob.imss.mssintrans.ccom.traslados.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.CodigoPostalServiceImpl;

//import mx.gob.imss.mssintetrans.unidades.dto.Respuesta;
//import mx.gob.imss.mssintetrans.unidades.service.impl.CodigoPostalServiceImpl;

@RestController
@RequestMapping("/codigo-postal")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class CodigoPostalController {
	@Autowired
	CodigoPostalServiceImpl codigoPostalServiceImpl;
	
	@GetMapping("/{cveCodigoPostal}")
	public ResponseEntity<Respuesta<?>> consultarPorCodigoPostal(@PathVariable String cveCodigoPostal) {
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

		response = codigoPostalServiceImpl.consultaGeneral(cveCodigoPostal);
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

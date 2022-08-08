package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.VehiculoServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class VehiculosController {
	
	private final VehiculoServiceImpl vehiculoService;
	
	/*@GetMapping("/buscar/{ecco}")
	public ResponseEntity<Respuesta<?>>consultaGeneralPorEcco(@RequestParam(defaultValue = "0", required = false) Integer pagina,
			@RequestParam(defaultValue = "10",required = false) Integer tamanio,@PathVariable String ecco){
		Pageable paginado= PageRequest.of(pagina, tamanio);
		Respuesta<?>response= vehiculoService.getEcco(ecco, paginado);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}*/
	
	@GetMapping("/{ecco}")
	public ResponseEntity<Respuesta<?>>consultaPorEcco(@PathVariable String ecco){
		 
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Respuesta<?>response=null;
	        if (usuario.equals("denegado")) {
	            response = denegado(usuario);
	            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	        }

	    Gson gson = new Gson();
	    DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
		response= vehiculoService.getEcco(ecco,datosUsuarios);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	public Respuesta<?> denegado( String usuario ){

        Respuesta<?> response = new Respuesta<>();
        response.setError(false);
        response.setCodigo(HttpStatus.UNAUTHORIZED.value());
        response.setMensaje(usuario);

        return response;
    }
}

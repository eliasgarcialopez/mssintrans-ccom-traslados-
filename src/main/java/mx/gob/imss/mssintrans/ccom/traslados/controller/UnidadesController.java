package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.UnidadesServiceImpl;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/unidades")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UnidadesController {
	
	@Autowired
	private final UnidadesServiceImpl unidadesServiceImpl;

	@GetMapping
	public ResponseEntity<Respuesta<?>>obtenerUnidadesPorOoAd(){
		String usuario = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Respuesta<?>response=null;
	        if (usuario.equals("denegado")) {
	            response = denegado(usuario);
	            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	        }

	    Gson gson = new Gson();
	    DatosUsuarioDTO datosUsuarios = gson.fromJson(usuario, DatosUsuarioDTO.class);
	    response=unidadesServiceImpl.consultarUnidadesPorCC(datosUsuarios);
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

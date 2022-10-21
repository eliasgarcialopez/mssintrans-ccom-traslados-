/**
 * 
 */
package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.VigenciaderechosRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.AccederServiceImpl;

/**
 * @author rarteaga
 *
 */
@RestController
@RequestMapping("/acceder")
public class AccederController {
	
	@Autowired
	AccederServiceImpl accederService;
	
	@GetMapping(value = "/vigenciaDerechosGrupoFamiliar")
	public ResponseEntity<?> getVigenciaDerechosGrupoFamiliar (@RequestParam(required = false) String nss) throws Exception {

		Respuesta<VigenciaderechosRespuesta> response = accederService.consultaVigenciaDerechosGrupoFamiliar(nss);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

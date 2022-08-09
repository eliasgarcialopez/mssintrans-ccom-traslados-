package mx.gob.imss.mssintrans.ccom.traslados.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.SiniestroServiceImpl;
import mx.gob.imss.mssintrans.ccom.traslados.service.impl.TrasladoServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping("/traslados")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class TrasladosController {

	private final TrasladoServiceImpl serviceImpl;
	
}

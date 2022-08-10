package mx.gob.imss.mssintrans.ccom.traslados.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;

import mx.gob.imss.mssintrans.ccom.traslados.service.impl.TrasladoServiceImpl;
import mx.gob.imss.mssintrans.ccom.traslados.util.FiltroTraslados;
import mx.gob.imss.mssintrans.ccom.traslados.util.TrasladosTablaRespuesta;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/traslados")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class TrasladosController {

	private final TrasladoServiceImpl trasladoServiceImpl;

	@GetMapping
	public ResponseEntity<Respuesta<?>> consultaGeneral(@RequestParam("pagina") int page,
			@RequestParam(value = "tamanio", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "idAseguradora,desc") String[] sort
			) {
		Respuesta<Page<TrasladosTablaRespuesta>> response = new Respuesta<>();
		Pageable pageable = null; 
				pageable=PageRequest.of(page, size, Sort.by(TrasladosController.convertSort(sort)));
	
	//	response = trasladoServiceImpl.consultaGeneral(pageable, null);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigo()));
	}

	private static List<Sort.Order> convertSort(String[] sort) {
		List<Sort.Order> orders = new ArrayList<>();
		if (sort[0].contains(",")) {
			for (String string : sort) {
				String[] strings = string.split(",");
				orders.add(new Sort.Order(Sort.Direction.fromString(strings[1]), strings[0]));
			}
		} else {
			orders.add(new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]));
		}
		return orders;
	}

	@PostMapping
	public ResponseEntity<Respuesta<?>> crearTraslado(@RequestBody Traslado traslado) {
		Respuesta<?> response = new Respuesta<>();
		response = trasladoServiceImpl.guardarNuevoRegistro(traslado);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Respuesta<?>> actualizarTraslado(@PathVariable Integer idSolicitud,
			@RequestBody Traslado traslado) {
		Respuesta<?> response = new Respuesta<>();
		traslado.setIdSolicitud(idSolicitud);
		response = trasladoServiceImpl.actualizarRegistro(traslado);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{idSolicitud}")
	public ResponseEntity<Respuesta<?>> eliminarSolicituda(@PathVariable Integer idSolicitud) {
		Respuesta<?> response = new Respuesta<>();
		response = trasladoServiceImpl.eliminarTraslado(idSolicitud);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

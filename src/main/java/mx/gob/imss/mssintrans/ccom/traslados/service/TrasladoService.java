package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;

public interface TrasladoService {

	<T>Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO);
	<T>Respuesta consultaPorId(Integer id);
	<T>Respuesta guardarNuevoRegistro(Traslado traslados);
	<T>Respuesta eliminarTraslado(Integer id);
	<T>Respuesta actualizarRegistro(Traslado siniestro);
}

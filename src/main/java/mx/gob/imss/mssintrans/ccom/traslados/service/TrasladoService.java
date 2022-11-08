package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;

public interface TrasladoService {

	<T>Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuario);
	<T>Respuesta consultaPorId(Integer id);
	<T>Respuesta guardarNuevoRegistro(Traslado traslados,String matricula);
	<T>Respuesta eliminarTraslado(Integer id,String matricula);
	<T>Respuesta actualizarRegistro(Traslado siniestro,String matricula);
}

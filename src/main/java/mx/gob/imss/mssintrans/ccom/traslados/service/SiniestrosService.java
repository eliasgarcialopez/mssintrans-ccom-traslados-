package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Siniestro;

public interface SiniestrosService {
	<T>Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO);
	<T>Respuesta consultarSiniestroPorId(Integer id);
	<T>Respuesta consultarSiniestroPorEcco(String ecco, Pageable pageable, DatosUsuarioDTO usuarioDTO);
	<T>Respuesta guardarNuevoRegistro(Siniestro siniestro, MultipartFile declaracion, MultipartFile reportAcc,MultipartFile reporFoto);
	<T>Respuesta eliminarSiniestro(Integer idSiniestro);
	<T>Respuesta actualizarRegistro(Siniestro siniestro,  MultipartFile declaracion, MultipartFile reportAcc,MultipartFile reporFoto);
	
	
	
}

package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;

public interface VehiculoService {
	<T>Respuesta getEcco (String ecco, DatosUsuarioDTO datosUsuarioDTO);
	
}

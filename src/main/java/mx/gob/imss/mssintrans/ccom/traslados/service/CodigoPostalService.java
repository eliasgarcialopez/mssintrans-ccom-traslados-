package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;


public interface CodigoPostalService {
	<T>Respuesta consultaGeneral (String codigoPostal);
}

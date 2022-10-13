package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenDocResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CensoDoctoresResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;

public interface CenDocService {
	
	Respuesta<CenDocResponse> crear(CenDocEntity cenDocEntity);
	Respuesta<CenDocResponse> actualizar(CenDocEntity cenDocEntity);
	Respuesta<CenDocResponse> consultaPorId(Integer idCenso);
	Respuesta<CenDocResponse> eliminar(Integer idCenso);
	Respuesta<Page<CensoDoctoresResponse>> obtenerCensoDoctores(Pageable pageable, String matricula, DatosUsuarioDTO datosUsuarios);

}

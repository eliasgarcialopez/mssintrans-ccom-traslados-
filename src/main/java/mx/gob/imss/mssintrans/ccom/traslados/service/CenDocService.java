package mx.gob.imss.mssintrans.ccom.traslados.service;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenDocResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;

public interface CenDocService {
	
	Respuesta<CenDocResponse> crear(CenDocEntity cenDocEntity);
	Respuesta<CenDocResponse> actualizar(CenDocEntity cenDocEntity);
	Respuesta<CenDocResponse> consultaPorId(Integer idCenso);

}

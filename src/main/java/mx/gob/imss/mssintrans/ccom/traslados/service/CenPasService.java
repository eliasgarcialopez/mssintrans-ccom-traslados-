package mx.gob.imss.mssintrans.ccom.traslados.service;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenPasResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;

public interface CenPasService {
	
	Respuesta<CenPasResponse> crear(CenPasEntity cenPasEntity);
	Respuesta<CenPasResponse> actualizar(CenPasEntity cenPasEntity);
	Respuesta<CenPasResponse> consultaPorId(Integer idCenso);
	Respuesta<CenPasResponse> eliminar(Integer idCenso);
	Respuesta<CenPasResponse> consultaPorNss(String desNss);
}

package mx.gob.imss.mssintrans.ccom.traslados.service;

import org.springframework.data.domain.Pageable;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenPasResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;

public interface CenPasService {
	
	Respuesta<CenPasResponse> crear(CenPasEntity cenPasEntity);
	Respuesta<CenPasResponse> actualizar(CenPasEntity cenPasEntity);
	Respuesta<CenPasResponse> consultaPorId(Integer idCenso);
	Respuesta<CenPasResponse> eliminar(Integer idCenso, String matriula);
	Respuesta<CenPasResponse> consultaPorNss(String desNss);
	<T>Respuesta consultaGeneral(Pageable pageable);
	
}

package mx.gob.imss.mssintrans.ccom.traslados.service;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.VigenciaderechosRespuesta;

public interface AccederService {
	
	Respuesta<VigenciaderechosRespuesta> consultaVigenciaDerechosGrupoFamiliar(String nss);

}

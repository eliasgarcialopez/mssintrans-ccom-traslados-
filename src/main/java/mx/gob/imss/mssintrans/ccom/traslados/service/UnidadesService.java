package mx.gob.imss.mssintrans.ccom.traslados.service;



import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;

public interface UnidadesService {
	<T>Respuesta consultarUnidadesPorCC(DatosUsuarioDTO usuario );
}

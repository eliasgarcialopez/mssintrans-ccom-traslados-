package mx.gob.imss.mssintrans.ccom.traslados.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.UnidadesRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.UnidadesEntity;



@Mapper
public interface UnidadesMapper {
	UnidadesMapper INSTANCE = Mappers.getMapper(UnidadesMapper.class);
	
	
	/*UnidadesEntity JsonAEntity(Unidad unidad);
	Unidad entityAJson(UnidadesEntity unidad);
	
	UnidadResponse formatearListaResponse ( UnidadesEntity unidad );*/
	
	List<UnidadesRespuesta> formatearListaResponse ( List<UnidadesEntity> consulta );
//	List<UnidadCatalogoRespuesta> formatearListaResponse3 ( List<UnidadesEntity> consulta );
}
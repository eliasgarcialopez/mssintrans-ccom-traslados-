package mx.gob.imss.mssintrans.ccom.traslados.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;

@Mapper
public interface TrasladosMapper {

	TrasladosMapper INSTANCE=Mappers.getMapper(TrasladosMapper.class);
	
	TrasladoEntity JsonAEntity(Traslado traslado);
	
	Traslado entityAJson(TrasladoEntity trasladoEntity);
}

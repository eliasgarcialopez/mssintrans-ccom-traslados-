package mx.gob.imss.mssintrans.ccom.traslados.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.TrasladoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.TrasladosTablaRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladosEntity;

@Mapper
public interface TrasladosMapper {

	TrasladosMapper INSTANCE=Mappers.getMapper(TrasladosMapper.class);
	
	TrasladoEntity JsonAEntity(Traslado traslado);
	
	Traslado entityAJson(TrasladoEntity trasladoEntity);
	
	TrasladoResponse entityASJson(TrasladosEntity trasladosEntity);
	
	List<TrasladosTablaRespuesta> formatLista(List<TrasladosEntity> trasladosEntity);
}

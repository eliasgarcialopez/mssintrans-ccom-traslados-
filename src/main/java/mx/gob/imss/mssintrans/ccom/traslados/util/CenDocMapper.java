package mx.gob.imss.mssintrans.ccom.traslados.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenDocResponse;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;

@Mapper
public interface CenDocMapper {
	
	CenDocMapper INSTANCE = Mappers.getMapper(CenDocMapper.class);
	
	CenDocEntity JsonAEntity(CenDocResponse cenDocResponse);
	
	CenDocResponse entityAJson(CenDocEntity cenDocEntity);
	
	

}

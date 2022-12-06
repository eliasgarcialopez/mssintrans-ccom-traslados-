package mx.gob.imss.mssintrans.ccom.traslados.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CenPasResponse;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;

@Mapper
public interface CenPasMapper {
	
	CenPasMapper INSTANCE = Mappers.getMapper(CenPasMapper.class);
	
	CenPasEntity JsonAEntity(CenPasResponse cenPasResponse);
	
	CenPasResponse entityAJson(CenPasEntity cenDocEntity);

}

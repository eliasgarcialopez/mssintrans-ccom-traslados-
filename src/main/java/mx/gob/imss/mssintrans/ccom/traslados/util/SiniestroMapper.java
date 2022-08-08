package mx.gob.imss.mssintrans.ccom.traslados.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.Siniestro;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroTablaResponse;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestrosEntity;


@Mapper
public interface SiniestroMapper {
	SiniestroMapper INSTANCE = Mappers.getMapper(SiniestroMapper.class);

	SiniestrosEntity JsonAEntity(Siniestro siniestro);

	Siniestro entityAJson(SiniestrosEntity siniestro);
	
	SiniestroResponse entityAJsonFormat ( SiniestrosEntity siniestro );

	SiniestroTablaResponse entityAJsonFormatTabla ( SiniestrosEntity siniestro );
	
	List<SiniestroResponse> formatearLista ( List<SiniestrosEntity> consulta );
	
	List<SiniestroTablaResponse> formatearListaTabla ( List<SiniestrosEntity> consulta );
}

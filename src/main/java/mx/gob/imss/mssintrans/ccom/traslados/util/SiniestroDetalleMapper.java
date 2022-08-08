package mx.gob.imss.mssintrans.ccom.traslados.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroDetalleRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestroDetalleEntity;


@Mapper
public interface SiniestroDetalleMapper {
	SiniestroDetalleMapper INSTANCE = Mappers.getMapper(SiniestroDetalleMapper.class);


	SiniestroDetalleRespuesta entityAJson(SiniestroDetalleEntity siniestro);
}

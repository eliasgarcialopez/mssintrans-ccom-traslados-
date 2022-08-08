package mx.gob.imss.mssintrans.ccom.traslados.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.mssintrans.ccom.traslados.dto.VehiculosRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosArrendadosEntity;

@Mapper
public interface VehiculosMapper {
	VehiculosMapper INSTANCE = Mappers.getMapper(VehiculosMapper.class);
	VehiculosRespuesta AsJson (VehiculosArrendadosEntity vehiculosArrendadosEntity);
	List<VehiculosRespuesta> formatearLista ( List<VehiculosArrendadosEntity> consulta );

}

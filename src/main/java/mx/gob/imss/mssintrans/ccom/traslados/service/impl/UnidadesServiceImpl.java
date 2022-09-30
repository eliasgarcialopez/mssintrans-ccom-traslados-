package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.UnidadesRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.UnidadesEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.UnidadesRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.UnidadesService;
import mx.gob.imss.mssintrans.ccom.traslados.util.UnidadesMapper;

@Transactional(rollbackOn = { SQLException.class, IOException.class })
@Service
@Slf4j
public class UnidadesServiceImpl implements UnidadesService{
	
	@Autowired
	private UnidadesRepository unidadesRepository;
	
	@Override
	public <T> Respuesta consultarUnidadesPorCC(DatosUsuarioDTO usuario) {
	Respuesta<T> respuesta = new Respuesta<>();
	List<UnidadesEntity> unidadesEntity = new ArrayList<>();
	try {
		if(usuario.equals("Administrador") || usuario.equals("Normativo") || usuario.IDOOAD == 9 || usuario.IDOOAD  == 39 ){
			unidadesEntity=unidadesRepository.consultaGeneral();
		}else{
			unidadesEntity=unidadesRepository.consultaPorOoad(usuario.getIDOOAD());
		}
		if (unidadesEntity.isEmpty()) {
			throw new Exception("No se encontro el registro solicitado");
		}
		
		} catch (Exception e) {
		
		respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
		respuesta.setError(true);
		respuesta.setMensaje(e.getMessage());
		return respuesta;
	}
	
//	SiniestroDetalleRespuesta siniestroJson=SiniestroDetalleMapper.INSTANCE.entityAJson(detalleEntity);
	List<UnidadesRespuesta> unidadesJson= UnidadesMapper.INSTANCE.formatearListaResponse(unidadesEntity);
	
	respuesta.setCodigo(HttpStatus.OK.value());
	respuesta.setError(false);
	respuesta.setMensaje("Exito");
	respuesta.setDatos((T) unidadesJson);

	return respuesta;
	}

	

}

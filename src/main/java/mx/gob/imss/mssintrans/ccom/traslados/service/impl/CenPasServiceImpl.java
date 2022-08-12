package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CenPasResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenPasRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenPasService;
import mx.gob.imss.mssintrans.ccom.traslados.util.CenPasMapper;

@Transactional(rollbackOn = SQLException.class)
@Service
@Slf4j
public class CenPasServiceImpl implements CenPasService {
	
	@Autowired
	private CenPasRepository cenPasRepository;

	@Transactional(rollbackOn = SQLException.class)
	@Override
	public Respuesta<CenPasResponse> crear(CenPasEntity cenPasEntity) {

		Respuesta<CenPasResponse> respuesta = new Respuesta<>();
		CenPasResponse response;
		
		cenPasEntity.setIndActivo(1);
		cenPasEntity.setIndSistema(0);
		
		Date d=new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		cenPasEntity.setFecAlta( formato.format(d) );
		
		try {
			
			log.info("Creando Nuevo Censo de Doctores");
			
			cenPasEntity = cenPasRepository.saveAndFlush(cenPasEntity);
			
			response = CenPasMapper.INSTANCE.entityAJson(cenPasEntity);
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos(response);
			
		} catch (Exception e) {
			
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			
		}
		
		return respuesta;
	}

	@Transactional(rollbackOn = SQLException.class)
	@Override
	public Respuesta<CenPasResponse> actualizar(CenPasEntity cenPasEntity) {

		Respuesta<CenPasResponse> respuesta = new Respuesta<>();
		
		try {
			
			cenPasRepository.actualizar(cenPasEntity.getDesNss(), cenPasEntity.getDesEstatus(), cenPasEntity.getLunes(),
					cenPasEntity.getMartes(), cenPasEntity.getMiercoles(), cenPasEntity.getJueves(), cenPasEntity.getViernes(),
					cenPasEntity.getSabado(), cenPasEntity.getDomingo(), cenPasEntity.getCveMatricula(), cenPasEntity.getIdCenso());
			
		} catch (Exception e) {
			 log.error("Ha ocurrido un error al actualizar el mantenimiento", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		return respuesta;
		
	}

	@Override
	public Respuesta<CenPasResponse> consultaPorId(Integer idCenso) {
		
		Respuesta<CenPasResponse> respuesta = new Respuesta<>();
		CenPasResponse response;
		CenPasEntity cenPasEntity;
		
		try {
			
			cenPasEntity = cenPasRepository.consultaPorId(idCenso);
			response = CenPasMapper.INSTANCE.entityAJson(cenPasEntity);
		
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos(response);
		
		return respuesta;
	}

}

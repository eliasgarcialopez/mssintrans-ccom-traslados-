package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CenDocResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenDocRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenDocService;
import mx.gob.imss.mssintrans.ccom.traslados.util.CenDocMapper;

@Transactional(rollbackOn = SQLException.class)
@Service
@Slf4j
public class CenDocServiceImpl implements CenDocService {

	@Autowired
	private CenDocRepository cenDocRepository;
	
	@Transactional(rollbackOn = SQLException.class)
	@Override
	public Respuesta<CenDocResponse> crear(CenDocEntity cenDocEntity) {
		
		Respuesta<CenDocResponse> respuesta = new Respuesta<>();
		CenDocResponse response;
		
		cenDocEntity.setIndActivo(1);
		cenDocEntity.setIndSistema(0);
		
		Date d=new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		cenDocEntity.setFecAlta( formato.format(d) );
		
		try {
			
			log.info("Creando Nuevo Censo de Doctores");
			
			CenDocEntity registro = cenDocRepository.consultaPorMat(cenDocEntity.getMatriculaDoctor());
			
			if(registro==null) {
			
				cenDocEntity = cenDocRepository.saveAndFlush(cenDocEntity);
			
				response = CenDocMapper.INSTANCE.entityAJson(cenDocEntity);
				respuesta.setCodigo(HttpStatus.OK.value());
				respuesta.setError(false);
				respuesta.setMensaje("Exito");
				respuesta.setDatos(response);
			
			} else {
				
				respuesta.setCodigo(HttpStatus.BAD_REQUEST.value());
				respuesta.setError(true);
				respuesta.setMensaje("Matricula repetida, el medico ya existe");
				
			}
			
		} catch (Exception e) {
			
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			
		}
		
		return respuesta;
	}

	@Transactional(rollbackOn = SQLException.class)
	@Override
	public Respuesta<CenDocResponse> actualizar(CenDocEntity cenDocEntity) {
		Respuesta<CenDocResponse> respuesta = new Respuesta<>();
		
		try {
			
			CenDocEntity registro = cenDocRepository.consultaPorMat(cenDocEntity.getCveMatricula());
			
			if(registro==null) {
				
				cenDocRepository.actualizar(cenDocEntity.getIdUnidad(), cenDocEntity.getDesEstatus(),
						cenDocEntity.getCveMatricula(), cenDocEntity.getIdCenso());
				
				respuesta.setCodigo(HttpStatus.OK.value());
				respuesta.setError(false);
				respuesta.setMensaje("Exito");
			
			} else {
				
				respuesta.setCodigo(HttpStatus.BAD_REQUEST.value());
				respuesta.setError(true);
				respuesta.setMensaje("Matricula repetida, el medico ya existe");
				
			}
			
			
			
		} catch (Exception e) {
			 log.error("Ha ocurrido un error al actualizar el mantenimiento", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
		}
		
		return respuesta;
	}

	@Override
	public Respuesta<CenDocResponse> consultaPorId(Integer idCenso) {
		
		Respuesta<CenDocResponse> respuesta = new Respuesta<>();
		CenDocResponse response;
		CenDocEntity cenDocEntity;
		
		try {
			
			cenDocEntity = cenDocRepository.consultaPorId(idCenso);
			
			if(cenDocEntity==null) {
				respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
				respuesta.setError(true);
				respuesta.setMensaje("Doctor no encontrado en el Censo.");
				return respuesta;
			}
			
			response = CenDocMapper.INSTANCE.entityAJson(cenDocEntity);
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
	public Respuesta<CenDocResponse> eliminar(Integer idCenso) {
		Respuesta<CenDocResponse> respuesta = new Respuesta<>();
		
		try {
			
			cenDocRepository.eliminar(idCenso);
			cenDocRepository.flush();
			
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			
		} catch (Exception e) {
			Log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
		}
		
		return respuesta;
	}

}

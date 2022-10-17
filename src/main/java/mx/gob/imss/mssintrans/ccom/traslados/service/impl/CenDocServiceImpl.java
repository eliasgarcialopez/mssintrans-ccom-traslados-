package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CenDocResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.CensoDoctoresResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenDocRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.UnidadesAdscripcionRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.CenDocService;
import mx.gob.imss.mssintrans.ccom.traslados.util.CenDocMapper;

@Transactional(rollbackOn = SQLException.class)
@Service
@Slf4j
public class CenDocServiceImpl implements CenDocService {

	@Autowired
	private CenDocRepository cenDocRepository;
	
	@Autowired
	private UnidadesAdscripcionRepository unidadesAdscripcionRepository;
	
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

	@Override
	public Respuesta<Page<CensoDoctoresResponse>> obtenerCensoDoctores(Pageable pageable, String matricula, DatosUsuarioDTO datosUsuarios) {
		Respuesta<Page<CensoDoctoresResponse>> respuesta = new Respuesta<>();
		final Page<CenDocEntity> result;
		List<CensoDoctoresResponse> content = null;
		try {
			if(matricula != null && matricula != "") {
				result = datosUsuarios.rol.equals("Administrador") || datosUsuarios.rol.equals("Normativo") || datosUsuarios.IDOOAD == 9 || datosUsuarios.IDOOAD  == 39 ?
						cenDocRepository.consultaGeneral(matricula, pageable) : cenDocRepository.consultaGeneralPorOoad(matricula, datosUsuarios.IDOOAD, pageable);
				
				if (!result.isEmpty()) {
					
					content = new ArrayList<>();
					for (CenDocEntity cenDocEntity : result.getContent()) {
						CensoDoctoresResponse censoDoctoresResponse = CenDocMapper.INSTANCE.entityToResponse(cenDocEntity);
						censoDoctoresResponse.setNombreUnidad(unidadesAdscripcionRepository.findUnidadesAdscripcionEntityByIdUnidadAdscripcion(censoDoctoresResponse.getIdUnidad()).trim());
						content.add(censoDoctoresResponse);
					}
					
					Page<CensoDoctoresResponse> pageTabla = new PageImpl<>(content, pageable,result.getTotalElements());
					
					respuesta.setDatos(pageTabla);
					respuesta.setError(false);
					respuesta.setMensaje("Exito");
					respuesta.setCodigo(HttpStatus.OK.value());
					
				} else {
					respuesta.setMensaje("Exito");
					respuesta.setCodigo(HttpStatus.NO_CONTENT.value());
				}
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
		}
		return respuesta;
	}

}

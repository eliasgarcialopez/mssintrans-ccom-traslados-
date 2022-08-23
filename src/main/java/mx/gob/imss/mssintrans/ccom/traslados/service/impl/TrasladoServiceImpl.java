package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenDocRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CenPasRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.TrasladoRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.TrasladosRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.TrasladoService;
import mx.gob.imss.mssintrans.ccom.traslados.util.TrasladosMapper;

@Transactional(rollbackOn = { SQLException.class, IOException.class })
@Service
@Slf4j
public class TrasladoServiceImpl implements TrasladoService {

	@Autowired
	private TrasladoRepository trasladoRepository;

	@Autowired
	private TrasladosRepository trasladosRepository;
	
	@Autowired
	private CenDocRepository cenDocRepository;
	
	@Autowired
	private CenPasRepository cenPasRepository;
	
	private static final Logger log = LoggerFactory.getLogger(TrasladoServiceImpl.class);

	@Override
	public <T> Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO) {
		Respuesta<T> respuesta = new Respuesta<>();
		try {
			Page consultaGeneral= trasladosRepository.consultaGeneral(pageable);
			log.info("{}",consultaGeneral.getContent());
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		//respuesta.setDatos((T) siniestroJson);

		return respuesta;
	}

	@Override
	public <T> Respuesta consultaPorId(Integer id) {
		Respuesta<T> respuesta = new Respuesta<>();
		try {
			TrasladosEntity trasladosEntity= trasladosRepository.consultaPorId(id);
			log.info("traslado {}",trasladosEntity);
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		//respuesta.setDatos((T) siniestroJson);

		return respuesta;
	}

	@Override
	public <T> Respuesta guardarNuevoRegistro(Traslado traslado,String matricula) {
		Respuesta<T>respuesta= new Respuesta<>();
		TrasladoEntity nuevoTraslado=null;
		
		try {
			
			CenPasEntity cenPasEntity= new CenPasEntity();
			CenDocEntity cenDoctorEntity= new CenDocEntity();
			// traslados
			TrasladoEntity trasladoEntity= TrasladosMapper.INSTANCE.JsonAEntity(traslado);
			trasladoEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			trasladoEntity.setIndActivo(1);
			trasladoEntity.setIndSistema(1);
			trasladoEntity.setCveMatricula(matricula);
			nuevoTraslado=trasladoRepository.saveAndFlush(trasladoEntity);
			
			//censo paciente
			if (cenPasRepository.consultaPorNss(trasladoEntity.getDesnsPaciente())== null) {
				cenPasEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenPasEntity.setDesNss(nuevoTraslado.getDesnsPaciente());
				cenPasEntity.setNombre(nuevoTraslado.getDesNomPaciente());
				cenPasEntity.setIndActivo(1);
				cenPasEntity.setIndSistema(1);
				cenPasEntity.setCveMatricula(matricula);
				cenPasRepository.saveAndFlush(cenPasEntity);

			}
			
			// censo doctores
			if (cenDocRepository.consultaPorMat(trasladoEntity.getNumMatriculaRecibe()) == null) {
				cenDoctorEntity.setMatriculaDoctor(traslado.getNumMatriculaRecibe());
				cenDoctorEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenDoctorEntity.setNombreDoctor(traslado.getDesNomDoctorRecibe());
				cenDoctorEntity.setIndActivo(1);
				cenDoctorEntity.setIndSistema(1);
				cenDoctorEntity.setCveMatricula(matricula);
				cenDocRepository.saveAndFlush(cenDoctorEntity);
			}
			
			cenDoctorEntity = null;
			if (cenDocRepository.consultaPorMat(trasladoEntity.getNumMatriculaAutoriza()) == null) {
				cenDoctorEntity=  new CenDocEntity();
				cenDoctorEntity.setMatriculaDoctor(traslado.getNumMatriculaAutoriza());
				cenDoctorEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenDoctorEntity.setNombreDoctor(traslado.getDesNomDoctorAutoriza());
				cenDoctorEntity.setIndActivo(1);
				cenDoctorEntity.setIndSistema(1);
				cenDoctorEntity.setCveMatricula(matricula);
				cenDocRepository.saveAndFlush(cenDoctorEntity);
			}
			
			
		} catch (Exception e) {
			log.debug("error {}", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		Traslado trasladoResponse=TrasladosMapper.INSTANCE.entityAJson(nuevoTraslado);
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T)trasladoResponse );
		
		return respuesta;
	}

	@Override
	public <T> Respuesta eliminarTraslado(Integer id) {
		Respuesta<T>respuesta= new Respuesta<>();
		TrasladoEntity trasladoEntity=null;
		TrasladoEntity elimandoEntity=null;
		
		try {
			trasladoEntity=trasladoRepository.findByIdSolicitudAndIndActivoEquals(id, 1).orElseThrow(()-> new Exception("No se encontro el traslado"));
			trasladoEntity.setIndActivo(0);
			trasladoEntity.setFecBaja(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			elimandoEntity=trasladoRepository.save(trasladoEntity);
			
		} catch (Exception e) {
			log.debug("error {}", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
	//	Traslado trasladoResponse=TrasladosMapper.INSTANCE.entityAJson(elimandoEntity);
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		//respuesta.setDatos((T)trasladoResponse);
		
		return respuesta;
	}

	@Override
	public <T> Respuesta actualizarRegistro(Traslado traslado,String matricula) {
		Respuesta<T>respuesta= new Respuesta<>();
		TrasladoEntity busquedaTraslado=null;
		TrasladoEntity actualizadoTraslado=null;
		
		try {
			busquedaTraslado=trasladoRepository.findByIdSolicitudAndIndActivoEquals(traslado.getIdSolicitud(), 1).orElseThrow(()-> new Exception("No se encontro el traslado"));
			busquedaTraslado.setDesDiagnostico(traslado.getDesDiagnostico());
			busquedaTraslado.setDesEstatusSolicitud(traslado.getDesEstatusSolicitud());
			busquedaTraslado.setDesmotivoCancelacion(traslado.getDesmotivoCancelacion());
			busquedaTraslado.setFecActualizacion(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			busquedaTraslado.setCveMatricula(matricula);
			actualizadoTraslado=trasladoRepository.save(busquedaTraslado);
		} catch (Exception e) {
			log.debug("error {}", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		Traslado trasladoResponse=TrasladosMapper.INSTANCE.entityAJson(actualizadoTraslado);
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T)trasladoResponse );
		
		return respuesta;
	}

}

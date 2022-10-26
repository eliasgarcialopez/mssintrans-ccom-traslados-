package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.dto.TrasladoResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.TrasladosTablaRespuesta;
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> Respuesta consultaGeneral(Pageable pageable, String usuario, Integer idooad) {
		Respuesta<T> respuesta = new Respuesta<>();
		Page consultaGeneral =null;
		List<TrasladosTablaRespuesta> tablaResponse=null;
		try {
			consultaGeneral = trasladosRepository.consultaGeneralOOAD(pageable, idooad);
			if(usuario.equals("Administrador") || usuario.equals("Normativo") || idooad == 9 || idooad == 39 ){
				consultaGeneral = trasladosRepository.consultaGeneral(pageable);
			}
			tablaResponse= TrasladosMapper.INSTANCE.formatLista(consultaGeneral.getContent());
			
			
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		Page<TrasladosTablaRespuesta> objetoMapeado = new PageImpl<>(tablaResponse, pageable,
	                    consultaGeneral.getTotalElements());
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T) objetoMapeado);

		return respuesta;
	}

	@Override
	public <T> Respuesta consultaPorId(Integer id) {
		Respuesta<T> respuesta = new Respuesta<>();
		TrasladoResponse trasladoResponse = null;
		TrasladosEntity trasladosEntity =null;
		try {
			trasladosEntity = trasladosRepository.consultaPorId(id);
			trasladoResponse= TrasladosMapper.INSTANCE.entityASJson(trasladosEntity);
			if(trasladosEntity == null) {
				throw new Exception("No se encontro el traslado");
			}
			
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		trasladoResponse.setCodigoPostal(trasladosEntity.getCodigoPostal() == null ?"":trasladoResponse.getCodigoPostal());
		trasladoResponse.setNomEstado(trasladosEntity.getNomEstado() == null ?"":trasladoResponse.getNomEstado());
		trasladoResponse.setNomMunicipio(trasladosEntity.getNomMunicipio() == null ?"":trasladoResponse.getNomMunicipio());
		//trasladoResponse.setNumTelDestino(trasladosEntity.getNumTelDestino() == null ?0:trasladoResponse.getNumTelDestino());
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
	    respuesta.setDatos((T) trasladoResponse);

		return respuesta;
	}

	@Override
	public <T> Respuesta guardarNuevoRegistro(Traslado traslado, String matricula) {
		Respuesta<T> respuesta = new Respuesta<>();
		TrasladoEntity nuevoTraslado = null;

		try {

			CenPasEntity cenPasEntity = new CenPasEntity();
			CenDocEntity cenDoctorEntity = new CenDocEntity();
			// traslados
			if (traslado.getIdCodigoPostal() == 0 || traslado.getIdCodigoPostal() == null) {
				traslado.setIdCodigoPostal(null);
			}
			
			if (traslado.getIdMunicipio() == 0 || traslado.getIdMunicipio() == null) {
				traslado.setIdMunicipio(null);
			}

			/*if (traslado.getNumTelDestino() == 0 || traslado.getNumTelDestino() == null) {
				traslado.setNumTelDestino(null);
			}*/
			
			TrasladoEntity trasladoEntity = TrasladosMapper.INSTANCE.JsonAEntity(traslado);
			trasladoEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			trasladoEntity.setIndActivo(1);
			trasladoEntity.setIndSistema(1);
			trasladoEntity.setCveMatricula(matricula);
			nuevoTraslado = trasladoRepository.saveAndFlush(trasladoEntity);
			
			// censo paciente
			if (cenPasRepository.consultaPorNss(nuevoTraslado.getDesnsPaciente()) == null) {
				cenPasEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenPasEntity.setDesNss(nuevoTraslado.getDesnsPaciente());
				cenPasEntity.setNombre(nuevoTraslado.getDesNomPaciente());
				cenPasEntity.setIndActivo(1);
				cenPasEntity.setIndSistema(1);
				cenPasEntity.setCveMatricula(matricula);
				cenPasRepository.saveAndFlush(cenPasEntity);

			}

			// censo doctores
			if (cenDocRepository.consultaPorMat(nuevoTraslado.getNumMatriculaRecibe()) == null) {
				cenDoctorEntity.setMatriculaDoctor(nuevoTraslado.getNumMatriculaRecibe());
				cenDoctorEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenDoctorEntity.setNombreDoctor(traslado.getDesNomDoctorRecibe());
				cenDoctorEntity.setIndActivo(1);
				cenDoctorEntity.setIndSistema(1);
				cenDoctorEntity.setCveMatricula(matricula);
				cenDocRepository.saveAndFlush(cenDoctorEntity);
			}

			cenDoctorEntity = null;
			if (cenDocRepository.consultaPorMat(nuevoTraslado.getNumMatriculaAutoriza()) == null) {
				cenDoctorEntity = new CenDocEntity();
				cenDoctorEntity.setMatriculaDoctor(nuevoTraslado.getNumMatriculaAutoriza());
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
		
		TrasladosEntity trasladosEntity = trasladosRepository.consultaPorId(nuevoTraslado.getIdSolicitud());
		TrasladoResponse trasladoResponse = TrasladosMapper.INSTANCE.entityASJson(trasladosEntity);
		trasladoResponse.setCodigoPostal(trasladosEntity.getCodigoPostal() == null ?"":trasladoResponse.getCodigoPostal());
		trasladoResponse.setNomEstado(trasladosEntity.getNomEstado() == null ?"":trasladoResponse.getNomEstado());
		trasladoResponse.setNomMunicipio(trasladosEntity.getNomMunicipio() == null ?"":trasladoResponse.getNomMunicipio());
		//trasladoResponse.setNumTelDestino(trasladosEntity.getNumTelDestino() == null ?0:trasladoResponse.getNumTelDestino());
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T) trasladoResponse);

		return respuesta;
	}

	@Override
	public <T> Respuesta eliminarTraslado(Integer id,String matricula) {
		Respuesta<T> respuesta = new Respuesta<>();
		TrasladoEntity trasladoEntity = null;
		TrasladoEntity elimandoEntity = null;

		try {
			trasladoEntity = trasladoRepository.findByIdSolicitudAndIndActivoEquals(id, 1)
					.orElseThrow(() -> new Exception("No se encontro el traslado"));
			trasladoEntity.setIndActivo(0);
			trasladoEntity.setFecBaja(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			trasladoEntity.setCveMatricula(matricula);
			elimandoEntity = trasladoRepository.save(trasladoEntity);

		} catch (Exception e) {
			log.debug("error {}", e.getMessage());
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
	public <T> Respuesta actualizarRegistro(Traslado traslado, String matricula) {
		Respuesta<T> respuesta = new Respuesta<>();
		TrasladoEntity busquedaTraslado = null;
		TrasladoEntity actualizadoTraslado = null;
		CenDocEntity cenDoctorEntity = new CenDocEntity();
		try {
			busquedaTraslado = trasladoRepository.findByIdSolicitudAndIndActivoEquals(traslado.getIdSolicitud(), 1)
					.orElseThrow(() -> new Exception("No se encontro el traslado"));
			busquedaTraslado.setDesDiagnostico(traslado.getDesDiagnostico());
			busquedaTraslado.setCveOrigen(traslado.getCveOrigen());
			busquedaTraslado.setDesAreaOrigen(traslado.getDesAreaOrigen());
			busquedaTraslado.setNumCamaOrigen(traslado.getNumCamaOrigen());
			busquedaTraslado.setCveDestino(traslado.getCveDestino());
			busquedaTraslado.setDesAreaDestino(traslado.getDesAreaDestino());
			busquedaTraslado.setNumCamaDestino(traslado.getNumCamaDestino());
			busquedaTraslado.setDesEstatusSolicitud(traslado.getDesEstatusSolicitud());
			busquedaTraslado.setDesmotivoCancelacion(traslado.getDesmotivoCancelacion());
			busquedaTraslado.setNumMatriculaRecibe(traslado.getNumMatriculaRecibe());
			busquedaTraslado.setNumMatriculaAutoriza(traslado.getNumMatriculaAutoriza());
			busquedaTraslado.setFecActualizacion(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			busquedaTraslado.setCveMatricula(matricula);
			busquedaTraslado.setDesMunicipio(traslado.getDesMunicipio());
			busquedaTraslado.setDesEstado(traslado.getDesEstado());
			busquedaTraslado.setDesCiudad(traslado.getDesCiudad());
			busquedaTraslado.setCveCodPostal(traslado.getCveCodPostal());
			busquedaTraslado.setDesFirma(traslado.getDesFirma());
			actualizadoTraslado = trasladoRepository.save(busquedaTraslado);
			
			// censo doctores
			if (cenDocRepository.consultaPorMat(busquedaTraslado.getNumMatriculaRecibe()) == null) {
				cenDoctorEntity.setMatriculaDoctor(busquedaTraslado.getNumMatriculaRecibe());
				cenDoctorEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				cenDoctorEntity.setNombreDoctor(traslado.getDesNomDoctorRecibe());
				cenDoctorEntity.setIndActivo(1);
				cenDoctorEntity.setIndSistema(1);
				cenDoctorEntity.setCveMatricula(matricula);
				cenDocRepository.saveAndFlush(cenDoctorEntity);
			}

			cenDoctorEntity = null;
			if (cenDocRepository.consultaPorMat(busquedaTraslado.getNumMatriculaAutoriza()) == null) {
				cenDoctorEntity = new CenDocEntity();
				cenDoctorEntity.setMatriculaDoctor(busquedaTraslado.getNumMatriculaAutoriza());
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
		TrasladosEntity trasladosEntity = trasladosRepository.consultaPorId(actualizadoTraslado.getIdSolicitud());
		TrasladoResponse trasladoResponse = TrasladosMapper.INSTANCE.entityASJson(trasladosEntity);
		trasladoResponse.setCodigoPostal(trasladosEntity.getCodigoPostal() == null ?"":trasladoResponse.getCodigoPostal());
		trasladoResponse.setNomEstado(trasladosEntity.getNomEstado() == null ?"":trasladoResponse.getNomEstado());
		trasladoResponse.setNomMunicipio(trasladosEntity.getNomMunicipio() == null ?"":trasladoResponse.getNomMunicipio());
		//trasladoResponse.setNumTelDestino(trasladosEntity.getNumTelDestino() == null ?0:trasladoResponse.getNumTelDestino());
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T) trasladoResponse);

		return respuesta;
	}

}

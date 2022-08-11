package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslado;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.TrasladoRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.TrasladoService;
import mx.gob.imss.mssintrans.ccom.traslados.util.TrasladosMapper;

@Transactional(rollbackOn = { SQLException.class, IOException.class })
@Service
@Slf4j
public class TrasladoServiceImpl implements TrasladoService {

	@Autowired
	private TrasladoRepository trasladoRepository;
	
	@Override
	public <T> Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO) {
		Respuesta<T> respuesta = new Respuesta<>();
		try {
			
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
	public <T> Respuesta guardarNuevoRegistro(Traslado traslado) {
		Respuesta<T>respuesta= new Respuesta<>();
		TrasladoEntity nuevoTraslado=null;
		
		try {
			TrasladoEntity trasladoEntity= TrasladosMapper.INSTANCE.JsonAEntity(traslado);
			trasladoEntity.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			trasladoEntity.setIndActivo(1);
			trasladoEntity.setIndSistema(1);
			nuevoTraslado=trasladoRepository.saveAndFlush(trasladoEntity);
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
	public <T> Respuesta actualizarRegistro(Traslado traslado) {
		Respuesta<T>respuesta= new Respuesta<>();
		TrasladoEntity busquedaTraslado=null;
		TrasladoEntity actualizadoTraslado=null;
		
		try {
			busquedaTraslado=trasladoRepository.findByIdSolicitudAndIndActivoEquals(traslado.getIdSolicitud(), 1).orElseThrow(()-> new Exception("No se encontro el traslado"));
			
		//	TrasladoEntity trasladoEntity= TrasladosMapper.INSTANCE.JsonAEntity(traslado);
		//	trasladoEntity.setIndOxigeno();
			busquedaTraslado.setIndOxigeno(traslado.getIndOxigeno());
			busquedaTraslado.setFecActualizacion(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
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

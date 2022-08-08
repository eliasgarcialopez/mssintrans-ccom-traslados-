package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.EliminarArchivosDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Siniestro;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroDetalleRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroTablaResponse;
import mx.gob.imss.mssintrans.ccom.traslados.dto.SiniestroTablaResponsePrincipal;
import mx.gob.imss.mssintrans.ccom.traslados.dto.VehiculosRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestroDetalleEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestrosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosArrendadosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosPropiosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.SiniestroDetalleRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.SiniestrosRepository;
import mx.gob.imss.mssintrans.ccom.traslados.repository.VehiculoRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.SiniestrosService;
import mx.gob.imss.mssintrans.ccom.traslados.util.RestTemplateUtil;
import mx.gob.imss.mssintrans.ccom.traslados.util.SiniestroDetalleMapper;
import mx.gob.imss.mssintrans.ccom.traslados.util.SiniestroMapper;
import mx.gob.imss.mssintrans.ccom.traslados.util.VehiculosMapper;

@Transactional(rollbackOn = { SQLException.class, IOException.class })
@Service
@Slf4j
public class SiniestroServiceImpl implements SiniestrosService {

	@Value("${endpoints.carga-archivos-endpoint}")
	private String CARGA_ARCHIVOS_URL;

	@Value("${archivos.agrupador-siniestro}")
	private String AGRUPADOR_SINIESTRO;
	@Autowired
	private SiniestrosRepository siniestroRepository;
	
	@Autowired
	private SiniestroDetalleRepository siniestroDetalleRepository;

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Autowired
	private RestTemplateUtil restTemplate;

	private static final Logger log = LoggerFactory.getLogger(SiniestroServiceImpl.class);

	/**
	 * Consulta general paginados de siniestros registrados
	 */
	@Override
	public <T> Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();

		try {
			Page consultaGeneral = siniestroRepository.consultaGeneral(pageable, usuarioDTO.idUnidadAdscripcion );
			final List<SiniestroTablaResponsePrincipal>listPrincipal= new ArrayList<>();
			

			final List<SiniestroTablaResponse> content = SiniestroMapper.INSTANCE
					.formatearListaTabla(consultaGeneral.getContent());
			 
			for (SiniestroTablaResponse siniestroTablaResponse : content) {
				SiniestroTablaResponsePrincipal principalResponse= new SiniestroTablaResponsePrincipal();
				principalResponse.setIdSiniestro(siniestroTablaResponse.getIdSiniestro());
				principalResponse.setNumFolio(siniestroTablaResponse.getNumFolio());
				principalResponse.setFecIngresoTaller(siniestroTablaResponse.getFecIngresoTaller()==null?null:siniestroTablaResponse.getFecIngresoTaller().substring(0,10));
				principalResponse.setFecSalidaTaller(siniestroTablaResponse.getFecSalidaTaller() == null?null:siniestroTablaResponse.getFecSalidaTaller().substring(0,10));
				principalResponse.setIdVehiculo(siniestroTablaResponse.getVehiculo().getIdVehiculo());
				principalResponse.setCveEcco(siniestroTablaResponse.getVehiculo().getCveEcco());
				listPrincipal.add(principalResponse);
			}

			Page<SiniestroTablaResponsePrincipal> objetoMapeado = new PageImpl<>(listPrincipal, pageable,
					consultaGeneral.getTotalElements());
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos((T) objetoMapeado);

			return respuesta;
		} catch (Exception e) {
			Log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}

	}

	/**
	 * consulta de ecco siniestrado
	 */
	@Override
	public <T> Respuesta consultarSiniestroPorEcco(String ecco, Pageable pageable, DatosUsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();

		try {
			Page consultaGeneral = siniestroRepository.consultaGeneralPorEcco(ecco, usuarioDTO.getIdUnidadAdscripcion(), pageable);
			final List<SiniestroTablaResponsePrincipal>listPrincipal= new ArrayList<>();

			final List<SiniestroTablaResponse> content = SiniestroMapper.INSTANCE
					.formatearListaTabla(consultaGeneral.getContent());
			for (SiniestroTablaResponse siniestroTablaResponse : content) {
				SiniestroTablaResponsePrincipal principalResponse= new SiniestroTablaResponsePrincipal();
				
				//String fechaIngreso=siniestroTablaResponse.getFecIngresoTaller()!=null || siniestroTablaResponse.getFecIngresoTaller().equals("")?siniestroTablaResponse.getFecIngresoTaller().substring(0, 10):"";
				//String fechaSalida=siniestroTablaResponse.getFecSalidaTaller()!=null ||siniestroTablaResponse.getFecSalidaTaller().equals("")?siniestroTablaResponse.getFecSalidaTaller().substring(0, 10):"";
				principalResponse.setIdSiniestro(siniestroTablaResponse.getIdSiniestro());
				principalResponse.setNumFolio(siniestroTablaResponse.getNumFolio());
				principalResponse.setFecIngresoTaller(siniestroTablaResponse.getFecIngresoTaller()==null?null:siniestroTablaResponse.getFecIngresoTaller().substring(0,10));
				principalResponse.setFecSalidaTaller(siniestroTablaResponse.getFecSalidaTaller() == null?null:siniestroTablaResponse.getFecSalidaTaller().substring(0,10));
				principalResponse.setIdVehiculo(siniestroTablaResponse.getVehiculo().getIdVehiculo());
				principalResponse.setCveEcco(siniestroTablaResponse.getVehiculo().getCveEcco());
				listPrincipal.add(principalResponse);
			}
			Page<SiniestroTablaResponsePrincipal> objetoMapeado = new PageImpl<>(listPrincipal, pageable,
					consultaGeneral.getTotalElements());
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos((T) objetoMapeado);
			return respuesta;

		} catch (Exception e) {
			Log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}

	}

	@Transactional(rollbackOn = SQLException.class)
	@Override
	public <T> Respuesta guardarNuevoRegistro(Siniestro siniestro, MultipartFile declaracion, MultipartFile reportAcc,
			MultipartFile reporFoto) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();
		siniestro.setFecAlta(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		siniestro.setIndActivo(1);
		siniestro.setIndSistema(0);

		SiniestrosEntity siniestroEntity = SiniestroMapper.INSTANCE.JsonAEntity(siniestro);
		VehiculosPropiosEntity vehiculosPropiosEntity = null;
		
		SiniestrosEntity nuevaSiniestro = null;
		VehiculosPropiosEntity propiosEntityActualizado = null;
		SiniestroDetalleEntity detalleEntity = null;
		try {
			
			
			vehiculosPropiosEntity = vehiculoRepository.findByIdVehiculoAndIndActivoEquals(siniestro.getIdVehiculo(), 1)
					.orElseThrow(() -> new Exception("No se encontro el id del vehiculo " + siniestro.getIdVehiculo()));
			siniestroEntity.setVehiculo(vehiculosPropiosEntity);

			vehiculosPropiosEntity.setDesEstatusVehiculo("Siniestrado");
			if (siniestro.getFecIngresoTaller() == null && siniestro.getFecSalidaTaller() == null && siniestro.getCantidadPorcPerdidad()<100) {
				vehiculosPropiosEntity.setDesEstatusVehiculo("Siniestrado Transito");
			}

			if (siniestro.getFecIngresoTaller() != null) {
				vehiculosPropiosEntity.setDesEstatusVehiculo("Siniestrado");
			}

			if (siniestro.getCantidadPorcPerdidad() == 100 && vehiculosPropiosEntity.getIndArrendado() == 0) {
				vehiculosPropiosEntity.setDesEstatusEnajenacion("descripcion enajenacion");
			}

			siniestroEntity.setVehiculo(vehiculosPropiosEntity);

			nuevaSiniestro = siniestroRepository.saveAndFlush(siniestroEntity);

		} catch (Exception e) {
			log.debug("error {}", e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		Map<String, MultipartFile> mapa = new HashMap<>();
		mapa.put("declaracion", declaracion);

		mapa.put("reportAcc", reportAcc);

		mapa.put("reporFoto", reporFoto);

		try {
			log.info("Guardando  archivos..." + mapa.toString());

			final String url = crearUrl(nuevaSiniestro.getIdSiniestro().toString(), "save");
			log.info("url..." + url);
			Respuesta<?> cargaArchivoResponse = restTemplate.sendPostRequest(url, mapa, Respuesta.class);
			log.info("Se ha recuperado la ruta correctamente, {}", cargaArchivoResponse.getDatos());

			if (cargaArchivoResponse.getCodigo() != HttpStatus.OK.value()) {
				throw new IOException("Ha ocurrido un error al guardar  archivos");
			} else {
				@SuppressWarnings("unchecked")
				HashMap<String, String> rutas = (HashMap<String, String>) cargaArchivoResponse.getDatos();

				log.info("Se ha recuperado la ruta correctamente, {}", rutas);

				String rutaDeclaracion = rutas.get("declaracion");
				String rutaReporteAcc = rutas.get("reportAcc");
				String rutaReporteFoto = rutas.get("reporFoto");

				nuevaSiniestro.setDesRutaDeclaracion(rutaDeclaracion);
				nuevaSiniestro.setDesRutaReproteAcc(rutaReporteAcc);
				nuevaSiniestro.setDesRutaReporteFoto(rutaReporteFoto);

				try {

					log.info("Guardando la ruta del archivo");
					siniestroRepository.updateRutas(nuevaSiniestro.getDesRutaDeclaracion(),
							nuevaSiniestro.getDesRutaReproteAcc(), nuevaSiniestro.getDesRutaReporteFoto(),
							nuevaSiniestro.getIdSiniestro());
					vehiculoRepository.actualizarEstatus(vehiculosPropiosEntity.getDesEstatusVehiculo(),
							vehiculosPropiosEntity.getIdVehiculo());
					nuevaSiniestro.setVehiculo(vehiculosPropiosEntity);
					detalleEntity=siniestroDetalleRepository.obtenerConsultaPorId(nuevaSiniestro.getIdSiniestro());
				} catch (Exception e) {

					respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
					respuesta.setError(true);
					respuesta.setMensaje(e.getMessage());

				}
			}

			//SiniestroResponse siniestroJson = SiniestroMapper.INSTANCE.entityAJsonFormat(nuevaSiniestro);
			//SiniestroDetalleRespuesta siniestroJson=SiniestroDetalleMapper.INSTANCE.entityAJson(detalleEntity);
			SiniestroDetalleRespuesta siniestroJson= this.mapearSiniestroDetalle(detalleEntity);
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos((T) siniestroJson);

			return respuesta;

		} catch (IOException e) {
			log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			siniestroRepository.deleteById(nuevaSiniestro.getIdSiniestro());
		} catch (Exception e) {
			log.error(e.getMessage());
			// todo - que error se va a regresar como general?
			siniestroRepository.deleteById(nuevaSiniestro.getIdSiniestro());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
		}
		return respuesta;

	}

	@Override
	public <T> Respuesta eliminarSiniestro(Integer idSiniestro) {
		// TODO Auto-generated method stub
		Respuesta respuesta = new Respuesta<>();

		try {
			siniestroRepository.eliminar(idSiniestro);
			siniestroRepository.flush();
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
		} catch (Exception e) {
			Log.error(e.getMessage());
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
	public <T> Respuesta actualizarRegistro(Siniestro siniestro, MultipartFile declaracion, MultipartFile reportAcc,
			MultipartFile reporFoto) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();
		SiniestroDetalleEntity detalleEntity = null;
		try {
			SiniestrosEntity siniestrosEntity = siniestroRepository
					.findByIdSiniestroAndIndActivoEquals(siniestro.getIdSiniestro(), 1).orElseThrow(() -> new Exception(
							"No se ha encontrado el siniestro con id: " + siniestro.getIdSiniestro()));
			VehiculosPropiosEntity vehiculosPropiosEntity = null;

			vehiculosPropiosEntity = vehiculoRepository
					.findByIdVehiculoAndIndActivoEquals(siniestrosEntity.getVehiculo().getIdVehiculo(), 1)
					.orElseThrow(() -> new Exception("No se encontro el id del vehiculo " + siniestro.getIdVehiculo()));

			siniestrosEntity.setVehiculo(vehiculosPropiosEntity);

			EliminarArchivosDTO eliminarArchivosDTO = new EliminarArchivosDTO();
			List<String> rutas = new ArrayList<String>();
			log.info("Eliminando el archivo anterior...");

			String urlEliminar = crearUrl(siniestrosEntity.getIdSiniestro().toString(), "delete");

			rutas.add(!declaracion.isEmpty() ? siniestrosEntity.getDesRutaDeclaracion() : "");
			rutas.add(!reportAcc.isEmpty() ? siniestrosEntity.getDesRutaReproteAcc() : "");
			rutas.add(!reporFoto.isEmpty() ? siniestrosEntity.getDesRutaReporteFoto() : "");

			eliminarArchivosDTO.setRutas(rutas);

			Respuesta<?> eliminarRequest = restTemplate.sendPostRequest(urlEliminar, eliminarArchivosDTO,
					Respuesta.class);

			Map<String, MultipartFile> mapa = new HashMap<>();
			if (!declaracion.isEmpty()) {
				mapa.put("declaracion", declaracion);
			}
			
			if (!reportAcc.isEmpty()) {
				mapa.put("reportAcc", reportAcc);
			}
			
			if (!reporFoto.isEmpty()) {
				mapa.put("reporFoto", reporFoto);
			}
			

			

			log.info("Actualizar siniestros y  archivos...");
			final String url = crearUrl(siniestrosEntity.getIdSiniestro().toString(), "save");
			log.info("url..." + url);
			Respuesta<?> cargaArchivoResponse= new Respuesta<>();
			if (mapa.size()>0) {
				cargaArchivoResponse = restTemplate.sendPostRequest(url, mapa, Respuesta.class);
			 log.info("Se ha recuperado la ruta correctamente, {}", cargaArchivoResponse.getDatos());
			}
			

			if (cargaArchivoResponse.getCodigo() != HttpStatus.OK.value() && mapa.size()>0) {
				throw new IOException("Ha ocurrido un error al guardar  archivos");
			} else {
				@SuppressWarnings("unchecked")
				HashMap<String, String> rutasActualizar = (HashMap<String, String>) cargaArchivoResponse.getDatos();

				log.info("Se ha recuperado la ruta correctamente, {}", rutas);

				String rutaDeclaracion =rutasActualizar!= null? rutasActualizar.get("declaracion"):null;
				String rutaReporteAcc = rutasActualizar!= null? rutasActualizar.get("reportAcc"):null;
				String rutaReporteFoto = rutasActualizar!= null?rutasActualizar.get("reporFoto"):null;
				
				if (!(rutaDeclaracion == null)) {
					siniestrosEntity.setDesRutaDeclaracion(rutaDeclaracion);
				}
				
				if (!(rutaReporteAcc == null)) {
					siniestrosEntity.setDesRutaReproteAcc(rutaReporteAcc);
				}
				
				if (!(rutaReporteFoto==null)) {
					siniestrosEntity.setDesRutaReporteFoto(rutaReporteFoto);
				}
				
				siniestrosEntity
						.setObservaciones(siniestro.getObservaciones() == null ? siniestrosEntity.getObservaciones()
								: siniestro.getObservaciones());
				siniestrosEntity.setFecIngresoTaller(
						siniestro.getFecIngresoTaller() == null ? siniestrosEntity.getFecIngresoTaller()
								: siniestro.getFecIngresoTaller());
				siniestrosEntity.setFecSalidaTaller(
						siniestro.getFecIngresoTaller() == null ? siniestrosEntity.getFecSalidaTaller()
								: siniestro.getFecSalidaTaller());
				siniestrosEntity.setCantidadPorcPerdidad(
						siniestro.getCantidadPorcPerdidad() == null ? siniestrosEntity.getCantidadPorcPerdidad()
								: siniestro.getCantidadPorcPerdidad());
				if (siniestro.getFecIngresoTaller() == null && siniestro.getFecSalidaTaller() == null  && siniestro.getCantidadPorcPerdidad()<100) {
					vehiculosPropiosEntity.setDesEstatusVehiculo("Siniestrado Transito");
				}
				if (siniestro.getFecIngresoTaller() != null) {
					vehiculosPropiosEntity.setDesEstatusVehiculo("Siniestrado");
				}
				if (siniestro.getCantidadPorcPerdidad() == 100 && vehiculosPropiosEntity.getIndArrendado() == 0) {
					vehiculosPropiosEntity.setDesEstatusEnajenacion("descripcion enajenacion");
				}

				try {

					log.info("Guardando la ruta del archivo");

					siniestroRepository.actualizar(siniestrosEntity.getFecIngresoTaller(),
							siniestrosEntity.getFecSalidaTaller(), siniestrosEntity.getObservaciones(),
							siniestrosEntity.getDesRutaDeclaracion(), siniestrosEntity.getDesRutaReproteAcc(),
							siniestrosEntity.getDesRutaReporteFoto(), siniestrosEntity.getIdSiniestro());
					vehiculoRepository.actualizarEstatus(vehiculosPropiosEntity.getDesEstatusVehiculo(),
							vehiculosPropiosEntity.getIdVehiculo());
					siniestrosEntity.setVehiculo(vehiculosPropiosEntity);
					detalleEntity=siniestroDetalleRepository.obtenerConsultaPorId(siniestrosEntity.getIdSiniestro());
				} catch (Exception e) {

					respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
					respuesta.setError(true);
					respuesta.setMensaje(e.getMessage());

				}
			}
			//SiniestroResponse siniestroJson = SiniestroMapper.INSTANCE.entityAJsonFormat(siniestrosEntity);
			//SiniestroDetalleRespuesta siniestroJson=SiniestroDetalleMapper.INSTANCE.entityAJson(detalleEntity);
			SiniestroDetalleRespuesta siniestroJson= this.mapearSiniestroDetalle(detalleEntity);
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
			respuesta.setDatos((T) siniestroJson);

			return respuesta;

		} catch (IOException e) {
			log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			// siniestroRepository.deleteById(nuevaSiniestro.getIdSiniestro());
		} catch (Exception e) {
			log.error(e.getMessage());
			// todo - que error se va a regresar como general?
			// siniestroRepository.deleteById(nuevaSiniestro.getIdSiniestro());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public <T> Respuesta consultarSiniestroPorId(Integer id) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();
		SiniestroDetalleEntity detalleEntity = null;
		try {
			//siniestrosEntity = siniestroRepository.getId(id);
			
			detalleEntity=siniestroDetalleRepository.obtenerConsultaPorId(id);
		
			if (detalleEntity==null) {
				throw new Exception("No se encontro el registro solicitado");
			}
				
			} catch (Exception e) {
			
			respuesta.setCodigo(HttpStatus.NOT_FOUND.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
	//	SiniestroDetalleRespuesta siniestroJson=SiniestroDetalleMapper.INSTANCE.entityAJson(detalleEntity);
		SiniestroDetalleRespuesta siniestroJson= this.mapearSiniestroDetalle(detalleEntity);
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T) siniestroJson);

		return respuesta;
	}

	/**
	 * Genera la url para consumir el servicio de carga de archivos.
	 *
	 * @param aseguradora
	 * @return
	 */
	private String crearUrl(String siniestro, String tipo) {
		if (tipo.equals("delete")) {
			return CARGA_ARCHIVOS_URL + "/eliminar-archivos";
		}
		return CARGA_ARCHIVOS_URL + "/" + AGRUPADOR_SINIESTRO + "/" + siniestro;
	}
	
	private SiniestroDetalleRespuesta mapearSiniestroDetalle(SiniestroDetalleEntity detalleEntity) {
		SiniestroDetalleRespuesta siniestroJson= new SiniestroDetalleRespuesta();
		siniestroJson.setIdSiniestro(detalleEntity.getIdSiniestro());
		siniestroJson.setNumFolio(detalleEntity.getNumFolio());
		siniestroJson.setFecReporte(detalleEntity.getFecReporte()==null?detalleEntity.getFecReporte():detalleEntity.getFecReporte().substring(0,10));
		siniestroJson.setFecSiniestro(detalleEntity.getFecSiniestro()== null?detalleEntity.getFecSiniestro():detalleEntity.getFecSiniestro().substring(0,10));
		siniestroJson.setFecIngresoTaller(detalleEntity.getFecIngresoTaller()==null?detalleEntity.getFecIngresoTaller():detalleEntity.getFecIngresoTaller().substring(0,10));
		siniestroJson.setFecSalidaTaller(detalleEntity.getFecSalidaTaller()==null?detalleEntity.getFecSalidaTaller():detalleEntity.getFecSalidaTaller().substring(0,10));
		siniestroJson.setObservaciones(detalleEntity.getObservaciones());
		siniestroJson.setDesRutaDeclaracion(detalleEntity.getDesRutaDeclaracion());
		siniestroJson.setDesRutaReproteAcc(detalleEntity.getDesRutaReproteAcc());
		siniestroJson.setDesRutaReporteFoto(detalleEntity.getDesRutaReporteFoto());
		siniestroJson.setCantidadPorcPerdidad(detalleEntity.getCantidadPorcPerdidad());
		siniestroJson.setIdVehiculo(detalleEntity.getIdVehiculo());
		siniestroJson.setCveEcco(detalleEntity.getCveEcco());
		siniestroJson.setNumPlacas(detalleEntity.getNumPlacas());
		siniestroJson.setDesEstatusVehiculo(detalleEntity.getDesEstatusVehiculo());
		siniestroJson.setIdReAsignacion(detalleEntity.getIdReAsignacion());
		siniestroJson.setNomArrendadora(detalleEntity.getNomArrendadora());
		siniestroJson.setDesNombreAseguradora(detalleEntity.getDesNombreAseguradora());
		siniestroJson.setNombreChofer(detalleEntity.getNombreChofer());
		siniestroJson.setCveMatricula(detalleEntity.getCveMatricula());
		return siniestroJson;
	}

}

package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.VehiculosRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosArrendadosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.VehiculosRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.VehiculoService;
import mx.gob.imss.mssintrans.ccom.traslados.util.VehiculosMapper;

@Transactional(rollbackOn = SQLException.class)
@Service
@AllArgsConstructor
@Slf4j
public class VehiculoServiceImpl implements VehiculoService {

	private final VehiculosRepository vehiculosRepository;

	private static final Logger log = LoggerFactory.getLogger(VehiculoServiceImpl.class);

	@Override
	public <T> Respuesta getEcco(String ecco, DatosUsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		Respuesta<T> respuesta = new Respuesta<>();
		try {  
			
			VehiculosArrendadosEntity consultaVehiculos = vehiculosRepository.obtenerConsultaEcco(ecco,usuarioDTO.idUnidadAdscripcion);
			
	        
	        VehiculosRespuesta content = VehiculosMapper.INSTANCE
	                    .AsJson(consultaVehiculos);

	           
			
			respuesta.setCodigo(HttpStatus.OK.value());
			respuesta.setError(false);
			respuesta.setMensaje("Exito");
		    respuesta.setDatos((T)content);

			return respuesta;
		} catch (Exception e) {
			Log.error(e.getMessage());
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}

		// return null;
	}

}

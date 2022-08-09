package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Traslados;
import mx.gob.imss.mssintrans.ccom.traslados.service.TrasladoService;

@Transactional(rollbackOn = { SQLException.class, IOException.class })
@Service
@Slf4j
public class TrasladoServiceImpl implements TrasladoService {

	@Override
	public <T> Respuesta consultaGeneral(Pageable pageable, DatosUsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Respuesta consultaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Respuesta guardarNuevoRegistro(Traslados traslados) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Respuesta eliminarTraslado(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Respuesta actualizarRegistro(Traslados siniestro) {
		// TODO Auto-generated method stub
		return null;
	}

}

package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.gob.imss.mssintrans.ccom.traslados.dto.CodigoPostalRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.model.CodigoPostalEntity;
import mx.gob.imss.mssintrans.ccom.traslados.repository.CodigoPostalRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.CodigoPostalService;
@Service
public class CodigoPostalServiceImpl implements CodigoPostalService{

	@Autowired 
	private CodigoPostalRepository codigoPostalRepository;
	
	@Override
	public <T> Respuesta consultaGeneral(String codigoPostal) {
		Respuesta<T> respuesta = new Respuesta<>();
		
		List<CodigoPostalEntity> codigoPostall = null;
		try {
			codigoPostall = codigoPostalRepository.consultaCodigoPostal(codigoPostal);
		} catch (Exception e) {
			respuesta.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
			respuesta.setError(true);
			respuesta.setMensaje(e.getMessage());
			return respuesta;
		}
		
		List<CodigoPostalRespuesta> codPostalRespuesta = new ArrayList<>();
		for (  CodigoPostalEntity codP : codigoPostall ) {
			CodigoPostalRespuesta codPostal = new CodigoPostalRespuesta();
			codPostal.setIdCodigoPostal(codP.getIdCodigoPostal());
			codPostal.setCodigoPostal(codP.getCveCodigoPostal());
			
			codPostal.setIdMunicipio(codP.getIdMunicipio().getIdMunicipio());
			codPostal.setNomMunicipio(codP.getIdMunicipio().getNomMunicipio());
			
			codPostal.setIdEstado(codP.getIdMunicipio().getEntidades().getIdEntidad());
			codPostal.setNomEstado(codP.getIdMunicipio().getEntidades().getNomEntidad());
			
			codPostalRespuesta.add(codPostal);
		}
		
		respuesta.setCodigo(HttpStatus.OK.value());
		respuesta.setError(false);
		respuesta.setMensaje("Exito");
		respuesta.setDatos((T)codPostalRespuesta );
		
		return respuesta;
	}

}

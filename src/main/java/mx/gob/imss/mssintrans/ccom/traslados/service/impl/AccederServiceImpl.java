/**
 * 
 */
package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Respuesta;
import mx.gob.imss.mssintrans.ccom.traslados.dto.VigenciaderechosRespuesta;
import mx.gob.imss.mssintrans.ccom.traslados.repository.UnidadesAdscripcionRepository;
import mx.gob.imss.mssintrans.ccom.traslados.service.AccederService;
import mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType;
import mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_Service;
import mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_ServiceLocator;
import mx.gob.imss.mssintrans.ccom.traslados.webservice.WSConsVigGpoFamComXNssPortBindingStub;

/**
 * @author rarteaga
 *
 */
@Service
@Slf4j
public class AccederServiceImpl implements AccederService {
	
	@Value("${application.id}")
	String cpid;
	
	@Value("${application.acceder}")
	String accederAddress;
	
	@Autowired
	private UnidadesAdscripcionRepository unidadesAdscripcionRepository;

	@Override
	public Respuesta<VigenciaderechosRespuesta> consultaVigenciaDerechosGrupoFamiliar(String nss)   {
		Respuesta<VigenciaderechosRespuesta> response = new Respuesta<>();
		try {
			IWSConsVigGpoFamComXNss_Service service = new IWSConsVigGpoFamComXNss_ServiceLocator(accederAddress);
			
			IWSConsVigGpoFamComXNss_PortType ws;
			ws = new WSConsVigGpoFamComXNssPortBindingStub(new URL(service.getWSConsVigGpoFamComXNssPortAddress()), service);
			
			try {
				VigenciaderechosRespuesta vigenciaderechosRespuesta = ws.getInfo(nss, cpid);
				 
				if (vigenciaderechosRespuesta.getCodigoError() == 0) {
					vigenciaderechosRespuesta.setDhUMF(unidadesAdscripcionRepository.findUnidadesAdscripcionEntityByIdUnidadAdscripcion(Integer.parseInt(vigenciaderechosRespuesta.getDhUMF().trim())));
				}
					
				response.setDatos(vigenciaderechosRespuesta);
	            response.setCodigo(HttpStatus.OK.value());
	            response.setMensaje("Exito");
	            response.setError(false);
			} catch (RemoteException e) {
	            log.error("Error no se puede acceder de forma remota: ", e.getMessage());
	            response.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            response.setMensaje("Error no se puede acceder de forma remota: " + e.getMessage());
	            response.setError(true);
			}
		} catch (AxisFault e) {
            log.error("Error la invocación del servicio web falla: ", e.getMessage());
            response.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMensaje("Error la invocación del servicio web falla: " + e.getMessage());
            response.setError(true);
		} catch (MalformedURLException e) {
            log.error("Error se ha producido una URL malformada: ", e.getMessage());
            response.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMensaje("Error se ha producido una URL malformada: " + e.getMessage());
            response.setError(true);
		}
		return response;
	}

}

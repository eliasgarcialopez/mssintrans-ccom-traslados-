package mx.gob.imss.mssintrans.ccom.traslados.siap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsultaMatriculaCliente {
	
	@Value("${endpoints.siap-endpoint}")
    private String urlSiap;

    public ConsultaMatriculaResponse.ConsultaMatriculaResult consultaNss(String matricula) {

    	 URL url = null;
         WebServiceException e = null;
         try {
             url = new URL(urlSiap);
         } catch (MalformedURLException ex) {
             e = new WebServiceException(ex);
             log.info(e.getMessage());
             throw e;
         }
    	
        WsConsultaSIT wConsultaSIT = new WsConsultaSIT(url);
        
        WsConsultaSITSoap wsConsultaSITSoap = wConsultaSIT.getWsConsultaSITSoap();

        return wsConsultaSITSoap.consultaMatricula(matricula, "", "", "");


    }



}

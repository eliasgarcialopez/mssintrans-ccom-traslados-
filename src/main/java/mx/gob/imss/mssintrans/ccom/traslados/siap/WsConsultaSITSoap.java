package mx.gob.imss.mssintrans.ccom.traslados.siap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "wsConsultaSITSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WsConsultaSITSoap {


    /**
     * 
     * @param delegacion
     * @param matricula
     * @param curp
     * @param nss
     * @return
     *     returns mx.gob.imss.consultamatricula.ConsultaMatriculaResponse.ConsultaMatriculaResult
     */
    @WebMethod(operationName = "ConsultaMatricula", action = "http://tempuri.org/ConsultaMatricula")
    @WebResult(name = "ConsultaMatriculaResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ConsultaMatricula", targetNamespace = "http://tempuri.org/", className = "mx.gob.imss.mssintrans.ccom.traslados.siap.ConsultaMatricula")
    @ResponseWrapper(localName = "ConsultaMatriculaResponse", targetNamespace = "http://tempuri.org/", className = "mx.gob.imss.mssintrans.ccom.traslados.siap.ConsultaMatriculaResponse")
    public ConsultaMatriculaResponse.ConsultaMatriculaResult consultaMatricula(
        @WebParam(name = "Matricula", targetNamespace = "http://tempuri.org/")
        String matricula,
        @WebParam(name = "Delegacion", targetNamespace = "http://tempuri.org/")
        String delegacion,
        @WebParam(name = "NSS", targetNamespace = "http://tempuri.org/")
        String nss,
        @WebParam(name = "CURP", targetNamespace = "http://tempuri.org/")
        String curp);

}

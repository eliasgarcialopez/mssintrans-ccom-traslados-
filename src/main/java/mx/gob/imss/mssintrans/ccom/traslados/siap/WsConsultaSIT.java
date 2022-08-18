package mx.gob.imss.mssintrans.ccom.traslados.siap;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "wsConsultaSIT", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://10.100.6.60/ServiciosWebSIT/wsConsultaSIT.asmx?WSDL")
public class WsConsultaSIT
    extends Service
{	
    URL WSCONSULTASIT_WSDL_LOCATION;
    private final static QName WSCONSULTASIT_QNAME = new QName("http://tempuri.org/", "wsConsultaSIT");

    public WsConsultaSIT(URL wsdlLocation) {
        super(wsdlLocation, WSCONSULTASIT_QNAME);
    }
    
    /**
     * 
     * @return
     *     returns WsConsultaSITSoap
     */
    @WebEndpoint(name = "wsConsultaSITSoap")
    public WsConsultaSITSoap getWsConsultaSITSoap() {
        return super.getPort(new QName("http://tempuri.org/", "wsConsultaSITSoap"), WsConsultaSITSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsConsultaSITSoap
     */
    @WebEndpoint(name = "wsConsultaSITSoap")
    public WsConsultaSITSoap getWsConsultaSITSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "wsConsultaSITSoap"), WsConsultaSITSoap.class, features);
    }

}


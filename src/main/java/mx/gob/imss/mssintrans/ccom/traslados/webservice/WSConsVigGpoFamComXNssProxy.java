package mx.gob.imss.mssintrans.ccom.traslados.webservice;

public class WSConsVigGpoFamComXNssProxy implements mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType {
  private String _endpoint = null;
  private mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType wSConsVigGpoFamComXNss_PortType = null;
  
  public WSConsVigGpoFamComXNssProxy() {
    _initWSConsVigGpoFamComXNssProxy();
  }
  
  public WSConsVigGpoFamComXNssProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSConsVigGpoFamComXNssProxy();
  }
  
  private void _initWSConsVigGpoFamComXNssProxy() {
    try {
      wSConsVigGpoFamComXNss_PortType = (new mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_ServiceLocator()).getWSConsVigGpoFamComXNssPort();
      if (wSConsVigGpoFamComXNss_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSConsVigGpoFamComXNss_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSConsVigGpoFamComXNss_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSConsVigGpoFamComXNss_PortType != null)
      ((javax.xml.rpc.Stub)wSConsVigGpoFamComXNss_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType getWSConsVigGpoFamComXNss_PortType() {
    if (wSConsVigGpoFamComXNss_PortType == null)
      _initWSConsVigGpoFamComXNssProxy();
    return wSConsVigGpoFamComXNss_PortType;
  }
  
  public mx.gob.imss.mssintrans.ccom.traslados.dto.VigenciaderechosRespuesta getInfo(java.lang.String nss, java.lang.String cpid) throws java.rmi.RemoteException{
    if (wSConsVigGpoFamComXNss_PortType == null)
      _initWSConsVigGpoFamComXNssProxy();
    return wSConsVigGpoFamComXNss_PortType.getInfo(nss, cpid);
  }
  
  
}
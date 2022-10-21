package mx.gob.imss.mssintrans.ccom.traslados.webservice;

public class IWSConsVigGpoFamComXNss_ServiceLocator extends org.apache.axis.client.Service implements mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_Service {
	
	private static final long serialVersionUID = 7888238858627854358L;
	
	private java.lang.String wsNssPortAddress = "";

	public IWSConsVigGpoFamComXNss_ServiceLocator() { }
	
	public IWSConsVigGpoFamComXNss_ServiceLocator(String address) {
		this.wsNssPortAddress = address;
    }


    public IWSConsVigGpoFamComXNss_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IWSConsVigGpoFamComXNss_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }
    
    public java.lang.String getWSConsVigGpoFamComXNssPortAddress() {
        return wsNssPortAddress;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSConsVigGpoFamComXNssPortWSDDServiceName = "WSConsVigGpoFamComXNssPort";

    public java.lang.String getWSConsVigGpoFamComXNssPortWSDDServiceName() {
        return WSConsVigGpoFamComXNssPortWSDDServiceName;
    }

    public void setWSConsVigGpoFamComXNssPortWSDDServiceName(java.lang.String name) {
        WSConsVigGpoFamComXNssPortWSDDServiceName = name;
    }

    public mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType getWSConsVigGpoFamComXNssPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsNssPortAddress);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSConsVigGpoFamComXNssPort(endpoint);
    }

    public mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType getWSConsVigGpoFamComXNssPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	mx.gob.imss.mssintrans.ccom.traslados.webservice.WSConsVigGpoFamComXNssPortBindingStub _stub = new mx.gob.imss.mssintrans.ccom.traslados.webservice.WSConsVigGpoFamComXNssPortBindingStub(portAddress, this);
            _stub.setPortName(getWSConsVigGpoFamComXNssPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSConsVigGpoFamComXNssPortEndpointAddress(java.lang.String address) {
    	wsNssPortAddress = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mx.gob.imss.mssintrans.ccom.traslados.webservice.IWSConsVigGpoFamComXNss_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
            	mx.gob.imss.mssintrans.ccom.traslados.webservice.WSConsVigGpoFamComXNssPortBindingStub _stub = new mx.gob.imss.mssintrans.ccom.traslados.webservice.WSConsVigGpoFamComXNssPortBindingStub(new java.net.URL(wsNssPortAddress), this);
                _stub.setPortName(getWSConsVigGpoFamComXNssPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSConsVigGpoFamComXNssPort".equals(inputPortName)) {
            return getWSConsVigGpoFamComXNssPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vigenciaderechos.imss.gob.mx/", "WSConsVigGpoFamComXNss");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vigenciaderechos.imss.gob.mx/", "WSConsVigGpoFamComXNssPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSConsVigGpoFamComXNssPort".equals(portName)) {
            setWSConsVigGpoFamComXNssPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

package mx.gob.imss.mssintrans.ccom.traslados.dto;

public class VigenciaderechosRespuesta  extends WSResponse  implements java.io.Serializable {
	
	private static final long serialVersionUID = 6165369136568569562L;

	private java.lang.String agregadoAfiliacion;

    private java.lang.String agregadoMedico;

    private InfoAseguradoDTO[] beneficiarios;

    private java.lang.String clavePresupuestal;

    private java.lang.String colonia;

    private java.lang.String conDerechoInc;

    private java.lang.String conDerechoSm;

    private java.lang.String consultorio;

    private java.lang.String cpid;

    private java.lang.String curp;

    private java.lang.String dhDeleg;

    private java.lang.String dhIpServer;

    private java.lang.String dhUMF;

    private java.lang.String direccion;

    private java.lang.String fechaNacimiento;

    private java.lang.String idee;

    private java.lang.String materno;

    private java.lang.String nombre;

    private java.lang.String nss;

    private java.lang.String paterno;

    private java.lang.String registroPatronal;

    private java.lang.String sexo;

    private java.lang.String telefono;

    private java.lang.String tipoPension;

    private java.lang.String turno;

    private java.lang.String vigenteHasta;

    private java.lang.Integer idPersona;

    public VigenciaderechosRespuesta() {
    }

    public VigenciaderechosRespuesta(
           java.lang.Integer codigoError,
           java.lang.String mensajeError,
           java.lang.String agregadoAfiliacion,
           java.lang.String agregadoMedico,
           mx.gob.imss.mssintrans.ccom.traslados.dto.InfoAseguradoDTO[] beneficiarios,
           java.lang.String clavePresupuestal,
           java.lang.String colonia,
           java.lang.String conDerechoInc,
           java.lang.String conDerechoSm,
           java.lang.String consultorio,
           java.lang.String cpid,
           java.lang.String curp,
           java.lang.String dhDeleg,
           java.lang.String dhIpServer,
           java.lang.String dhUMF,
           java.lang.String direccion,
           java.lang.String fechaNacimiento,
           java.lang.String idee,
           java.lang.String materno,
           java.lang.String nombre,
           java.lang.String nss,
           java.lang.String paterno,
           java.lang.String registroPatronal,
           java.lang.String sexo,
           java.lang.String telefono,
           java.lang.String tipoPension,
           java.lang.String turno,
           java.lang.String vigenteHasta,
           java.lang.Integer idPersona) {
        super(
            codigoError,
            mensajeError);
        this.agregadoAfiliacion = agregadoAfiliacion;
        this.agregadoMedico = agregadoMedico;
        this.beneficiarios = beneficiarios;
        this.clavePresupuestal = clavePresupuestal;
        this.colonia = colonia;
        this.conDerechoInc = conDerechoInc;
        this.conDerechoSm = conDerechoSm;
        this.consultorio = consultorio;
        this.cpid = cpid;
        this.curp = curp;
        this.dhDeleg = dhDeleg;
        this.dhIpServer = dhIpServer;
        this.dhUMF = dhUMF;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.idee = idee;
        this.materno = materno;
        this.nombre = nombre;
        this.nss = nss;
        this.paterno = paterno;
        this.registroPatronal = registroPatronal;
        this.sexo = sexo;
        this.telefono = telefono;
        this.tipoPension = tipoPension;
        this.turno = turno;
        this.vigenteHasta = vigenteHasta;
        this.idPersona = idPersona;
    }


    /**
     * Gets the agregadoAfiliacion value for this _return.
     * 
     * @return agregadoAfiliacion
     */
    public java.lang.String getAgregadoAfiliacion() {
        return agregadoAfiliacion;
    }


    /**
     * Sets the agregadoAfiliacion value for this _return.
     * 
     * @param agregadoAfiliacion
     */
    public void setAgregadoAfiliacion(java.lang.String agregadoAfiliacion) {
        this.agregadoAfiliacion = agregadoAfiliacion;
    }


    /**
     * Gets the agregadoMedico value for this _return.
     * 
     * @return agregadoMedico
     */
    public java.lang.String getAgregadoMedico() {
        return agregadoMedico;
    }


    /**
     * Sets the agregadoMedico value for this _return.
     * 
     * @param agregadoMedico
     */
    public void setAgregadoMedico(java.lang.String agregadoMedico) {
        this.agregadoMedico = agregadoMedico;
    }


    /**
     * Gets the beneficiarios value for this _return.
     * 
     * @return beneficiarios
     */
    public mx.gob.imss.mssintrans.ccom.traslados.dto.InfoAseguradoDTO[] getBeneficiarios() {
        return beneficiarios;
    }


    /**
     * Sets the beneficiarios value for this _return.
     * 
     * @param beneficiarios
     */
    public void setBeneficiarios(mx.gob.imss.mssintrans.ccom.traslados.dto.InfoAseguradoDTO[] beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public mx.gob.imss.mssintrans.ccom.traslados.dto.InfoAseguradoDTO getBeneficiarios(int i) {
        return this.beneficiarios[i];
    }

    public void setBeneficiarios(int i, mx.gob.imss.mssintrans.ccom.traslados.dto.InfoAseguradoDTO value) {
        this.beneficiarios[i] = value;
    }


    /**
     * Gets the clavePresupuestal value for this _return.
     * 
     * @return clavePresupuestal
     */
    public java.lang.String getClavePresupuestal() {
        return clavePresupuestal;
    }


    /**
     * Sets the clavePresupuestal value for this _return.
     * 
     * @param clavePresupuestal
     */
    public void setClavePresupuestal(java.lang.String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }


    /**
     * Gets the colonia value for this _return.
     * 
     * @return colonia
     */
    public java.lang.String getColonia() {
        return colonia;
    }


    /**
     * Sets the colonia value for this _return.
     * 
     * @param colonia
     */
    public void setColonia(java.lang.String colonia) {
        this.colonia = colonia;
    }


    /**
     * Gets the conDerechoInc value for this _return.
     * 
     * @return conDerechoInc
     */
    public java.lang.String getConDerechoInc() {
        return conDerechoInc;
    }


    /**
     * Sets the conDerechoInc value for this _return.
     * 
     * @param conDerechoInc
     */
    public void setConDerechoInc(java.lang.String conDerechoInc) {
        this.conDerechoInc = conDerechoInc;
    }


    /**
     * Gets the conDerechoSm value for this _return.
     * 
     * @return conDerechoSm
     */
    public java.lang.String getConDerechoSm() {
        return conDerechoSm;
    }


    /**
     * Sets the conDerechoSm value for this _return.
     * 
     * @param conDerechoSm
     */
    public void setConDerechoSm(java.lang.String conDerechoSm) {
        this.conDerechoSm = conDerechoSm;
    }


    /**
     * Gets the consultorio value for this _return.
     * 
     * @return consultorio
     */
    public java.lang.String getConsultorio() {
        return consultorio;
    }


    /**
     * Sets the consultorio value for this _return.
     * 
     * @param consultorio
     */
    public void setConsultorio(java.lang.String consultorio) {
        this.consultorio = consultorio;
    }


    /**
     * Gets the cpid value for this _return.
     * 
     * @return cpid
     */
    public java.lang.String getCpid() {
        return cpid;
    }


    /**
     * Sets the cpid value for this _return.
     * 
     * @param cpid
     */
    public void setCpid(java.lang.String cpid) {
        this.cpid = cpid;
    }


    /**
     * Gets the curp value for this _return.
     * 
     * @return curp
     */
    public java.lang.String getCurp() {
        return curp;
    }


    /**
     * Sets the curp value for this _return.
     * 
     * @param curp
     */
    public void setCurp(java.lang.String curp) {
        this.curp = curp;
    }


    /**
     * Gets the dhDeleg value for this _return.
     * 
     * @return dhDeleg
     */
    public java.lang.String getDhDeleg() {
        return dhDeleg;
    }


    /**
     * Sets the dhDeleg value for this _return.
     * 
     * @param dhDeleg
     */
    public void setDhDeleg(java.lang.String dhDeleg) {
        this.dhDeleg = dhDeleg;
    }


    /**
     * Gets the dhIpServer value for this _return.
     * 
     * @return dhIpServer
     */
    public java.lang.String getDhIpServer() {
        return dhIpServer;
    }


    /**
     * Sets the dhIpServer value for this _return.
     * 
     * @param dhIpServer
     */
    public void setDhIpServer(java.lang.String dhIpServer) {
        this.dhIpServer = dhIpServer;
    }


    /**
     * Gets the dhUMF value for this _return.
     * 
     * @return dhUMF
     */
    public java.lang.String getDhUMF() {
        return dhUMF;
    }


    /**
     * Sets the dhUMF value for this _return.
     * 
     * @param dhUMF
     */
    public void setDhUMF(java.lang.String dhUMF) {
        this.dhUMF = dhUMF;
    }


    /**
     * Gets the direccion value for this _return.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this _return.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the fechaNacimiento value for this _return.
     * 
     * @return fechaNacimiento
     */
    public java.lang.String getFechaNacimiento() {
        return fechaNacimiento;
    }


    /**
     * Sets the fechaNacimiento value for this _return.
     * 
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(java.lang.String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    /**
     * Gets the idee value for this _return.
     * 
     * @return idee
     */
    public java.lang.String getIdee() {
        return idee;
    }


    /**
     * Sets the idee value for this _return.
     * 
     * @param idee
     */
    public void setIdee(java.lang.String idee) {
        this.idee = idee;
    }


    /**
     * Gets the materno value for this _return.
     * 
     * @return materno
     */
    public java.lang.String getMaterno() {
        return materno;
    }


    /**
     * Sets the materno value for this _return.
     * 
     * @param materno
     */
    public void setMaterno(java.lang.String materno) {
        this.materno = materno;
    }


    /**
     * Gets the nombre value for this _return.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this _return.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the nss value for this _return.
     * 
     * @return nss
     */
    public java.lang.String getNss() {
        return nss;
    }


    /**
     * Sets the nss value for this _return.
     * 
     * @param nss
     */
    public void setNss(java.lang.String nss) {
        this.nss = nss;
    }


    /**
     * Gets the paterno value for this _return.
     * 
     * @return paterno
     */
    public java.lang.String getPaterno() {
        return paterno;
    }


    /**
     * Sets the paterno value for this _return.
     * 
     * @param paterno
     */
    public void setPaterno(java.lang.String paterno) {
        this.paterno = paterno;
    }


    /**
     * Gets the registroPatronal value for this _return.
     * 
     * @return registroPatronal
     */
    public java.lang.String getRegistroPatronal() {
        return registroPatronal;
    }


    /**
     * Sets the registroPatronal value for this _return.
     * 
     * @param registroPatronal
     */
    public void setRegistroPatronal(java.lang.String registroPatronal) {
        this.registroPatronal = registroPatronal;
    }


    /**
     * Gets the sexo value for this _return.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this _return.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the telefono value for this _return.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this _return.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the tipoPension value for this _return.
     * 
     * @return tipoPension
     */
    public java.lang.String getTipoPension() {
        return tipoPension;
    }


    /**
     * Sets the tipoPension value for this _return.
     * 
     * @param tipoPension
     */
    public void setTipoPension(java.lang.String tipoPension) {
        this.tipoPension = tipoPension;
    }


    /**
     * Gets the turno value for this _return.
     * 
     * @return turno
     */
    public java.lang.String getTurno() {
        return turno;
    }


    /**
     * Sets the turno value for this _return.
     * 
     * @param turno
     */
    public void setTurno(java.lang.String turno) {
        this.turno = turno;
    }


    /**
     * Gets the vigenteHasta value for this _return.
     * 
     * @return vigenteHasta
     */
    public java.lang.String getVigenteHasta() {
        return vigenteHasta;
    }


    /**
     * Sets the vigenteHasta value for this _return.
     * 
     * @param vigenteHasta
     */
    public void setVigenteHasta(java.lang.String vigenteHasta) {
        this.vigenteHasta = vigenteHasta;
    }


    /**
     * Gets the idPersona value for this _return.
     * 
     * @return idPersona
     */
    public java.lang.Integer getIdPersona() {
        return idPersona;
    }


    /**
     * Sets the idPersona value for this _return.
     * 
     * @param idPersona
     */
    public void setIdPersona(java.lang.Integer idPersona) {
        this.idPersona = idPersona;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VigenciaderechosRespuesta)) return false;
        VigenciaderechosRespuesta other = (VigenciaderechosRespuesta) obj;
        if (!(obj instanceof Object)) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean comparador;
        comparador = ((this.agregadoAfiliacion==null && other.getAgregadoAfiliacion()==null) || 
             (this.agregadoAfiliacion!=null &&
              this.agregadoAfiliacion.equals(other.getAgregadoAfiliacion()))) &&
            ((this.agregadoMedico==null && other.getAgregadoMedico()==null) || 
             (this.agregadoMedico!=null &&
              this.agregadoMedico.equals(other.getAgregadoMedico()))) &&
            ((this.beneficiarios==null && other.getBeneficiarios()==null) || 
             (this.beneficiarios!=null &&
              java.util.Arrays.equals(this.beneficiarios, other.getBeneficiarios()))) &&
            ((this.clavePresupuestal==null && other.getClavePresupuestal()==null) || 
             (this.clavePresupuestal!=null &&
              this.clavePresupuestal.equals(other.getClavePresupuestal()))) &&
            ((this.colonia==null && other.getColonia()==null) || 
             (this.colonia!=null &&
              this.colonia.equals(other.getColonia()))) &&
            ((this.conDerechoInc==null && other.getConDerechoInc()==null) || 
             (this.conDerechoInc!=null &&
              this.conDerechoInc.equals(other.getConDerechoInc()))) &&
            ((this.conDerechoSm==null && other.getConDerechoSm()==null) || 
             (this.conDerechoSm!=null &&
              this.conDerechoSm.equals(other.getConDerechoSm()))) &&
            ((this.consultorio==null && other.getConsultorio()==null) || 
             (this.consultorio!=null &&
              this.consultorio.equals(other.getConsultorio()))) &&
            ((this.cpid==null && other.getCpid()==null) || 
             (this.cpid!=null &&
              this.cpid.equals(other.getCpid()))) &&
            ((this.curp==null && other.getCurp()==null) || 
             (this.curp!=null &&
              this.curp.equals(other.getCurp()))) &&
            ((this.dhDeleg==null && other.getDhDeleg()==null) || 
             (this.dhDeleg!=null &&
              this.dhDeleg.equals(other.getDhDeleg()))) &&
            ((this.dhIpServer==null && other.getDhIpServer()==null) || 
             (this.dhIpServer!=null &&
              this.dhIpServer.equals(other.getDhIpServer()))) &&
            ((this.dhUMF==null && other.getDhUMF()==null) || 
             (this.dhUMF!=null &&
              this.dhUMF.equals(other.getDhUMF()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
             (this.fechaNacimiento!=null &&
              this.fechaNacimiento.equals(other.getFechaNacimiento()))) &&
            ((this.idee==null && other.getIdee()==null) || 
             (this.idee!=null &&
              this.idee.equals(other.getIdee()))) &&
            ((this.materno==null && other.getMaterno()==null) || 
             (this.materno!=null &&
              this.materno.equals(other.getMaterno()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.nss==null && other.getNss()==null) || 
             (this.nss!=null &&
              this.nss.equals(other.getNss()))) &&
            ((this.paterno==null && other.getPaterno()==null) || 
             (this.paterno!=null &&
              this.paterno.equals(other.getPaterno()))) &&
            ((this.registroPatronal==null && other.getRegistroPatronal()==null) || 
             (this.registroPatronal!=null &&
              this.registroPatronal.equals(other.getRegistroPatronal()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.tipoPension==null && other.getTipoPension()==null) || 
             (this.tipoPension!=null &&
              this.tipoPension.equals(other.getTipoPension()))) &&
            ((this.turno==null && other.getTurno()==null) || 
             (this.turno!=null &&
              this.turno.equals(other.getTurno()))) &&
            ((this.vigenteHasta==null && other.getVigenteHasta()==null) || 
             (this.vigenteHasta!=null &&
              this.vigenteHasta.equals(other.getVigenteHasta()))) &&
            ((this.idPersona==null && other.getIdPersona()==null) || 
             (this.idPersona!=null &&
              this.idPersona.equals(other.getIdPersona())));
        __equalsCalc = null;
        return comparador;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAgregadoAfiliacion() != null) {
            _hashCode += getAgregadoAfiliacion().hashCode();
        }
        if (getAgregadoMedico() != null) {
            _hashCode += getAgregadoMedico().hashCode();
        }
        if (getBeneficiarios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBeneficiarios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBeneficiarios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getClavePresupuestal() != null) {
            _hashCode += getClavePresupuestal().hashCode();
        }
        if (getColonia() != null) {
            _hashCode += getColonia().hashCode();
        }
        if (getConDerechoInc() != null) {
            _hashCode += getConDerechoInc().hashCode();
        }
        if (getConDerechoSm() != null) {
            _hashCode += getConDerechoSm().hashCode();
        }
        if (getConsultorio() != null) {
            _hashCode += getConsultorio().hashCode();
        }
        if (getCpid() != null) {
            _hashCode += getCpid().hashCode();
        }
        if (getCurp() != null) {
            _hashCode += getCurp().hashCode();
        }
        if (getDhDeleg() != null) {
            _hashCode += getDhDeleg().hashCode();
        }
        if (getDhIpServer() != null) {
            _hashCode += getDhIpServer().hashCode();
        }
        if (getDhUMF() != null) {
            _hashCode += getDhUMF().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        if (getIdee() != null) {
            _hashCode += getIdee().hashCode();
        }
        if (getMaterno() != null) {
            _hashCode += getMaterno().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNss() != null) {
            _hashCode += getNss().hashCode();
        }
        if (getPaterno() != null) {
            _hashCode += getPaterno().hashCode();
        }
        if (getRegistroPatronal() != null) {
            _hashCode += getRegistroPatronal().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getTipoPension() != null) {
            _hashCode += getTipoPension().hashCode();
        }
        if (getTurno() != null) {
            _hashCode += getTurno().hashCode();
        }
        if (getVigenteHasta() != null) {
            _hashCode += getVigenteHasta().hashCode();
        }
        if (getIdPersona() != null) {
            _hashCode += getIdPersona().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VigenciaderechosRespuesta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vigenciaderechos.imss.gob.mx/", "return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregadoAfiliacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AgregadoAfiliacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregadoMedico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AgregadoMedico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiarios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Beneficiarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vigenciaderechos.imss.gob.mx/", "InfoAseguradoVO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clavePresupuestal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClavePresupuestal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colonia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Colonia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conDerechoInc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConDerechoInc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conDerechoSm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConDerechoSm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultorio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Consultorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cpid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Cpid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("curp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Curp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dhDeleg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DhDeleg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dhIpServer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DhIpServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dhUMF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DhUMF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Idee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Materno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nss");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Nss"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paterno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Paterno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroPatronal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistroPatronal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPension");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TipoPension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("turno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Turno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vigenteHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VigenteHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

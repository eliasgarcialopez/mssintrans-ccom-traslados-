package mx.gob.imss.mssintrans.ccom.traslados.siap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "consultaMatriculaResult"
})
@XmlRootElement(name = "ConsultaMatriculaResponse")
public class ConsultaMatriculaResponse {

    @XmlElement(name = "ConsultaMatriculaResult")
    protected ConsultaMatriculaResponse.ConsultaMatriculaResult consultaMatriculaResult;

    /**
     * Obtiene el valor de la propiedad consultaMatriculaResult.
     * 
     * @return
     *     possible object is
     *     {@link ConsultaMatriculaResponse.ConsultaMatriculaResult }
     *     
     */
    public ConsultaMatriculaResponse.ConsultaMatriculaResult getConsultaMatriculaResult() {
        return consultaMatriculaResult;
    }

    /**
     * Define el valor de la propiedad consultaMatriculaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultaMatriculaResponse.ConsultaMatriculaResult }
     *     
     */
    public void setConsultaMatriculaResult(ConsultaMatriculaResponse.ConsultaMatriculaResult value) {
        this.consultaMatriculaResult = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    @XmlRootElement(name = "ConsultaMatriculaResult")
    public static class ConsultaMatriculaResult {

        @XmlMixed
        @XmlAnyElement(lax = true)
        protected List<Object> content;

        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<>();
            }
            return this.content;
        }
    }
}
package mx.gob.imss.mssintrans.ccom.traslados.siap;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.imss.consultamatricula
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaMatriculaResponse }
     * 
     */
    public ConsultaMatriculaResponse createConsultaMatriculaResponse() {
        return new ConsultaMatriculaResponse();
    }

    /**
     * Create an instance of {@link ConsultaMatriculaResponse.ConsultaMatriculaResult }
     * 
     */
    public ConsultaMatriculaResponse.ConsultaMatriculaResult createConsultaMatriculaResponseConsultaMatriculaResult() {
        return new ConsultaMatriculaResponse.ConsultaMatriculaResult();
    }

    /**
     * Create an instance of {@link ConsultaMatricula }
     * 
     */
    public ConsultaMatricula createConsultaMatricula() {
        return new ConsultaMatricula();
    }

}

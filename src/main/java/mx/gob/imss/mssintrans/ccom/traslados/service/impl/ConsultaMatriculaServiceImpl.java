package mx.gob.imss.mssintrans.ccom.traslados.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.mssintrans.ccom.traslados.dto.Empleado;
import mx.gob.imss.mssintrans.ccom.traslados.service.ConsultaMatriculaService;
import mx.gob.imss.mssintrans.ccom.traslados.siap.ConsultaMatriculaCliente;
import mx.gob.imss.mssintrans.ccom.traslados.siap.ConsultaMatriculaResponse;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Slf4j
@Service
public class ConsultaMatriculaServiceImpl implements ConsultaMatriculaService {

    @Autowired
    private ConsultaMatriculaCliente consultaMatriculaCliente;

    public Empleado consultaMatricula(String matricula) {
    	
    	log.info("Matricula a buscar: " + matricula);

        ConsultaMatriculaResponse.ConsultaMatriculaResult result = consultaMatriculaCliente.consultaNss(matricula);

        return jaxbObjectToJSON(result);

    }

    private Empleado jaxbObjectToJSON(ConsultaMatriculaResponse.ConsultaMatriculaResult ret) {
    	
    	Empleado matricula = null;
    	
        try {
        	
            JAXBContext jaxbContext = JAXBContext.newInstance(ConsultaMatriculaResponse.ConsultaMatriculaResult.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(ret, sw);
           
            if( sw.toString().contains("errores") ) {
            	
            	log.info("No se encontro el registro.");
            	
            }else {
            	
            	org.json.JSONObject json = XML.toJSONObject(sw.toString());

                json = (JSONObject) json.get("ConsultaMatriculaResult");
                
                json = (JSONObject) json.get("NewDataSet");
                
                json = (JSONObject) json.get("EMPLEADOS");
                
                Gson gson = new Gson();
                matricula = gson.fromJson(json.toString(), Empleado.class);
            	
            }
            

        } catch (JAXBException e) {
            log.info("Error!" + e.getMessage());
        }
        
        return matricula;
    }
}

package mx.gob.imss.mssintrans.ccom.traslados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "response")
public class Respuesta<T> {
    @JsonProperty
    private int codigo;
    
    @JsonProperty
    private String mensaje;
    
    @JsonProperty
    private boolean error;
    
    @JsonProperty
    private T datos;
}

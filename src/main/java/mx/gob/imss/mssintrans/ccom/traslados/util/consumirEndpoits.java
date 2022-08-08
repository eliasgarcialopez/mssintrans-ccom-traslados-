package mx.gob.imss.mssintrans.ccom.traslados.util;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class consumirEndpoits {
	@Autowired
	private RestTemplate restTemplate;
	
	public String servicioEndpoint(String cveEspecialidad) {
		if (cveEspecialidad == null || cveEspecialidad.isEmpty()) {
			log.error("No se recibio clave de ubicacion");
			return null;
		}

		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.ALL);
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>(header);
		
		
		ResponseEntity<String> responseEntity = null;

		try {
			responseEntity = restTemplate.exchange("localhost:8086/mssintetrans-carga-archivos/save/", HttpMethod.POST, entity,
					new ParameterizedTypeReference<String>() {
					});
		} catch (Exception e) {
			log.error("Fallo al consumir el servicio");
		}

		String descripcion = "";
		String cveEspecialidadArray = "";

		if (responseEntity != null && responseEntity.getBody() != null) {
			JSONArray json = new JSONArray(responseEntity.getBody());
			int numeroDeElementos = json.length();

			for (int i = 0; i <= numeroDeElementos; i++) {
				JSONObject ubicacion = json.getJSONObject(i);
				cveEspecialidadArray = ubicacion.getString("EJEMPLO_JSON");

				if (cveEspecialidadArray.equals(cveEspecialidad)) {
					descripcion = ubicacion.getString("EJEMPLO_ELEMENTO");
					break;
				}
			}
		}

		return descripcion;
	}
}

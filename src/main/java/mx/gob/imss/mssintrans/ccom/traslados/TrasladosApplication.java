package mx.gob.imss.mssintrans.ccom.traslados;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import mx.gob.imss.mssintrans.ccom.traslados.util.NoRedirectSimpleClientHttpRequestFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;



@SpringBootApplication
public class TrasladosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TrasladosApplication.class, args);
	}

	 @Bean
	    public RestTemplate restTemplate() {
	        //        return new RestTemplate(factory);
	        return new RestTemplateBuilder()
	                .requestFactory(NoRedirectSimpleClientHttpRequestFactory.class)
	                .setConnectTimeout(Duration.ofMillis(3000))
	                .setReadTimeout(Duration.ofMillis(3000))
	                .build();
	    }
}

package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.CodigoPostalEntity;



public interface CodigoPostalRepository extends JpaRepository<CodigoPostalEntity, Integer> {
	@Query(value=""
    		+ "SELECT	*																						"
    		+ "FROM		SINTRANSC_CODIGO_POSTAL SCP																"
    		+ "INNER	JOIN SINTRANSC_MUNICIPIOS SMU ON SMU.ID_MUNICIPIO = SCP.ID_MUNICIPIO					"
    		+ "INNER 	JOIN SINTRANSC_ENTIDADES SEN ON SEN.ID_ENTIDAD = SMU.ID_ENTIDAD							"
//    		+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SUA ON SUA.ID_CODIGO_POSTAL = SCP.ID_CODIGO_POSTAL	"
    		+ "WHERE	SCP.CVE_COD_POSTAL IN (?)",nativeQuery = true)
    List<CodigoPostalEntity> consultaCodigoPostal (String codigoPostal);
	
}

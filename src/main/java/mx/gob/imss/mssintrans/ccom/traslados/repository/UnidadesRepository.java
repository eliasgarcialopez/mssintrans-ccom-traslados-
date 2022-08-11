package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.UnidadesEntity;



public interface UnidadesRepository extends JpaRepository<UnidadesEntity, Integer> {

	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANSC_UNIDADES_ADSCRIPCION SCA "
			+ "INNER	JOIN SINTRANSC_OOAD SOO ON SOO.ID_OOAD = SCA.ID_OOAD "
			+ "WHERE 	SCA.ID_OOAD = ? "
			+ "AND   	SCA.IND_ACTIVO 	= '1' 						"
			+ "",
			countQuery = "SELECT	count(*) FROM		SINTRANSC_UNIDADES_ADSCRIPCION SCA INNER	JOIN SINTRANSC_OOAD SOO ON SOO.ID_OOAD = SCA.ID_OOAD "
					+ "WHERE 	SCA.ID_OOAD = ? AND   	SCA.IND_ACTIVO 	= '1' 						",
			nativeQuery = true )
	List<UnidadesEntity> consultaPorOoad (Integer idOoad);
	
	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANSC_UNIDADES_ADSCRIPCION SCA "
			+ "INNER	JOIN SINTRANSC_OOAD SOO ON SOO.ID_OOAD = SCA.ID_OOAD "
			+ "WHERE 	 SCA.IND_ACTIVO 	= '1' "
			,
			countQuery = "SELECT	count(*) FROM		SINTRANSC_UNIDADES_ADSCRIPCION SCA INNER	JOIN SINTRANSC_OOAD SOO ON SOO.ID_OOAD = SCA.ID_OOAD "
					+ "WHERE    	SCA.IND_ACTIVO 	= '1' 						",
			nativeQuery = true )
	List<UnidadesEntity> consultaGeneral ();
}

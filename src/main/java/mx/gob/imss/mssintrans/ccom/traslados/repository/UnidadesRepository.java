package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.UnidadesAdscripcionEntity;
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
	
    //@Query(value = "select us.unidad from UsuariosEntity us where us.matricula = ?1 and us.unidad.desTipoUnidad = 'CENTRACOM' and us.activo = true")
    //UnidadesAdscripcionEntity findUnidadCentracom(String matricula);
	
	@Query(value = "SELECT ua.ID_UNIDAD_ADSCRIPCION, ua.NOM_UNIDAD_ADSCRIPCION "
			+ "FROM SINTRANSC_USUARIOS us "
			+ "LEFT JOIN SINTRANSC_UNIDADES_ADSCRIPCION ua USING (ID_UNIDAD_ADSCRIPCION) "
			+ "WHERE us.CVE_MATRICULA = ?1 "
			+ "AND ua.DES_TIPO_UNIDAD = 'CENTRACOM' "
			+ "AND us.IND_ACTIVO = 1 ", nativeQuery = true)
	UnidadesEntity findUnidadCentracom(String matricula);
	
}

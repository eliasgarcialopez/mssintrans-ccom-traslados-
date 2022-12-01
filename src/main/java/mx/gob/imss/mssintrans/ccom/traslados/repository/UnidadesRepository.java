package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	@Query(value = "SELECT ua.ID_UNIDAD_ADSCRIPCION, ua.NOM_UNIDAD_ADSCRIPCION "
			+ "FROM SINTRANSC_USUARIOS us "
			+ "LEFT JOIN SINTRANSC_UNIDADES_ADSCRIPCION ua USING (ID_UNIDAD_ADSCRIPCION) "
			+ "WHERE us.CVE_MATRICULA = ?1 "
			+ "AND ua.DES_TIPO_UNIDAD = 'CENTRACOM' "
			+ "AND us.IND_ACTIVO = 1 ", nativeQuery = true)
	UnidadesEntity findUnidadCentracom(String matricula);
	
    @Query(value = "SELECT * FROM SINTRANSC_UNIDADES_ADSCRIPCION UA WHERE UA.ID_UNIDAD_ADSCRIPCION = :idUnidadAdscripcion  AND UA.IND_ACTIVO = 1", nativeQuery = true)
    UnidadesEntity findUnidadesAdscripcionById(@Param("idUnidadAdscripcion") Integer idUnidadAdscripcion);
	
	@Query(value= " SELECT UD.ID_UNIDAD_ADSCRIPCION FROM SINTRANSC_USUARIOS U INNER JOIN SINTRANSC_UNIDADES_ADSCRIPCION UD "
		    + " ON U.ID_UNIDAD_ADSCRIPCION = UD.ID_UNIDAD_ADSCRIPCION WHERE U.DES_ESTATUS_USUARIO = 3  "
		    + " AND UD.IND_ACTIVO = 1 AND U.CVE_MATRICULA = ? " ,
		nativeQuery = true)
	Integer findUnidadCentraComByCveMatricula(String matricula);
	
}

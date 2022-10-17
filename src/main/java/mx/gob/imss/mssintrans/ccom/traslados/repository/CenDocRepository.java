package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;

public interface CenDocRepository extends JpaRepository<CenDocEntity, Integer> {
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE SINTRANST_CENSO_DOCTORES  "
			+ " "
			+ "SET 	 "
			+ "		ID_UNIDAD=?, "
			+ "		DES_ESTATUS=?, "
			+ "		CVE_MATRICULA=?, "
			+ "		FEC_ACTUALIZACION = NOW() "
			+ " "
			+ "WHERE 	ID_CENSO=?"
			,nativeQuery = true )
	void actualizar (
			Integer idUnidad,
			String desEstatus,
			String cveMatricula,
			Integer idCenso );
	
	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
			+ "WHERE    SCC.ID_CENSO = ? "
			+ "AND   	SCC.IND_ACTIVO 	= '1' "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)"
					+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
					+ "WHERE    SCC.ID_CENSO = ? "
					+ "AND   	SCC.IND_ACTIVO 	= '1' "
					+ "",
			nativeQuery = true )
	CenDocEntity consultaPorId (Integer idCenso);
	
	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
			+ "WHERE    SCC.CVE_MATRICULA_DOCTOR = ? "
			+ "AND   	SCC.IND_ACTIVO 	= '1' "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)"
					+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
					+ "WHERE    SCC.CVE_MATRICULA_DOCTOR = ? "
					+ "AND   	SCC.IND_ACTIVO 	= '1' "
					+ "",
			nativeQuery = true )
	CenDocEntity consultaPorMat (String matricula);
	
	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
			+ "WHERE    SCC.IND_ACTIVO 	= '1' "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)"
					+ "FROM		SINTRANST_CENSO_DOCTORES SCC "
					+ "WHERE    SCC.IND_ACTIVO 	= '1' "
					+ "",
			nativeQuery = true )
	List<CenDocEntity> consultaMat ();
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE SINTRANST_CENSO_DOCTORES "
			+ "SET	"
			+ "FEC_BAJA	= NOW(), "
			+ "IND_ACTIVO = 0 "
			+ "WHERE IND_ACTIVO = '1' "
			+ "AND ID_CENSO = ? "
			,nativeQuery = true )
	void eliminar ( int id );
	
	@Query(value = ""
			+ " SELECT	* "
			+ " FROM SINTRANST_CENSO_DOCTORES SCC INNER JOIN SINTRANSC_UNIDADES_ADSCRIPCION SUA "
			+ " ON SCC.ID_UNIDAD = SUA.ID_UNIDAD_ADSCRIPCION WHERE SCC.CVE_MATRICULA_DOCTOR = :matricula "
			+ " AND SCC.IND_ACTIVO = '1' AND SUA.IND_ACTIVO = '1'"
			+ "",
			countQuery = ""
					+ " SELECT	COUNT(*) "
					+ " FROM SINTRANST_CENSO_DOCTORES SCC INNER JOIN SINTRANSC_UNIDADES_ADSCRIPCION SUA "
					+ " ON SCC.ID_UNIDAD = SUA.ID_UNIDAD_ADSCRIPCION WHERE SCC.CVE_MATRICULA_DOCTOR = :matricula "
					+ " AND SCC.IND_ACTIVO = '1' AND SUA.IND_ACTIVO = '1' "
					+ "",
			nativeQuery = true )
	Page<CenDocEntity> consultaGeneral(@Param("matricula")String matricula, Pageable pageable);
	
	@Query(value = ""
			+ " SELECT	* "
			+ " FROM SINTRANST_CENSO_DOCTORES SCC INNER JOIN SINTRANSC_UNIDADES_ADSCRIPCION SUA "
			+ " ON SCC.ID_UNIDAD = SUA.ID_UNIDAD_ADSCRIPCION WHERE SCC.CVE_MATRICULA_DOCTOR = :matricula "
			+ " AND SUA.ID_OOAD = :ooad AND SCC.IND_ACTIVO = '1' AND SUA.IND_ACTIVO = '1'"
			+ "",
			countQuery = ""
					+ " SELECT	COUNT(*) "
					+ " FROM SINTRANST_CENSO_DOCTORES SCC INNER JOIN SINTRANSC_UNIDADES_ADSCRIPCION SUA "
					+ " ON SCC.ID_UNIDAD = SUA.ID_UNIDAD_ADSCRIPCION WHERE SCC.CVE_MATRICULA_DOCTOR = :matricula "
					+ " AND SUA.ID_OOAD = :ooad AND SCC.IND_ACTIVO = '1' AND SUA.IND_ACTIVO = '1' "
					+ "",
			nativeQuery = true )
	Page<CenDocEntity> consultaGeneralPorOoad(@Param("matricula")String matricula, @Param("ooad") Integer ooad, Pageable pageable);
}

package mx.gob.imss.mssintrans.ccom.traslados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.CenDocEntity;

public interface CenDocRepository extends JpaRepository<CenDocEntity, Integer> {
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE SINTRANST_CENSO_DOCTORES  "
			+ " "
			+ "SET 	 "
			+ "		CVE_MATRICULA=?, "
			+ "		ID_UNIDAD=?, "
			+ "		DES_ESTATUS=?, "
			+ "		CVE_MATRICULA_AUDITORIA=?, "
			+ "		FEC_ACTUALIZACION=CURRENT_DATE() "
			+ " "
			+ "WHERE 	ID_CENSO=?"
			,nativeQuery = true )
	void actualizar (
			Integer cveMatricula,
			Integer idUnidad,
			String desEstatus,
			String cveMatriculaAud,
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
	
}

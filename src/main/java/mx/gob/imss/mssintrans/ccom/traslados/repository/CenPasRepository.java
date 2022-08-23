package mx.gob.imss.mssintrans.ccom.traslados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import mx.gob.imss.mssintrans.ccom.traslados.model.CenPasEntity;

public interface CenPasRepository extends JpaRepository<CenPasEntity, Integer> {
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE SINTRANST_CENSO_PACIENTES  "
			+ " "
			+ "SET 	 "
			+ "		DES_ESTATUS=?, "
			+ "		IND_LUNES=?, "
			+ "		IND_MARTES=?, "
			+ "		IND_MIERCOLES=?, "
			+ "		IND_JUEVES=?, "
			+ "		IND_VIERNES=?, "
			+ "		IND_SABADO=?, "
			+ "		IND_DOMINGO=?, "
			+ "		CVE_MATRICULA=?, "
			+ "		FEC_ACTUALIZACION=CURRENT_DATE() "
			+ " "
			+ "WHERE 	ID_CENSO=?"
			,nativeQuery = true )
	void actualizar (
			String desEstatus,
			Integer lunes,
			Integer martes,
			Integer miercoles,
			Integer jueves,
			Integer viernes,
			Integer sabado,
			Integer domingo,
			String cveMatricula,
			Integer idCenso );
	
	@Query(value = ""
			+ "SELECT	* "
			+ "FROM		SINTRANST_CENSO_PACIENTES SCP "
			+ "WHERE    SCP.ID_CENSO = ? "
			+ "AND   	SCP.IND_ACTIVO 	= '1' "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)"
					+ "FROM		SINTRANST_CENSO_PACIENTES SCP "
					+ "WHERE    SCP.ID_CENSO = ? "
					+ "AND   	SCP.IND_ACTIVO 	= '1' "
					+ "",
			nativeQuery = true )
	CenPasEntity consultaPorId (Integer idCenso);
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE SINTRANST_CENSO_PACIENTES "
			+ "SET	"
			+ "FEC_BAJA	= CURRENT_DATE(), "
			+ "IND_ACTIVO = 0 "
			+ "WHERE IND_ACTIVO = '1' "
			+ "AND ID_CENSO = ? "
			,nativeQuery = true )
	void eliminar ( int id );
	
}

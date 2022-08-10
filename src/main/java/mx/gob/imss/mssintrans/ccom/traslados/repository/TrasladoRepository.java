package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;

public interface TrasladoRepository extends JpaRepository<TrasladoEntity, Integer> {

	@Query(value = ""
			+ "SELECT	SSI.*															 "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			//+ "INNER 	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'"
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)															 "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
				//	+ "INNER	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
					+ "WHERE   	SST.IND_ACTIVO 	= '1'"
					,
			nativeQuery = true )
	Page<TrasladoEntity>consultaGeneral(Pageable paginado);
	
	@Query(value = ""
			+ "SELECT	SSI.*															 "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			//+ "INNER 	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'"
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)															 "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
				//	+ "INNER	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
					+ "WHERE   	SST.IND_ACTIVO 	= '1', SST.ID_SOLICITUD 	= ? "
					,
			nativeQuery = true )
	TrasladoEntity consultaPorId(int id);
	
	Optional<TrasladoEntity> findByIdSolicitudAndIndActivoEquals(Integer id, Integer activo);
}

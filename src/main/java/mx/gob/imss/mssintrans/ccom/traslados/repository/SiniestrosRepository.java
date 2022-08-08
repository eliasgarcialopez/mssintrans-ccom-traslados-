package mx.gob.imss.mssintrans.ccom.traslados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.gob.imss.mssintrans.ccom.traslados.dto.DatosUsuarioDTO;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestroDetalleEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestrosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosArrendadosEntity;

import java.util.List;
import java.util.Optional;


public interface SiniestrosRepository extends JpaRepository<SiniestrosEntity, Integer> {

	@Query(value = ""
			+ "SELECT	SSI.*,SVE.ID_VEHICULO,SVE.NUM_PLACAS															 "
			+ "FROM		SINTRANST_SINIESTROS SSI   									 "
			+ "INNER 	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "			
			+ "WHERE   	SVE.IND_ACTIVO 	= '1' AND SSI.IND_ACTIVO='1' AND SVE.ID_UNIDAD_ADSCRIPCION=? AND SVE.DES_ESTATUS_ENAJENACION IS NULL AND (SVE.DES_ESTATUS_VEHICULO ='Siniestrado' OR SVE.DES_ESTATUS_VEHICULO = 'Siniestrado Transito') "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)															 "
					+ "FROM		SINTRANST_SINIESTROS SSI   									 "
					+ "INNER	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
					+ "WHERE   	SVE.IND_ACTIVO 	= '1' AND SSI.IND_ACTIVO='1' AND SVE.ID_UNIDAD_ADSCRIPCION=? AND SVE.DES_ESTATUS_ENAJENACION IS NULL AND (SVE.DES_ESTATUS_VEHICULO ='Siniestrado' OR SVE.DES_ESTATUS_VEHICULO = 'Siniestrado Transito') "
					,
			nativeQuery = true )
	Page<List<SiniestrosEntity>> consultaGeneral ( Pageable paginado, int usuarioUnidad);
	
	@Query(value = ""
			+ "SELECT	SSI.*,SVE.*															 "
			+ "FROM		SINTRANST_SINIESTROS SSI   									 "
			+ "INNER 	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
			+ "WHERE   	SVE.IND_ACTIVO 	= '1' AND SSI.IND_ACTIVO='1' AND SVE.DES_ESTATUS_ENAJENACION IS NULL AND (SVE.DES_ESTATUS_VEHICULO ='Siniestrado' OR SVE.DES_ESTATUS_VEHICULO = 'Siniestrado Transito') AND SVE.CVE_ECCO=? AND SVE.ID_UNIDAD_ADSCRIPCION=?  "
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)															 "
					+ "FROM		SINTRANST_SINIESTROS SSI   									 "
					+ "INNER	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
					+ "WHERE   	SVE.IND_ACTIVO 	= '1' AND SSI.IND_ACTIVO='1' AND SVE.DES_ESTATUS_ENAJENACION IS NULL AND (SVE.DES_ESTATUS_VEHICULO ='Siniestrado' OR SVE.DES_ESTATUS_VEHICULO = 'Siniestrado Transito') AND SVE.CVE_ECCO=? AND SVE.ID_UNIDAD_ADSCRIPCION=?   "
					,
			nativeQuery = true )
	Page<List<SiniestrosEntity>> consultaGeneralPorEcco ( String ecco,  int usuarioUnidad, Pageable paginado);
	
	@Query(value = ""
			+ "SELECT  	/* QUERY PARA OBTENER 1 REGISTRO; ACTIVO; POR ID*/ 					"
			+ "        	*  																	"
			+ "FROM    	SINTRANST_SINIESTROS 												"
			+ "WHERE 	IND_ACTIVO 								= '1' 						"
			+ "AND 		ID_SINIESTRO 							= ?							"
			,nativeQuery = true )
	SiniestrosEntity getId ( int id );
	
	

	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE  	/* QUERY PARA BORRAR UN VEHICULO DEL SISTEMA; POR BANDERA */ 		"
			+ "        	SINTRANST_SINIESTROS 												"
			+ "SET      																	"
			+ "        	FEC_BAJA                    			= CURRENT_TIMESTAMP(),			"
			+ "        	IND_ACTIVO                  			= 0	 						"
			+ "WHERE   	IND_ACTIVO 								= '1' 						"
			+ "AND 		ID_SINIESTRO 							= ?  						"
			,nativeQuery = true )
	void eliminar(int id);

	
	
	
	
	@Modifying(flushAutomatically = true)
	@Query(value = "" + "UPDATE 	SINTRANST_SINIESTROS  " + " " + "SET 	 " + "		FEC_INGRESO_TALLER=?, "
			+ "		FEC_SALIDA_TALLER=?, " + "		OBSERVACIONES=?, " + "		DES_RUTA_DECLARACION=?, "
			+ "		DES_RUTA_REPORTE_ACCID=?, " + "		DES_RUTA_REPORTE_FOTO=?, "
			+ "		FEC_ACTUALIZACION=CURRENT_TIMESTAMP() " + " " + "WHERE 	ID_SINIESTRO=?", nativeQuery = true)
	void actualizar(String fechaIngreso, String fechaSalida, String observaciones, String desRutaDeclaracion,
			String desRutaReproteAcc, String desRutaReporteFoto, int idSiniestro);
	
	@Modifying(flushAutomatically = true)
	@Query(value = ""
			+ "UPDATE  	/* QUERY PARA BORRAR UN VEHICULO DEL SISTEMA; POR BANDERA */ 	"
			+ "        	SINTRANST_SINIESTROS 											"
			+ "SET      																"
			+ "			DES_RUTA_DECLARACION 		= ?, 						"
			+ " 		DES_RUTA_REPORTE_ACCID 				= ?, 						"		
			+ " 		DES_RUTA_REPORTE_FOTO 				= ? 						"
			+ "        	 																"
			+ "WHERE   	IND_ACTIVO = '1' AND ID_SINIESTRO 	= ?  						"
			,nativeQuery = true )
	void updateRutas(		
			String desRutaDeclaracion,
			String desRutaReproteAcc, 
			String desRutaReporteFoto, int idSiniestro);
	
	 Optional<SiniestrosEntity> findByIdSiniestroAndIndActivoEquals(Integer id, Integer activo);
	 
	 @Query(value = ""
				+ "SELECT	SSI.*,SVE.*															 "
				+ "FROM		SINTRANST_SINIESTROS SSI   									 "
				+ "INNER 	JOIN SINTRANST_VEHICULOS SVE ON SVE.ID_VEHICULO = SSI.ID_VEHICULO "
				+ "WHERE   	SVE.IND_ACTIVO 	= '1' AND SSI.IND_ACTIVO='1' AND SVE.DES_ESTATUS_ENAJENACION IS NULL AND (SVE.DES_ESTATUS_VEHICULO ='Siniestrado' OR SVE.DES_ESTATUS_VEHICULO = 'Siniestrado Transito') AND SVE.ID_VEHICULO=? "
				+ "",
				nativeQuery = true )
	List<SiniestrosEntity> getIdSiniestroVehiculo ( int id );
	 
}

package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladosEntity;

public interface TrasladosRepository extends JpaRepository<TrasladosEntity, Integer> {

	@Query(value = ""
			+ "SELECT	SST.*															 "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE"			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION"			
			+ "INNER 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL"			
			+ "INNER 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO"			
			+ "INNER 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD"			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'"
			+ "",
			countQuery = ""
					+ "SELECT	COUNT(*)															 "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE"			
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION"			
					+ "INNER 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL"			
					+ "INNER 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO"			
					+ "INNER 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD"			
					+ "WHERE   	SST.IND_ACTIVO 	= '1'"
					,
			nativeQuery = true )
	Page<TrasladosEntity>consultaGeneral(Pageable paginado);
	
	@Query(value = ""
			+ "SELECT	SST.*,SCP.CVE_COD_POSTAL AS CVE_COD_POSTAL, SM.NOM_MUNICIPIO AS NOM_MUNICIPIO, SE.ID_ENTIDAD AS ID_ENTIDAD, SE.NOM_ENTIDAD AS NOM_ENTIDAD															 "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "			
			+ "INNER 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
			+ "INNER 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
			+ "INNER 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'                                                                               "
			+ "",
			nativeQuery = true )
	TrasladosEntity consultaPorId(int id);
	
}

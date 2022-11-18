package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladosEntity;

public interface TrasladosRepository extends JpaRepository<TrasladosEntity, Integer> {

	@Query(value = ""
			+ "SELECT DISTINCT SST.*, SAS.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_SOLICITANTE, SAA.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_ADSCRIPCION, SAD.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_DESTINO,"
			+ "         SCP.CVE_COD_POSTAL AS CVE_COD_POSTAL, SM.NOM_MUNICIPIO AS NOM_MUNICIPIO, SE.ID_ENTIDAD AS ID_ENTIDAD, SE.NOM_ENTIDAD AS NOM_ENTIDAD, SDR.NOM_DOCTOR AS NOMBRE_DOCTOR_RECIBE, SDA.NOM_DOCTOR AS NOMBRE_DOCTOR_AUTORIZA "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "
			+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
			+ "LEFT  	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
			+ "LEFT  	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
			+ "LEFT  	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
			+ "LEFT  	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
			+ "LEFT  	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1' AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                                "
			+ "",
			countQuery =""
					+ "SELECT DISTINCT	COUNT(SST.ID_SOLICITUD) "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "
					+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
					+ "LEFT     JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
					+ "LEFT     JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
					+ "LEFT    JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE      "			
					+ "LEFT    JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA    "			
					+ "LEFT  	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
					+ "WHERE   	SST.IND_ACTIVO 	= '1'   AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                              "
					+ "",
			nativeQuery = true )
	Page<TrasladosEntity>consultaGeneral(Pageable paginado);
	
	@Query(value = ""
			+ "SELECT DISTINCT	SST.*, SAS.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_SOLICITANTE, SAA.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_ADSCRIPCION, SAD.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_DESTINO,"
			+ "         SCP.CVE_COD_POSTAL AS CVE_COD_POSTAL, SM.NOM_MUNICIPIO AS NOM_MUNICIPIO, SE.ID_ENTIDAD AS ID_ENTIDAD, SE.NOM_ENTIDAD AS NOM_ENTIDAD, SDR.NOM_DOCTOR AS NOMBRE_DOCTOR_RECIBE, SDA.NOM_DOCTOR AS NOMBRE_DOCTOR_AUTORIZA "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "
			+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
			+ "LEFT 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
			+ "LEFT 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
			+ "LEFT 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
			//+ "INNER 	JOIN SINTRANSC_USUARIOS STA ON STA.CVE_MATRICULA = SST.CVE_MATRICULA                                "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'  AND SAS.ID_OOAD = ?     AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                                                                       "
			+ "",
			countQuery =""
					+ "SELECT DISTINCT	COUNT(SST.ID_SOLICITUD) "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "	
					+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
					+ "LEFT 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
					+ "LEFT 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
					+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
					+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
					+ "LEFT 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
				//	+ "INNER 	JOIN SINTRANSC_USUARIOS STA ON STA.CVE_MATRICULA = SST.CVE_MATRICULA                                "			
					+ "WHERE   	SST.IND_ACTIVO 	= '1'  AND SAS.ID_OOAD = ?  AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                                                                          "
					+ "",
			nativeQuery = true )
	Page<TrasladosEntity>consultaGeneralOOAD(Pageable paginado, Integer ooad);
	
	@Query(value = ""
			+ "SELECT DISTINCT	SST.*, SAS.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_SOLICITANTE, SAA.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_ADSCRIPCION, SAD.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_DESTINO,"
			+ "         SCP.CVE_COD_POSTAL AS CVE_COD_POSTAL, SM.NOM_MUNICIPIO AS NOM_MUNICIPIO, SE.ID_ENTIDAD AS ID_ENTIDAD, SE.NOM_ENTIDAD AS NOM_ENTIDAD, SDR.NOM_DOCTOR AS NOMBRE_DOCTOR_RECIBE, SDA.NOM_DOCTOR AS NOMBRE_DOCTOR_AUTORIZA "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "
			+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
			+ "LEFT 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
			+ "LEFT 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
			+ "LEFT 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'  AND SAA.ID_OOAD = ?  AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                                                                       "
			+ "",
			countQuery =""
					+ "SELECT DISTINCT COUNT(SST.ID_SOLICITUD) "
					+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
					+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "	
					+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
					+ "LEFT 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
					+ "LEFT 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
					+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
					+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
					+ "LEFT 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
					+ "WHERE   	SST.IND_ACTIVO 	= '1'  AND SAA.ID_OOAD = ?  AND SST.DES_ESTATUS_SOLICITUD IN ('1','2','3')                                                                          "
					+ "",
			nativeQuery = true )
	Page<TrasladosEntity>consultaGeneralUnidad(Pageable paginado, Integer ooad);
	
	@Query(value = ""
			+ "SELECT DISTINCT SST.*, SAS.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_SOLICITANTE, SAA.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_ADSCRIPCION, SAD.NOM_UNIDAD_ADSCRIPCION AS NOM_UNIDAD_DESTINO,"
			+ "         SCP.CVE_COD_POSTAL AS CVE_COD_POSTAL, SM.NOM_MUNICIPIO AS NOM_MUNICIPIO, SE.ID_ENTIDAD AS ID_ENTIDAD, SE.NOM_ENTIDAD AS NOM_ENTIDAD, SDR.NOM_DOCTOR AS NOMBRE_DOCTOR_RECIBE, SDA.NOM_DOCTOR AS NOMBRE_DOCTOR_AUTORIZA "
			+ "FROM		SINTRANST_SOLICITUD_TRASLADO SST   									 "
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAS ON SAS.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_SOLICITANTE     "			
			+ "INNER 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAA ON SAA.ID_UNIDAD_ADSCRIPCION = SST.ID_UNIDAD_ADSCRIPCION     "	
			+ "LEFT 	JOIN SINTRANSC_UNIDADES_ADSCRIPCION SAD ON SAD.ID_UNIDAD_ADSCRIPCION = SST.CVE_DESTINO         "
			+ "LEFT 	JOIN SINTRANSC_CODIGO_POSTAL SCP ON SCP.ID_CODIGO_POSTAL = SST.ID_CODIGO_POSTAL                     "			
			+ "LEFT 	JOIN SINTRANSC_MUNICIPIOS SM ON SM.ID_MUNICIPIO = SST.ID_MUNICIPIO                                  "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDR ON SDR.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_RECIBE     "			
			+ "LEFT 	JOIN SINTRANST_CENSO_DOCTORES SDA ON SDA.CVE_MATRICULA_DOCTOR = SST.CVE_MATRICULA_DOCTOR_AUTORIZA   "			
			+ "LEFT 	JOIN SINTRANSC_ENTIDADES SE ON SE.ID_ENTIDAD = SM.ID_ENTIDAD                                        "			
			+ "WHERE   	SST.IND_ACTIVO 	= '1'  AND   SST.ID_SOLICITUD = ?                                                   "
			+ "",
			nativeQuery = true )
	TrasladosEntity consultaPorId(int id);
	
}

package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosArrendadosEntity;

public interface VehiculosRepository extends JpaRepository<VehiculosArrendadosEntity, Integer> {
	
	 
	 @Query(value = ""
	            + "SELECT  	SV.*, SA.NOM_ARRENDADORA, SAS.DES_NOMBRE_ASEGURADORA, SRA.ID_REASIGNACION, SC.NOM_CHOFER														 "
	            + "FROM    	SINTRANST_VEHICULOS SV															 "
	            + "LEFT OUTER		JOIN SINTRANST_ARRENDATARIOS SA ON SA.ID_ARRENDATARIO = SV.ID_ARRENDATARIO	 "
	            + "LEFT OUTER		JOIN SINTRANSC_ASEGURADORAS SAS ON SAS.ID_ASEGURADORA = SV.ID_ASEGURADORA	 "
	            + "INNER		JOIN SINTRANST_REASIGNACION_RUTAS SRA ON SRA.ID_VEHICULO = SV.ID_VEHICULO	 "
	            + "INNER		JOIN SINTRANST_CHOFERES SC ON SC.ID_CHOFER = SRA.ID_CHOFER	 "
	            + "WHERE 		SV.IND_ACTIVO 								= '1' AND SV.CVE_ECCO=?1 AND SV.ID_UNIDAD_ADSCRIPCION=?2 AND SV.DES_ESTATUS_VEHICULO='En Transito' AND SRA.FEC_ALTA IS NOT NULL	AND SRA.FEC_ALTA=CURRENT_DATE "
	          //  + "AND			SV.IND_ARRENDADO 							= 1								 "
	            , countQuery = " SELECT COUNT(sv.ID_VEHICULO) FROM SINTRANST_VEHICULOS sv                     "
	            		+ "LEFT OUTER		JOIN SINTRANST_ARRENDATARIOS SA ON SA.ID_ARRENDATARIO = SV.ID_ARRENDATARIO	 "
	     	            + "LEFT OUTER		JOIN SINTRANSC_ASEGURADORAS SAS ON SAS.ID_ASEGURADORA = SV.ID_ASEGURADORA	 "
	     	            + "INNER		JOIN SINTRANST_REASIGNACION_RUTAS SRA ON SRA.ID_VEHICULO = SV.ID_VEHICULO	 "
	     	            + "INNER		JOIN SINTRANST_CHOFERES SC ON SC.ID_CHOFER = SRA.ID_CHOFER	 "
	     	            + "WHERE 		SV.IND_ACTIVO 								= '1'AND SV.DES_ESTATUS_VEHICULO='En Transito' AND SV.CVE_ECCO=?1 AND SV.ID_UNIDAD_ADSCRIPCION=?2   AND SRA.FEC_ALTA IS NOT NULL  AND SRA.FEC_ALTA=CURRENT_DATE  ",
	            nativeQuery = true)
		VehiculosArrendadosEntity obtenerConsultaEcco ( String cveEcco, int usuarioUnidad);
	 
	

}

package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.SiniestrosEntity;
import mx.gob.imss.mssintrans.ccom.traslados.model.VehiculosPropiosEntity;

public interface VehiculoRepository extends JpaRepository<VehiculosPropiosEntity, Integer>{
	
	@Query(value = ""
			+ "UPDATE  	/* QUERY PARA ACTUALIZAR ESTATUS; POR BANDERA */ 		"
			+ "        	SINTRANST_VEHICULOS 												"
			+ "SET      																	"
			+ "        	DES_ESTATUS_VEHICULO                  			= ?	                "
			+ "WHERE   	IND_ACTIVO 								= '1' 						"
			+ "AND 		ID_VEHICULO 							= ?  						"
			+ "AND		IND_ARRENDADO 							= 0							"
			,nativeQuery = true )
	void actualizarEstatus (String estatusVehiculo, int id );
	
	Optional<VehiculosPropiosEntity> findByIdVehiculoAndIndActivoEquals(Integer id, Integer activo);
	

}

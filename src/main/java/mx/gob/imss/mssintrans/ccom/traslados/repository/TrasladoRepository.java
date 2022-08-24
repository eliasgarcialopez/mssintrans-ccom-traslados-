package mx.gob.imss.mssintrans.ccom.traslados.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.imss.mssintrans.ccom.traslados.model.TrasladoEntity;

public interface TrasladoRepository extends JpaRepository<TrasladoEntity, Integer> {

	
		
	Optional<TrasladoEntity> findByIdSolicitudAndIndActivoEquals(Integer id, Integer activo);
}

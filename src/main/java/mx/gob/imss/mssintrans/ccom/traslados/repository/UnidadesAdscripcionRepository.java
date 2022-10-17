package mx.gob.imss.mssintrans.ccom.traslados.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.gob.imss.mssintrans.ccom.traslados.model.UnidadesAdscripcionEntity;

@Repository
public interface UnidadesAdscripcionRepository extends JpaRepository<UnidadesAdscripcionEntity, Integer> {
    
    @Query(value = "select u.nomUnidadAdscripcion from UnidadesAdscripcionEntity u where u.idUnidadAdscripcion = :idUnidadAdscripcion AND u.indActivo = 1")
    String findUnidadesAdscripcionEntityByIdUnidadAdscripcion(@Param("idUnidadAdscripcion") Integer idUnidadAdscripcion);
}
package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANSC_ENTIDADES")
public class EntidadEntity {
	@Id
	@Column(name = "ID_ENTIDAD", unique = false, nullable = true)
	private Integer idEntidad;

	@Column(name = "NOM_ENTIDAD", unique = false, nullable = true)
	private String nomEntidad;
	
	@Column(name = "IND_ACTIVO", unique = false, nullable = true)
    private Integer activo;
    
    @Column(name = "IND_SISTEMA", unique = false, nullable = true)
    private Integer indiceSistema;
}

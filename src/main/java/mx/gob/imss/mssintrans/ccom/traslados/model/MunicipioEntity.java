package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANSC_MUNICIPIOS")
public class MunicipioEntity {
	@Id
	@Column(name = "ID_MUNICIPIO", unique = false, nullable = true)
	private Integer idMunicipio;

	@Column(name = "NOM_MUNICIPIO", unique = false, nullable = true)
	private String nomMunicipio;

	@OneToOne
    @JoinColumn(name = "ID_ENTIDAD", unique = false, nullable = true)
    private EntidadEntity entidades;
	
	@Column(name = "IND_ACTIVO", unique = false, nullable = true)
    private Integer activo;
    
    @Column(name = "IND_SISTEMA", unique = false, nullable = true)
    private Integer indiceSistema;
}

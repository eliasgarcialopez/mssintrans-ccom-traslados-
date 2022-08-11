package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SINTRANSC_UNIDADES_ADSCRIPCION")
public class UnidadesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UNIDAD_ADSCRIPCION", unique = false, nullable = true)
	private Integer idUnidad;

	@Column(name = "NOM_UNIDAD_ADSCRIPCION", unique = false, nullable = true)
	private String nomUnidadAdscripcion;
}

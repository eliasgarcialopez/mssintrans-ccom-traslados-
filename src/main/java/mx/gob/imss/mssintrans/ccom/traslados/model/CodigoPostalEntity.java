package mx.gob.imss.mssintrans.ccom.traslados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SINTRANSC_CODIGO_POSTAL")
public class CodigoPostalEntity {
	@Id
	@Column(name = "ID_CODIGO_POSTAL", unique = false, nullable = true)
	private Integer idCodigoPostal;
	
	@OneToOne
	@JoinColumn(name = "ID_MUNICIPIO", unique = false, nullable = true)
	private MunicipioEntity idMunicipio;
	
	@Column(name = "CVE_COD_POSTAL", unique = false, nullable = true)
	private String cveCodigoPostal;	


	@Column(name = "IND_ACTIVO", unique = false, nullable = true)
    private Integer activo;
    
    @Column(name = "IND_SISTEMA", unique = false, nullable = true)
    private Integer indiceSistema;
}

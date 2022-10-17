package mx.gob.imss.mssintrans.ccom.traslados.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "SINTRANSC_OOAD")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OoadEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2868337678097207101L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OOAD", nullable = false)
    private Integer idOoad;

    @Column(name = "NOM_OOAD", nullable = false, length = 100)
    private String nomOoad;

    @Convert(disableConversion = true)
    @Column(name = "FEC_ALTA")
    private Instant fecAlta;

    @Convert(disableConversion = true)
    @Column(name = "FEC_ACTUALIZACION")
    private Instant fecActualizacion;

    @Convert(disableConversion = true)
    @Column(name = "FEC_BAJA")
    private Instant fecBaja;

    @Column(name = "IND_ACTIVO", nullable = false)
    private Boolean indActivo = false;

    @Column(name = "IND_SISTEMA", nullable = false)
    private Boolean indSistema = false;
}
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
@Table(name = "SINTRANSC_UNIDADES_ADSCRIPCION")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UnidadesAdscripcionEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UNIDAD_ADSCRIPCION", nullable = false)
    private Integer idUnidadAdscripcion;

    @Column(name = "NOM_UNIDAD_ADSCRIPCION", nullable = false, length = 150)
    private String nomUnidadAdscripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_OOAD", nullable = false)
    private OoadEntity ooad;

    @Column(name = "DES_TIPO_UNIDAD", nullable = false, length = 20)
    private String desTipoUnidad;

    @Column(name = "IND_UNIDAD_PERNOCTA")
    private Boolean indUnidadPernocta;

    @Column(name = "NUM_UN_INF", length = 10)
    private String numUnInf;

    @Column(name = "NUM_UN_OPE", length = 12)
    private String numUnOpe;

    @Column(name = "NUM_CC", length = 12)
    private String numCc;

    @Column(name = "NUM_CU", length = 10)
    private String numCu;

    @Column(name = "NUM_DIV", length = 10)
    private String numDiv;

    @Column(name = "NUM_SDIV", length = 10)
    private String numSdiv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CODIGO_POSTAL", nullable = false)
    private CodigoPostalEntity idCodigoPostal;

    @Column(name = "NOM_COLONIA", length = 150)
    private String nomColonia;

    @Column(name = "DES_CALLE_NUM", length = 200)
    private String desCalleNum;
    
    @Column(name = "CVE_MATRICULA", nullable = false, length = 20)
    private String cveMatricula;
    
    @Column(name = "CVE_MATRICULA_MODIFICA", nullable = false, length = 20)
    private String cveMatriculaModifica;
    
    @Column(name = "CVE_MATRICULA_BAJA", nullable = false, length = 20)
    private String cveMatriculaBaja;

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
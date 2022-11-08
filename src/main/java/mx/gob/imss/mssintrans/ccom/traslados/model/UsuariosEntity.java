package mx.gob.imss.mssintrans.ccom.traslados.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "SINTRANSC_USUARIOS")
public class UsuariosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", unique = true)
    private Integer idUsuario;

    @Column(name = "CVE_MATRICULA")
    private String matricula;

    @Column(name = "DES_ESTATUS_USUARIO")
    private String estatusUsuario;
	
 
    @Column(name = "DES_MOTIVO", nullable = true)
    private String motivo;
    
	@Column(name = "ID_ROLES_USUARIO")
    private Integer idRol;
    
	@ManyToOne
    @JoinColumn(name = "ID_UNIDAD_ADSCRIPCION", referencedColumnName = "ID_UNIDAD_ADSCRIPCION")
    private UnidadesAdscripcionEntity unidad;
    
    @Column(name = "NOM_USUARIO", unique = true, nullable = true)
    private String nombreUsuario;
    
	@Column(name = "NOM_APELLIDO_PATERNO")
    private String apellidoPaterno;
    
	@Column(name = "NOM_APELLIDO_MATERNO")
    private String apellidoMaterno;
    
	@Column(name = "CVE_PASSWORD", nullable = true)
    private String password;
    
	@Column(name = "FEC_PWD_CADUCA", unique = true, nullable = true)
    private Date fechaPassCaduca;
    
	@Column(name = "CVE_MATRIC_AUDIT")
    private String matriculaAudita;
    
	@Column(name = "FEC_ALTA", unique = true, nullable = true)
    private Date fechaAlta;
    
	@Column(name = "FEC_ACTUALIZACION", unique = true, nullable = true)
    private Date fechaActualizacion;
    
	@Column(name = "FEC_BAJA", unique = true, nullable = true)
    private Date fechaBaja;
    
	@Column(name = "IND_ACTIVO", unique = true, nullable = true)
    private boolean activo;
    
	@Column(name = "IND_SISTEMA")
    private boolean indSistema;
    
	@Column(name = "NUM_INTENTOS")
    private Integer numeroIntentos;

}

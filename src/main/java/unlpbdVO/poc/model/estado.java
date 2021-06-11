package unlpbdVO.poc.model;

import java.util.UUID;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "estado")
@Table(name = "estados", schema = "tplp")
public class estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private LocalDateTime fecha;
	
	@Version
	private Integer version;
	
	@ManyToOne
    @JoinColumn(name="id_tipo_estado")
	private tipoEstado tipoEstado;
	
    @ManyToOne
    @JoinColumn(name = "id_item")
    @JsonIgnore 
    @JsonManagedReference
    private item item;
	
	/**
	 * Constructor. No utilizar.
	 */	
	public estado() {

	}

	/**
	 * Constructor.
	 * 
	 */
	public estado(LocalDateTime pfecha, tipoEstado pTipoEstado) {
		this.setFecha(pfecha);
		this.setTipoEstado(pTipoEstado);		
	}
	

	/**
	 * Getters. 
	 */
	
	public UUID getId() {
		return this.id;
	}
	
	public LocalDateTime getFecha() {	
		   return fecha;	
		}
	
	public tipoEstado getTipoEstado() {	
		   return tipoEstado;	
		}
		
     /**
	 * Setters. 
	 */
	
	public void setId(UUID pId) {
		this.id = pId;
	}
	
	public void setFecha(LocalDateTime pFecha) {
		this.fecha = pFecha;
	}
	
	public void setTipoEstado(tipoEstado pTipoEstado) {
		this.tipoEstado = pTipoEstado;
	}
	
}

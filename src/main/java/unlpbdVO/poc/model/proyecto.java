package unlpbdVO.poc.model;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Date;

@Entity(name = "proyecto")
@Table(name = "proyectos", schema = "tplp")
public class proyecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nombre;
	private Date fechaInicio;
	
	@Version
	private Integer version;
	
	@OneToMany
    @JoinColumn(name="id_proyecto")
	@JsonBackReference
    private Collection<item> items;

	
	/**
	 * Constructor. No utilizar.
	 */	
	public proyecto() {

	}

	/**
	 * Constructor.
	 * 
	 * 
	 */
	public proyecto(String pnombre, Date pFechaInicio) {
		this.setNombre(pnombre);
		this.setFechaInicio(pFechaInicio);;
	}
	
	/**
	 * Reglas de negocio. 
	 */
	
	/**
	 * agrega un item a un proyecto
	 */
	public void agregaItem(item pItem) {
		this.getItems().add(pItem);
	}

	/**
	 * el proyectro tiene items? False or True 
	 */
	public boolean tieneItems() {
		
		if (this.items.size() > 1) {
			  return true;
		}  
		else {
			return false;
		}	
	}
	
	
	/**
	 * Getters. 
	 */
	
	public UUID getId() {
		return this.id;
	}
	
	public String getNombre() {	
		   return nombre;	
		}
		
	public Date getFechaInicio() {	
			   return fechaInicio;	
		}
	
	public Collection<item> getItems(){	
		   return items;	
	}

	/**
	 * Setters. 
	 */
	
	public void setId(UUID pId) {
		this.id = pId;
	}
	
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
	public void setFechaInicio(Date pFechaInicio) {
		this.fechaInicio = pFechaInicio;
	}

	public void setItems(Collection<item> pItems) {
		this.items = pItems;
	}

	
}

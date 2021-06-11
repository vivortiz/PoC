package unlpbdVO.poc.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity(name = "tipoItem")
@Table(name = "tipoitems", schema = "tplp")
public class tipoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nombre;
	
	@Version
	private Integer version;
	
	
	/**
	 * Constructor. No utilizar.
	 */	
	public tipoItem() {

	}

	/**
	 * Constructor.
	 * 
	 * 
	 */
	public tipoItem(String pnombre) {
		this.setNombre(pnombre);		
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
		
	/**
	 * Setters. 
	 */
	
	public void setId(UUID pId) {
		this.id = pId;
	}
	
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
}

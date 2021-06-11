/**
 * Este paquete contiene las clases que se utilizan para transferir información de las 
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.util.Collection;
import java.util.UUID;

import unlpbdVO.poc.model.item;

import java.sql.Date;

/**
 * Las instancias de esta clase se utilizan para transferir información de los
 * proyectos.
 * 
 *
 */
public class proyectoDTO {

	
	// Identificador de cada una de las instancias.
	private UUID id;

	// nombre del proyecto.
	private String nombre;

	// prioridad del proyecto.
	private Date fechaInicio;
	
	// items del proyecto.
	private Collection<item> items;

	/**
	 * Constructor.
	 * 
	 */
	public proyectoDTO(UUID pId, String pnombre, Date pFechaInicio, Collection<item> pItems) {
		this.setId(pId);
		this.setNombre(pnombre);
		this.setFechaInicio(pFechaInicio);
		this.setItems(pItems);
	}

	/**
	 * Getters.
	 */
	
	public UUID getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	
	public Collection<item> getItems() {
		return this.items;
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

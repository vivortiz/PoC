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
 * proyectos sin sus items.
 * 
 *
 */


public class proyectoSinItemsDTO {


	// Identificador de cada una de las instancias.
	private UUID id;

	// nombre del proyecto.
	private String nombre;

	// prioridad del proyecto.
	private Date fechaInicio;

	
	/**
	 * Constructor.
	 * 
	 */
	public proyectoSinItemsDTO(UUID pId, String pnombre, Date pFechaInicio) {
		this.setId(pId);
		this.setNombre(pnombre);
		this.setFechaInicio(pFechaInicio);
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
		
}



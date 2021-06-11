/**
 * Este paquete contiene las clases que se utilizan para transferir información de las 
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.util.CollectionUtils;

import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.estado;

/**
 * Las instancias de esta clase se utilizan para transferir información de los
 * items.
 * 
 *
 */
public class itemDTO {

	// Identificador de cada una de las instancias.
	private UUID id;

	//nombre del item.
	private String nombre;

	
	// prioridad del item.
	private int prioridad;

	// tipo item del item.
	private String tipoItem;
	
	// estados del item.
	private List<estado> estados;

	
	/**
	 * Constructor.
	 * 
	 */
	public itemDTO(UUID pId, String pnombre, int pPrioridad, String pTipoItem, List<estado> pEstados) {
		this.setId(pId);
		this.setNombre(pnombre);
		this.setPrioridad(pPrioridad);
		this.setTipoItem(pTipoItem);
		if (!(pEstados == null)) {
			this.setEstados(pEstados);
		}
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

	public int getPrioridad() {
		return this.prioridad;
	}

	public String getTipoItem() {
		return this.tipoItem;
	}
	
	public List<estado> getEstados() {
		if (!CollectionUtils.isEmpty(this.estados)) {
		   return this.estados;	 
		}
		else {
			return null;
		}
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

	public void setPrioridad(int pPrioridad) {
		this.prioridad = pPrioridad;
	}
	
	public void setTipoItem(String pTipoItem) {
		this.tipoItem = pTipoItem;
	}
		
	public void setEstados(List<estado> pEstados) {	
		if (!CollectionUtils.isEmpty(pEstados)) {
			    this.estados = pEstados;
			}	
		else {
			this.estados = null;
		}
	}
}

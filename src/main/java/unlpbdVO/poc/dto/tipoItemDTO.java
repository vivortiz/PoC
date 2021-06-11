/**
 * Este paquete contiene las clases que se utilizan para transferir información de las 
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.util.UUID;

/**
 * Las instancias de esta clase se utilizan para transferir información de los
 * tipos de items.
 * 
 *
 */
public class tipoItemDTO {


	// Identificador de cada una de las instancias.
	private UUID id;

	// nombre del estado.
	private String nombre;


	/**
	 * Constructor.
	 * 
	 */
	public tipoItemDTO(UUID pId, String pnombre) {
		this.setId(pId);
		this.setNombre(pnombre);
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

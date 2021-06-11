/**
 * Este paquete contiene las clases que se utilizan para transferir información de las 
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.util.UUID;

/**
 * Las instancias de esta clase se utilizan para transferir información de los
 * tipos de estado.
 * 
 *
 */
public class tipoEstadoDTO {

	// Identificador de cada una de las instancias.
	private UUID id;

	// nombre del estado.
	private String nombre;

	// estado inicial del estado.
	private Boolean esInicial;
	
	//estado final del estado.
	private Boolean esFinal;

	/**
	 * Constructor.
	 * 
	 */
	public tipoEstadoDTO(UUID pId, String pnombre, Boolean pEsInicial, Boolean pEsFinal) {
		this.setId(pId);
		this.setNombre(pnombre);
		this.setEsInicial(pEsInicial);
		this.setEsFinal(pEsFinal);
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

	public Boolean getEsInicial() {
		return this.esInicial;
	}

	
	public Boolean getEsFinal() {
		return this.esFinal;
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

	public void setEsInicial(Boolean pEsInicial) {
		this.esInicial = pEsInicial;
	}

	public void setEsFinal(Boolean pEsFinal) {
		this.esFinal = pEsFinal;
	}

}

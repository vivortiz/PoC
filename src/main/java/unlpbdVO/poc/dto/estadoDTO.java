/**
 * Este paquete contiene las clases que se utilizan para transferir información de las 
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.util.UUID;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Las instancias de esta clase se utilizan para transferir información de los
 * estados.
 * 
 *
 */
public class estadoDTO {

	
	// Identificador de cada una de las instancias.
	private UUID id;

	// fecha del estado.
	private LocalDateTime fecha;
	
	// tipo estado del estado
	private String tipoEstado;


	/**
	 * Constructor
	 */
	public estadoDTO(UUID pId, LocalDateTime pfecha, String pTipoEstado) {
		this.setId(pId);
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
		return this.fecha;
	}
	
	public String getTipoEstado() {
		return this.tipoEstado;
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
	
	public void setTipoEstado(String pTipoEstado) {
		this.tipoEstado = pTipoEstado;
	}
}

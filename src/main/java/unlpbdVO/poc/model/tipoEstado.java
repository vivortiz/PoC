package unlpbdVO.poc.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity(name = "tipoEstado")
@Table(name = "tipoestados", schema = "tplp")
public class tipoEstado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nombre;
	private boolean esInicial;
	private boolean esFinal;

	
	@Version
	private Integer version;
	
	/**
	 * Constructor. No utilizar.
	 */	
	public tipoEstado() {

	}

	/**
	 * Constructor.
	 * 
	 * 
	 */
	public tipoEstado(String pnombre, boolean pesInicial, boolean pesFinal) {
		this.setNombre(pnombre);
		this.setEsFinal(pesFinal);
		this.setEsInicial(pesInicial);
	}
	

	/**
	 * Getters. 
	 */
	
	public UUID getIdTipoEstado() {
		return this.id;
	}
	
	public String getNombre() {	
		   return nombre;	
		}
		
	public boolean getEsInicial() {	
			   return esInicial;	
		}

	public boolean getEsFinal() {	
			   return esFinal;	
	    }
	
	/**
	 * Setters. 
	 */
	
	public void setIdTipoEstado(UUID anId) {
		this.id = anId;
	}
	
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
	public void setEsInicial(boolean pEsInicial) {
		this.esInicial = pEsInicial;
	}

	public void setEsFinal(boolean pEsFinal) {
		this.esFinal = pEsFinal;
	}

}

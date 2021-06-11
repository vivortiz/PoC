package unlpbdVO.poc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "item")
@Table(name = "items", schema = "tplp")
public class item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String nombre;
	private int prioridad;
	
	@Version
	private Integer version;
	
	@ManyToOne
    @JoinColumn(name="id_tipo_item")
	private tipoItem tipoItem;
	
    @OneToMany
    @JoinColumn(name="id_item")
	private List<estado> estados;	
	
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    @JsonIgnore 
    @JsonManagedReference
    private proyecto proyecto;
	

	/**
	 * Constructor. No utilizar.
	 */	
	public item() {

	}

	/**
	 * Constructor.
	 * 
	 */
	public item(String pnombre, int pprioridad, tipoItem ptipoItem) {
		this.setNombre(pnombre);
		this.setPrioridad(pprioridad);
		this.setTipoItem(ptipoItem);
	}
	
	
	/**
	 * Reglas de negocio. 
	 */
	
	
	/**
	 * agrega un estado a un item
	 */
	public void agregaEstado(estado pEstado) {
		this.getEstados().add(pEstado);
	}

	/**
	 * borra el ultimo estado de un item
	 */
	public void eliminaUltimoEstado() {
		int ultimo = this.estados.size()-1;
		this.getEstados().remove(ultimo);
	}
	
	
	/**
	 * ¿el item tiene estados? retorna True o False
	 */
    public boolean tieneEstados(){
    	return !CollectionUtils.isEmpty(this.getEstados());  //not empty   	
    }
	
    /**
	 * el item retorna el estado actual
	 */
	public estado estadoActual(){
		if ((this.tieneEstados())) {
			return this.estados.get(estados.size()-1);
		}
		else {
			return null;
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
		
	public int getPrioridad() {	
			   return prioridad;	
		}

	public tipoItem getTipoITem() {	
		   return tipoItem;	
	}
	
	public List<estado> getEstados() {	
		if (!(estados == null)) {
		   return estados;	
		}
		else {
			return null;
		}
	}

	
	/**
	 * Setters. 
	 */
	
	public void setId(UUID anId) {
		this.id = anId;
	}
	
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
	public void setPrioridad(int pPrioridad) {
		this.prioridad = pPrioridad;
	}
	
	public void setTipoItem(tipoItem ptipoItem) {
		this.tipoItem = ptipoItem;
	}
	
	public void setEstados(List<estado> pEstados) {
		this.estados = pEstados;
	}

}

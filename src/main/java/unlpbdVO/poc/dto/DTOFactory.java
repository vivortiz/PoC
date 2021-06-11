/**
 * Este paquete contiene las clases que se utilizan para transferir información de  
 * distintas instancias entre capas.
 */
package unlpbdVO.poc.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Component;

import unlpbdVO.poc.model.tipoEstado;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;



/**
 * Las instancias de esta clase se utilizan para crear DTOs en forma
 * centralizada.
 * 
 */
@Component
public class DTOFactory {

	/**
	 * Crea un DTO que representa a un tipoEstado
	 * 
	 * @return un DTO con los datos básicos.
	 */
	public tipoEstadoDTO createTipoEstadoDTO(tipoEstado pTipoEstado) {
		return new tipoEstadoDTO(pTipoEstado.getIdTipoEstado(), pTipoEstado.getNombre(), pTipoEstado.getEsInicial(), pTipoEstado.getEsFinal());
	}

	/**
	 * Crea un DTO que representa a un tipoItem
	 * 
	 * @return un DTO con los datos básicos.
	 */
	public tipoItemDTO createTipoItemDTO(tipoItem pTipoItem) {
		return new tipoItemDTO(pTipoItem.getId(), pTipoItem.getNombre());
	}
	
	/**
	 * Crea un DTO que representa a un estado
	 * 
	 * @return un DTO con los datos básicos.
	 */
	public estadoDTO createEstadoDTO(estado pEstado) {
		return new estadoDTO(pEstado.getId(), pEstado.getFecha(),pEstado.getTipoEstado().getNombre());
	}
	
	/**
	 * Crea un DTO que representa a un item
	 * 
	 * @return un DTO con los datos básicos del item
	 */
	public itemDTO createItemDTO(item pItem) {
		return new itemDTO(pItem.getId(), pItem.getNombre(), pItem.getPrioridad(), pItem.getTipoITem().getNombre(),pItem.getEstados());				
	}

	/**
	 * Crea un DTO que representa a un item sin sus estados
	 * 
	 * @return un DTO con los datos del item sin sus estados
	 */
	public itemDTOSinEstados createItemDTOSinEstados(item pItem) {
		return new itemDTOSinEstados(pItem.getId(), pItem.getNombre(), pItem.getPrioridad(), pItem.getTipoITem().getNombre());			
	}
	
	
	/**
	 * Crea un DTO que representa a un proyecto
	 * 
	 * @return un DTO con todos sus datos.
	 */
	public proyectoDTO createProyectoDTO(proyecto pProyecto) {
		return new proyectoDTO(pProyecto.getId(), pProyecto.getNombre(), pProyecto.getFechaInicio(),pProyecto.getItems());
	}

	/**
	 * Crea un DTO que representa a un proyecto sin sus items
	 * 
	 * @return un DTO de proyecto sin sus items
	 */
	public proyectoSinItemsDTO createProyectoSinItemsDTO(proyecto pProyecto) {
		return new proyectoSinItemsDTO(pProyecto.getId(), pProyecto.getNombre(), pProyecto.getFechaInicio());
	}

	
}



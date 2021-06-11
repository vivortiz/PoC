/**
 * Este paquete contiene todas las clases e interfaces que componen la capa de servicios.
 */
package unlpbdVO.poc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.dto.estadoDTO;
import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.dto.itemDTOSinEstados;
import unlpbdVO.poc.dto.proyectoDTO;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.model.item;


/**
 * Esta interface define el comportamiento esperado por los servicios
 * relacionados con los Items.
 */
public interface IItemService {


	//agrega un nuevo item
	public itemDTO addItem(String pNombre, int pPrioridad, UUID pIDTipoItem); 
	
	// agrega un estado a un item
	public estadoDTO addEstadoAItem (UUID pIdItem, LocalDateTime pfecha, UUID pIdTipoEstado);
	
	// retorna los items de un proyecto
    public List<itemDTOSinEstados> ListaProyectos(UUID pidProyecto);
	
	// retorna el ultimo estado de un Item
    public estadoDTO getUltimoEstadoItem(UUID pIdItem);
    
	// retorna el ultimo estado de un Item
    public void deleteUltimoEstadoItem(UUID pIdItem);
      
	// Retorna si existe el item dado un id
	public boolean existeItem(UUID pIdItem);

    // retorna el item correspondiente a un UUID 
	public item getItem(UUID pIdItem);
	
	
}


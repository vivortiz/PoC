/**
 * Este paquete contiene todas las clases e interfaces que componen la capa de servicios.
 */
package unlpbdVO.poc.service;

import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.dto.tipoEstadoDTO;
import unlpbdVO.poc.dto.tipoItemDTO;
import unlpbdVO.poc.model.tipoItem;

/**
 * Esta interface define el comportamiento esperado por los servicios
 * relacionados con los tipo de Item.
 * 
 *
 */
public interface ITipoItemService {

	// Agrega un nuevo tipo de item
	public tipoItemDTO addTipoItem(String pNombre); 
	
	// retorna el tipo de item correspondiente a un UUID 
	public tipoItem getTipoItem (UUID pIdTipoItem);
	
	// retorna el tipo de item correspondiente a un nombre
	public tipoItem getTipoItem (String pNombre);
	
	// controla si existe un tipo de item con el nombre pasado por parametro
	public boolean existeTipoItem (String pNombre); 
	
	// Retorna si existe el tipo de item dado un id
    public boolean existeTipoItem(UUID pTipoItem);
	 
	//Lista los tipos de estado 
	public List<tipoItemDTO> ListaTipoItems();
		
}

/**
 * Este paquete contiene todas las clases e interfaces que componen la capa de servicios.
 */
package unlpbdVO.poc.service;

import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.dto.tipoEstadoDTO;
import unlpbdVO.poc.model.tipoEstado;

/**
 * Esta interface define el comportamiento esperado por los servicios
 * relacionados con los tipo de Estados.
 * 
 *
 */
public interface ITipoEstadoService {

	
	// Agrega un nuevo tipo de Estado.
	public tipoEstadoDTO addTipoEstado(String pNombre, Boolean pEsInicial, Boolean pEsFinal); 
	
	// retorna el tipo de estado correspondiente a un UUID 
	public tipoEstado getTipoEstado (UUID pId);
	
	// controla si existe un tipo de estado con el nombre pasado por parametro
	public boolean existeTipoEstado (String pNombre); 
	
	//Lista los tipos de estado 
	public List<tipoEstadoDTO> ListaTipoEstados();
	
	// controla si existe un tipo de estado con el id pasado por parametro
	public boolean existeTipoDeEstado(UUID pIdTipoEstado);
	
	 
	
	
}

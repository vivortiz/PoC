/**
 * Este paquete contiene todas las clases e interfaces que componen la capa de servicios.
 */
package unlpbdVO.poc.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.dto.estadoDTO;
import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;

/**
 * Esta interface define el comportamiento esperado por los servicios
 * relacionados con los tipo de Estados.
 * 
 *
 */
public interface IEstadoService {

	
	//Agrega un nuevo Estado
	public estadoDTO addEstado(LocalDateTime pfecha, UUID pIdTipoEstado); 
	
	// retorna los estados de un item
	public List<estadoDTO> listaEstados(UUID pidItem);
	
	// retorna el DTO de un estado
	public estadoDTO showEstado(UUID pidEstado);
		
	// Retorna si existe el estado dado un id
	public boolean existeEstado(UUID pIdEstado);

	// Busqueda, retorna el objeto estado
	public estado getEstado(UUID pId); 
	
	
	
	
	
}

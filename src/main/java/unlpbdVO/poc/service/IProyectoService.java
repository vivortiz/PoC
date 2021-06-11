/**
 * Este paquete contiene todas las clases e interfaces que componen la capa de servicios.
 */
package unlpbdVO.poc.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.dto.proyectoDTO;
import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.model.proyecto;

/**
 * Esta interface define el comportamiento esperado por los servicios
 * relacionados con los proyectos.
 * 
 *
 */
public interface IProyectoService {

	
	//Agrega un nuevo proyecto.
	public proyectoDTO addProyecto(String pNombre, Date pFechaInicio); 
	
	//agrega un item existente a un proyecto existente
	public proyectoDTO addItem (UUID pIdProyecto, UUID pIdItem);
	
	//crea un item en un proyecto existente
	public itemDTO addItem (UUID pIdProyecto,String pNombre, int pPrioridad, UUID pIDTipoItem);
	
	//Lista los proyectos sin mostrar sus items 
	public List<proyectoSinItemsDTO> ListaProyectosSinDataItems();
	
	//Modifica el nombre de un proyecto
	public proyectoSinItemsDTO  modifyNombreProyecto(UUID pIdProyecto, String pNuevoNombre);
	
	// Retorna si existe el proyecto dado un id
	public boolean existeProyecto(UUID pIdProyecto);
	
	//Determina si un proyecto tiene items (T) o no (F)
    public boolean proyectoSinItems(UUID pIdProyecto);
	
	//Busquedas retorna el objeto proyecto
	public proyecto getProyecto (UUID  pId);
	
	

}

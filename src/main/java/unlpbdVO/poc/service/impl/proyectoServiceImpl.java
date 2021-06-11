/**
 * Este paquete contiene las implementaciones de los servicios.
 */
package unlpbdVO.poc.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import unlpbdVO.poc.dto.DTOFactory;
import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.dto.proyectoDTO;
import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.repository.itemRepository;
import unlpbdVO.poc.repository.proyectoRepository;
import unlpbdVO.poc.service.IProyectoService;



/**
 * Esta clase contiene la implementación de los servicios relacionados con los
 * proyectos
 */
@Service
@Transactional
public class proyectoServiceImpl extends AbstractServiceImpl implements IProyectoService {

	/**
	 * Es el repositorio ligado a los proyectos.
	 */
	@Inject
	private proyectoRepository proyectoRepository;

	/**
	 * Es el objeto encargado de crear los DTOs.
	 */
	@Inject
	private DTOFactory dtoFactory;

	/**
	 * Agrega un nuevo proyecto.
	 * @return un DTO que representa al proyecto recientemente creado.
	 */
	
	@Override
	public proyectoDTO  addProyecto(String pNombre, Date pFechaInicio) {

		proyecto newProyecto = new proyecto(pNombre,pFechaInicio);
	
		this.getProyectoRepository().save(newProyecto);  //es necesario porque no lo asocio a ningún otro objeto persistido en al ambito de la PoC

		return this.getDtoFactory().createProyectoDTO(newProyecto);
	}

	/**
	 * Modifica el nombre de proyecto.
	 * @return un DTO que representa al proyecto con el nombre modificado
	 */
	
	@Override
	public proyectoSinItemsDTO  modifyNombreProyecto(UUID pIdProyecto, String pNuevoNombre) {

		if (existeProyecto(pIdProyecto)) {
			proyecto elProyecto = this.getProyecto(pIdProyecto);
	
			elProyecto.setNombre(pNuevoNombre);
		
		//	this.getProyectoRepository().save(elProyecto); // no es necesario por persistencia por alcance

			return this.getDtoFactory().createProyectoSinItemsDTO(elProyecto);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Valor de parámetro incorrecto.Proyecto inexistente");
		}
	}
	
	/**
	 * Crea un item y lo agrega al proyecto existente.
	 * @return un DTO que representa el nuevo item recientemente agregado al proyecto
	 */		
	@Override
	public itemDTO addItem (UUID pIdProyecto,String pNombre, int pPrioridad, UUID pIdTipoItem) {
		
		if (existeProyecto(pIdProyecto)) {
			//obtiene el objeto proyecto
			proyecto elProyecto = this.getProyecto(pIdProyecto);
			
			//crea el item
			itemDTO itemdto = this.getBroker().getItemService().addItem(pNombre, pPrioridad, pIdTipoItem);
			item elItem = this.getBroker().getItemService().getItem(itemdto.getId());
			
			//agrega el item al proyecto
			elProyecto.agregaItem(elItem);
		//	this.getProyectoRepository().save(elProyecto); //no es necesario por persistencia por alcance

		    return itemdto;
			
		}	
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Proyecto inexistente");
		}
		
	}
	
	/**
	 * Agrega un item existente a un proyecto existente.
	 * @return un DTO que representa al proyecto con el nuevo item recientemente agregado.
	 */	
	
	@Override
	public proyectoDTO addItem (UUID pIdProyecto, UUID pIdItem) {
		
		//obtiene el objeto proyecto
		proyecto elProyecto = this.getProyecto(pIdProyecto);
		//obtiene el objeto item
		item elItem = this.getBroker().getItemService().getItem(pIdItem);
						
		elProyecto.agregaItem(elItem);
		//this.getProyectoRepository().save(elProyecto);  //no es necesario por persistencia por alcance

		return this.getDtoFactory().createProyectoDTO(elProyecto);
		
	}
	
	
	/**
	 * Lista los proyectos ordenados por fecha de inicio desc
	 * @return lista de DTO sin items
	 */
	@Override
	public List<proyectoSinItemsDTO> ListaProyectosSinDataItems(){	
		List<proyecto> lista;
		lista = proyectoRepository.findAllByOrderByFechaInicioDesc();
	
		List listaDTOS = new ArrayList();
		for (int i= 0; i <lista.size(); i++) {
			proyectoSinItemsDTO dto = null;
			dto = this.getDtoFactory().createProyectoSinItemsDTO(lista.get(i));
			listaDTOS.add(dto);
			
		}
		return listaDTOS;
	}
	
	
	/**
	 * Determina si un proyecto tiene items (T) o no (F)
	 */
	@Override
	public boolean proyectoSinItems(UUID pIdProyecto) {
		// recupera el objeto proyecto
		proyecto elProyecto = this.getProyecto(pIdProyecto);
		// controla que tenga items
		if (elProyecto.tieneItems()) {
			  return false;
		}    	
		else {
			return true;		
		}
	}
	
	/**
	 * dado un id de un proyecto
	 * @return el objeto proyecto
	 */
	@Override
	public proyecto getProyecto (UUID pId) {
			
		proyecto proyecto = proyectoRepository.findByid(pId);
		
		return proyecto;
	}

		
	/**
	 * Retorna si existe el proyecto dado un id
	 */
	@Override
	public boolean existeProyecto(UUID pIdProyecto){
			
		return 	proyectoRepository.existsById(pIdProyecto);
			
	}

	
	/**
	 * Getter.
	 * 
	 * @return el repositorio de proyecto
	 */
	public proyectoRepository getProyectoRepository() {
		return this.proyectoRepository;
	}

	/**
	 * Getter.
	 * 
	 * @return el objeto que debe utilizarse para crear los DTOs.
	 */
	public DTOFactory getDtoFactory() {
		return this.dtoFactory;
	}

}

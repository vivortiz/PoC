/*
* Este paquete contiene las implementaciones de los servicios.
 */
package unlpbdVO.poc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import unlpbdVO.poc.dto.DTOFactory;
import unlpbdVO.poc.dto.estadoDTO;
import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.dto.itemDTOSinEstados;
import unlpbdVO.poc.dto.proyectoDTO;
import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.repository.itemRepository;
import unlpbdVO.poc.service.IItemService;
import unlpbdVO.poc.model.tipoItem;


/**
 * Esta clase contiene la implementación de los servicios relacionados con los
 * items.
 * 
 *
 */
@Service
@Transactional
public class itemServiceImpl extends AbstractServiceImpl implements IItemService{

	/**
	 * Es el repositorio ligado a los items.
	 */
	@Inject
	private itemRepository itemRepository;
	
	/**
	 * Es el objeto encargado de crear los DTOs.
	 */
	@Inject
	private DTOFactory dtoFactory;

	/**
	 * Agrega un nuevo item.
	 * @return un DTO que representa al item recientemente creado.
	 */
	
	@Override
	public itemDTO  addItem(String pNombre, int pPrioridad, UUID pIdTipoItem) {
		
		if (this.getBroker().getTipoItemService().existeTipoItem(pIdTipoItem)){  //controla q exista el tipo de item
		
			tipoItem tipoItem = this.getBroker().getTipoItemService().getTipoItem(pIdTipoItem);
			
			item newItem = new item(pNombre, pPrioridad, tipoItem);
				  
			this.getItemRepository().save(newItem);

			return this.getDtoFactory().createItemDTO(newItem);
		}
		else {
			
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Tipo de Item inexistente");
		    
		}
	}
		

	/**
	 * Agrega un estado a un item.
	 * @return un DTO que representa al item con el nuevo estado recientemente agregado.
	 */
	public estadoDTO addEstadoAItem (UUID pIdItem, LocalDateTime pfecha, UUID pIdTipoEstado) {
		boolean okagregar;
		boolean eselprimerestado;

		if (existeItem(pIdItem)) {  //controla que exista un item para el UUID
		  if (this.getBroker().getTipoEstadoService().existeTipoDeEstado(pIdTipoEstado)) { 	 //controla q exista el tipo de estado para el UUID
		
			//obtiene el objeto item
			item elItem = this.getItem(pIdItem);
				
			//tiene estados el item?
			if (elItem.tieneEstados()) {
				estadoDTO ultEstadoDTO = this.getUltimoEstadoItem(elItem.getId());
				//obtiene el estado del ultimo estado
				estado ultEstado = this.getBroker().getEstadoService().getEstado(ultEstadoDTO.getId());
				okagregar = !ultEstado.getTipoEstado().getEsFinal();	//permite agregar si el ultimo estado no es final
				eselprimerestado = false;
			}
			else { //si es primer estado, en ppio, permite agregarlo
				okagregar = true;	
				eselprimerestado = true;
			}
				
			if (okagregar) {
				//controla si es inicial el tipo de estado		
				boolean esInicial= this.getBroker().getTipoEstadoService().getTipoEstado(pIdTipoEstado).getEsInicial();
				
				if (eselprimerestado && !esInicial) { //si es el primer estado y es del tipo no inicial
				    	throw new ResponseStatusException(HttpStatus.CONFLICT, "Es el primer estado del item pero no es del tipo inicial. No puede agregarse el estado");
				}
				else {	
				    if (!eselprimerestado && esInicial) {  //si no es primer estado y es del tipo inicial
				    	throw new ResponseStatusException(HttpStatus.CONFLICT, "No es el primer estado del item pero es del tipo inicial. No puede agregarse el estado");   		
				    	}
				    else {	
				    	//si los tipos iniciales y finales estan ok, crea el estado
				    	estadoDTO nuevoEstadoDTO = this.getBroker().getEstadoService().addEstado(pfecha, pIdTipoEstado);
				    	//obtiene el objeto estado
						estado elEstado = this.getBroker().getEstadoService().getEstado(nuevoEstadoDTO.getId());
						//le agrega el estado al item
						elItem.agregaEstado(elEstado);
						
						//this.getItemRepository().save(elItem);  //no es necesario por persistencia por alcance
				
						return nuevoEstadoDTO;
				    }
				 }
			}
		    else { //si el ultimo estado es final no puede agregarse
					throw new ResponseStatusException(HttpStatus.CONFLICT,"El ultimo estado del item es final. No puede agregarse un nuevo estado");
		    }
		  }	
		  else { //no existe el id del tipo de estado
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Tipo de estado inexistente");			  
		  }
		}	
		else {	//no existe el id del item
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Item inexistente");
		}			
	}
	
	/**
	 * Lista los items de un proyecto
	 * @return lista de itemsDTO
	 */
	public List<itemDTOSinEstados> ListaProyectos(UUID pidProyecto){	
		if (this.getBroker().getProyectoService().existeProyecto(pidProyecto)) { //controla que exista un proyecto para el id
			List<item> lista;
			lista = itemRepository.findByProyectoId(pidProyecto);
				
			List listDTO = new ArrayList();
			for (int i= 0; i <lista.size(); i++) {
				itemDTOSinEstados dto = null;
				dto = this.getDtoFactory().createItemDTOSinEstados(lista.get(i));
				listDTO.add(dto);
		    }
			return listDTO;
		 }	
		 else { // no existe el id del proyecto
			 throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Proyecto inexistente");
		 }		
	}
	
	/**
	 * Busca el ultimo estado de un item
	 * @return el ultimo estado
	 */
	 @Override
	 public estadoDTO getUltimoEstadoItem(UUID pIdItem) {
		
		if (existeItem(pIdItem)) {  //controla que exista un item para el UUID
		 
			 //obtiene el objeto item
			 item elItem = this.getItem(pIdItem);
			 
			 if (elItem.tieneEstados()) {
	
				 //crea el DTO del estado actual del objeto item
				 estado estado = elItem.estadoActual();
		
				 return this.getDtoFactory().createEstadoDTO(elItem.estadoActual());
			 }
			 else { // el item no tiene estados
				 throw new ResponseStatusException(HttpStatus.CONFLICT,"El item no tiene asociado ningún estado");
			 }
		 }
		 else {	//no existe el id del item
			 throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Item inexistente");
		 }
						 
	 }
	 

	 /**
	 * Borra el ultimo estado de un item
	 * 	 
	 */
	@Override
	public void deleteUltimoEstadoItem(UUID pIdItem) {
			
		if (existeItem(pIdItem)) {  //controla que exista un item para el UUID
			 
			//obtiene el objeto item
			item elItem = this.getItem(pIdItem);
				 
			if (elItem.tieneEstados()) {
		
				//elimina el ultimo estado
				elItem.eliminaUltimoEstado();
				//this.getItemRepository().save(elItem);  //no es necesario por persistencia por alcance

			}
			else { // el item no tiene estados
				throw new ResponseStatusException(HttpStatus.CONFLICT,"El item no tiene asociado ningún estado");
			}
		 }
		 else {	//no existe el id del item
				 throw new ResponseStatusException(HttpStatus.CONFLICT,"Error. Item inexistente");
		 }
							 
	}
		 	 
	 
	 
	 
	 
	/**
	 * Retorna si existe el item dado un id
	 */
	@Override
	public boolean existeItem(UUID pIdItem)	{
			
		return 	itemRepository.existsById(pIdItem);
			
	}

	/**
	 * dado un id de un item
	 * @return el objeto item
	 */
	public item getItem(UUID pIdItem) {
		item elItem = itemRepository.findByid(pIdItem);
		
		return elItem;
	}
	 
	public item getItem (String pNombre) {
			
		item elItem = itemRepository.findBynombre(pNombre);
		
		return elItem;
	}
		

	/**
	 * Getter.
	 * 
	 * @return el repositorio de tipo Estado.
	 */
	public itemRepository getItemRepository() {
		return this.itemRepository;
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

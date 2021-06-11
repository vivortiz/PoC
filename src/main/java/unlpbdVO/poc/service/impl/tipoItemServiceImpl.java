/**
 * Este paquete contiene las implementaciones de los servicios.
 */
package unlpbdVO.poc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import unlpbdVO.poc.dto.DTOFactory;
import unlpbdVO.poc.dto.tipoEstadoDTO;
import unlpbdVO.poc.dto.tipoItemDTO;
import unlpbdVO.poc.model.tipoEstado;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.repository.tipoItemRepository;
import unlpbdVO.poc.service.ITipoItemService;



/**
 * Esta clase contiene la implementación de los servicios relacionados con los
 * tipos de items.
 */

@Service
@Transactional
public class tipoItemServiceImpl implements ITipoItemService {

	/**
	 * Es el repositorio ligado a los tipos de items.
	 */
	@Inject
	private tipoItemRepository tipoItemRepository;

	/**
	 * Es el objeto encargado de crear los DTOs.
	 */
	@Inject
	private DTOFactory dtoFactory;

	/**
	 * Agrega un nuevo tipoItem
	 * @return un DTO que representa al tipo de item recientemente creado.
	 */
	
	@Override
	public tipoItemDTO  addTipoItem(String pNombre) {
		
		if (!this.existeTipoItem(pNombre)) {   //controla si no existe el tipo de item con esa descripcion

			tipoItem newTipoItem = new tipoItem(pNombre);
	
			this.getTipoItemRepository().save(newTipoItem); //es necesario porque no lo asocio a ningún otro objeto persistido en al ambito de la PoC

			return this.getDtoFactory().createTipoItemDTO(newTipoItem);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tipo item con esa descripción");  
		}
	 }

	/**
	 * dado un nombre de un tipo de item
	 * @return el objeto tipoItem
	 */
	@Override
	public tipoItem getTipoItem (String pNombre) {
			
		tipoItem tipoItem = tipoItemRepository.findBynombre(pNombre);
		
		return tipoItem;
	}

	/**
	 * dado el id de un tipo de item
	 * @return el objeto tipoItem
	 */
	@Override
	public tipoItem getTipoItem (UUID pIdTipoItem) {
			
		tipoItem tipoItem = tipoItemRepository.findByid(pIdTipoItem);
		
		return tipoItem;
	}
	
	/**
	 * Lista los tipos de items existentes
	 */
	public List<tipoItemDTO> ListaTipoItems(){
		List<tipoItem> lista;
		lista = tipoItemRepository.findAll(); 	//recupera los tipos de items existentes
	
		//crea los DTOs para transferir los tipos de items existentes
		List listaDTOS = new ArrayList();
		for (int i= 0; i <lista.size(); i++) {
			tipoItemDTO dto = null;
			dto = this.getDtoFactory().createTipoItemDTO(lista.get(i));
			listaDTOS.add(dto);		
		}
		return listaDTOS;
	}
	
	/**
	 * dado un nombre de un tipo de item
	 * @return si existe o no el objeto tipo de item con ese nombre -- controla unicidad
	 */
	@Override
	public boolean existeTipoItem (String pNombre) {
		
		return  tipoItemRepository.existsBynombre(pNombre);
	}
	
	/**
	 * Retorna si existe el tipo de item dado un id
	 */
	@Override
	public boolean existeTipoItem(UUID pIdTipoItem){
			
		return 	tipoItemRepository.existsById(pIdTipoItem);			
	}	
	
	
	/**
	 * Getter.
	 * 
	 * @return el repositorio de tipo Estado.
	 */
	public tipoItemRepository getTipoItemRepository() {
		return this.tipoItemRepository;
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

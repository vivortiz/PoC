/**
 * Este paquete contiene las implementaciones de los servicios.
 */
package unlpbdVO.poc.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import unlpbdVO.poc.dto.DTOFactory;
import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.dto.tipoEstadoDTO;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.model.tipoEstado;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.repository.tipoEstadoRepository;
import unlpbdVO.poc.service.ITipoEstadoService;




/**
 * Esta clase contiene la implementación de los servicios relacionados con los
 * tipos de estados.
 */

@Service
@Transactional
public class tipoEstadoServiceImpl extends AbstractServiceImpl implements ITipoEstadoService {

	/**
	 * Es el repositorio ligado a los tipos de estado
	 */
	@Inject
	private tipoEstadoRepository tipoEstadoRepository;

	/**
	 * Es el objeto encargado de crear los DTOs.
	 */
	@Inject
	private DTOFactory dtoFactory;

	/**
	 * Agrega un nuevo tipoEstado.
	 * @return un DTO que representa el tipo de estado recientemente creado.
	 */
	
	@Override
	public tipoEstadoDTO  addTipoEstado(String pNombre, Boolean pEsInicial, Boolean pEsFinal) {
		
		//controla si ya existe un tipo estado con ese nombre
		if  (!this.existeTipoEstado(pNombre)) {   //controla si no existe el tipo de estado con esa descripcion

			tipoEstado newTipoEstado = new tipoEstado(pNombre, pEsInicial, pEsFinal);
	
			this.getTipoEstadoRepository().save(newTipoEstado); //es necesario porque no lo asocio a ningún otro objeto persistido en al ambito de la PoC

			return this.getDtoFactory().createTipoEstadoDTO(newTipoEstado);
		   }
		else
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tipo estado con esa descripción");   		
		}			
	}

		
	/**
	 * dado el id de un tipo de estado
	 * @return el objeto tipoEstado
	 */
	public tipoEstado getTipoEstado (UUID pId) {

		tipoEstado tipoEstado = tipoEstadoRepository.findByid(pId);
		
		return tipoEstado;

	}
	
	/**
	 * Lista los tipos de estado existentes
	 */
	public List<tipoEstadoDTO> ListaTipoEstados(){
		List<tipoEstado> lista;
		//recupera los tipos de estado existentes
		lista = tipoEstadoRepository.findAll();
	
		//crea los DTOs para transferir los tipos de estado existentes
		List listaDTOS = new ArrayList();
		for (int i= 0; i <lista.size(); i++) {
			tipoEstadoDTO dto = null;
			dto = this.getDtoFactory().createTipoEstadoDTO(lista.get(i));
			listaDTOS.add(dto);		
		}
		return listaDTOS;
	}
	
	/**
	 * dado un nombre de un tipo de estado
	 * @return si existe o no el objeto tipo de estado con ese nombre -- controla unicidad
	 */
	@Override
	public boolean existeTipoEstado (String pNombre) {
		
		return  tipoEstadoRepository.existsBynombre(pNombre);
	}
	
	/**
	 * Retorna si existe el tipo de estado dado un id 
	 */
	@Override
	public boolean existeTipoDeEstado(UUID pIdTipoEstado){
		
		return tipoEstadoRepository.existsById(pIdTipoEstado);
		
	}
	
	
	
	
	/**
	 * Getter.
	 * 
	 * @return el repositorio de tipo Estado.
	 */
	public tipoEstadoRepository getTipoEstadoRepository() {
		return this.tipoEstadoRepository;
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

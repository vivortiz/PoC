/**
 * Este paquete contiene las implementaciones de los servicios.
 */
package unlpbdVO.poc.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import unlpbdVO.poc.dto.DTOFactory;
import unlpbdVO.poc.dto.estadoDTO;
import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.model.tipoItem;
import unlpbdVO.poc.repository.estadoRepository;
import unlpbdVO.poc.service.IEstadoService;
import unlpbdVO.poc.model.tipoEstado;



/**
 * Esta clase contiene la implementación de los servicios relacionados con los
 *  estados.
 */
@Service
@Transactional
public class estadoServiceImpl extends AbstractServiceImpl implements IEstadoService {

	/**
	 * Es el repositorio ligado a los estados.
	 */
	@Inject
	private estadoRepository estadoRepository;

	/**
	 * Es el objeto encargado de crear los DTOs.
	 */
	@Inject
	private DTOFactory dtoFactory;

	/**
	 * Agrega un nuevo estado 
	 * @return un DTO que estado recientemente creado.
	 */
	
	@Override   	 
	public estadoDTO addEstado(LocalDateTime pfecha, UUID pIdTipoEstado) {
		
		//obtengo el objeto tipo estado a partir de su id
		tipoEstado tipoEstado = this.getBroker().getTipoEstadoService().getTipoEstado(pIdTipoEstado);
		
		if (tipoEstado == null) {  //si no existe el tipo de estado del id pasado como parametro
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Valor de parámetro incorrecto.Tipo de Estado inexistente");
		}
		else {		
			estado newEstado = new estado(pfecha, tipoEstado);
				
			this.getEstadoRepository().save(newEstado);

			return this.getDtoFactory().createEstadoDTO(newEstado);
		} 
	}

	/**
	 * Lista los estados de un item
	 * @return lista de estadoDTO
	 */
	@Override   	 	
	public List<estadoDTO> listaEstados(UUID pidItem){	
		List<estado> lista;
		lista = estadoRepository.findByItemId(pidItem);
				
		List listDTO = new ArrayList();
		for (int i= 0; i <lista.size(); i++) {
			estadoDTO dto = null;
			dto = this.getDtoFactory().createEstadoDTO(lista.get(i));
			listDTO.add(dto);
			
		}
		return listDTO;
	}
	
	
	/**
	 * dado un id de un estado
	 * @return su DTO
	 */
	public estadoDTO showEstado(UUID pidEstado) {
		
		if (existeEstado(pidEstado)) {  //controla que exista un estado para el UUID
			
			estado elEstado = this.getEstado(pidEstado);
			return this.getDtoFactory().createEstadoDTO(elEstado);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Id de estado inexistente");
		}
	}

	/**
	 * dado un id de un estado
	 * @return el objeto estado
	 */
	public estado getEstado(UUID pId) {
		
		estado elEstado = estadoRepository.findByid(pId);
		
		return elEstado;
	}
		
	
	/**
	 * Retorna si existe el estado dado un id
	 */
	@Override
	public boolean existeEstado(UUID pIdEstado)	{
			
		return 	estadoRepository.existsById(pIdEstado);
			
	}
	
	
	/**
	 * Getter.
	 * 
	 * @return el repositorio del Estado.
	 */
	public estadoRepository getEstadoRepository() {
		return this.estadoRepository;
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

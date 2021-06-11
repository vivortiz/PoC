/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package unlpbdVO.poc.repository;

import java.util.List;
import java.util.UUID;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.model.tipoItem;

/**
 * Clase que representa el repositorio de Estado.
 *
 */
@Repository
public interface estadoRepository extends JpaRepository<estado, UUID> {
	
	//recupera un estado dado un id
	public estado findByid(UUID pId);
	
	// recupera la lista de estados de un item dado su id
	public List<estado> findByItemId(UUID pidItem);
	
	// retorna si existe un estado dado un id
	public boolean existsById(UUID pidEstado); 
	

}

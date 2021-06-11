/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package unlpbdVO.poc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;

/**
 * Clase que representa el repositorio de item.
 *
 *
 */
@Repository
public interface itemRepository extends JpaRepository<item, UUID> {

	// recupera item por nombre
	public item findBynombre(String pnombre);
	
	//recupera item por id
	public item findByid(UUID pId);
	
	// recupera la lista de items de para el Id de un proyecto
    public List<item> findByProyectoId(UUID pidProyecto);
    
    // retorna si existe un proyecto dado un Id
	public boolean existsById(UUID pIdItem); 
	
    
}

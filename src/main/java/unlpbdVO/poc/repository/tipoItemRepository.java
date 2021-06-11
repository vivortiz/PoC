/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package unlpbdVO.poc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlpbdVO.poc.model.tipoItem;

/**
 * Clase que representa el repositorio de tipoEstado.
 *
 */
@Repository
public interface tipoItemRepository extends JpaRepository<tipoItem, UUID> {

	
	// recupera item por nombre
	public tipoItem findBynombre(String pnombre);
	
	// recupera item por id
	public tipoItem findByid(UUID pId);
			
	// retorna si existe item por el nombre
	public boolean existsBynombre(String pnombre); 
	
	// retorna si existe item por el id
	public boolean existsById(UUID pId); 

}

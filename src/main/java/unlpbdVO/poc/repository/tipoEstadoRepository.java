/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package unlpbdVO.poc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.model.tipoEstado;
import unlpbdVO.poc.model.tipoItem;

/**
 * Clase que representa el repositorio de tipoEstado.
 *
 */
@Repository
public interface tipoEstadoRepository extends JpaRepository<tipoEstado, UUID> {

	
	// recupera tipo de estado por nombre
    public tipoEstado findBynombre(String pnombre);
    
    // recupera tipo de estado por id
    public tipoEstado findByid(UUID pid);
    
    // recupera todos los tipos de estado
    public List<tipoEstado> findAll();

    // retorna si existe tipo de estado por el nombre
 	public boolean existsBynombre(String pnombre); 

}

/**
 * Este paquete contiene todas las implementaciones de los repositorios.
 */
package unlpbdVO.poc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unlpbdVO.poc.model.item;
import unlpbdVO.poc.model.proyecto;

/**
 * Clase que representa el repositorio de proyecto.
 *
 */
@Repository
public interface proyectoRepository extends JpaRepository<proyecto, UUID> {

	// recupera proyecto por nombre
	public proyecto findBynombre(String pnombre);
	
	//recupera proyecto por Id
	public proyecto findByid(UUID pId);
	
	//recupera los proyectos ordenados por fecha de inicio descendente
	public List<proyecto> findAllByOrderByFechaInicioDesc();
	
    // retorna si existe un proyecto para un id 
 	public boolean existsById(UUID pId); 
	
	//@EntityGraph(value = "Proyecto.items", type = EntityGraphType.FETCH)  -- no funcionó
	//public List<item> findByIdProyecto(UUID pId);

	
	

}

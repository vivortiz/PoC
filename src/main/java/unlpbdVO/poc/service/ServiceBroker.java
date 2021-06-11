package unlpbdVO.poc.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class ServiceBroker {
	
	/*servicios para acceder inter servicios. A través del broker, se puede accceder a cualq serv desde otro serv 
	 * 
	 */
	
	@Inject
	@Getter
	private ITipoItemService tipoItemService;  
	
	@Inject
	@Getter
	private IItemService itemService;
	
	@Inject
	@Getter
	private ITipoEstadoService tipoEstadoService;
	
	@Inject
	@Getter
	private IEstadoService estadoService;
	
	@Inject
	@Getter
	private IProyectoService proyectoService;
	
	

}

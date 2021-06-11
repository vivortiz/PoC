package unlpbdVO.poc.service.impl;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import lombok.Getter;
import unlpbdVO.poc.service.ServiceBroker;

@Service
public abstract class AbstractServiceImpl {
	
	/** es el contenedor de servicios que permite acceder a todos los 
	 * servicios en forma indirecta
	 */

	@Inject
    @Getter
	private ServiceBroker broker;

}

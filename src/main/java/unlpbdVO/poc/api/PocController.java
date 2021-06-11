/**
 * Este paquete contiene las clases que definen la api rest de la aplicación.
 */
package unlpbdVO.poc.api;

import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import unlpbdVO.poc.service.ITipoEstadoService; 
import unlpbdVO.poc.service.ITipoItemService;
import unlpbdVO.poc.dto.estadoDTO;
import unlpbdVO.poc.dto.itemDTO;
import unlpbdVO.poc.dto.itemDTOSinEstados;
import unlpbdVO.poc.dto.proyectoDTO;
import unlpbdVO.poc.dto.proyectoSinItemsDTO;
import unlpbdVO.poc.dto.tipoEstadoDTO;
import unlpbdVO.poc.dto.tipoItemDTO;
import unlpbdVO.poc.model.estado;
import unlpbdVO.poc.model.proyecto;
import unlpbdVO.poc.service.IEstadoService;
import unlpbdVO.poc.service.IItemService;
import unlpbdVO.poc.service.IProyectoService;




/**
 * Esta clase presenta los diferentes "endpoints" de la api rest.
 * 
 */
@RestController
public class PocController {

	/**
	 * ---------------------------------------------------------------------------------------------
	 * Servicios relacionados con los tipos de estado.
	 * ---------------------------------------------------------------------------------------------
	 */
	@Inject
	private ITipoEstadoService tipoEstadoService;

	/**
	 * Endpoint "dummy" para crear tipo de estados
	 */
	@RequestMapping(value = "/TipoEstado/create", method = RequestMethod.POST, produces = "application/json")
	public tipoEstadoDTO createTipoEstado(String pNombre, Boolean pEsInicial, Boolean pEsFinal ) {
		return this.getTipoEstadoService().addTipoEstado(pNombre, pEsInicial, pEsFinal);
	}

	/**
	 * Endpoint "dummy" para listar los tipos de estados 
	 */	
	@RequestMapping(value = "/TipoEstado/list", method = RequestMethod.GET, produces = "application/json")
	public List<tipoEstadoDTO> ListaTipoEstados(){
		
		return this.getTipoEstadoService().ListaTipoEstados();
	}
	
	/**
	 * Getter.
	 * @return el servicio relacionado con los tipos de estado.
	 */
	public ITipoEstadoService getTipoEstadoService() {
		return this.tipoEstadoService;
	}

	/**
    * ---------------------------------------------------------------------------------------------
	* Servicios relacionado con los tipos de items.
	* ---------------------------------------------------------------------------------------------
	*/
	@Inject
	private ITipoItemService tipoItemService;

	/**
	 * Endpoint "dummy" para crear un tipo de item
	 */
	@RequestMapping(value = "/TipoItem/create", method = RequestMethod.POST, produces = "application/json")
	public tipoItemDTO createTipoItem (String pnombre) {
		return this.getTipoItemService().addTipoItem(pnombre);
	}

	/**
	 * Endpoint "dummy" para listar los tipos de items
	 */	
	@RequestMapping(value = "/TipoItem/list", method = RequestMethod.GET, produces = "application/json")
	public List<tipoItemDTO> ListaTipoItem(){
		
		return this.getTipoItemService().ListaTipoItems();
	}
	
	
	/**
	 * Getter.
	 * @return el servicio relacionado con los tipos de item.
	 */
	public ITipoItemService getTipoItemService() {
		return this.tipoItemService;
	}
	
	/**
	 * ---------------------------------------------------------------------------------------------
	 * Servicios relacionados con los estados.
	 * ---------------------------------------------------------------------------------------------
	 */
	@Inject
	private IEstadoService estadoService;


	/**
	 * Endpoint "dummy" para listar los estados de un item
	 */	
	@RequestMapping(value = "/estados", method = RequestMethod.GET, produces = "application/json")
	public List<estadoDTO> ListEstadosItem(String pIdItem) {
		
		UUID idItem = UUID.fromString(pIdItem);	
		return this.getEstadoService().listaEstados(idItem);
	}

	/**
	 * Endpoint "dummy" para mostrar los datos de un estado
	 */	
	@RequestMapping(value = "/estados/showEstado", method = RequestMethod.GET, produces = "application/json")
	public estadoDTO showEstado(String pIdEstado) {
		
		UUID idEstado = UUID.fromString(pIdEstado);	
		
		return this.getEstadoService().showEstado(idEstado);
	}
	
	
	
	
	/**
	 * Getter.
	 * @return el servicio relacionado con los estados.
	 */
	public IEstadoService getEstadoService() {
		return this.estadoService;
	}
	

	/**
	 * ---------------------------------------------------------------------------------------------
	 * Servicios relacionados con los items.
	 * ---------------------------------------------------------------------------------------------
	 */

	@Inject
	private IItemService itemService;
	
	/**
	 * Endpoint "dummy" para listar items de un proyectos 
	 */	
	@RequestMapping(value = "/Item", method = RequestMethod.GET, produces = "application/json")
	public List<itemDTOSinEstados> ListItemsProyecto(String pIdProyecto) {
		
		UUID idProyecto = UUID.fromString(pIdProyecto);
		
		return this.getItemService().ListaProyectos(idProyecto);
	}

	/**
	 * Endpoint "dummy" para agregar un estado a un item
	 */	
	@RequestMapping(value = "/Item/addEstadoItem", method = RequestMethod.POST, produces = "application/json")
	public estadoDTO addEstadoAItem(String pIdItem, String pIdTipoEstado) {
				
		UUID idItem = UUID.fromString(pIdItem);	
		UUID idTipoEstado= UUID.fromString(pIdTipoEstado);
		LocalDateTime hoy = LocalDateTime.now();

		return this.getItemService().addEstadoAItem(idItem, hoy, idTipoEstado);

	}

	/**
	 * Endpoint "dummy" para consultar el ultimo estado de un item
	 */	
	@RequestMapping(value = "/Item/lastEstadoItem", method = RequestMethod.GET, produces = "application/json")
	public estadoDTO lastEstadoItem(String pIdItem) {

		UUID idItem = UUID.fromString(pIdItem);	
	
		return this.getItemService().getUltimoEstadoItem(idItem);

	}

	/**
	 * Endpoint "dummy" para borrar el ultimo estado de un item
	 */	
	@RequestMapping(value = "/Item/deleteLastEstadoItem", method = RequestMethod.POST, produces = "application/json")
	public void deleteLastEstadoItem(String pIdItem) {

		UUID idItem = UUID.fromString(pIdItem);	
	
		this.getItemService().deleteUltimoEstadoItem(idItem);

	}

	
	/**
	 * Getter.
	 * @return el servicio relacionado con los tipos de item.
	 */
	public IItemService getItemService() {
		return this.itemService;
	}


	/**
	 * ---------------------------------------------------------------------------------------------
	 * Servicios relacionados con los proyectos.
     * ---------------------------------------------------------------------------------------------
	 */
	@Inject
	private IProyectoService proyectoService;

	/**
	 * Endpoint "dummy" para crear proyectos
	 */
	@RequestMapping(value = "/Proyecto/create", method = RequestMethod.POST, produces = "application/json")	
	public proyectoDTO createProyecto(String pNombre, Date pFechaInicio) {
		
		return this.getProyectoService().addProyecto(pNombre,pFechaInicio);
	}

	/**
	 * Endpoint "dummy" para agregar un item a un proyecto
	 */	
	@RequestMapping(value = "/Proyecto/addItemAProyecto", method = RequestMethod.POST, produces = "application/json")	
	public itemDTO addItemAProyecto(String pIdProyecto, String pNombre, Integer pPrioridad, String pIdTipoItem) {

		UUID idProyecto = UUID.fromString(pIdProyecto);
		UUID idTipoItem = UUID.fromString(pIdTipoItem);	

		
		return this.getProyectoService().addItem(idProyecto, pNombre, pPrioridad, idTipoItem);
		
	}

	/**
	 * Endpoint "dummy" para listar los proyectos (sin mostrar sus items) ordernados por fecha inicio desc
	 */	
	@RequestMapping(value = "/proyectos", method = RequestMethod.GET, produces = "application/json")
	public List<proyectoSinItemsDTO> ListProyectos() {
		
		return this.getProyectoService().ListaProyectosSinDataItems();
	}
	
	
	/**
	 * Endpoint "dummy" para modificar el nombre de  un proyecto
	 */	
	@RequestMapping(value = "/Proyecto/modifyNombreProyecto", method = RequestMethod.POST, produces = "application/json")
	public proyectoSinItemsDTO  modifyNombreProyecto(String pIdProyecto, String pNuevoNombre) {
		
		UUID idProyecto = UUID.fromString(pIdProyecto);
		
		return this.getProyectoService().modifyNombreProyecto(idProyecto, pNuevoNombre) ;
	}
	
	
	/**
	 * Getter.
	 * @return el servicio relacionado con los proyectos.
	 */
	public IProyectoService getProyectoService() {
		return this.proyectoService;
	}

	
}

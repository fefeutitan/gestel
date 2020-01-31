/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.MappBusiness;
import br.gov.ce.seinfra.gestel.model.Mapp;

/**
 * Implementacao de CrudMappFacade para {@link Mapp}.
 * @author ferreira
 *
 */
@Component("crudMappFacade")
public class CrudMappFacade extends CrudFacadeGenerico<Mapp, MappBusiness> {

}

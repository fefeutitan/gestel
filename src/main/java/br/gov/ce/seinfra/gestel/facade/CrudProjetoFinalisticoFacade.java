/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.ProjetoFinalisticoBusiness;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;

/**
 * Implementacao de CrudProjetoFinalisticoFacade para
 * {@link ProjetoFinalistico}.
 * 
 * @author ferreira
 *
 */
@Component("crudProjetoFinalisticoFacade")
public class CrudProjetoFinalisticoFacade extends CrudFacadeGenerico<ProjetoFinalistico, ProjetoFinalisticoBusiness> {

}

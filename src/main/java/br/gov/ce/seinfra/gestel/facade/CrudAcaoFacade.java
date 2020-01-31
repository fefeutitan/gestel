/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.AcaoBusiness;
import br.gov.ce.seinfra.gestel.model.Acao;

/**
 * Facada Acao.
 * @author ferreira
 *
 */
@Component("crudAcaoFacade")
public class CrudAcaoFacade extends CrudFacadeGenerico<Acao, AcaoBusiness> {

}

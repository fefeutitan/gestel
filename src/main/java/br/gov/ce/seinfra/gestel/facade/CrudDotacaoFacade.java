/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.model.Dotacao;

/**
 * Implemetacao de CrudDotacaoFacade para {@link Dotacao}.
 * @author ferreira
 *
 */
@Component("crudDotacaoFacade")
public class CrudDotacaoFacade extends CrudFacadeGenerico<Dotacao, DotacaoBusiness> {

}

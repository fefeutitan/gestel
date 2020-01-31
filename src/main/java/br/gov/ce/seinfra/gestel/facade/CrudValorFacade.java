package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.ValorBusiness;
import br.gov.ce.seinfra.gestel.model.Valor;

/**
 * Facada Acao.
 * @author fernando
 *
 */
@Component("CrudValorFacade")
public class CrudValorFacade extends CrudFacadeGenerico<Valor, ValorBusiness> {

}

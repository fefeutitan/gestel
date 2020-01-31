package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.ValorDao;
import br.gov.ce.seinfra.gestel.model.Valor;

/**
 * Business AcaoBusiness.
 * @author fernando
 *
 */
@Component("contaValorBusiness")
public class ValorBusiness extends BusinessGenerico<Valor, ValorDao> {

}

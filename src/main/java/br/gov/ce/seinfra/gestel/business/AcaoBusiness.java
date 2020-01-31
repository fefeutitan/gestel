/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.AcaoDao;
import br.gov.ce.seinfra.gestel.model.Acao;

/**
 * Business AcaoBusiness.
 * @author ferreira
 *
 */
@Component("acaoBusiness")
public class AcaoBusiness extends BusinessGenerico<Acao, AcaoDao> {

}

/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.ProjetoFinalisticoDao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;

/**
 * Business ProjetoFinalisticoBusiness.
 * @author ferreira
 *
 */
@Component("projetoFinalisticoBusiness")
public class ProjetoFinalisticoBusiness extends BusinessGenerico<ProjetoFinalistico, ProjetoFinalisticoDao> {

}

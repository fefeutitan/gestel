/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;

/**
 * Implementacao de ProjetoFinalisticoDao para {@link ProjetoFinalistico}.
 * @author ferreira
 *
 */
@Component("projetoFinalisticoDao")
public class ProjetoFinalisticoDao extends HibernateDaoGenerico<ProjetoFinalistico> {

}

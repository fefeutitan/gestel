/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Acao;

/**
 * Implementacao de AcaoDao para {@link Acao}.
 * @author ferreira
 *
 */
@Component("acaoDao")
public class AcaoDao extends HibernateDaoGenerico<Acao> {

}

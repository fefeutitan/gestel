package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Acao;
import br.gov.ce.seinfra.gestel.model.Valor;

/**
 * Implementacao de AcaoDao para {@link Acao}.
 * @author ferreira
 *
 */
@Component("valorDao")
public class ValorDao extends HibernateDaoGenerico<Valor> {

}

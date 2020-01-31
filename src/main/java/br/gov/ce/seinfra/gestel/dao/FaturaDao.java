package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Fatura;

/**
 * Implementacao de FaturaDao para {@link Fatura}.
 * @author fernando
 *
 */
@Component("faturaDao")
public class FaturaDao extends HibernateDaoGenerico<Fatura> {


}

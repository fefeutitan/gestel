package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Telefone;

/**
 * Implementacao de TelefoneDao para {@link TelefoneDao}.
 * @author fernando
 *
 */
@Component("telefoneDao")
public class TelefoneDao extends HibernateDaoGenerico<Telefone> {

}

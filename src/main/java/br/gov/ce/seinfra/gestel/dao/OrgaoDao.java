package br.gov.ce.seinfra.gestel.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateCriteriaFinder;
import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.model.Orgao;

/**
 * Implementacao de Dao para {@link Orgao}.
 * 
 * @author ferreira
 *
 */
@Component("orgaoDao")
public class OrgaoDao extends HibernateDaoGenerico<Orgao> {

	@Override
	public List<Orgao> listar(Orgao filtro) throws DaoException {
		return super.listar(filtro);
	}

	@Override
	public List<Orgao> listar(Orgao filtro, String... camposOrdenacao) throws DaoException {
		return super.listar(filtro, camposOrdenacao);
	}

	/** {@inheritDoc} */
	public List<Orgao> listarOrgaoComFaturas() throws DaoException {
		HibernateCriteriaFinder<Orgao> action = new HibernateCriteriaFinder<Orgao>(Orgao.class, null, new String[] {});
		action.addCriterion(Restrictions.isNotEmpty("mapps"));
		return listar(action);
	}
}

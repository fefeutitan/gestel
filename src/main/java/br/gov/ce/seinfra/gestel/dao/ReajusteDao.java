package br.gov.ce.seinfra.gestel.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateCriteriaFinder;
import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Reajuste;

/**
 * Implementacao de TelefoneDao para {@link TelefoneDao}.
 * @author fernando
 *
 */
@Component("reajusteDao")
public class ReajusteDao extends HibernateDaoGenerico<Reajuste> {

	public List<Reajuste> listaParaAnalise() {
		 HibernateCriteriaFinder<Reajuste> action = new HibernateCriteriaFinder<Reajuste>(Reajuste.class, null, new String[] {});

//		 action.addCriterion(Restrictions.eq("orgao", reajuste.getOrgao()));
         
		try {
			return super.listar(action);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<Reajuste>();
		}
	}

}

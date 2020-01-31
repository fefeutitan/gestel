package br.gov.ce.seinfra.gestel.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateCriteriaFinder;
import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ViewResumo;

/**
 * 
 * @author ferreira
 *
 */
@Component("viewResumoDao")
public class ViewResumoDao extends HibernateDaoGenerico<ViewResumo> {

	@Override
	public List<ViewResumo> listar(ViewResumo filtro) throws DaoException {
		HibernateCriteriaFinder<ViewResumo> action = new HibernateCriteriaFinder<ViewResumo>(ViewResumo.class, null,
				new String[] {});
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM");
		String data = formatador.format(filtro.getMesano());
		action.addCriterion(Restrictions.sqlRestriction("to_char(this_.mesano, 'yyyy/MM')=?", data, Hibernate.STRING));
		action.addCriterion(Restrictions.eq("orgao", filtro.getOrgao()));
		action.addCriterion(Restrictions.eq("projeto", filtro.getProjeto()));
		return listar(action);
	}

	public List<ViewResumo> listarVinculacaoAtual() throws DaoException {

		Session session = getSession();

		SQLQuery query = session.createSQLQuery("SELECT " + "to_char(mesano, 'MM/yyyy') AS ano_vinculado, "
				+ "	numero_dotacao ,  sum(total_of_dotacao_vinculado + total_tes_dotacao_vinculado) AS valor_vinculado "
				+ "  FROM " + " bdgestel.resumo " + " GROUP BY mesano,numero_dotacao "
				+ " ORDER BY mesano,numero_dotacao");
		query.setResultTransformer(Transformers.aliasToBean(ViewResumo.class));
		query.addScalar("ano_vinculado", new StringType());
		query.addScalar("numero_dotacao", new StringType());
		query.addScalar("valor_vinculado", new DoubleType());
		List<ViewResumo> lista = query.list();

		return lista;

	}
}

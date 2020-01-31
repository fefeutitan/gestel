/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;

/**
 * Implementacao de dao para {@link Dotacao}.
 * @author ferreira
 *
 */
@Component("dotacaoDao")
public class DotacaoDao extends HibernateDaoGenerico<Dotacao>{
	
	  /**
     * Lista de proprietario e inquilinos.
     * @param filtro filtro
     * @return lista
     * @throws DaoException caso haja erros
     */
    public List<Dotacao> geValorDotacoes(final Orgao orgao, final int ano) throws DaoException {

        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria(Dotacao.class);
                criteria.add(Restrictions.eq("orgao", orgao));
                criteria.add(Restrictions.eq("ano", ano));
                return getDotacoes(criteria);
            }
        });
    }
    
  private List<Dotacao> getDotacoes(Criteria criteria) {
	  
        criteria.createAlias("conta.dotacao", "conta.dotacao");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("numero"));
        projectionList.add(Projections.sum("valorTesouro"));
        projectionList.add(Projections.sum("valorOutrasFontes"));
        projectionList.add(Projections.sum("conta.dotacao.valorTesouro"));
        projectionList.add(Projections.groupProperty("numero"));
        
        criteria.setProjection(projectionList);
        List<Object[]> results = criteria.list();
        List<Dotacao> lista = new ArrayList<Dotacao>();
        
        for (Object[] object : results) {
            int col = 0;
            Dotacao dotacao = new Dotacao();
            dotacao.setNumero((String) object[col++]);
            dotacao.setValorTesouro((BigDecimal) object[col++]);
            dotacao.setValorOutrasFontes((BigDecimal) object[col++]);
            dotacao.setValorTesouroExe((BigDecimal) object[col++]);
            
            lista.add(dotacao);
        }
        
        return lista;
  }
  
  }

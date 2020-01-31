/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateCriteriaFinder;
import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;

/**
 * Implementacao de ContaDao para {@link Conta}.
 * 
 * @author ferreira
 *
 */
@Component("contaDao")
public class ContaDao extends HibernateDaoGenerico<Conta> {

	/** {@inheritDoc} */
	public List<Conta> listaParaAnalise(Conta conta) {
		 HibernateCriteriaFinder<Conta> action = new HibernateCriteriaFinder<Conta>(Conta.class, null, new String[] {});
            
		action.createAlias("status", "status");
		List<STATUS_CONTA> status =  new ArrayList<STATUS_CONTA>();
		status.add(STATUS_CONTA.ENVIADA_PARA_ANALISE);
		status.add(STATUS_CONTA.EM_ANALISE);
		status.add(STATUS_CONTA.APROVADO);
		status.add(STATUS_CONTA.REPROVADO);
		status.add(STATUS_CONTA.CADASTRADA);/*
		status.add(STATUS_CONTA.VINCULADO);*/
		
        action.addCriterion(Restrictions.in("status.status", status));
		action.addCriterion(Restrictions.eq("orgao", conta.getOrgao()));

		try {
			return super.listar(action);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<Conta>();
		}
	}
	
	/** {@inheritDoc} */
	public List<Conta> listaResumo(Conta conta,boolean ano) {
	    HibernateCriteriaFinder<Conta> action = new HibernateCriteriaFinder<Conta>(Conta.class, null, new String[] {});
	    
		action.createAlias("status", "status");
		action.addCriterion(Restrictions.eq("orgao", conta.getOrgao()));
        if (conta.getMesAno()!=null ){
        String anoMes = getDataFormatada(conta.getMesAno(), "yyyy/MM");
		action.addCriterion(Restrictions.sqlRestriction("to_char(this_.mesano, 'yyyy/MM')<=?", anoMes, Hibernate.STRING));
        //action.addCriterion(Restrictions.sqlRestriction("to_char(this_.mesano, 'yyyy')=?",  anoMes.substring(0,4), Hibernate.STRING));
        action.addCriterion(Restrictions.eq("status.status", STATUS_CONTA.APROVADO));
		
        }

		try {
			return listar(action);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<Conta>();
		}
	}
	

	  /**
   * Lista de proprietario e inquilinos.
   * @param filtro filtro
   * @return lista
   * @throws DaoException caso haja erros
   */
  public List<Dotacao> getValorDotacoes(final Orgao orgao, final int ano) throws DaoException {

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
	  

	  criteria.createAlias("status", "status", Criteria.FULL_JOIN);
	  criteria.createAlias("dotacao", "dotacao", Criteria.FULL_JOIN);
	  criteria.createAlias("dotacao.orgao", "dotacao.orgao", Criteria.FULL_JOIN);


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


	/**
	 * formata data.
	 * @param formato  formato
	 * @return data formatada
	 */
	public String getDataFormatada(Date d, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String data = sdf.format(d);
		return data;
	}
/*
 * listaParaAnalise valendo
 * */
	public List<Conta> listaParaAnalise() {
		 HibernateCriteriaFinder<Conta> action = new HibernateCriteriaFinder<Conta>(Conta.class, null, new String[] {});
         
		action.createAlias("status", "status");
		List<STATUS_CONTA> status =  new ArrayList<STATUS_CONTA>();
		status.add(STATUS_CONTA.ENVIADA_PARA_ANALISE);
		status.add(STATUS_CONTA.EM_ANALISE);
		status.add(STATUS_CONTA.APROVADO);
		status.add(STATUS_CONTA.REPROVADO);
		status.add(STATUS_CONTA.CADASTRADA);
		status.add(STATUS_CONTA.VINCULADO);
		
       action.addCriterion(Restrictions.in("status.status", status));

		try {
			return super.listar(action);
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<Conta>();
		}
	}
	
	
}

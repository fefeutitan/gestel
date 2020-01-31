/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.dao.DotacaoDao;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Mapp;
import br.gov.ce.seinfra.gestel.model.Orgao;

/**
 * Implementacao de MappBusiness para {@link Mapp}.
 * @author ferreira
 *
 */
@Component("dotacaoBusiness")
public class DotacaoBusiness extends BusinessGenerico<Dotacao, DotacaoDao>{

	public List<Dotacao> geValorDotacoes(Orgao orgao, int ano) throws BusinessException {
			try {
				return getDao().geValorDotacoes(orgao, ano);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			return new ArrayList<Dotacao>();
	}
	

	public void salvar(Dotacao resumo) throws ValidacaoException, BusinessException {
			super.persiste(resumo);
	}
	

}

package br.gov.ce.seinfra.gestel.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.dao.ViewResumoDao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ViewResumo;

/**
 * Negocio view resumo.
 * 
 * @author ferreira
 *
 */
@Component("viewResumoBusiness")
public class ViewResumoBusiness extends BusinessGenerico<ViewResumo, ViewResumoDao> {

	public void salvar(ViewResumo resumo) throws ValidacaoException, BusinessException {
		super.persiste(resumo);
	}

	public void excluir(ViewResumo resumo) throws ValidacaoException, BusinessException {
		super.excluir(resumo);
	}

	public List<ViewResumo> listarVinculacaoAtual() throws DaoException {
		return getDao().listarVinculacaoAtual();
	}
}

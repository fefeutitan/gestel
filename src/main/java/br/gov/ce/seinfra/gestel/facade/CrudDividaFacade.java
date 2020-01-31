/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.DividaBusiness;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.business.ProjetoFinalisticoBusiness;
import br.gov.ce.seinfra.gestel.model.Divida;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;

/**
 * Facada Divida.
 * 
 * @author ferreira
 *
 */
@Component("crudDividaFacade")
public class CrudDividaFacade extends CrudFacadeGenerico<Divida, DividaBusiness> {

	public List<ProjetoFinalistico> getPfs(Orgao orgao) throws BusinessException {
		try {
			ProjetoFinalistico pf = new ProjetoFinalistico();
			pf.setOrgao(orgao);
			return getBusiness(ProjetoFinalisticoBusiness.class).listar(pf);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Dotacao> getDotacoes(Orgao orgao) throws BusinessException {
		try {
			Dotacao dotacao = new Dotacao();
			dotacao.setOrgao(orgao);
			return getBusiness(DotacaoBusiness.class).listar(dotacao);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}
}

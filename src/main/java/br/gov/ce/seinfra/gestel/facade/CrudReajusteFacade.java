package br.gov.ce.seinfra.gestel.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.FacadeException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.business.OrgaoBusiness;
import br.gov.ce.seinfra.gestel.business.ReajusteBusiness;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Reajuste;

/**
 * Facada Telefone.
 * 
 * @author FERNANDO
 *
 */
@Component("crudReajusteFacade")
public class CrudReajusteFacade extends CrudFacadeGenerico<Reajuste, ReajusteBusiness> {

	@Autowired
	@Qualifier("dotacaoBusiness")
	private DotacaoBusiness dotacaoBusiness;
	
	@Autowired
	@Qualifier("reajusteBusiness")
	private ReajusteBusiness reajusteBusiness;

	public List<Dotacao> getDotacoes(Orgao orgao) throws BusinessException {
		try {
			Dotacao dotacao = new Dotacao();
			dotacao.setOrgao(orgao);
			return getBusiness(DotacaoBusiness.class).listar(dotacao);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Dotacao> getDotacoes() throws BusinessException {
		try {
			Dotacao dotacao = new Dotacao();
			return getBusiness(DotacaoBusiness.class).listar(dotacao);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Reajuste> getReajustes(Orgao orgao) throws BusinessException {
		try {
			Reajuste reajuste = new Reajuste();
			reajuste.setOrgao(orgao);
			return getBusiness(ReajusteBusiness.class).listar(reajuste);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Reajuste> listaParaAnalise() {
		return getBusiness().listaParaAnalise();
	}

	public void atualizarDotacao(Reajuste reajuste) throws BusinessException {
		Dotacao d = new Dotacao();
		d.setId(reajuste.getDotacao().getId());
		d = dotacaoBusiness.get(d);
		d.setValorOutrasFontes(d.getValorOutrasFontes().add(reajuste.getValorReajusteOutraFonte()));
		d.setValorTesouro(d.getValorTesouro().add(reajuste.getValorReajusteTesouro()));
		try {
			dotacaoBusiness.salvar(d);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		}
		atualizarReajuste(reajuste);
	}

	private void atualizarReajuste(Reajuste reajuste) {
		reajuste.setReajustado(true);
		reajuste.setOrgao(reajuste.getDotacao().getOrgao());
		try {
			this.salvar(reajuste);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		} catch (FacadeException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarOrgao(Reajuste reajuste) {
		Reajuste r = new Reajuste();
		r.setId(reajuste.getDotacao().getId());
		try {
			r = reajusteBusiness.get(r);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		r.setOrgao(reajuste.getDotacao().getOrgao());
		try {
			this.salvar(reajuste);
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

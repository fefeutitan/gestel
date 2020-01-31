/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.AcaoBusiness;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.business.MappBusiness;
import br.gov.ce.seinfra.gestel.business.OrgaoBusiness;
import br.gov.ce.seinfra.gestel.business.ProjetoFinalisticoBusiness;
import br.gov.ce.seinfra.gestel.business.TelefoneBusiness;
import br.gov.ce.seinfra.gestel.model.Acao;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Mapp;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Telefone;

/**
 * Implementacao de facada para {@link Orgao}.
 * 
 * @author ferreira
 *
 */
@Component("crudOrgaoFacade")
public class CrudOrgaoFacade extends CrudFacadeGenerico<Orgao, OrgaoBusiness> {

	public List<Mapp> getMapps(Orgao orgao) throws BusinessException {
		try {
			Mapp mapp = new Mapp();
			mapp.setOrgao(orgao);
			return getBusiness(MappBusiness.class).listar(mapp);
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

	public List<Acao> getAcoes(Orgao orgao) throws BusinessException {
		try {
			Acao acao = new Acao();
			acao.setOrgao(orgao);
			return getBusiness(AcaoBusiness.class).listar(acao);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<ProjetoFinalistico> getProjetos(Orgao orgao) throws BusinessException {
		try {
			ProjetoFinalistico pf = new ProjetoFinalistico();
			pf.setOrgao(orgao);
			return getBusiness(ProjetoFinalisticoBusiness.class).listar(pf);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<Telefone> getTelefones(Orgao orgao) throws BusinessException {
		try {
			Telefone telefone = new Telefone();
			telefone.setOrgao(orgao);
			return getBusiness(TelefoneBusiness.class).listar(telefone);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

}

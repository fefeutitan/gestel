/**
 * 
 */
package br.gov.ce.seinfra.gestel.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.AcaoBusiness;
import br.gov.ce.seinfra.gestel.business.ContaBusiness;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.business.FaturaBusiness;
import br.gov.ce.seinfra.gestel.business.MappBusiness;
import br.gov.ce.seinfra.gestel.business.ProjetoFinalisticoBusiness;
import br.gov.ce.seinfra.gestel.business.StatusBusiness;
import br.gov.ce.seinfra.gestel.business.TelefoneBusiness;
import br.gov.ce.seinfra.gestel.business.ValorBusiness;
import br.gov.ce.seinfra.gestel.business.ViewResumoBusiness;
import br.gov.ce.seinfra.gestel.model.Acao;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Fatura;
import br.gov.ce.seinfra.gestel.model.Mapp;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Status;
import br.gov.ce.seinfra.gestel.model.Telefone;
import br.gov.ce.seinfra.gestel.model.Valor;
import br.gov.ce.seinfra.gestel.model.ViewResumo;

/**
 * Facada Conta.
 * @author ferreira
 *
 */
@Component("crudContaFacade")
public class CrudContaFacade extends CrudFacadeGenerico<Conta, ContaBusiness> {

	@Autowired
	@Qualifier("viewResumoBusiness")
	private ViewResumoBusiness viewResumoBusiness;

	@Autowired
	@Qualifier("dotacaoBusiness")
	private DotacaoBusiness dotacaoBusiness;
	
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
	

	public List<Fatura> getFaturas(Conta conta) throws BusinessException {
		try {
			Fatura fatura = new Fatura();
			fatura.setConta(conta);
			return getBusiness(FaturaBusiness.class).listar(fatura);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}
	

	public List<Valor> getValores(Conta conta) throws BusinessException {
		try {
			Valor valor = new Valor();
			valor.setConta(conta);
			return getBusiness(ValorBusiness.class).listar(valor);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}
	
	public void enviar(Status status) throws BusinessException{
		try {
			Conta conta = status.getConta();
			getBusiness(StatusBusiness.class).persiste(status);
			conta.setStatus(status);
			getBusiness().persiste(conta);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}catch (ValidacaoException e) {
			throw new BusinessException(e);
		}
	}
	

	public List<Conta> listaParaAnalise() throws BusinessException {
			return getBusiness().listaParaAnalise();
	}
	

	public List<Conta> listaResumo(Conta conta, boolean ano) throws BusinessException {
			return getBusiness().listaResumo(conta,ano);
	}

	/** {@inheritDoc} 
	 * @throws DaoException */
	public List<Dotacao> listarValores(Orgao orgao, int ano) throws BusinessException {
		return 	getBusiness(DotacaoBusiness.class).geValorDotacoes(orgao, ano);
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

	public void salvarResumo(ViewResumo resumo) {
		try {
			getBusiness(ViewResumoBusiness.class).salvar(resumo);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

	public void excluirResumo(Valor valor) throws BusinessException {
		
		ViewResumo vr = viewResumoBusiness.get(valor.getResumo().getId());
		//antes de excluir subtrai a dotação o valor exe
		atualizarDotacao(vr);
		try {
			viewResumoBusiness.excluir(vr);
		} catch (ValidacaoException e1) {
			e1.printStackTrace();
		}
	}

	public void atualizarDotacao(ViewResumo resumo) throws BusinessException {
		Dotacao d = new Dotacao();
		d.setId(resumo.getDotacao().longValue());
		d = dotacaoBusiness.get(d);
		d.setValorOutrasFontesExe(d.getValorOutrasFontesExe().subtract(resumo.getValorOutrasFontesVinculado()));
		d.setValorTesouroExe(d.getValorTesouroExe().subtract(resumo.getValorTesouroVinculado()));
		try {
			dotacaoBusiness.salvar(d);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		}

	}
		
}

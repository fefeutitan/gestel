package br.gov.ce.seinfra.gestel.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.EmailBusinessImpl;
import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.ContaBusiness;
import br.gov.ce.seinfra.gestel.business.DotacaoBusiness;
import br.gov.ce.seinfra.gestel.business.OrgaoBusiness;
import br.gov.ce.seinfra.gestel.business.ProjetoFinalisticoBusiness;
import br.gov.ce.seinfra.gestel.business.ViewResumoBusiness;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.ViewResumo;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;
import br.gov.ce.seinfra.model.Email;

@Component("viewResumoFacade")
public class ViewResumoFacade extends CrudFacadeGenerico<ViewResumo, ViewResumoBusiness> {

	/**
	 * HOST do SMTP.
	 */
	private static final String HOST_SMTP = "relay.etice.ce.gov.br";

	@Autowired
	@Qualifier("orgaoBusiness")
	private OrgaoBusiness orgaoBusiness;

	@Autowired
	@Qualifier("projetoFinalisticoBusiness")
	private ProjetoFinalisticoBusiness projetoFinalisticoBusiness;

	@Autowired
	@Qualifier("viewResumoBusiness")
	private ViewResumoBusiness viewResumoBusiness;

	@Autowired
	@Qualifier("contaBusiness")
	private ContaBusiness contaBusiness;

	@Autowired
	@Qualifier("dotacaoBusiness")
	private DotacaoBusiness dotacaoBusiness;

	/**
	 * lista de orgaos com faturas.
	 * 
	 * @return orgaos
	 * @throws BusinessException
	 */
	public List<Orgao> getOrgaos() throws BusinessException {
		try {
			return orgaoBusiness.listarOrgaoComFaturas();
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * lista de orgaos com faturas.
	 * 
	 * @return orgaos
	 * @throws BusinessException
	 */
	public List<ProjetoFinalistico> getProjetoFinalisticos(Orgao orgao, Date data) throws BusinessException {
		try {
			ProjetoFinalistico pf = new ProjetoFinalistico();
			pf.setOrgao(orgao);
			List<ProjetoFinalistico> projetos = projetoFinalisticoBusiness.listar(pf);
			projetos = getResumo(projetos, data);
			return projetos;
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public List<ProjetoFinalistico> getResumo(List<ProjetoFinalistico> projetos, Date data) throws BusinessException {

		for (ProjetoFinalistico projeto : projetos) {
			List<ViewResumo> resumos = new ArrayList<ViewResumo>();
			ViewResumo resumo = new ViewResumo();
			resumo.setProjeto(projeto.getId());
			resumo.setOrgao(projeto.getOrgao().getId());
			resumo.setMesano(data);
			resumos = viewResumoBusiness.listar(resumo);
			projeto.getDotacoes().addAll(resumos);
		}
		return projetos;
	}

	public void vincular(Conta conta) throws BusinessException, ValidacaoException {
		Orgao orgao = orgaoBusiness.get(conta.getOrgao().getId());
		Conta c = new Conta();
		c.setOrgao(orgao);
		c.setMesAno(conta.getMesAno());
		c = contaBusiness.get(c);
		contaBusiness.atualizarStatus(c);
		// vincula os valores

	}

	public void setOrgaoBusiness(OrgaoBusiness orgaoBusiness) {
		this.orgaoBusiness = orgaoBusiness;
	}

	public void setProjetoFinalisticoBusiness(ProjetoFinalisticoBusiness projetoFinalisticoBusiness) {
		this.projetoFinalisticoBusiness = projetoFinalisticoBusiness;
	}

	public void setViewResumoBusiness(ViewResumoBusiness viewResumoBusiness) {
		this.viewResumoBusiness = viewResumoBusiness;
	}

	public List<Dotacao> getDotacoes(ProjetoFinalistico pf) throws BusinessException {
		try {
			Dotacao dotacao = new Dotacao();
			dotacao.setPf(pf);
			return getBusiness(DotacaoBusiness.class).listar(dotacao);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	public void atualizarResumo(ViewResumo resumo) throws BusinessException {
		try {
			getBusiness(ViewResumoBusiness.class).salvar(resumo);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		}

	}

	public void atualizarDotacao(ViewResumo resumo) throws BusinessException {
		Dotacao d = new Dotacao();
		d.setId(resumo.getDotacao().longValue());
		d = dotacaoBusiness.get(d);
		d.setValorOutrasFontesExe(d.getValorOutrasFontesExe().add(resumo.getDotacaoExe().getValorOutrasFontesExe()));
		d.setValorTesouroExe(d.getValorTesouroExe().add(resumo.getDotacaoExe().getValorTesouroExe()));
		try {
			dotacaoBusiness.salvar(d);
		} catch (ValidacaoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Notifica vinculação.
	 * 
	 * @param conta
	 * @return
	 * @throws BusinessException
	 */
	public boolean notificar(Conta conta, STATUS_CONTA vinculado, String texto) throws BusinessException {
		try {

			HtmlEmail email = new HtmlEmail();
			Orgao orgao = new Orgao();
			orgao.setId(conta.getOrgao().getId());
			orgao = orgaoBusiness.get(orgao);
			orgao.getEmailContato();
			email.setHostName(HOST_SMTP);
			email.setSmtpPort(25);
			email.setFrom("desenvolvimento@seinfra.ce.gov.br");
			email.setSubject("[CONTA VINCULADA COM SUCESSO!]");
			// seta destinatarios
			String[] emails = orgao.getEmailContato().split(",");
			for (int i = 0; i < emails.length; i++) {
				email.addTo(emails[i].toString().trim());
			}
			email.setHtmlMsg("CONTA VINCULADA COM SUCESSO!");
			// usuario para autenticacao
			email.setAuthentication("desenvolvimento@seinfra.ce.gov.br", "dsv2013@");

			email.send();

			return true;
		} catch (EmailException e) {
			throw new BusinessException(e);
		}
	}

	public List<ViewResumo> listarVinculacaoAtual() throws BusinessException, DaoException {
//		ViewResumo a = new ViewResumo();
		return viewResumoBusiness.listarVinculacaoAtual();
	}

}

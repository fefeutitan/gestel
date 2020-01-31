/**
 * 
 */
package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.business.ViewResumoBusiness;
import br.gov.ce.seinfra.gestel.facade.ViewResumoFacade;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;
import br.gov.ce.seinfra.gestel.model.Valor;
import br.gov.ce.seinfra.gestel.model.ViewResumo;
import br.gov.ce.seinfra.view.BaseBean;
import br.gov.ce.seinfra.view.Outcome;

/**
 * Bean do caso de uso analise de conta.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "viewResumoBean")
public class ViewResumoBean extends BaseBean {

	/**
	 * orgao.
	 */
	private Orgao orgao;
	/**
	 * mes e ano.
	 */
	private Date mesAno;

	/**
	 * selecionado.
	 */
	private Conta selecionado;

	/**
	 * dotacao.
	 */
	private ViewResumo dotacao;

	private Dotacao dotacaoExe;

	/**
	 * Bean da Dotação.
	 */
	@ManagedProperty(value = "#{crudDotacao}")
	private CrudDotacaoBean crudDotacaoBean;

	/**
	 * dotacao.
	 */
	private ProjetoFinalistico pf;

	/**
	 * valor que calcula o total dos saldos de valores da conta
	 */
	@SuppressWarnings("unused")
	private BigDecimal saldoTotal;

	private List<ProjetoFinalistico> projetos = new ArrayList<ProjetoFinalistico>();

	/**
	 * facada;
	 */
	@ManagedProperty(value = "#{viewResumoFacade}")
	private ViewResumoFacade facada;

	/**
	 * lista de orgaos.
	 */
	private List<Orgao> orgaos = new ArrayList<Orgao>();

	private List<ViewResumo> resumos = new ArrayList<ViewResumo>();

	public void relatorio() {

		List<ViewResumo> selecionados = new ArrayList<ViewResumo>();

		try {
			selecionados.addAll(facada.listarVinculacaoAtual());
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}

		super.gerarRelatorioPDF(selecionados, super.getRealPath("/reports/valor_vinculado_atual.jrxml"),
				"valor_vinculado_atual");

	}

	public String iniciar() {
		try {
			orgaos = facada.getOrgaos();
			// Limpar sessão
			projetos.clear();

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return Navegacao.ANALISAR_RESUMO_ORGAO;
	}

	// debugin
	public void filtrar() {
		try {
			projetos = new ArrayList<ProjetoFinalistico>();
			projetos = facada.getProjetoFinalisticos(orgao, mesAno);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	// VINCULAR
	public void vincular() {
		Conta conta = new Conta();
		Dotacao d = new Dotacao();
		Orgao o = new Orgao();
		d.setId(dotacao.getDotacao().longValue());
		o.setId(dotacao.getOrgao());
		/*
		 * conta.setDotacaos(d); conta.setPfs(pf);
		 */
		conta.setOrgao(o);
		conta.setMesAno(mesAno);
		try {
			facada.vincular(conta);
			filtrar();
			pf = null;
		} catch (ValidacaoException e) {
			tratarValidacaoException(e);
		} catch (BusinessException e) {
			tratarException(e);
		}

		dotacao.setVinculado(true);
		dotacao.setValorOutrasFontesVinculado(dotacao.getTotalOutrasFontesDotacaoMes());
		dotacao.setValorTesouroVinculado(dotacao.getTotalTesouroDotacaoMes());
		dotacao.setTotalOutrasFontesVinculado(dotacao.getTotalOutrasFontesDotacaoMes());
		dotacao.setTotalTesouroVinculado(dotacao.getTotalTesouroDotacaoMes());
		try {
			facada.atualizarResumo(dotacao);
			filtrar();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		dotacao.getDotacaoExe().setValorOutrasFontesExe(dotacao.getValorOutrasFontesVinculado());
		dotacao.getDotacaoExe().setValorTesouroExe(dotacao.getValorTesouroVinculado());

		try {
			facada.atualizarDotacao(dotacao);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		String texto = "teste";
		// Enviar Email de confirmação de vinculação
		try {
			facada.notificar(conta, STATUS_CONTA.VINCULADO, texto);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String cancelar() {
		return Outcome.CANCELAR;
	}

	public BigDecimal getSaldoTotal() {
		BigDecimal saldoTotal = new BigDecimal("0.0");

		for (Valor valor : getSelecionado().getValores()) {
			saldoTotal = saldoTotal.add(valor.getValorOutraFonte().add(valor.getValorTesouro()));
		}

		return saldoTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	/**
	 * Lista de orgaos.
	 * 
	 * @return lista
	 */
	public List<SelectItem> getOrgaos() {
		return ordena(gerarListaSelectItem(orgaos));
	}

	public ViewResumoFacade getFacada() {
		return facada;
	}

	public void setFacada(ViewResumoFacade facada) {
		this.facada = facada;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}

	public Conta getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Conta selecionado) {
		this.selecionado = selecionado;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public List<ProjetoFinalistico> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoFinalistico> projetos) {
		this.projetos = projetos;
	}

	public Date getMesAno() {
		return mesAno;
	}

	public void setMesAno(Date mesAno) {
		this.mesAno = mesAno;
	}

	public ViewResumo getDotacao() {
		return dotacao;
	}

	public void setDotacao(ViewResumo dotacao) {
		this.dotacao = dotacao;
	}

	public ProjetoFinalistico getPf() {
		return pf;
	}

	public void setPf(ProjetoFinalistico pf) {
		this.pf = pf;
	}

	public CrudDotacaoBean getCrudDotacaoBean() {
		return crudDotacaoBean;
	}

	public void setCrudDotacaoBean(CrudDotacaoBean crudDotacaoBean) {
		this.crudDotacaoBean = crudDotacaoBean;
	}

	public Dotacao getDotacaoExe() {
		return dotacaoExe;
	}

	public void setDotacaoExe(Dotacao dotacaoExe) {
		this.dotacaoExe = dotacaoExe;
	}

	public List<ViewResumo> getResumos() {
		return resumos;
	}

	public void setResumos(List<ViewResumo> resumos) {
		this.resumos = resumos;
	}

}

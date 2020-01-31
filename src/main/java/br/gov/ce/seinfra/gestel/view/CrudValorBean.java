package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudValorFacade;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Fatura;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Valor;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

@SessionScoped
@ManagedBean(name = "crudValor")
public class CrudValorBean extends CrudBeanGenerico<Valor, CrudValorFacade> {

	@ManagedProperty(value = "#{crudConta}")
	private CrudContaBean crudContaBean;

	/**
	 * Lista de projetos.
	 */
	private List<ProjetoFinalistico> projetos = new ArrayList<ProjetoFinalistico>();

	/**
	 * Lista de projetos.
	 */
	private List<Dotacao> dotacoes = new ArrayList<Dotacao>();

	private List<Fatura> faturas = new ArrayList<Fatura>();

	/* private BigDecimal valor; */

	public void adicionar() {
		setSelecionado(new Valor());
		getSelecionado().setConta(crudContaBean.getSelecionado());
		//bug 
		getSelecionado().setId((long) 0);

		getSelecionado().setValorOutraFonte(BigDecimal.ZERO);
		getSelecionado().setValorTesouro(BigDecimal.ZERO);
		getSelecionado().setValorReajusteOutraFonte(BigDecimal.ZERO);
		getSelecionado().setValorReajusteTesouro(BigDecimal.ZERO);
		getSelecionado().setValorTotal(BigDecimal.ZERO);
		getSelecionado().setValorReajusteTesouro(BigDecimal.ZERO);
		crudContaBean.getSelecionado().getValores().add(getSelecionado());
	}

	@Override
	public String salvar() {
		String retorno = super.salvar();
		crudContaBean.setSelecionado(crudContaBean.getSelecionado());
		return retorno;
	}

	@Override
	public String excluir() {
		String retorno = super.excluir();
		crudContaBean.setSelecionado(crudContaBean.getSelecionado());
		return retorno;
	}

	@Override
	protected String getNavegacaoInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public BigDecimal getValor() { return valor; }
	 * 
	 * public void setValor(BigDecimal valor) { this.valor = valor; }
	 */

	public List<ProjetoFinalistico> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoFinalistico> projetos) {
		this.projetos = projetos;
	}

	public List<Dotacao> getDotacoes() {
		return dotacoes;
	}

	public void setDotacoes(List<Dotacao> dotacoes) {
		this.dotacoes = dotacoes;
	}

	public CrudContaBean getCrudContaBean() {
		return crudContaBean;
	}

	public void setCrudContaBean(CrudContaBean crudContaBean) {
		this.crudContaBean = crudContaBean;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

}

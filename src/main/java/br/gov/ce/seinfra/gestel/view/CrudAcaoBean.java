package br.gov.ce.seinfra.gestel.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudAcaoFacade;
import br.gov.ce.seinfra.gestel.model.Acao;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudAcao")
public class CrudAcaoBean extends CrudBeanGenerico<Acao, CrudAcaoFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * Acao temp.
	 */
	private Acao Acao;
	
	@Override
	protected String getNavegacaoInicial() {
		return null;
	}
	
	public void adicionar() {
			setSelecionado(new Acao());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getAcoes().add(getSelecionado());
	}

	@Override
	public String salvar() {
		String retorno = super.salvar();
		crudOrgaoBean.setSelecionado(crudOrgaoBean.getSelecionado());
		return retorno;
	}

	@Override
	public String excluir() {
		String retorno = super.excluir();
		crudOrgaoBean.setSelecionado(crudOrgaoBean.getSelecionado());
		return retorno;
	}
	
	public CrudOrgaoBean getCrudOrgaoBean() {
		return crudOrgaoBean;
	}

	public void setCrudOrgaoBean(CrudOrgaoBean crudOrgaoBean) {
		this.crudOrgaoBean = crudOrgaoBean;
	}

	public Acao getAcao() {
		return Acao;
	}

	public void setAcao(Acao Acao) {
		this.Acao = Acao;
	}

}

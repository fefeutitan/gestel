package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudDotacaoFacade;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudDotacao")
public class CrudDotacaoBean extends CrudBeanGenerico<Dotacao, CrudDotacaoFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * Dotacao temp.
	 */
	private Dotacao Dotacao;
	
	@Override
	protected String getNavegacaoInicial() {
		return null;
	}
	
	public void adicionar() {
			setSelecionado(new Dotacao());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getDotacoes().add(getSelecionado());
			getSelecionado().setValorOutrasFontes(BigDecimal.ZERO);
			getSelecionado().setValorOutrasFontesExe(BigDecimal.ZERO);
			getSelecionado().setValorTesouro(BigDecimal.ZERO);
			getSelecionado().setValorTesouroExe(BigDecimal.ZERO);
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

	public Dotacao getDotacao() {
		return Dotacao;
	}

	public void setDotacao(Dotacao Dotacao) {
		this.Dotacao = Dotacao;
	}

}

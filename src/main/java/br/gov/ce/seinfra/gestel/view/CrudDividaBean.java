package br.gov.ce.seinfra.gestel.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.gestel.facade.CrudDividaFacade;
import br.gov.ce.seinfra.gestel.model.Divida;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudDivida")
public class CrudDividaBean extends CrudBeanGenerico<Divida, CrudDividaFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * divida temp.
	 */
	private Divida divida;
	
	@Override
	protected String getNavegacaoInicial() {
		return null;
	}
	
	
	/*public void adicionar() {
			setSelecionado(new Divida());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getDividas().add(getSelecionado());
	}*/

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

	public List<SelectItem>  getPfs(){
		try {
			List<ProjetoFinalistico> pfs = getCrudFacade().getPfs(crudOrgaoBean.getSelecionado());
			return inverte(ordena(gerarListaSelectItem(pfs)));
		} catch (BusinessException e) {
			tratarException(e);
		}
		
		return null;
	}
	
	public List<SelectItem>  getDotacoes(){
		try {
			List<Dotacao> dotacoes = getCrudFacade().getDotacoes(crudOrgaoBean.getSelecionado());
			return inverte(ordena(gerarListaSelectItem(dotacoes)));
		} catch (BusinessException e) {
			tratarException(e);
		}
		
		return null;
	}
	  
	public CrudOrgaoBean getCrudOrgaoBean() {
		return crudOrgaoBean;
	}

	public void setCrudOrgaoBean(CrudOrgaoBean crudOrgaoBean) {
		this.crudOrgaoBean = crudOrgaoBean;
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

}

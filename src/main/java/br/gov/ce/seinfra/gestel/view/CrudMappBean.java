package br.gov.ce.seinfra.gestel.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudMappFacade;
import br.gov.ce.seinfra.gestel.model.Mapp;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudMapp")
public class CrudMappBean extends CrudBeanGenerico<Mapp, CrudMappFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * Mapp temp.
	 */
	private Mapp mapp;
	
	@Override
	protected String getNavegacaoInicial() {
		return null;
	}
	
	public void adicionar() {
			setSelecionado(new Mapp());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getMapps().add(getSelecionado());
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

	public Mapp getMapp() {
		return mapp;
	}

	public void setMapp(Mapp mapp) {
		this.mapp = mapp;
	}

}

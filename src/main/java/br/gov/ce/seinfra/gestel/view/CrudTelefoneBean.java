package br.gov.ce.seinfra.gestel.view;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.gov.ce.seinfra.gestel.facade.CrudTelefoneFacade;
import br.gov.ce.seinfra.gestel.model.Telefone;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

@SessionScoped
@ManagedBean(name = "crudTelefone")
public class CrudTelefoneBean extends CrudBeanGenerico<Telefone, CrudTelefoneFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * Acao temp.
	 */
	private Telefone telefone;

	@Override
	protected String getNavegacaoInicial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void adicionar() {
			setSelecionado(new Telefone());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getTelefones().add(getSelecionado());
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
	
	/**
	 * Lista de tipos de contas.
	 * 
	 * @return lista
	 */
	public List<SelectItem> getTipos() {
		return ordena(gerarListaSelectItem(Arrays.asList(Telefone.TIPO.values())));
	}
		
	public CrudOrgaoBean getCrudOrgaoBean() {
		return crudOrgaoBean;
	}

	public void setCrudOrgaoBean(CrudOrgaoBean crudOrgaoBean) {
		this.crudOrgaoBean = crudOrgaoBean;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	

}

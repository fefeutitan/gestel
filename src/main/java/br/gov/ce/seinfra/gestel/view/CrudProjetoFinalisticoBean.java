package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudProjetoFinalisticoFacade;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudProjetoFinalistico")
public class CrudProjetoFinalisticoBean extends CrudBeanGenerico<ProjetoFinalistico, CrudProjetoFinalisticoFacade>{

	/**
	 * Bean do orgao.
	 */
	@ManagedProperty(value="#{crudOrgao}")
	private CrudOrgaoBean crudOrgaoBean;
	
	/**
	 * ProjetoFinalistico temp.
	 */
	private ProjetoFinalistico pf;
	
	@Override
	protected String getNavegacaoInicial() {
		return null;
	}
	
	public void adicionar() {
			setSelecionado(new ProjetoFinalistico());
			getSelecionado().setOrgao(crudOrgaoBean.getSelecionado());
			crudOrgaoBean.getSelecionado().getProjetos().add(getSelecionado());
			getSelecionado().setValorTesouro(BigDecimal.ZERO);
			getSelecionado().setValorOutrasFontes(BigDecimal.ZERO);
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

	public ProjetoFinalistico getPf() {
		return pf;
	}

	public void setPf(ProjetoFinalistico pf) {
		this.pf = pf;
	}


}

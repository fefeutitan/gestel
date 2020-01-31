package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.gestel.facade.CrudFaturaFacade;
import br.gov.ce.seinfra.gestel.model.Fatura;
import br.gov.ce.seinfra.gestel.model.Telefone;
import br.gov.ce.seinfra.view.CrudBeanGenerico;
import br.gov.ce.seinfra.view.Outcome;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudFatura")
public class CrudFaturaBean extends CrudBeanGenerico<Fatura, CrudFaturaFacade> {
	
	@ManagedProperty(value = "#{crudConta}")
	private CrudContaBean crudContaBean;
    
    private Telefone telefone;	
    	
	public void adicionar() {
		setSelecionado(new Fatura());
		getSelecionado().setConta(crudContaBean.getSelecionado());
		// trata o nullPointException
		getSelecionado().setValorFatura(BigDecimal.ZERO);
		crudContaBean.getSelecionado().getFaturas().add(getSelecionado());
	}

	@Override
	public String salvar() {
		// VALIDA DUPLICIDADE
		for (Fatura fatura : crudContaBean.getSelecionado().getFaturas()) {
			// captura o ultimo da lista desde que não seja o primeiro registro
			if (crudContaBean.getSelecionado().getFaturas().size() > 1) {
				Fatura list = crudContaBean.getSelecionado().getFaturas()
						.get(crudContaBean.getSelecionado().getFaturas().size() - 1);
				// evita comparar o ultimo com ele mesmo
				if (fatura.getId() != null && (fatura.getId() != list.getId())) {
					// valida codigo de barras
					if (fatura.getCdBarras().equals(list.getCdBarras())) {
						addErrorMessage("Esse código de barras já foi adicionado anteriormente!");
						return Outcome.ERROR;
					}
					// valida telefone
					if (fatura.getTelefone().equals(list.getTelefone())) {
						addErrorMessage("Esse telefone já foi adicionado anteriormente!");
						return Outcome.ERROR;
					}
				}
			}
		}
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

	public CrudContaBean getCrudContaBean() {
		return crudContaBean;
	}

	public void setCrudContaBean(CrudContaBean crudContaBean) {
		this.crudContaBean = crudContaBean;
	}
		
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	
		
}

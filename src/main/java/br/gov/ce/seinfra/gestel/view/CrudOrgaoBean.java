/**
 * 
 */
package br.gov.ce.seinfra.gestel.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.gestel.facade.CrudOrgaoFacade;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Orgao.NivelHierarquico;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Telefone;
import br.gov.ce.seinfra.gestel.model.Telefone.TIPO;
import br.gov.ce.seinfra.view.CrudBeanGenerico;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudOrgao")
public class CrudOrgaoBean extends CrudBeanGenerico<Orgao, CrudOrgaoFacade> {
	/**
	 * tab selecionada.
	 */
	private int tabSelecionada = 0;

	@Override
	protected String getNavegacaoInicial() {
		return Navegacao.CRUD_ORGAO;
	}

	@Override
	public String filtrar() {
//		getSelecionado().setNivel(NivelHierarquico.ORGAO);
		getSelecionado();
		return super.filtrar();
	}

	@Override
	public String editar() {
		String retorno = super.editar();
		tabSelecionada = 0;
		return retorno;
	}

	@Override
	public String salvar() {
		Orgao orgao = getSelecionado();
		String retorno = super.salvar();
		setSelecionado(orgao);
		return retorno;
	}

	@Override
	public void setSelecionado(Orgao selecionado) {
		try {
			super.setSelecionado(selecionado);
			getSelecionado().getMapps().clear();
			getSelecionado().getAcoes().clear();
			getSelecionado().getDotacoes().clear();
			getSelecionado().getProjetos().clear();
			getSelecionado().getTelefones().clear();

			getSelecionado().getMapps().addAll(getCrudFacade().getMapps(getSelecionado())); 
			getSelecionado().getAcoes().addAll(getCrudFacade().getAcoes(getSelecionado()));
			getSelecionado().getDotacoes().addAll(getCrudFacade().getDotacoes(getSelecionado()));
			getSelecionado().getProjetos().addAll(getCrudFacade().getProjetos(getSelecionado()));
			getSelecionado().getTelefones().addAll(getCrudFacade().getTelefones(getSelecionado()));

		} catch (BusinessException e) {
			tratarException(e);
		}
	}

	/**
	 * Lista de projetos.
	 * 
	 * @return lista
	 */
	public List<SelectItem> getProjetos() {
		List<ProjetoFinalistico> projetos = new ArrayList<ProjetoFinalistico>();
		try {
			projetos = getCrudFacade().getProjetos(getSelecionado());
		} catch (BusinessException e) {
			tratarException(e);
		}
		return ordena(gerarListaSelectItem(projetos));
	}

	/**
	 * Lista de telefones.
	 * 
	 * @return lista
	 */
	public List<SelectItem> getTipos() {
		List<TIPO> tipos = new ArrayList<TIPO>();
		return ordena(gerarListaSelectItem(tipos));
	}
	

	/**
	 * Lista de projetos.
	 * 
	 * @return lista
	 */
	public List<SelectItem> getTelefones() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		try {
			telefones = getCrudFacade().getTelefones(getSelecionado());
		} catch (BusinessException e) {
			tratarException(e);
		}
		return ordena(gerarListaSelectItem(telefones));
	}

	public int getTabSelecionada() {
		return tabSelecionada;
	}

	public void setTabSelecionada(int tabSelecionada) {
		this.tabSelecionada = tabSelecionada;
	}

}

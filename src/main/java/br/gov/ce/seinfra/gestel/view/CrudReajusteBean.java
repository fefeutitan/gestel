package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.gestel.facade.CrudReajusteFacade;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Reajuste;
import br.gov.ce.seinfra.model.UnidadeAdministrativa;
import br.gov.ce.seinfra.model.Usuario;
import br.gov.ce.seinfra.view.CrudBeanGenerico;
import br.gov.ce.seinfra.view.Outcome;

@SessionScoped
@ManagedBean(name = "crudReajuste")
public class CrudReajusteBean extends CrudBeanGenerico<Reajuste, CrudReajusteFacade> {

	private List<Dotacao> dotacoes = new ArrayList<Dotacao>();

	private List<Reajuste> reajustes = new ArrayList<Reajuste>();

	private Dotacao dotacao;

	private Integer ano;

	@Column(name = "valor_reajuste_outra_fonte")
	private BigDecimal valorReajusteOutraFonte;

	@Column(name = "valor_reajuste_tesouro")
	private BigDecimal valorReajusteTesouro;

	private boolean reajustado;

	@Override
	protected String getNavegacaoInicial() {
		return Navegacao.CRUD_REAJUSTE;
	}

	@Override
	public String iniciar() {
		String retorno = super.iniciar();
		try {
//			Orgao orgao = getOrgao();
//			getSelecionado().setOrgao(orgao);
			dotacoes = getCrudFacade().getDotacoes();
		} catch (BusinessException e) {
			tratarException(e);
		}
		filtrar();
		System.out.println(getSelecionados().size());
		if (getSelecionados().size() > 0) {
			int index = getSelecionados().size()-1; 
			//EVITAR QUE A DOTAÇÃO SEJA ATUALIZADA SEM NECESSIDADE
			if (getSelecionados().get(index).getReajustado() == false) {
				try {
					getCrudFacade().atualizarDotacao(getSelecionados().get(index));
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
		return retorno;
	}

	@Override
	public String filtrar() {
		setSelecionados(getCrudFacade().listaParaAnalise());		
		return Outcome.CRUD_LISTAR;
	}

	@Override
	public String salvar() {
		String retorno = super.salvar();	
		getCrudFacade().atualizarOrgao(getSelecionado());
		return retorno;
	}

	public Orgao getOrgao() {
		Orgao orgao = new Orgao();
		UnidadeAdministrativa adm = getUsuario().getUnidadeAdministrativa();
		orgao.setId(adm.getId());
		orgao.setNome(adm.getNome());
		return orgao;
	}

	public Usuario getUsuario() {
		return getUsuarioLogado();
	}

	@Override
	public void setSelecionado(Reajuste selecionado) {
		super.setSelecionado(selecionado);
	}

	public List<Dotacao> getDotacoes() {
		return dotacoes;
	}

	public void setDotacoes(List<Dotacao> dotacoes) {
		this.dotacoes = dotacoes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getValorReajusteOutraFonte() {
		return valorReajusteOutraFonte;
	}

	public void setValorReajusteOutraFonte(BigDecimal valorReajusteOutraFonte) {
		this.valorReajusteOutraFonte = valorReajusteOutraFonte;
	}

	public BigDecimal getValorReajusteTesouro() {
		return valorReajusteTesouro;
	}

	public void setValorReajusteTesouro(BigDecimal valorReajusteTesouro) {
		this.valorReajusteTesouro = valorReajusteTesouro;
	}

	public Dotacao getDotacao() {
		return dotacao;
	}

	public void setDotacao(Dotacao dotacao) {
		this.dotacao = dotacao;
	}

	public boolean isReajustado() {
		return reajustado;
	}

	public void setReajustado(boolean reajustado) {
		this.reajustado = reajustado;
	}

	public List<Reajuste> getReajustes() {
		return reajustes;
	}

	public void setReajustes(List<Reajuste> reajustes) {
		this.reajustes = reajustes;
	}

}

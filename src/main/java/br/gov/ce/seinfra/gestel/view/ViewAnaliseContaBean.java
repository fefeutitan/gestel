/**
 * 
 */
package br.gov.ce.seinfra.gestel.view;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.FacadeException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.facade.CrudContaFacade;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Status;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;
import br.gov.ce.seinfra.gestel.model.Valor;
import br.gov.ce.seinfra.gestel.model.ViewResumo;
import br.gov.ce.seinfra.view.CrudBeanGenerico;
import br.gov.ce.seinfra.view.Outcome;

/**
 * Bean do caso de uso analise de conta.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudAnalise")
public class ViewAnaliseContaBean extends CrudBeanGenerico<Conta, CrudContaFacade> {

	@ManagedProperty(value = "#{crudConta}")
	private CrudContaBean crudContaBean;
	
	private List<Valor> valores = new ArrayList<Valor>();

	/** soma as dotacoes. */
	private Map<Dotacao, Resumo> mapDotacao;

	/**
	 * parecer.
	 */
	private String parecer;

	private List<Resumo> listarResumosMes = new ArrayList<ViewAnaliseContaBean.Resumo>();
	private List<Resumo> listarResumosAno = new ArrayList<ViewAnaliseContaBean.Resumo>();


	/**
	 * valor que calcula o total dos saldos de valores da conta
	 */
	@SuppressWarnings("unused")
	private BigDecimal saldoTotal;
	
	@Override
	protected String getNavegacaoInicial() {
		return Navegacao.ANALISAR_DADOS;
	}

	@Override
	public String iniciar() {
		String retorno = super.iniciar();
		filtrar();
		return retorno;
	}

	public String filtrar() {
		try {
			setSelecionados(getCrudFacade().listaParaAnalise());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Outcome.CRUD_LISTAR;
	}

	public String cancelar() {
		return Navegacao.ANALISAR_DADOS;
	}

	public String parecer() {
		return Navegacao.EMITIR_PARECER;
	}

	public void bloquear() {
		Status status = new Status();
		status.setConta(getSelecionado());
		status.setStatus(STATUS_CONTA.EM_ANALISE);
		getSelecionado().setStatus(status);
		getSelecionado().setUsuario(getUsuarioLogado());
		try {
			getCrudFacade().salvar(getSelecionado());
		} catch (ValidacaoException e) {
			tratarValidacaoException(e);
		} catch (FacadeException e) {
			tratarFacadeException(e);
		}

	}

	public void desbloquear() {
		Status status = new Status();
		status.setConta(getSelecionado());
		status.setStatus(STATUS_CONTA.ENVIADA_PARA_ANALISE);
		getSelecionado().setStatus(status);
		getSelecionado().setUsuario(null);
		try {
			getCrudFacade().salvar(getSelecionado());
		} catch (ValidacaoException e) {
			tratarValidacaoException(e);
		} catch (FacadeException e) {
			tratarFacadeException(e);
		}

	}

	public void aprovar() throws NumberFormatException, BusinessException {
		Status status = new Status();
		status.setConta(getSelecionado());
		status.setStatus(STATUS_CONTA.APROVADO);
		getSelecionado().setStatus(status);
		getSelecionado().setUsuario(getUsuarioLogado());

		try {
			getCrudFacade().salvar(getSelecionado());
		} catch (ValidacaoException e) {
			tratarValidacaoException(e);
		} catch (FacadeException e) {
			tratarFacadeException(e);
		}

		// adiciona resumo quando aprovado

		for (Valor v : getCrudFacade().getValores(getSelecionado())) {
			
			ViewResumo resumo = new ViewResumo();
			resumo.setNumeroDotacao(v.getDotacao().getNumero());
			resumo.setDotacao(v.getDotacao().getId());
			resumo.setDotacaoExe(v.getDotacao());
			resumo.setNumeroProjeto(v.getPf().getNumero());
			resumo.setProjeto(v.getPf().getId());
			resumo.setOrgao(getSelecionado().getOrgao().getId());
			resumo.setMesano(getSelecionado().getMesAno());
			resumo.setValor(v);

			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			String strDate = dateFormat.format(resumo.getMesano());
			int ano = Integer.parseInt(strDate);

			resumo.setAno(ano);
			resumo.setSomaTesoutoDotacaoPrevisto(v.getDotacao().getValorTesouro());
			resumo.setSomaOutrasFontesDotacaoPrevisto(v.getDotacao().getValorOutrasFontes());
			resumo.setTotalOutrasFontesDotacaoMes(v.getValorOutraFonte());
			resumo.setTotalTesouroDotacaoMes(v.getValorTesouro());
			resumo.setValorOutrasFontesVinculado(BigDecimal.ZERO);
			resumo.setValorTesouroVinculado(BigDecimal.ZERO);
			resumo.setTotalOutrasFontesVinculado(BigDecimal.ZERO);
			resumo.setTotalTesouroVinculado(BigDecimal.ZERO);
			resumo.setVinculado(false);
			getCrudFacade().salvarResumo(resumo);

		}

	}

	public String confirmarDesaprovacao() throws BusinessException {
		boolean retorno = false;
		Status status = new Status();
		status.setConta(getSelecionado());
		if (getSelecionado().getStatus().getStatus() == STATUS_CONTA.APROVADO) {
			retorno = true;
		}
		status.setStatus(STATUS_CONTA.REPROVADO);
		status.setParecer(parecer);
		getSelecionado().setStatus(status);
		getSelecionado().setUsuario(getUsuarioLogado());
		try {
			getCrudFacade().salvar(getSelecionado());
			setSelecionado(getCrudFacade().get(getSelecionado().getId()));
			setSelecionados(getCrudFacade().listaParaAnalise());
			parecer = "";
		} catch (ValidacaoException e) {
			tratarValidacaoException(e);
		} catch (FacadeException e) {
			tratarFacadeException(e);
		}

		// apaga resumo quando desaprovado um aprovado
		if(retorno == true) {			
			//apaga resumo
			for (Valor v : getCrudFacade().getValores(getSelecionado())) {				
				getCrudFacade().excluirResumo(v);						
			}			
		}
		return Navegacao.ANALISAR_DADOS;

	}

	@Override
	public void setSelecionado(Conta selecionado) {
		super.setSelecionado(selecionado);
		try {
			processarDotacoes(true);
			processarDados(true);
			listarResumosAno = new ArrayList<ViewAnaliseContaBean.Resumo>(mapDotacao.values());
			getSelecionado().getValores().clear();

			getSelecionado().getValores().addAll(getCrudFacade().getValores(getSelecionado()));

		} catch (FacadeException e) {
			tratarException(e);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Processa os dados que serao lancados no relatorio.
	 * 
	 * @throws FacadeException erros gerais
	 */
	private void processarDados(boolean somaAno) throws FacadeException {
		// mapDotacao = new HashMap<Dotacao, Resumo>();

		List<Conta> contas = new ArrayList<Conta>();
		try {
			contas = getCrudFacade().listaResumo(getSelecionado(), somaAno);
			List<Resumo> resumos = getResumos(contas);
			for (Resumo r : resumos) {
				if (r.getDotacao() != null) {
					getResumo(r.getDotacao()).addExe(r);
				}
			}

		} catch (BusinessException e) {
			tratarException(e);
		}
	}

	/**
	 * Processa os dados que serao lancados no relatorio.
	 * 
	 * @throws FacadeException erros gerais
	 */
	private void processarDotacoes(boolean somaAno) throws FacadeException {
		mapDotacao = new HashMap<Dotacao, Resumo>();
		List<Dotacao> dotacoes = new ArrayList<Dotacao>();
		try {
			dotacoes = getCrudFacade().getDotacoes(getSelecionado().getOrgao());
			List<Resumo> resumos = getResumoDotacao(dotacoes);
			for (Resumo r : resumos) {
				if (r.getDotacao() != null) {
					getResumo(r.getDotacao()).add(r);
				}
			}
		} catch (BusinessException e) {
			tratarException(e);
		}

	}

	/**
	 * Pega o resumo baseado na fonte.
	 * 
	 * @param fonte filtro
	 * @return resumo da fonte e pagamentos
	 */
	private Resumo getResumo(Dotacao dotacao) {
		Resumo temp = mapDotacao.get(dotacao);
		if (temp == null) {
			temp = new Resumo(dotacao);
			mapDotacao.put(dotacao, temp);
		}
		return temp;
	}

	public List<Resumo> getResumos(List<Conta> contas) throws BusinessException {
		List<Resumo> resumos = new ArrayList<ViewAnaliseContaBean.Resumo>();
		//for (Conta c : contas) {
			//int i = contas.indexOf(c);
			
			for (Valor v : getCrudFacade().getValores(getSelecionado())) {
				Resumo r = new Resumo(null);
				r.setDotacao(v.getDotacao());
				r.setValorOutrasFontesExe(v.getValorOutraFonte());
				r.setValorTesouroExe(v.getValorTesouro());
				r.setProjeto(v.getPf());
				resumos.add(r);
			}
		//}
		return resumos;
	}

	public List<Resumo> getResumoDotacao(List<Dotacao> dotacoes) {
		List<Resumo> resumos = new ArrayList<ViewAnaliseContaBean.Resumo>();
		for (Dotacao d : dotacoes) {
			Resumo r = new Resumo(null);
			r.setDotacao(d);
			r.setValorOutrasFontes(d.getValorOutrasFontes());
			r.setValorTesouro(d.getValorTesouro());
			// r.setProjeto(d.getPf());//************
			resumos.add(r);
		}
		return resumos;
	}

	/**
	 * Resumo por pf.
	 * 
	 * @author Ferreirar
	 */
	public class Resumo implements Comparable<Resumo> {
		// debugin
		Dotacao dotacao;
		ProjetoFinalistico projeto;
		BigDecimal valorTesouro = BigDecimal.ZERO;;
		BigDecimal valorOutrasFontes = BigDecimal.ZERO;;

		BigDecimal valorTesouroExe = BigDecimal.ZERO;;
		BigDecimal valorOutrasFontesExe = BigDecimal.ZERO;;

		public BigDecimal getValorTesouroExe() {
			return valorTesouroExe;
		}

		public void setDotacao(List<Dotacao> dotacaos) {
			// TODO Auto-generated method stub

		}

		public void setProjeto(List<ProjetoFinalistico> pfs) {
			// TODO Auto-generated method stub

		}

		public void setValorTesouroExe(BigDecimal valorTesouroExe) {
			this.valorTesouroExe = valorTesouroExe;
		}

		public BigDecimal getValorOutrasFontesExe() {
			return valorOutrasFontesExe;
		}

		public void setValorOutrasFontesExe(BigDecimal valorOutrasFontesExe) {
			this.valorOutrasFontesExe = valorOutrasFontesExe;
		}

		public Resumo(Dotacao dotacao) {
			this.dotacao = dotacao;

		}

		public void add(Resumo resumo) {
			this.valorTesouro = this.valorTesouro.add(trataValor(resumo.getValorTesouro()));
			this.valorOutrasFontes = this.valorOutrasFontes.add(trataValor(resumo.getValorOutrasFontes()));
			this.projeto = resumo.getProjeto();
		}

		public void addExe(Resumo resumo) {
			this.valorTesouroExe = this.valorTesouroExe.add(trataValor(resumo.getValorTesouroExe()));
			this.valorOutrasFontesExe = this.valorOutrasFontesExe.add(trataValor(resumo.getValorOutrasFontesExe()));

			this.projeto = resumo.getProjeto();
		}

		public int compareTo(Resumo o) {
			return dotacao.getId().compareTo(o.dotacao.getId());
		}

		public Dotacao getDotacao() {
			return dotacao;
		}

		public void setDotacao(Dotacao dotacao) {
			this.dotacao = dotacao;
		}

		public BigDecimal getValorTesouro() {
			return valorTesouro;
		}

		public void setValorTesouro(BigDecimal valorTesouro) {
			this.valorTesouro = valorTesouro;
		}

		public BigDecimal getValorOutrasFontes() {
			return valorOutrasFontes;
		}

		public void setValorOutrasFontes(BigDecimal valorOutrasFontes) {
			this.valorOutrasFontes = valorOutrasFontes;
		}

		public ProjetoFinalistico getProjeto() {
			return projeto;
		}

		public void setProjeto(ProjetoFinalistico projeto) {
			this.projeto = projeto;
		}
	}

	public BigDecimal trataValor(BigDecimal valor) {
		if (valor == null) {
			return new BigDecimal(0);
		}
		return valor;
	}

	public List<Resumo> getListarResumosMes() {
		return listarResumosMes;
	}

	public void setListarResumosMes(List<Resumo> listarResumosMes) {
		this.listarResumosMes = listarResumosMes;
	}

	public List<Resumo> getListarResumosAno() {
		return listarResumosAno;
	}

	public void setListarResumosAno(List<Resumo> listarResumosAno) {
		this.listarResumosAno = listarResumosAno;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public CrudContaBean getCrudContaBean() {
		return crudContaBean;
	}

	public void setCrudContaBean(CrudContaBean crudContaBean) {
		this.crudContaBean = crudContaBean;
	}

	public List<Valor> getValores() {
		return valores;
	}

	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

	public BigDecimal getSaldoTotal() {
		BigDecimal saldoTotal = new BigDecimal("0.0");

		for (Valor valor : getSelecionado().getValores()) {
			saldoTotal = saldoTotal.add(valor.getValorOutraFonte().add(valor.getValorTesouro()));
		}

		return saldoTotal;
	}

}

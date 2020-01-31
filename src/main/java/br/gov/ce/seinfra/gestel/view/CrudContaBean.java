package br.gov.ce.seinfra.gestel.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.UploadedFile;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.FacadeException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.facade.CrudContaFacade;
import br.gov.ce.seinfra.gestel.model.Acao;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Dotacao;
import br.gov.ce.seinfra.gestel.model.Fatura;
import br.gov.ce.seinfra.gestel.model.Imagem;
import br.gov.ce.seinfra.gestel.model.Mapp;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.ProjetoFinalistico;
import br.gov.ce.seinfra.gestel.model.Status;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;
import br.gov.ce.seinfra.gestel.model.Telefone;
import br.gov.ce.seinfra.gestel.model.Valor;
import br.gov.ce.seinfra.model.UnidadeAdministrativa;
import br.gov.ce.seinfra.model.Usuario;
import br.gov.ce.seinfra.util.DateHelper;
import br.gov.ce.seinfra.view.CrudBeanGenerico;
import br.gov.ce.seinfra.view.Outcome;

/**
 * Bean do caso de uso manter.
 * 
 * @author ferreira
 *
 */
@SessionScoped
@ManagedBean(name = "crudConta")
public class CrudContaBean extends CrudBeanGenerico<Conta, CrudContaFacade> {

	public static final Integer ANO_ATUAL = Calendar.getInstance().get(Calendar.YEAR);

	private List<ProjetoFinalistico> projetos = new ArrayList<ProjetoFinalistico>();

	private List<Dotacao> dotacoes = new ArrayList<Dotacao>();

	private List<Mapp> mapps = new ArrayList<Mapp>();

	private List<Acao> acoes = new ArrayList<Acao>();

	private List<Fatura> faturas = new ArrayList<Fatura>();

	private List<Telefone> telefones = new ArrayList<Telefone>();

	private List<Valor> valores = new ArrayList<Valor>();

	private String nomeArquivo;

	/**
	 * valor que calcula o total das faturas
	 */
	@SuppressWarnings("unused")
	private BigDecimal valorTotal;

	/**
	 * valor que calcula o total dos saldos de valores da conta
	 */
	@SuppressWarnings("unused")
	private BigDecimal saldoTotal;

	@Override
	protected String getNavegacaoInicial() {
		return Navegacao.CRUD_CONTA;
	}

	public String validarDados() {
		return Navegacao.VALIDAR_DADOS;
	}

	public String visualizarDados() {
		return Navegacao.VISUALIZAR_DADOS;
	}

	@Override
	public String iniciar() {
		String retorno = super.iniciar();
		try {
			Orgao orgao = getOrgao();
			getSelecionado().setOrgao(orgao);
			projetos = getCrudFacade().getProjetos(orgao);
			dotacoes = getCrudFacade().getDotacoes(orgao);
			mapps = getCrudFacade().getMapps(orgao);
			acoes = getCrudFacade().getAcoes(orgao);
			telefones = getCrudFacade().getTelefones(orgao);
			// valores = getCrudFacade().getValores(conta);
		} catch (BusinessException e) {
			tratarException(e);
		}
		return retorno;
	}

	@Override
	public String novo() {
		String retorno = super.novo();
		getSelecionado().setOrgao(getOrgao());
		return retorno;
	}

	@Override
	public String salvar() {
		@SuppressWarnings("unused")
		Conta conta = getSelecionado();
		getSelecionado().setOrgao(getOrgao());
		try {
			getCrudFacade().salvar(getSelecionado());
		} catch (ValidacaoException e) {
			e.printStackTrace();
		} catch (FacadeException e) {
			e.printStackTrace();
		}
		// String retorno = super.salvar();

		return Outcome.CRUD_SALVAR;

	}

	@Override
	public void setSelecionado(Conta selecionado) {
		try {
			super.setSelecionado(selecionado);
			getSelecionado().getFaturas().clear();
			getSelecionado().getValores().clear();
			getSelecionado().getDotacoes().clear();

			getSelecionado().getFaturas().addAll(getCrudFacade().getFaturas(getSelecionado()));
			getSelecionado().getValores().addAll(getCrudFacade().getValores(getSelecionado()));
			getSelecionado().getDotacoes().addAll(getCrudFacade().getDotacoes(getOrgao()));

		} catch (BusinessException e) {
			tratarException(e);
		}
	}

	public Usuario getUsuario() {
		return getUsuarioLogado();
	}

	public Orgao getOrgao() {
		Orgao orgao = new Orgao();
		UnidadeAdministrativa adm = getUsuario().getUnidadeAdministrativa();
		orgao.setId(adm.getId());
		orgao.setNome(adm.getNome());
		return orgao;
	}

	public String analisar() {
		return Navegacao.ANALISAR_RESUMO;
	}

	public List<SelectItem> getProjetos() {
		return ordena(gerarListaSelectItem(projetos));
	}

	public List<SelectItem> getDotacoes() {
		return ordena(gerarListaSelectItem(dotacoes));
	}

	public List<SelectItem> getMapps() {
		return ordena(gerarListaSelectItem(mapps));
	}

	public List<SelectItem> getFaturas() {
		return ordena(gerarListaSelectItem(faturas));
	}

	/*
	 * public List<SelectItem> getValores() { return
	 * ordena(gerarListaSelectItem(valores)); }
	 */

	public List<SelectItem> getAcoes() {
		return ordena(gerarListaSelectItem(acoes));
	}

	public List<SelectItem> getTelefones() {
		return ordena(gerarListaSelectItem(telefones));
	}

	public List<Conta> listarValores() {
		try {
			return getCrudFacade().listaResumo(getSelecionado(), true);
		} catch (BusinessException e) {
			tratarException(e);
		}
		return new ArrayList<Conta>();
	}

	/**
	 * Faz um upload do arquivo de imagens.
	 * 
	 * @param event evento de upload
	 * @throws IOException erros de escrita
	 */
	public void upload(FileUploadEvent event) throws IOException {
		UploadedFile item = event.getFile();
		if (item == null) {
			return;
		}

		// Pega a extensão do arquivo
		String extension = item.getFileName().substring(item.getFileName().lastIndexOf(".") + 1);

		// Pega o nome do arquivo
		String fileName = getSelecionado().getId().toString().replaceAll("/", "-") + "-"
				+ DateHelper.getFormat("yyyyMMddHHmmssmmm").format(new Date()) + "." + extension;

		String folderName = "/arquivo/gestel/" + getUsuarioLogado().getUnidadeAdministrativa().getSigla() + "/";
		String path = getPath(folderName);

		// cria pasta e arquivo
		File diretorio = new File(path);
		File arquivo = new File(path + fileName);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		Imagem imagem = new Imagem();
		imagem.setConta(getSelecionado());
		imagem.setUrl(folderName + fileName);
		imagem.setTamanho(item.getSize());
		imagem.setNome(getNomeArquivo());

		/*
		 * *********************************************************************
		 * ********* SALVA O ARQUIVO
		 */
		FileOutputStream fos = new FileOutputStream(arquivo);
		fos.write(item.getContents());
		fos.close();

		/*
		 * *********************************************************************
		 * ********* FAZ LEITURA DO PDF
		 */
		PDDocument pd = PDDocument.load(arquivo);
		imagem.setPaginas(pd.getNumberOfPages());

		/*
		 * *********************************************************************
		 * ********* SALVA O PROCESSO
		 */
		try {
			getSelecionado().getImagens().add(imagem);
			getCrudFacade().salvar(getSelecionado());
			setSelecionado(getSelecionado());
			setNomeArquivo("");
		} catch (ValidacaoException e) {
			imagem.setUrl(null);
			imagem.setTamanho(null);
			imagem.setPaginas(null);
			tratarValidacaoException(e);
		} catch (FacadeException e) {
			imagem.setUrl(null);
			imagem.setTamanho(null);
			imagem.setPaginas(null);
			tratarFacadeException(e);
		}

	}

	public String enviar() {
		try {
			Status status = new Status();
			status.setConta(getSelecionado());
			status.setStatus(STATUS_CONTA.ENVIADA_PARA_ANALISE);
			getCrudFacade().enviar(status);
			setSelecionado(getCrudFacade().get(getSelecionado().getId()));
			return Outcome.CRUD_SALVAR;
		} catch (BusinessException e) {
			tratarException(e);
		} catch (FacadeException e) {
			tratarFacadeException(e);
		}

		return Outcome.ERROR;
	}

	public void onClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
				"Closed panel id:'" + event.getComponent().getId() + "'");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
				"Status:" + event.getVisibility().name());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Pega o caminho real.
	 * 
	 * @param caminho caminho
	 * @return caminho real
	 */
	private String getPath(String caminho) {
		return getRealPath("").replaceAll(getServletContext().getContextPath().replaceAll("/", ""), caminho);
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = new BigDecimal("0.0");

		for (Fatura fatura : getSelecionado().getFaturas()) {
			valorTotal = valorTotal.add(fatura.getValorFatura());
		}

		return valorTotal;
	}

	public BigDecimal getSaldoTotal() {
		BigDecimal saldoTotal = new BigDecimal("0.0");

		for (Valor valor : getSelecionado().getValores()) {
			saldoTotal = saldoTotal.add(valor.getValorOutraFonte().add(valor.getValorTesouro()));
		}

		return saldoTotal;
	}
	
	public String getVerificaTotal() {
		saldoTotal = getSaldoTotal();
		valorTotal = getValorTotal();
		if (saldoTotal.equals(valorTotal)) {
			return "Total Confere";
		}else {
			return "Total não confere";
		}		
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setSaldoTotal(BigDecimal saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Valor> getValores() {
		return valores;
	}

	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

}

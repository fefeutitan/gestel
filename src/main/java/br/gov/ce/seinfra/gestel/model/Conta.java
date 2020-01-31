package br.gov.ce.seinfra.gestel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;
import br.gov.ce.seinfra.model.BaseModel;
import br.gov.ce.seinfra.model.Usuario;

/**
 * Conta telefonica.
 * 
 * @author ferreira
 *
 */
@Entity
@Table(name = "tb_conta", schema = "bdgestel")
public class Conta extends BaseModel {

	private Date mesAno;

	private String observacao = "";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_conta_adm")
	private Orgao orgao;

	/** Todas as referentes conta. */
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Imagem> imagens = new ArrayList<Imagem>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_conta_status")
	private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_conta_usuario")
	private Usuario usuario;

	private transient List<Fatura> faturas = new ArrayList<Fatura>();
	
	private transient List<Mapp> mapps = new ArrayList<Mapp>();

	private transient List<Acao> acoes = new ArrayList<Acao>();

	private transient List<Dotacao> dotacoes = new ArrayList<Dotacao>();

	private transient List<ProjetoFinalistico> pfs = new ArrayList<ProjetoFinalistico>();

	private transient List<Valor> valores = new ArrayList<Valor>();
	
	public boolean isEditavel() {
		return status.getStatus().equals(STATUS_CONTA.CADASTRADA) || status.getStatus().equals(STATUS_CONTA.REPROVADO);
	}

	public boolean isBloqueado() {
		return status == null ? false : status.getStatus().equals(STATUS_CONTA.EM_ANALISE);
	}

	public boolean isAprovado() {
		return status == null ? false : (status.getStatus().equals(STATUS_CONTA.APROVADO));
	}

	public boolean isVinculado() {
		return status == null ? false : (status.getStatus().equals(STATUS_CONTA.VINCULADO));
		//return status.getStatus().equals(STATUS_CONTA.VINCULADO);
	}

	public boolean isEnviada() {
		return status == null ? false : (status.getStatus().equals(STATUS_CONTA.ENVIADA_PARA_ANALISE));
	}

	public Date getMesAno() {
		return mesAno;
	}

	public void setMesAno(Date mesAno) {
		this.mesAno = mesAno;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	public List<Mapp> getMapps() {
		return mapps;
	}

	public void setMapps(List<Mapp> mapps) {
		this.mapps = mapps;
	}

	public List<ProjetoFinalistico> getPfs() {
		return pfs;
	}

	public void setPfs(List<ProjetoFinalistico> pfs) {
		this.pfs = pfs;
	}

	public List<Valor> getValores() {
		return valores;
	}

	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public List<Dotacao> getDotacoes() {
		return dotacoes;
	}

	public void setDotacoes(List<Dotacao> dotacoes) {
		this.dotacoes = dotacoes;
	}


}

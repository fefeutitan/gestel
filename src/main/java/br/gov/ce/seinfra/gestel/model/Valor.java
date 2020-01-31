package br.gov.ce.seinfra.gestel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;

/**
 * conta valor.
 * 
 * @author FERNANDO
 *
 */
@Entity
@Table(name = "tb_contaValor", schema = "bdgestel")
public class Valor extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mapp_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_valor_mapp")
	private Mapp mapp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_valor_acao")
	private Acao acao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pf_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_valor_pf")
	private ProjetoFinalistico pf;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dotacao_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_valor_dotacao")
	private Dotacao dotacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_valor_id")
	private Conta conta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_mapp_adm")
	private Orgao orgao;

	@OneToOne(mappedBy = "valor", fetch = FetchType.LAZY)
	private ViewResumo resumo;

	private transient List<Valor> valores = new ArrayList<Valor>();

	@Column(name = "mes_referencia")
	private Date mesReferencia;

	@Column(name = "valor")
	private BigDecimal valorTotal;

	@Column(name = "valor_tesouro")
	private BigDecimal valorTesouro;

	@Column(name = "valor_outra_fonte")
	private BigDecimal valorOutraFonte;

	@Column(name = "valor_reajuste_tesouro")
	private BigDecimal valorReajusteTesouro;

	@Column(name = "valor_reajuste_outra_fonte")
	private BigDecimal valorReajusteOutraFonte;

	public ProjetoFinalistico getPf() {
		return pf;
	}

	public void setPf(ProjetoFinalistico pf) {
		this.pf = pf;
	}

	public Dotacao getDotacao() {
		return dotacao;
	}

	public void setDotacao(Dotacao dotacao) {
		this.dotacao = dotacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Mapp getMapp() {
		return mapp;
	}

	public void setMapp(Mapp mapp) {
		this.mapp = mapp;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Date getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(Date mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTesouro() {
		return valorTesouro;
	}

	public void setValorTesouro(BigDecimal valorTesouro) {
		this.valorTesouro = valorTesouro;
	}

	public BigDecimal getValorOutraFonte() {
		return valorOutraFonte;
	}

	public void setValorOutraFonte(BigDecimal valorOutraFonte) {
		this.valorOutraFonte = valorOutraFonte;
	}

	public BigDecimal getValorReajusteTesouro() {
		return valorReajusteTesouro;
	}

	public void setValorReajusteTesouro(BigDecimal valorReajusteTesouro) {
		this.valorReajusteTesouro = valorReajusteTesouro;
	}

	public BigDecimal getValorReajusteOutraFonte() {
		return valorReajusteOutraFonte;
	}

	public void setValorReajusteOutraFonte(BigDecimal valorReajusteOutraFonte) {
		this.valorReajusteOutraFonte = valorReajusteOutraFonte;
	}

	public List<Valor> getValores() {
		return valores;
	}

	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Valor)) {
			return false;
		}
		Valor v = (Valor) obj;
		return v.getId().equals(getId());
	}

	@Override
	public String toString() {
		return dotacao + "" + getId();
	}

	public ViewResumo getResumo() {
		return resumo;
	}

	public void setResumo(ViewResumo resumo) {
		this.resumo = resumo;
	}

	

}

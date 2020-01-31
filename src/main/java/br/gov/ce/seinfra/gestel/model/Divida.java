/**
 * 
 */
package br.gov.ce.seinfra.gestel.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;

/**
 * Entidade que representa divida.
 * 
 * @author ferreira
 *
 */

@Entity
@Table(name = "tb_divida", schema = "bdgestel")
public class Divida extends BaseModel {

	/**
	 * Unidade administrativa.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_divida_adm")
	private Orgao orgao;

	/**
	 * dotacao.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dotacao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_divida_dotacao")
	private Dotacao dotacao;

	/**
	 * dotacao.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pf_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_divida_pf")
	private ProjetoFinalistico pf;

	/**
	 * valor tesouro.
	 */
	@Column(name = "valor_tesouro")
	private BigDecimal valorTesouro;

	/**
	 * valor outras fontes.
	 */
	@Column(name = "valor_outra_fonte")
	private BigDecimal valorOutraFonte;

	/**
	 * ano.
	 */
	private Integer ano;

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Dotacao getDotacao() {
		return dotacao;
	}

	public void setDotacao(Dotacao dotacao) {
		this.dotacao = dotacao;
	}

	public ProjetoFinalistico getPf() {
		return pf;
	}

	public void setPf(ProjetoFinalistico pf) {
		this.pf = pf;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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

}

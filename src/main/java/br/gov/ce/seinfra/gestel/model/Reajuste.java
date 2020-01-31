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

@Entity
@Table(name = "tb_reajuste", schema = "bdgestel")
public class Reajuste extends BaseModel {
	
	@Column(name="ano") 
    private Integer ano;  

    @Column(name="valor_reajuste_outra_fonte")
    private BigDecimal valorReajusteOutraFonte;
    
    @Column(name="valor_reajuste_tesouro")
    private BigDecimal valorReajusteTesouro;

    /**
     * Unidade administrativa.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgao_id")
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_reajuste_adm")
    private Orgao orgao;
        
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dotacao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_reajuste_dotacao")
	private Dotacao dotacao;
	
	@Column(name = "reajustado")
	private boolean reajustado;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

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

	public boolean getReajustado() {
		return reajustado;
	}

	public void setReajustado(boolean reajustado) {
		this.reajustado = reajustado;
	}


}

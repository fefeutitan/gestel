package br.gov.ce.seinfra.gestel.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * View resumo.
 * @author ferreira
 *
 */
@Entity
@Table(name = "resumo", schema = "bdgestel")
public class ViewResumo extends BaseModel{

	/**
	 * dotacao.
	 */
	private Long orgao;
	
	/**
	 * dotacao.
	 */
	private Long dotacao;
	
	private String ano_vinculado;

	@Column(name = "dotacao_vinculado")
	private String numero_dotacao;

	private Double valor_vinculado;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dotacao_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_resumo_dotacao")
	private Dotacao dotacaoExe;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "valor_id", nullable = false)
	private Valor valor;

	/*private Long valor_id;*/
	
	/**
	 * dotacao.
	 */
	@Column(name = "numero_dotacao")
	private String numeroDotacao;
	
	/**
	 * id do pf.
	 */
	private Long projeto;
	
	/**
	 * numero pf.
	 */
	@Column(name = "numero_projeto")
	private String numeroProjeto;
	
	/**
	 * ano.
	 */
	private Integer ano;
	
	/**
	 * mes ano.
	 */
	private Date mesano;
	
	/**
	 * soma valor tesouro previsto da dotacao.
	 */
	@Column(name = "soma_tes_dotacao_previsto")
	private BigDecimal somaTesoutoDotacaoPrevisto;
	

	/**
	 * soma valor tesouro previsto da dotacao.
	 */
	@Column(name = "soma_of_dotacao_previsto")
	private BigDecimal somaOutrasFontesDotacaoPrevisto;
	
	/**
	 *  valor tesouro ja vinculado.
	 */
	@Column(name = "valor_tesouro_vinculado")
	private BigDecimal valorTesouroVinculado;
	
	/**
	 *  valor outras fontes ja vinculado.
	 */
	@Column(name = "valor_outrasfontes_vinculado")
	private BigDecimal valorOutrasFontesVinculado;
	
	
	/**
	 *  total tesouro ja vinculado.
	 */
	@Column(name = "total_tes_dotacao_vinculado")
	private BigDecimal totalTesouroVinculado;
	
	/**
	 *  total outras fontes ja vinculado.
	 */
	@Column(name = "total_of_dotacao_vinculado")
	private BigDecimal totalOutrasFontesVinculado;

	/**
	 * total tesouro no mes
	 */
	@Column(name = "total_tes_dotacao_mes")
	private BigDecimal totalTesouroDotacaoMes;


	/**
	 * total outras fontes no mes
	 */
	@Column(name = "total_of_dotacao_mes")
	private BigDecimal totalOutrasFontesDotacaoMes;
	
	@Column(name = "viculado")
	private boolean vinculado;	
	
	public Long getDotacao() {
		return dotacao;
	}

	public void setDotacao(Long dotacao) {
		this.dotacao = dotacao;
	}

	public String getNumeroDotacao() {
		return numeroDotacao;
	}

	public void setNumeroDotacao(String numeroDotacao) {
		this.numeroDotacao = numeroDotacao;
	}

	public Long getProjeto() {
		return projeto;
	}

	public void setProjeto(Long projeto) {
		this.projeto = projeto;
	}

	public String getNumeroProjeto() {
		return numeroProjeto;
	}

	public void setNumeroProjeto(String numeroProjeto) {
		this.numeroProjeto = numeroProjeto;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Date getMesano() {
		return mesano;
	}

	public void setMesano(Date mesano) {
		this.mesano = mesano;
	}

	public BigDecimal getSomaTesoutoDotacaoPrevisto() {
		return somaTesoutoDotacaoPrevisto;
	}

	public void setSomaTesoutoDotacaoPrevisto(BigDecimal somaTesoutoDotacaoPrevisto) {
		this.somaTesoutoDotacaoPrevisto = somaTesoutoDotacaoPrevisto;
	}

	public BigDecimal getSomaOutrasFontesDotacaoPrevisto() {
		return somaOutrasFontesDotacaoPrevisto;
	}

	public void setSomaOutrasFontesDotacaoPrevisto(BigDecimal somaOutrasFontesDotacaoPrevisto) {
		this.somaOutrasFontesDotacaoPrevisto = somaOutrasFontesDotacaoPrevisto;
	}

	public BigDecimal getValorTesouroVinculado() {
		return valorTesouroVinculado;
	}

	public void setValorTesouroVinculado(BigDecimal valorTesouroVinculado) {
		this.valorTesouroVinculado = valorTesouroVinculado;
	}

	public BigDecimal getValorOutrasFontesVinculado() {
		return valorOutrasFontesVinculado;
	}

	public void setValorOutrasFontesVinculado(BigDecimal valorOutrasFontesVinculado) {
		this.valorOutrasFontesVinculado = valorOutrasFontesVinculado;
	}

	public BigDecimal getTotalTesouroVinculado() {
		return totalTesouroVinculado;
	}

	public void setTotalTesouroVinculado(BigDecimal totalTesouroVinculado) {
		this.totalTesouroVinculado = totalTesouroVinculado;
	}

	public BigDecimal getTotalOutrasFontesVinculado() {
		return totalOutrasFontesVinculado;
	}

	public void setTotalOutrasFontesVinculado(BigDecimal totalOutrasFontesVinculado) {
		this.totalOutrasFontesVinculado = totalOutrasFontesVinculado;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public BigDecimal getTotalTesouroDotacaoMes() {
		return totalTesouroDotacaoMes;
	}

	public void setTotalTesouroDotacaoMes(BigDecimal totalTesouroDotacaoMes) {
		this.totalTesouroDotacaoMes = totalTesouroDotacaoMes;
	}

	public BigDecimal getTotalOutrasFontesDotacaoMes() {
		return totalOutrasFontesDotacaoMes;
	}

	public void setTotalOutrasFontesDotacaoMes(BigDecimal totalOutrasFontesDotacaoMes) {
		this.totalOutrasFontesDotacaoMes = totalOutrasFontesDotacaoMes;
	}

	public boolean getVinculado() {
		return vinculado;
	}

	public void setVinculado(boolean vinculado) {
		this.vinculado = vinculado;
	}
	
	

	public Dotacao getDotacaoExe() {
		return dotacaoExe;
	}

	public void setDotacaoExe(Dotacao dotacaoExe) {
		this.dotacaoExe = dotacaoExe;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public String getAno_vinculado() {
		return ano_vinculado;
	}

	public void setAno_vinculado(String ano_vinculado) {
		this.ano_vinculado = ano_vinculado;
	}

	public Double getValor_vinculado() {
		return valor_vinculado;
	}

	public void setValor_vinculado(Double valor_vinculado) {
		this.valor_vinculado = valor_vinculado;
	}

	public String getNumero_dotacao() {
		return numero_dotacao;
	}

	public void setNumero_dotacao(String numero_dotacao) {
		this.numero_dotacao = numero_dotacao;
	}

	
}

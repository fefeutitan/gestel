package br.gov.ce.seinfra.gestel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
 * Projeto Finalistico - PF
 * 
 * @author ferreira
 *
 */
@Entity
@Table(name = "tb_ProjetoFinalistico", schema = "bdgestel")
public class ProjetoFinalistico extends BaseModel {

	/**
	 * numero.
	 */
	@Column(length = 50, nullable = true)
	private String numero;

	/**
	 * nome
	 */
	private String nome;

    /**
     * Unidade administrativa.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgao_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_mapp_adm")
    private Orgao orgao;
    
    /**
     * valor tesouro pf.
     */
    private BigDecimal valorTesouro;

    /**
     * valor outras fontes pf.
     */
    private BigDecimal valorOutrasFontes;
    
    /**
     * ano.
     */
    private Integer ano;

    
    /** Todas as referentes orgao. */
    private transient List<ViewResumo> dotacoes = new ArrayList<ViewResumo>();
    
  
    public BigDecimal getSomaValorPrevistoTesouro() {
        return getPrevisto(true);
    }
    
    public BigDecimal getSomaValorPrevistoOutraFonte() {
        return getPrevisto(false);
    }

    public BigDecimal getSomaValorVinculadoTesouro() {
        return getVinculado(true);
    }
    
    public BigDecimal getSomaValorVinculadoOutraFonte() {
        return getVinculado(false);
    }
    
    /**
     * total tesouro vinculado mais o mes selecionado.
     * @return
     */
    public BigDecimal getTotalVinculadoMesTesouro() {
        return   getSomaValorVinculadoTesouro().add(getValorMesTesouro());
    }
    
    /**
     * total tesouro vinculado mais o mes selecionado.
     * @return
     */
    public BigDecimal getTotalVinculadoMesOutrasFontes() {
        return   getSomaValorVinculadoOutraFonte().add(getValorMesOutraFonte());
    }
  
    /**
     * Saldo tesouro previsto - vinculado.
     * @return
     */
    public BigDecimal getSaldoPrevistoVinculadoTesouro() {
        return getSomaValorPrevistoTesouro().subtract(getSomaValorVinculadoTesouro());
    }

    /**
     * Saldo outras fontes previsto - vinculado.
     * @return
     */
    public BigDecimal getSaldoPrevistoVinculadoOutraFonte() {
        return getSomaValorPrevistoOutraFonte().subtract(getSomaValorVinculadoOutraFonte());
    }
    
    /**
     * Saldo TOTAL tesouro
     * @return
     */
    public BigDecimal getSaldoTesouro() {
        return getSomaValorPrevistoTesouro().subtract(getSomaValorVinculadoTesouro());
    }

    public BigDecimal getSaldoOutraFonte() {
        return getSomaValorPrevistoOutraFonte().subtract(getSomaValorVinculadoOutraFonte());
    }

    
    /**
     * valor mes tesouro.
     * @return
     */
    public BigDecimal getValorMesTesouro() {
        return getMes(true);
    }

    /**
     * valor mes outras fontes.
     * @return
     */
    public BigDecimal getValorMesOutraFonte() {
        return getMes(false);
    }
    
    
    
  
    
    public BigDecimal getPrevisto(Boolean fonte){
    	BigDecimal soma = BigDecimal.ZERO;
    	for (ViewResumo dotacao : dotacoes) {
    		soma = soma.add(fonte? trataValor(dotacao.getSomaTesoutoDotacaoPrevisto()):trataValor(dotacao.getSomaOutrasFontesDotacaoPrevisto()));
		}
    	return soma;
    }
    
    
    public BigDecimal getVinculado(Boolean fonte){
    	BigDecimal soma = BigDecimal.ZERO;
    	for (ViewResumo dotacao : dotacoes) {
    		soma = soma.add(fonte? trataValor(dotacao.getValorTesouroVinculado()):trataValor(dotacao.getValorOutrasFontesVinculado()));
		}
    	return soma;
    }
    
    public BigDecimal getMes(Boolean fonte){
    	BigDecimal soma = BigDecimal.ZERO;
    	for (ViewResumo dotacao : dotacoes) {
    		soma = soma.add(fonte? trataValor(dotacao.getTotalTesouroDotacaoMes()):trataValor(dotacao.getTotalOutrasFontesDotacaoMes()));
		}
    	return soma;
    }
    
    
	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Orgao getOrgao() {
		return orgao;
	}


	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	
	@Override
	public String toString() {
		return numero;
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


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}

	
	   public BigDecimal trataValor(BigDecimal valor) {
	        if (valor == null) {
	            return new BigDecimal(0);
	        }
	        return valor;
	    }


	public List<ViewResumo> getDotacoes() {
		return dotacoes;
	}


	public void setDotacoes(List<ViewResumo> dotacoes) {
		this.dotacoes = dotacoes;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProjetoFinalistico)) {
            return false;
        }
        ProjetoFinalistico p = (ProjetoFinalistico) obj;
        return p.getId().equals(getId());
    }
}

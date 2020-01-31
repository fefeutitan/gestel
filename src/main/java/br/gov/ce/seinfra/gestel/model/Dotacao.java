package br.gov.ce.seinfra.gestel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;

/**
 * Entidade que representa dotacao.
 * @author ferreira
 *
 */
@Entity
@Table(name = "tb_dotacao", schema = "bdgestel")
public class Dotacao extends BaseModel {

	/**
	 * numero.
	 */
	private String numero;

    /**
     * Unidade administrativa.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgao_id")
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_dotacao_adm")
    private Orgao orgao;
    
    /**
     * PF.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pf_id")
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_dotacao_pf")
    private ProjetoFinalistico pf;

	/**
	 * Lista de reajustes.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reajuste")
	private transient List<Reajuste> reajustes = new ArrayList<Reajuste>();
	
	/**
     * valor tesouro.
     */
    private BigDecimal valorTesouro;
    

    /**
     * valor outras fontes.
     */
    private BigDecimal valorOutrasFontes;
    
    /**
     * ano.
     */
    private Integer ano;
    
    
    /**
     * valor tesouro.
     */
    private BigDecimal valorTesouroExe;
    
    /**
     * valor outras fontes.
     */
    private BigDecimal valorOutrasFontesExe;
    
    
	public BigDecimal getValorTesouroExe() {
		return valorTesouroExe;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
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

	public ProjetoFinalistico getPf() {
		return pf;
	}

	public void setPf(ProjetoFinalistico pf) {
		this.pf = pf;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dotacao)) {
            return false;
        }
        Dotacao d = (Dotacao) obj;
        return d.getId().equals(getId());
    }
	
	@Override
	public String toString() {
		return numero;
	}

	public List<Reajuste> getReajustes() {
		return reajustes;
	}

	public void setReajustes(List<Reajuste> reajustes) {
		this.reajustes = reajustes;
	}


    
}

/*
 * 22/09/2010
 */
package br.gov.ce.seinfra.gestel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;

/**
 * Status da conta.
 * @author ferreira
 */
@Entity
@Table(name = "tb_status", schema = "bdgestel")
public class Status extends BaseModel {

    /**
     * Conta a qual o status pertence.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_conta_status")
    private Conta conta;
    
    /***
     * Parecer.
     */
    @Column(name = "status_parecer", length=1000)
    private String parecer;
    
    /**
     * status.
     */
    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private STATUS_CONTA status;
    

    /**
     * Decisão que pode ser tomada no status.
     * @author ferreira
     */
    public enum STATUS_CONTA {
        /**
         * Cadastrada, status inicial
         */
        CADASTRADA,

    	/**
         * Aprovado.
         */
        APROVADO,
        /**
         * Reprovado.
         */
        REPROVADO,
        /**
         * Em análise.
         */
        EM_ANALISE,
        /**enviada      */
        ENVIADA_PARA_ANALISE,
        /**enviada      */
        VINCULADO;

    	public String getNome() {
			return toString().replaceAll("_", " ");
		}
    }

    
    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public STATUS_CONTA getStatus() {
		return status;
	}

	public void setStatus(STATUS_CONTA status) {
		this.status = status;
	}
}

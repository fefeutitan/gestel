/*
 * 01/12/2009
 */
package br.gov.ce.seinfra.gestel.model;

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
 * Acao (Projeto Atividade).
 * @author ferreira
 */
@Entity
@Table(name = "tb_acao", schema = "bdgestel")
public class Acao extends BaseModel {

	/**
     * Nome da acao.
     */
    @Column(name = "nome")
    private String nome;

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
    @ForeignKey(name = "fk_acao_adm")
    private Orgao orgao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return numero.toString();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Acao)) {
            return false;
        }
        Acao p = (Acao) obj;
        return p.getId().equals(getId());
    }
	
	
	
}
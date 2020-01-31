package br.gov.ce.seinfra.gestel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;

/**
 * TELEFONE.
 * 
 * @author FERNANDO
 *
 */
@Entity
@Table(name = "tb_telefone", schema = "bdgestel")
public class Telefone extends BaseModel {

	/**
	 * numero do telefone.
	 */
	private String numero;

	/**
	 * TIPO fixou ou móvel
	 */
	@Column(name = "tipo", nullable = false)
	@Enumerated(EnumType.STRING)
	private TIPO tipo;

	public enum TIPO {
		FIXO, MOVEL;
	}

	private String descricao;

	/**
	 * Unidade administrativa.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgao_id")
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_mapp_adm")
	private Orgao orgao;

	/**
	 * Lista de Fatura.
	 */

	private transient List<Fatura> faturas = new ArrayList<Fatura>();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TIPO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return numero;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Telefone)) {
            return false;
        }
        Telefone t = (Telefone) obj;
        return t.getId().equals(getId());
    }
    
    

}

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
 * fatura.
 * 
 * @author FERNANDO
 *
 */
@Entity
@Table(name = "tb_fatura", schema = "bdgestel")
public class Fatura extends BaseModel {

	/**
	 * telefone.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "telefone_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_fatura_telefone")
	private Telefone telefone;

	@Column(name = "valor")
	private BigDecimal valorFatura;

	@Column(name = "cd_Barras", length = 48, nullable = false)
	private String cdBarras;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_fatura_id")
	private Conta conta;
	
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "valor_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	@ForeignKey(name = "fk_fatura_valor")
	private Valor valor;
	*/

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fatura)) {
            return false;
        }
        Fatura f = (Fatura) obj;
        return f.getId().equals(getId());
    }

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public String getCdBarras() {
		return cdBarras;
	}

	public void setCdBarras(String cdBarras) {
		this.cdBarras = cdBarras;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
/*
	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}
*/
	
}

package br.gov.ce.seinfra.gestel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import br.gov.ce.seinfra.model.BaseModel;


/**
 * Entidade que representa imagem.
 * @author ferreira
 *
 */
@Entity
@Table(name = "tb_imagem", schema = "bdgestel")
public class Imagem extends BaseModel{

    /**
     * Conta a qual a imagem pertence.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @ForeignKey(name = "fk_conta_imagem")
    private Conta conta;
    
    /**
     * nome do arquivo
     */
    private String nome;
    
    /**
     * endereco da localizacao do arquivo.
     */
    private String url;

    /**
     * tamanho da conta digitalizado.
     */
    private Long tamanho;

    /**
     * Quantidade de paginas no arquivo.
     */
    private Integer paginas;
    

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

/*
 * 05/08/2010
 */
package br.gov.ce.seinfra.gestel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.gov.ce.seinfra.model.BaseModel;


/**
 * Entidade que representa orgao.
 * 
 * @author ferreira
 */
@Entity
@Table(name = "tb_uadm", schema = "bdger")
public class Orgao extends BaseModel implements Comparable<Orgao> {

	/**
	 * Nome.
	 */
	@Column(name = "uadm_nome")
	private String nome;
	/**
	 * Sigla, ou abreviacao.
	 */
	@Column(name = "uadm_sig")
	private String sigla;

	/**
	 * Lista de mapps.
	 */
    @OneToMany(mappedBy = "orgao", cascade = CascadeType.ALL)
    @JoinColumn(name = "orgao_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Mapp> mapps = new ArrayList<Mapp>();

	/**
	 * Lista de dotacoes.
	 */

	private transient List<Dotacao> dotacoes = new ArrayList<Dotacao>();
	
	
	private transient List<Reajuste> reajustes = new ArrayList<Reajuste>();


	/**
	 * Lista de acoes.
	 */

	private transient List<Acao> acoes = new ArrayList<Acao>();

	/**
	 * Lista de projetos finalisticos.
	 */

	private transient List<ProjetoFinalistico> projetos = new ArrayList<ProjetoFinalistico>();
	
	/**
	 * Lista de telefones.
	 */

	private transient List<Telefone> telefones = new ArrayList<Telefone>();
	

	/**
	 * pessoa de contato no orgao.
	 */
	@Column(name="uadm_pessoa_contato", length = 50)
	private String pessoaContato;

	/**
	 * telefones de contatos.
	 */
	@Column(name="uadm_telefone_contato", length = 50)
	private String telefoneContato;

	/**
	 * email da pessoa de contato.
	 */
	@Column(name="uadm_email_contato", length = 60)
	private String emailContato;

    /**
     * Identificar célula e/ou coordenadoria.
     */
    @Column(name = "uadm_nvl_hrq", nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelHierarquico nivel;
    
	  /**
     * Tipo da unidade administrativa.
     * @author heber
     */
    public enum NivelHierarquico {
        /** Coordenadoria. */
        COORDENADORIA,
        /** Célula. */
        CELULA,
        /** Prefeituras. */
        PREFEITURA,
        /** Empresas estatais vinculdas. */
        VINCULADA,
        /** Empresas. */
        EMPRESA,
        /** Orgão estatal. */
        ORGAO,
    }

    

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/** {@inheritDoc} */
	public int compareTo(Orgao o) {
		if (o == null) {
			return 1;
		}
		return compare(nome, o.nome);
	}

	public List<Mapp> getMapps() {
		return mapps;
	}

	public void setMapps(List<Mapp> mapps) {
		this.mapps = mapps;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public List<ProjetoFinalistico> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoFinalistico> projetos) {
		this.projetos = projetos;
	}

	public String getPessoaContato() {
		return pessoaContato;
	}

	public void setPessoaContato(String pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public NivelHierarquico getNivel() {
		return nivel;
	}

	public void setNivel(NivelHierarquico nivel) {
		this.nivel = nivel;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public List<Dotacao> getDotacoes() {
		return dotacoes;
	}

	public void setDotacoes(List<Dotacao> dotacoes) {
		this.dotacoes = dotacoes;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public String toString() {
		return sigla+"-"+nome;
	}

	public List<Reajuste> getReajustes() {
		return reajustes;
	}

	public void setReajustes(List<Reajuste> reajustes) {
		this.reajustes = reajustes;
	}

}

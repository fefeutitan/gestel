/*
 * 13/04/2010
 */
package br.gov.ce.seinfra.gestel.view;

import br.gov.ce.seinfra.model.Permissao;
import br.gov.ce.seinfra.view.DefaultPermissoesBean;


/**
 * Implementação para permissões do sistema.
 */
public class PermissoesBean extends DefaultPermissoesBean {


	public Permissao getOrgao() {
		return getPermissao("orgao");
	}

	public Permissao getMapp() {
		return getPermissao("mapp");
	}
	
	public Permissao getAcao() {
		return getPermissao("acao");
	}
	
	public Permissao getDotacao() {
		return getPermissao("dotacao");
	}
	
	public Permissao getPf() {
		return getPermissao("pf");
	}
	
	public Permissao getTelefone() {
		return getPermissao("telefone");
	}

	public Permissao getConta() {
		return getPermissao("conta");
	}
	
	public Permissao getAnalise() {
		return getPermissao("analise");
	}
	
	public Permissao getFatura() {
		return getPermissao("fatura");
	}
	
	public Permissao getReajuste() {
		return getPermissao("reajuste");
	}

}

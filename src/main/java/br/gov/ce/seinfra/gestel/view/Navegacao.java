/*
 * 13/04/2010
 */
package br.gov.ce.seinfra.gestel.view;

import br.gov.ce.seinfra.view.Outcome;


/**
 * Classe utilitária que armazena todos os "outcomes" do JSF.
 *
 * @author heber
 */
public final class Navegacao extends Outcome {

	/**
	 * Navegação inicial crud.
	 */
	public static final String CRUD = "list";
	/**
	 * Resultado de um processamento bem sucedido.
	 */
	public static final String SUCCESS = "sucesso";
    /**
     * Navegacao inicial para o caso de uso Manter Projetos.
     */
    public static final String CRUD_PROJETO = "listProjeto";
    /**
     * Navegacao inicial para o caso de uso Manter orgao.
     */
    public static final String CRUD_ORGAO = "listOrgao";
    /**
     * Navegacao inicial para o caso de uso Manter orgao.
     */
    public static final String CRUD_CONTA = "listConta";
    /**
     * Navegacao inicial para o caso de uso Manter orgao.
     */
    /*public static final String CRUD_REAJUSTE = "reajusteForm";*/
    /**
     * Navegacao inicial para o caso de uso Manter orgao.
     */
    public static final String CRUD_REAJUSTE = "reajusteList";

    /**
     * Navegacao inicial para o caso de uso Manter orgao.
     */
    public static final String VALIDAR_DADOS = "viewValidarDados";

    /**
     * Navegacao visualizar dados.
     */
    public static final String VISUALIZAR_DADOS = "viewDados";

    /**
     * Navegacao analisar dados.
     */
    public static final String ANALISAR_DADOS = "viewAnalise";
    
    /**
     * Navegacao analisar dados.
     */
    public static final String ANALISAR_RESUMO = "viewResumo";
    
    /**
     * Navegacao parecer.
     */
    public static final String EMITIR_PARECER = "viewParecer";
    
    /**
     * Navegacao analisar dados.
     */
    public static final String ANALISAR_RESUMO_ORGAO = "viewResumoOrgao";
    

}

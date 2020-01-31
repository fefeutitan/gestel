/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Mapp;

/**
 * Implementacao de mappDao para {@link Mapp}.
 * 
 * @author ferreira
 *
 */
@Component("mappDao")
public class MappDao extends HibernateDaoGenerico<Mapp> {

}

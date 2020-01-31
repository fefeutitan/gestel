/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Divida;

/**
 * Implementacao de DividaDao para {@link Divida}.
 * 
 * @author ferreira
 *
 */
@Component("dividaDao")
public class DividaDao extends HibernateDaoGenerico<Divida> {

}

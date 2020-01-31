/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.DividaDao;
import br.gov.ce.seinfra.gestel.model.Divida;

/**
 * Business DividaBusiness.
 * @author ferreira
 *
 */
@Component("dividaBusiness")
public class DividaBusiness extends BusinessGenerico<Divida, DividaDao> {

}

/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.MappDao;
import br.gov.ce.seinfra.gestel.model.Mapp;

/**
 * Implementacao de MappBusiness para {@link Mapp}.
 * @author ferreira
 *
 */
@Component("mappBusiness")
public class MappBusiness extends BusinessGenerico<Mapp, MappDao>{

}

/**
 * 
 */
package br.gov.ce.seinfra.gestel.dao;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.dao.impl.HibernateDaoGenerico;
import br.gov.ce.seinfra.gestel.model.Status;

/**
 * Implementacao de statusDao para {@link Status}.
 * 
 * @author ferreira
 *
 */
@Component("statusDao")
public class StatusDao extends HibernateDaoGenerico<Status> {

}

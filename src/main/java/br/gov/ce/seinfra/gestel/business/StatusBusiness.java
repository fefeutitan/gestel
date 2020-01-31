/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.StatusDao;
import br.gov.ce.seinfra.gestel.model.Status;

/**
 * Business StatusBusiness.
 * 
 * @author ferreira
 *
 */
@Component("statusBusiness")
public class StatusBusiness extends BusinessGenerico<Status, StatusDao> {

}

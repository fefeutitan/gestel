package br.gov.ce.seinfra.gestel.business;


import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.TelefoneDao;
import br.gov.ce.seinfra.gestel.model.Telefone;

/**
 * Business AcaoBusiness.
 * @author FERNANDO
 *
 */
@Component("telefoneBusiness")
public class TelefoneBusiness extends BusinessGenerico<Telefone, TelefoneDao> {

}

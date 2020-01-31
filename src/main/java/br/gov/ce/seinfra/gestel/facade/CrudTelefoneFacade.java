package br.gov.ce.seinfra.gestel.facade;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.TelefoneBusiness;
import br.gov.ce.seinfra.gestel.model.Telefone;

/**
 * Facada Telefone.
 * @author FERNANDO
 *
 */
@Component("crudTelefoneFacade")
public class CrudTelefoneFacade extends CrudFacadeGenerico<Telefone, TelefoneBusiness> {

}

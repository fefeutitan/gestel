package br.gov.ce.seinfra.gestel.business;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.FaturaDao;
import br.gov.ce.seinfra.gestel.model.Fatura;

/**
 * Business FaturaBusiness.
 * @author fernando
 *
 */
@Component("faturaBusiness")
public class FaturaBusiness extends BusinessGenerico<Fatura, FaturaDao> {

}

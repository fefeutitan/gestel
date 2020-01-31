package br.gov.ce.seinfra.gestel.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.facade.impl.CrudFacadeGenerico;
import br.gov.ce.seinfra.gestel.business.ContaBusiness;
import br.gov.ce.seinfra.gestel.business.FaturaBusiness;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Fatura;

/**
 * Facada Fatura.
 * @author fernando
 *
 */
@Component("crudFaturaFacade")
public class CrudFaturaFacade extends CrudFacadeGenerico<Fatura, FaturaBusiness> {

	public List<Conta> getContas(Fatura fatura) throws BusinessException {
		try {
			Conta conta = new Conta();
			fatura.setConta(conta);
			return getBusiness(ContaBusiness.class).listar(conta);
		} catch (BusinessException e) {
			throw new BusinessException(e);
		}
	}

	
}

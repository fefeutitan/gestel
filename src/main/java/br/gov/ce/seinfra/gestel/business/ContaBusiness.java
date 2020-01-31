/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.ValidacaoException;
import br.gov.ce.seinfra.gestel.dao.ContaDao;
import br.gov.ce.seinfra.gestel.model.Conta;
import br.gov.ce.seinfra.gestel.model.Status;
import br.gov.ce.seinfra.gestel.model.Status.STATUS_CONTA;

/**
 * Business ContaBusiness.
 * 
 * @author ferreira
 *
 */
@Component("contaBusiness")
public class ContaBusiness extends BusinessGenerico<Conta, ContaDao> {

	@Autowired
	@Qualifier("statusBusiness")
	private StatusBusiness statusBusiness;

	@Override
	public void persiste(Conta conta) throws ValidacaoException,
			BusinessException {
		if (conta.getId() == null) {
			super.persiste(conta);
			Status status = new Status();
			status.setConta(conta);
			status.setStatus(STATUS_CONTA.CADASTRADA);
			statusBusiness.persiste(status);
			conta.setStatus(status);
		}else if (conta.getStatus().getId() ==null){
			statusBusiness.persiste(conta.getStatus());
		}
		super.persiste(conta);

	}


	/** {@inheritDoc} */
	public List<Conta> listaParaAnalise() {
		return getDao().listaParaAnalise();
	}
	
	/** {@inheritDoc} */
	public List<Conta> listaResumo(Conta conta,boolean ano) {
		return getDao().listaResumo(conta,ano);
	}

	public void setStatusBusiness(StatusBusiness statusBusiness) {
		this.statusBusiness = statusBusiness;
	}
	
	public void atualizarStatus(Conta conta) throws ValidacaoException, BusinessException {
			Status status = new Status();
			status.setConta(conta);
			status.setStatus(STATUS_CONTA.VINCULADO);
			statusBusiness.persiste(status);//PERSISTE STATUS
			conta.setStatus(status);
			super.persiste(conta);
	}
	
}

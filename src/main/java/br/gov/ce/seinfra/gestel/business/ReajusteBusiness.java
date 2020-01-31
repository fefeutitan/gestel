package br.gov.ce.seinfra.gestel.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.gestel.dao.ReajusteDao;
import br.gov.ce.seinfra.gestel.model.Orgao;
import br.gov.ce.seinfra.gestel.model.Reajuste;

/**
 * Business AcaoBusiness.
 * @author FERNANDO
 *
 */
@Component("reajusteBusiness")
public class ReajusteBusiness extends BusinessGenerico<Reajuste, ReajusteDao> {

	public List<Reajuste> listaParaAnalise() {
		return getDao().listaParaAnalise();
	}


}

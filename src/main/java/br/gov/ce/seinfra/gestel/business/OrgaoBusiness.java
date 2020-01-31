/**
 * 
 */
package br.gov.ce.seinfra.gestel.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.gov.ce.seinfra.business.impl.BusinessGenerico;
import br.gov.ce.seinfra.exception.BusinessException;
import br.gov.ce.seinfra.exception.DaoException;
import br.gov.ce.seinfra.gestel.dao.OrgaoDao;
import br.gov.ce.seinfra.gestel.model.Orgao;

/**
 * Modelo de negocio para {@link Orgao}.
 * @author ferreira
 *
 */
@Component("orgaoBusiness")
public class OrgaoBusiness extends BusinessGenerico<Orgao, OrgaoDao> {

	/** {@inheritDoc} */
	public List<Orgao> listarOrgaoComFaturas() throws BusinessException{
		try {
			return getDao().listarOrgaoComFaturas();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Orgao>();
	}
}

package br.com.xtrategia.fiscon.command.veiculo;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

/**
 * Lista Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class QuantidadeItensAutoInfracaoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			AutoInfracaoPojo pojo = (AutoInfracaoPojo) transferObject;
			
			Criteria criteria = sessao.createCriteria(
					transferObject.getClass()).setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY);
			
			if(pojo.getVeiculoPojo()!=null &&
					pojo.getVeiculoPojo().getVeiculoPlaca()!=null &&
					!pojo.getVeiculoPojo().getVeiculoPlaca().equals("")){
				
				Criteria cri = sessao.createCriteria(VeiculoPojo.class).
				add(Restrictions.eq("veiculoPlaca",pojo.getVeiculoPojo().getVeiculoPlaca().toUpperCase()));
				
				VeiculoPojo veiculo =  (VeiculoPojo) cri.uniqueResult();
				
				criteria.add(Restrictions.eq("veiculoPojo", veiculo));
			}
			if(pojo.getNumeroAutoInfracao()!=null && !pojo.getNumeroAutoInfracao().equals("")){
				criteria.add(Restrictions.eq("numeroAutoInfracao", pojo.getNumeroAutoInfracao()));
			}
			if(pojo.getDataInfracao()!=null ){
				Date dataFim = new Date(pojo.getDataInfracao().getTime()+1000*60*60*24);
				criteria.add(Restrictions.ge("dataInfracao",pojo.getDataInfracao()));
				criteria.add(Restrictions.le("dataInfracao",dataFim));
			}
			
			transferObject.setConteudo(new Long(criteria.list().size()));

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
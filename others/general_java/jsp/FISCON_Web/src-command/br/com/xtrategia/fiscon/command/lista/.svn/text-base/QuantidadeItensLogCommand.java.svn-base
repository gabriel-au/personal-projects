package br.com.xtrategia.fiscon.command.lista;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.LogPojo;

/**
 * Classe para uxiliar a paginação das telas de LOG
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class QuantidadeItensLogCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		try{
		// fazer a consulta de lista
			//int tamanhoMaximoLista=15;
			Criteria criteria = sessao.createCriteria(LogPojo.class);
			
			LogPojo log = (LogPojo) transferObject;
			if(log.getDataEvento()!=null){
				Date dataFim = new Date(log.getDataEvento().getTime()+1000*60*60*24);
				criteria.add(Restrictions.ge("dataEvento",log.getDataEvento()));
				criteria.add(Restrictions.le("dataEvento",dataFim));
			}
			
			if(log.getIp()!=null && !log.getIp().equals("")){
				criteria.add(Restrictions.eq("ip",log.getIp()));
			}
			
			criteria.add(Restrictions.eq("tipo", log.getTipo()));
			
			
			Long count = new Long(criteria.list().size());
			
			//count = (count/tamanhoMaximoLista) +1;
			
			transferObject.setConteudo(count);

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
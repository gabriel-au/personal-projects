package br.com.xtrategia.fiscon.command.lista;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.LogPojo;

/**
 * Classe para lista os itens de log
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class ListarLogCommand extends AbstractCommand {

	private static Log log = LogFactory.getLog(ListarLogCommand.class);
	
	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			
			int tamanhoMaximoLista=20;
			int pagina=1;
			if(transferObject.getPagina()!=null){
				pagina = transferObject.getPagina().intValue();
			}
			
			log.debug("fazer a consulta de lista");
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
			
			criteria.setFirstResult(tamanhoMaximoLista*(pagina-1));  
			criteria.setMaxResults(tamanhoMaximoLista);
			
			criteria.addOrder(Order.asc("dataEvento"));
			
			transferObject.setConteudo(criteria.list());

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
package br.com.xtrategia.fiscon.command;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Lista Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class ListarCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			int tamanhoMaximoLista=20;
			int pagina=1;
			if(transferObject.getPagina()!=null){
				pagina = transferObject.getPagina().intValue();
			}
			
			Criteria criteria = sessao.createCriteria(
					transferObject.getClass()).setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY);
			
			criteria.setFirstResult(tamanhoMaximoLista*(pagina-1));  
			criteria.setMaxResults(tamanhoMaximoLista);
			
			criteria.addOrder(Order.asc("id"));
			
			transferObject.setConteudo(criteria.list());

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
package br.com.xtrategia.fiscon.command.lista;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.MarcaModeloPojo;

/**
 * Lista Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class ListarMarcaModeloCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			int tamanhoMaximoLista=20;
			int pagina=1;
			if(transferObject.getPagina()!=null){
				pagina = transferObject.getPagina().intValue();
			}
			
			MarcaModeloPojo pojo = (MarcaModeloPojo) transferObject;
			
			Criteria criteria = sessao.createCriteria(
					transferObject.getClass()).setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY);
			
			if(pojo.getNome()!=null && !pojo.getNome().equals("")){
				criteria.add(Restrictions.like("nome",pojo.getNome().toUpperCase(), MatchMode.ANYWHERE));
			}
			
			
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
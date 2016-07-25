package br.com.xtrategia.fiscon.command.usuario;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
/**
 * realiza o a lista das ultimas atualizações
 * @author Gustavo Marcelo Costa
 *
 */
public class AtualizacaoListarCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		// fazer a consulta de lista
		try {

			Criteria criteria = sessao.createCriteria(
					transferObject.getClass()).setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY);
			
			//máximo de 50 itens por página
			criteria.setMaxResults(10);
			
			criteria.addOrder(Order.desc("dataInclusao"));
			
			transferObject.setConteudo(criteria.list());

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
				
	}

}
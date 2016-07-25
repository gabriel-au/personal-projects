package br.com.xtrategia.fiscon.command.lista;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

/**
 * Lista Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class QuantidadeItensUsuarioCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			UsuarioPojo pojo = (UsuarioPojo) transferObject;
			
			Criteria criteria = sessao.createCriteria(
					transferObject.getClass()).setResultTransformer(
							Criteria.DISTINCT_ROOT_ENTITY);
			
			if(pojo.getNome()!=null && !pojo.getNome().equals("")){
				criteria.add(Restrictions.like("nome",pojo.getNome().toUpperCase(), MatchMode.ANYWHERE));
			}
			if(pojo.getMatricula()!=null && !pojo.getMatricula().equals("")){
				criteria.add(Restrictions.eq("matricula",pojo.getMatricula()));
			}
			
			transferObject.setConteudo(new Long(criteria.list().size()));

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
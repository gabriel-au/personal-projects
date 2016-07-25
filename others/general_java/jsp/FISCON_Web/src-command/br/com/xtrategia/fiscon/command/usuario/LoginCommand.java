package br.com.xtrategia.fiscon.command.usuario;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;
/**
 * realiza o login do usuário
 * @author Gustavo Marcelo Costa
 *
 */
public class LoginCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			UsuarioPojo pojo = (UsuarioPojo)transferObject;
			
			Criteria cri = sessao.createCriteria(UsuarioPojo.class).setResultTransformer(
					Criteria.DISTINCT_ROOT_ENTITY).
			add(Restrictions.eq("ativo", new Boolean(true))).
			add(Restrictions.eq("username", pojo.getUsername())).
			add(Restrictions.eq("passwordWeb", pojo.getPasswordWeb()));
			
			cri.addOrder(Order.asc("perfilPojos"));
			
			pojo = (UsuarioPojo) cri.uniqueResult();
			
			return pojo;
			
		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:"+e.getMessage());
		}
				
	}

}
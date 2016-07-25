package br.com.xtrategia.fiscon.command.veiculo;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;
/**
 * realiza o login do usuário
 * @author Gustavo Marcelo Costa
 *
 */
public class ConsultarCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			VeiculoPojo pojo = (VeiculoPojo)transferObject;
			
			Criteria cri = sessao.createCriteria(VeiculoPojo.class).setResultTransformer(
					Criteria.DISTINCT_ROOT_ENTITY).
			add(Restrictions.eq("veiculoPlaca", pojo.getVeiculoPlaca().toUpperCase()));
			
			pojo = (VeiculoPojo) cri.uniqueResult();
			
			if(pojo!=null){
				pojo = (VeiculoPojo) this.sessao.load(pojo.getClass(),pojo.getId()) ;
			}
			
			
			return pojo;
			
		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:"+e.getMessage());
		}
				
	}

}
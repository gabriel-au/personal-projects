package br.com.xtrategia.fiscon.command.veiculo;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;

/**
 * realiza o login do usuário
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class CancelarInfracaoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {

		AutoInfracaoPojo pojo = (AutoInfracaoPojo) transferObject;
		Criteria criteria = this.sessao.createCriteria(AutoInfracaoPojo.class);
		criteria.add(Restrictions.eq("numeroAutoInfracao", pojo.getNumeroAutoInfracao()));
		pojo = ((AutoInfracaoPojo) criteria.uniqueResult());
		if(pojo==null){
			throw new FISCONException("Auto infração não encontrado.");
		}
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, -2);
			if(pojo.getDataInfracao().after(calendar.getTime())){
				pojo.setSituacao("Cancelado");
				
				this.sessao.update(pojo);
			}else{
				throw new FISCONException("Ultrapassou o prazo para cancelamento.");
			}
		} catch (HibernateException e) {
			throw new FISCONException("Erro no cancelamento:" + e.getMessage());
		}

		return pojo;

	}
}
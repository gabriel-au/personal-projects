package br.com.xtrategia.fiscon.command;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
/**
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class AlterarCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			this.sessao.update(transferObject);
		} catch (HibernateException e) {
			throw new FISCONException("Erro na alteração:"+e.getMessage());
		}
				
		return transferObject;
	}

}
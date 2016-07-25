package br.com.xtrategia.fiscon.command;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;

public class ExcluirCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			transferObject = (TransferObject) this.sessao.load(transferObject.getClass(),transferObject.getId());
			sessao.flush();
			this.sessao.delete(transferObject);
		} catch (HibernateException e) {
			throw new FISCONException("Erro na exclusão:"+e.getMessage());
		}
				
		return transferObject;
	}

}
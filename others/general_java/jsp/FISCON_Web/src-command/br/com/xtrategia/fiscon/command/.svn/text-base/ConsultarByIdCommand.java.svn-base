package br.com.xtrategia.fiscon.command;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;

public class ConsultarByIdCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a consulta
		try {
			transferObject = (TransferObject) this.sessao.load(transferObject.getClass(),transferObject.getId()) ;
			return transferObject;
		} catch (HibernateException e) {
			throw new FISCONException("Erro na inclusao:"+e.getMessage());
		}
				
	}

}
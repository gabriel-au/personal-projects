package br.com.xtrategia.fiscon.command;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
/**
 * Força a inclusão de um registro
 * @author Gustavo Marcelo Costa
 *
 */
public class IncluirCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			this.sessao.save(transferObject);
		} catch (HibernateException e) {
			throw new FISCONException("Erro na inclusao:"+e.getMessage());
		}
				
		return transferObject;
	}

}
package br.com.xtrategia.fiscon.command;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * contador Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class QuantidadeItensCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		try{
		// fazer a consulta de lista
			//int tamanhoMaximoLista=15;
			
			Long count = (Long) sessao.createQuery("select count(*) from "+transferObject.getClass().getName()).uniqueResult();
			
			//count = (count/tamanhoMaximoLista) +1;
			
			transferObject.setConteudo(count);

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
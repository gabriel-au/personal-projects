package br.com.xtrategia.fiscon.command.veiculo;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Lista Genérica
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class QuantidadeItensChavesAutoInfracaoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject)
			throws FISCONException {
		// fazer a consulta de lista
		try {
			
			int quantidade = this.sessao.
			createSQLQuery("select sum(1), chave_extracao from auto_infracao group by chave_extracao")
			.list().size();

			
			transferObject.setConteudo(new Long(quantidade));

		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:" + e.getMessage());
		}

		return transferObject;
	}

}
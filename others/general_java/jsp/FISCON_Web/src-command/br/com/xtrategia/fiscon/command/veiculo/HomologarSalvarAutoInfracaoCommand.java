package br.com.xtrategia.fiscon.command.veiculo;

import java.util.Date;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
/**
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class HomologarSalvarAutoInfracaoCommand extends AbstractCommand {

	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			AutoInfracaoPojo pojo = (AutoInfracaoPojo) this.sessao.load(transferObject.getClass(),transferObject.getId()) ;
			
			pojo.setSituacao(((AutoInfracaoPojo)transferObject).getSituacao());
			pojo.setUsuarioHomologacao(((AutoInfracaoPojo)transferObject).getUsuarioHomologacao());
			pojo.setDataHomologacao(new Date());
			
			this.sessao.update(pojo);
		} catch (HibernateException e) {
			throw new FISCONException("Erro na alteração:"+e.getMessage());
		}
				
		return transferObject;
	}

}
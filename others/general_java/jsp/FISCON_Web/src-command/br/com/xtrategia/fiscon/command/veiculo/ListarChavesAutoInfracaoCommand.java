package br.com.xtrategia.fiscon.command.veiculo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.AbstractCommand;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.ChaveAutoInfracaoPojo;
/**
 * realiza o login do usuário
 * @author Gustavo Marcelo Costa
 *
 */
public class ListarChavesAutoInfracaoCommand extends AbstractCommand {

	@SuppressWarnings({ "unchecked" })
	public TransferObject execute(TransferObject transferObject) throws FISCONException {
		//fazer a inclusão
		try {
			int tamanhoMaximoLista=20;
			int pagina=1;
			if(transferObject.getPagina()!=null){
				pagina = transferObject.getPagina().intValue();
			}
			
			
			List<Object[]> lista =  (List<Object[]>) sessao.getNamedQuery("ChaveAutoInfracao").
			setInteger("tamanhoMaximoLista", tamanhoMaximoLista).
			setInteger("pagina", tamanhoMaximoLista*(pagina-1)).
			list();
			
			List<ChaveAutoInfracaoPojo> listaChave = new ArrayList<ChaveAutoInfracaoPojo>();
			
			for(Object[] o : lista){
				
				ChaveAutoInfracaoPojo pojo = new ChaveAutoInfracaoPojo();
				pojo.setQuantidade((Integer)o[0]);
				pojo.setCodigo((String)o[1]);

				Long valor = new Long(pojo.getCodigo().substring(1));
				pojo.setData(new Date(valor));
				
				listaChave.add(pojo);
				
			}
			
			
			transferObject.setConteudo(listaChave);
			
			return transferObject;
			
		} catch (HibernateException e) {
			throw new FISCONException("Erro na consulta:"+e.getMessage());
		}
				
	}

}
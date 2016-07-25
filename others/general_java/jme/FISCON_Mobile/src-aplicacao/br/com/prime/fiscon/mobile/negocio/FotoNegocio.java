package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.pojo.FotoPojo;
import br.com.prime.fiscon.mobile.pojo.TransactionObject;

public class FotoNegocio extends CRUDNegocio {
	
	
	
	
	
	public void gravar(TransactionObject obj) throws BancoDadosException {
		BancoDados bd = new BancoDados(obj.getBD());
		try {
			//log.debug(obj.getClass().getName()+" Salvar ou atualizar: "+obj.getHashtable());
			bd.salvarOuAtulizar(obj);
			
		} catch (BancoDadosException e) {
			//log.trace(getClass().getName(), e);
			throw new BancoDadosException();
		}
	}
	
	
	public TransactionObject getObj(TransactionObject obj) throws BancoDadosException{
		BancoDados bd = new BancoDados(obj.getBD());
		
		
		 Vector lista = bd.procurarById(obj.getHashtable());
		for (int i = 0; i < lista.size(); i++) {
			//System.out.println(((Hashtable)lista.elementAt(i)).toString());
			if(((FotoPojo)obj).getTipoFoto().equals(((Hashtable)lista.elementAt(i)).get(FotoPojo.TIPOFOTO) )){
				obj.carregar((Hashtable)lista.elementAt(i));
				return obj;
			}
			
		}
		return null;
		
	}
}

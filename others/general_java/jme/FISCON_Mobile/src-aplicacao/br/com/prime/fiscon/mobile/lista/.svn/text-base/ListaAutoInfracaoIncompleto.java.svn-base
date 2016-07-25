package br.com.prime.fiscon.mobile.lista;

import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;

/**
 * Lista de auto de infração incompletas
 *
 * @author Gustavo Luis
 */
public class ListaAutoInfracaoIncompleto {

	public static Vector listaBairro;

	public static void load() throws BancoDadosException {
		listaBairro =getListaAutoInfracaoIncompleto();
	}
	
	private static Vector getListaAutoInfracaoIncompleto() throws BancoDadosException{
		
		BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
	
		
		return bd.listar();
	}

	
}

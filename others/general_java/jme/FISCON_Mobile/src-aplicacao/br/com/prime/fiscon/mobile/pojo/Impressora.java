package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;

public class Impressora extends DeviceBluetooth{
	private static Impressora impressora;
	private Impressora(){
		

	}
	public static Impressora getInstance() throws BancoDadosException{
		if(impressora==null){
			impressora = new Impressora();
			recarregar();
			return impressora;
		}else{
			recarregar();
			return impressora;
		}
	}
	
	private static void recarregar() throws BancoDadosException{
		BancoDados bd = new BancoDados(BancoDados.IMPRESSORA);
		Vector lista;
		lista = bd.listar();
		for (int i = 0; i < lista.size(); i++) {
			impressora = (Impressora) impressora.carregar((Hashtable)lista.elementAt(i));
		}
	}
}

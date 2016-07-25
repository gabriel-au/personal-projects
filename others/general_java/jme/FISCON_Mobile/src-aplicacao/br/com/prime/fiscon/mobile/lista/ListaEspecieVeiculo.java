package br.com.prime.fiscon.mobile.lista;

import java.util.Vector;

import br.com.prime.fiscon.mobile.pojo.EspecieVeiculoPojo;

/**
 * Lista de Bairros de Brasília
 *
 * @author Gustavo
 */
public class ListaEspecieVeiculo {

	public static Vector listaVeiculoEspecie;

	public static void load() {
		listaVeiculoEspecie =getListaVeiculoEspecie();
	}
	 
	private static Vector getListaVeiculoEspecie(){
		Vector lista = new Vector();
		
		lista.addElement(new EspecieVeiculoPojo("1","PASSAGEIRO"));
		lista.addElement(new EspecieVeiculoPojo("2","CARGA"));
		lista.addElement(new EspecieVeiculoPojo("3","MISTO"));
		lista.addElement(new EspecieVeiculoPojo("4","CORRIDA"));
		lista.addElement(new EspecieVeiculoPojo("5","TRACAO"));
		lista.addElement(new EspecieVeiculoPojo("6","ESPECIAL"));
		lista.addElement(new EspecieVeiculoPojo("9","OUTROS"));
		lista.addElement(new EspecieVeiculoPojo("7","COLECAO"));
		
		return lista;
	}

	
}

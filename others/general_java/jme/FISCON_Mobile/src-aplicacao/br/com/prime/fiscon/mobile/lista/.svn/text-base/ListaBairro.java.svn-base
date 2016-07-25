package br.com.prime.fiscon.mobile.lista;

import java.util.Vector;

import br.com.prime.fiscon.mobile.pojo.BairroPojo;

/**
 * Lista de Bairros de Brasília
 *
 * @author Gustavo
 */
public class ListaBairro {

	public static Vector listaBairro;

	public static void load() {
		listaBairro =getListaBairro();
	}
	
	private static Vector getListaBairro(){
		Vector lista = new Vector();
		
		lista.addElement(new BairroPojo("1", "Brasília"));
		lista.addElement(new BairroPojo("2", "Gama"));
		lista.addElement(new BairroPojo("3", "Taguatinga"));
		lista.addElement(new BairroPojo("4", "Brazlândia"));
		lista.addElement(new BairroPojo("5", "Sobradinho"));
		lista.addElement(new BairroPojo("6", "Planaltina"));
		lista.addElement(new BairroPojo("7", "Paranoá"));
		lista.addElement(new BairroPojo("8", "Núcleo Bandeirante"));
		lista.addElement(new BairroPojo("9", "Ceilândia"));
		lista.addElement(new BairroPojo("10", "Guará"));
		lista.addElement(new BairroPojo("11", "Cruzeiro"));
		lista.addElement(new BairroPojo("12", "Samambaia"));
		lista.addElement(new BairroPojo("13", "Santa Maria"));
		lista.addElement(new BairroPojo("14", "São Sebastião"));
		lista.addElement(new BairroPojo("15", "Recanto das Emas"));
		lista.addElement(new BairroPojo("16", "Lago Sul"));
		lista.addElement(new BairroPojo("17", "Riacho Fundo"));
		lista.addElement(new BairroPojo("18", "Lago Norte"));
		lista.addElement(new BairroPojo("19", "Candangolândia"));
		lista.addElement(new BairroPojo("20", "çguas Claras"));
		lista.addElement(new BairroPojo("21", "Riacho Fundo II"));
		lista.addElement(new BairroPojo("22", "Sudoeste/Octogonal"));
		lista.addElement(new BairroPojo("23", "Varjão"));
		lista.addElement(new BairroPojo("24", "Park Way"));
		lista.addElement(new BairroPojo("25", "SCIA - Setor Complementar de Indústria e Abastecimento"));
		lista.addElement(new BairroPojo("26", "Sobradinho II"));
		lista.addElement(new BairroPojo("27", "Jardim Botânico"));
		lista.addElement(new BairroPojo("28", "Itapoã"));
		lista.addElement(new BairroPojo("29", "SIA - Setor de Indústria e Abastecimento"));
		lista.addElement(new BairroPojo("30", "Vicente Pires"));
		
		return lista;
	}
	
	public Vector procurar(String chave){
		Vector resultado = new Vector();
		for (int i = 0; i < getListaBairro().size(); i++) {
			BairroPojo bairroPojo = (BairroPojo)getListaBairro().elementAt(i);
			if(bairroPojo.getNome().indexOf(chave)>0){
				resultado.addElement(bairroPojo);
			}
		}
		return resultado;
	}
	

	
}

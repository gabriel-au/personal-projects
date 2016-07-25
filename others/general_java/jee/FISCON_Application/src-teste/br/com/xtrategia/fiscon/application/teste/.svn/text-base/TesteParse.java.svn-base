package br.com.xtrategia.fiscon.application.teste;

import br.com.xtrategia.fiscon.application.pojo.VeiculoPojo;

public class TesteParse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String linha="JHH19292027221113111970119291929";
		VeiculoPojo pojo = new VeiculoPojo();
		
	    pojo.setPlaca(linha.substring(0, 7).trim());
	    
	    pojo.getMarcaModelo().setCodigo(linha.substring(7, 13).trim());
	    pojo.getCor().setCodigo(linha.substring(13, 15).trim());
	    pojo.getTipo().setCodigo(linha.substring(15, 17).trim());
	    pojo.getCategoria().setCodigo(linha.substring(17, 19).trim());
	    pojo.getEspecie().setCodigo(linha.substring(19, 20).trim());
	    pojo.getMunicipio().setCodigo(linha.substring(20, 24).trim());
	    
	    pojo.setAnoFabricacao(Integer.parseInt(linha.substring(24, 28).trim()));
	    pojo.setAnoModelo(Integer.parseInt(linha.substring(28, 32).trim()));

		System.out.print(pojo.getPlaca());
	    System.out.print(pojo.getMarcaModelo().getCodigo());
	    System.out.print(pojo.getCor().getCodigo());
	    System.out.print(pojo.getTipo().getCodigo());
	    System.out.print(pojo.getCategoria().getCodigo());
	    System.out.print(pojo.getEspecie().getCodigo());
	    System.out.print(pojo.getMunicipio().getCodigo());
	    System.out.print(pojo.getAnoFabricacao());
	    System.out.println(pojo.getAnoModelo());
	    System.out.println(linha);
	}

}

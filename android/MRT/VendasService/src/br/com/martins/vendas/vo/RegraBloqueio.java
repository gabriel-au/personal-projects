package br.com.martins.vendas.vo;

public class RegraBloqueio {

	public Integer codigoBloqueioMercadoria;
	public Integer tipoPublicoAlvo;
	public Integer codigoPublicoAlvo;
	public Integer codigoRegional;
	public Integer tipoBloqueioMercadoria;
	public Integer valorReducaoItem;

	public static final int TAMANHO_VLR_RDC_ITEM = 3;
	public static final int TAMANHO_TIPO_PUBLICO_ALVO = 2;
	public static final int TAMANHO_TIPO_REGIAO = 2;
	public static final int TAMANHO_CODIGO_BLOQUEIO = 4;
	
	@Override
	public String toString(){
		String regra = String.format("%0" + TAMANHO_TIPO_PUBLICO_ALVO + "d", tipoPublicoAlvo);
		regra += String.format("%0" + TAMANHO_TIPO_REGIAO + "d", codigoRegional);
		regra += String.format("%0" + TAMANHO_CODIGO_BLOQUEIO + "d", codigoBloqueioMercadoria);
		regra += String.format("%0" + TAMANHO_VLR_RDC_ITEM + "d", valorReducaoItem);
		//01112004
		return regra;
		}

//	@Override
//	public String toString(){
//		String regra = String.format("%0" + TAMANHO_TIPO_PUBLICO_ALVO + "d", codigoPublicoAlvo);
//		regra += String.format("%0" + TAMANHO_TIPO_REGIAO + "d", codigoRegional);
//		regra += codigoBloqueioMercadoria.toString();
//		regra += String.format("%0" + TAMANHO_VLR_RDC_ITEM + "d", valorReducaoItem);
//		//01112004
//		return regra;		
//	}
	
}

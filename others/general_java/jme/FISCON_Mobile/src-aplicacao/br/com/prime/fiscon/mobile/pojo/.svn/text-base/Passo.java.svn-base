package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;

public class Passo extends TransactionObject{
	public static final int FOTO1 = 1;
	public static final int FOTO2 = 2;
	public static final int AUTOINFRACAOCONSULTACNH = 3;
	public static final int AGUARDELERGPS = 4;
	public static final int AUTOINFRACAOCONSULTAVEICULO = 5;
	public static final int AUTOINFRACAOOBSERVACAO = 6;
	public static final int AUTOINFRACAOCOMPLEMENTO = 7;
	public static final int AUTOINFRACAOFINALIZACAOCOMFOTO = 8;
	public static final int AUTOINFRACAOCODIGOINFRACAO = 9;
	public static final int AUTOINFRACAOIMPRIMIR = 10;
	
	
	private int proximoPasso;
	public static final String PROXIMOPASSO = "proximoPasso";
	
	public Passo(){
		
	}
	public Passo(String id){
		setId(id);
		
	}
	
	public Passo(String id, int proximoPasso) {
		setId(id);
		setProximoPasso(proximoPasso);
	}
	public int getProximoPasso() {
		return proximoPasso;
	}
	public void setProximoPasso(int proximoPasso) {
		this.proximoPasso = proximoPasso;
	}
	
	public Hashtable getHashtable(){
		Hashtable passo = new Hashtable();
		passo.put(ID, getId());
		passo.put(PROXIMOPASSO, String.valueOf(getProximoPasso()));
		return passo;
	}
	
	public void carregarPasso(Hashtable passo){
		setId((String)passo.get(ID));
		setProximoPasso(Integer.parseInt((String)passo.get(PROXIMOPASSO)));
	}
	public void carregar(Hashtable passo) {
		setId((String)passo.get(ID));
		setProximoPasso(Integer.parseInt((String)passo.get(PROXIMOPASSO)));		
	}
	public String getBD() {
		// TODO Auto-generated method stub
		return BancoDados.PASSO;
	}
	
	
	
}

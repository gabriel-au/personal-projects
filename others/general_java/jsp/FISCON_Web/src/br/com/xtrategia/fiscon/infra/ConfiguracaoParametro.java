package br.com.xtrategia.fiscon.infra;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Suporte a configuração do sistema
 * 
 * @author Gustavo Marcelo Costa
 *
 */
@XStreamAlias("command")
public class ConfiguracaoParametro {

	@XStreamAlias("alias")
	private String alias;
	@XStreamAlias("classe")
	private String classe;
	
	public ConfiguracaoParametro(){
		
	}
	
	public ConfiguracaoParametro(String alias, String classe){
		this.alias=alias;
		this.classe=classe;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
}

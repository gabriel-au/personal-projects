package br.com.xtrategia.fiscon.teste;



import br.com.xtrategia.fiscon.infra.Configuracao;
import br.com.xtrategia.fiscon.infra.ConfiguracaoParametro;

import com.thoughtworks.xstream.XStream;

public class TesteConfiguracao {

	public static void main(String[] args) {
		TesteConfiguracao tc = new TesteConfiguracao();
		tc.carregar();
		

	}
	
	
	public void carregar(){
		XStream xstream = new XStream();
		
		xstream.processAnnotations(Configuracao.class);
		Configuracao con = (Configuracao) xstream.fromXML(this.getClass()
				.getResourceAsStream("/configuracao.xml"));
		
		for(ConfiguracaoParametro p: con.getParametros()){
			System.out.println(p.getAlias()+"-"+p.getClasse());
		}
		
		
	}
	
}

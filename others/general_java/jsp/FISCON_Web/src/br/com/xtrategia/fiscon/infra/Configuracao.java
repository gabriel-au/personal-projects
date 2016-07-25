package br.com.xtrategia.fiscon.infra;


import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Suporte a configuração do sistema
 * 
 * @author Gustavo Marcelo Costa
 *
 */
@XStreamAlias(value="configuracao")
public class Configuracao {


	@XStreamImplicit(itemFieldName="command")
	private List<ConfiguracaoParametro> commands;

	public Configuracao(ConfiguracaoParametro... content) {
		this.commands = Arrays.asList(content);
	}

	public List<ConfiguracaoParametro> getParametros() {
		return commands;
	}

}


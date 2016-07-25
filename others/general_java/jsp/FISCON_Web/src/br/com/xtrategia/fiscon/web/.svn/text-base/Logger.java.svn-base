package br.com.xtrategia.fiscon.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.xtrategia.fiscon.infra.NegocioFacade;
import br.com.xtrategia.fiscon.infra.NegocioFacadeImpl;
import br.com.xtrategia.fiscon.web.pojo.LogPojo;


/**
 * Controle de gravação dos Logs, utiliza uma thread para não atrapalhar 
 * ou deixar lento os outros processos
 * 
 * @author Gustavo
 *
 */
public class Logger implements Runnable{

	private LogPojo pojo;
	private static Log log = LogFactory.getLog(Logger.class);
	
	static private NegocioFacade fachada = new NegocioFacadeImpl();
	public Logger(LogPojo pojo) {
		this.pojo=pojo;
	}

	/**
	 * @return Instância da fachada
	 */
	static public NegocioFacade getFachada() {
		return Logger.fachada;
	}
	
	@Override
	public void run() {
		try {
			getFachada().execute(pojo,"Incluir");
		} catch (Exception e) {
			log.error("Erro:"+e.getMessage());
		}
	}

	/**
	 * Método para gravação dos logs
	 * @param tipo
	 * @param acao
	 */
	public static void gravar(String tipo, String ip, Integer idUsuario, String acao) {
		Logger l = new Logger(new LogPojo(tipo,ip, idUsuario,acao));
		Thread t = new Thread(l);
		t.start();
	}

}

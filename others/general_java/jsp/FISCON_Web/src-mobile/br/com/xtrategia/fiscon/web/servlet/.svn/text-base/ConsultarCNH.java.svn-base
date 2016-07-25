package br.com.xtrategia.fiscon.web.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.CnhPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * 
 * @author Gustavo
 * 
 */
public class ConsultarCNH extends MobileAction {

	private String pagina;
	private CnhPojo pojo;

	public String consultarPorCPF(String cpf) {
		String retorno = "registro=00123456789;" + "dataNascimento=01/01/1970;"
				+ "nome=JOSE NINGUEM;" + "categoria= B ;"
				+ "dataValidade=08/06/2010;" + "tipo=CONDUTOR;"
				+ "totalPontos=0";
		return retorno;
	}

	public String consultar(String registro, String data) {
		String retorno;

		String url = "http://novosite.detran.df.gov.br/habilitacao/consultas/consulta-habilitacao.jsp?REGISTRO="
				+ registro + "&DATANASCIMENTO=" + data;

		try {
			URL link = new URL(url);
			URLConnection con = link.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con
					.getInputStream()));

			// BufferedReader in = new BufferedReader(new
			// FileReader("pagina.html"));

			String inputLine;
			pagina = "";

			while ((inputLine = in.readLine()) != null) {
				pagina += inputLine;

			}
			
			pojo = new CnhPojo();
			pojo.setCnh( buscarCampo("Registro:"));
			pojo.setDataNascimento(toDate(buscarCampo("Data de Nascimento:")));
			pojo.setNome(buscarCampo("Nome:"));
			pojo.setCategoria(buscarCampo("Categoria:"));
			pojo.setValidade(toDate(buscarCampo("Data de Validade da CNH:")));
			pojo.setTipo(buscarCampo("Tipo:"));
			pojo.setPontuacao(new Integer(buscarPontos().trim().substring(0,buscarPontos().indexOf(" "))));

			retorno = "registro=" + buscarCampo("Registro:") + ";"
					+ "dataNascimento=" + buscarCampo("Data de Nascimento:")
					+ ";" + "nome=" + buscarCampo("Nome:") + ";" + "categoria="
					+ buscarCampo("Categoria:") + ";" + "dataValidade="
					+ buscarCampo("Data de Validade da CNH:") + ";" + "tipo="
					+ buscarCampo("Tipo:") + ";" + "totalPontos="
					+ buscarPontos() + "";

			in.close();
		} catch (Exception e) {
			//e.printStackTrace();
			pojo = null;
			return "Falha";
		}

		return retorno;
	}

	private String buscarCampo(String campo) {
		// primeiro corte
		int inicio = pagina.indexOf(campo);
		// segundo corte - formatação
		inicio = pagina.indexOf("fundodadosbold", inicio);
		// terceiro corte - inicio do valor
		inicio = pagina.indexOf(">", inicio) + 1;

		// quarto corte- fim do campo
		int fim = pagina.indexOf("<", inicio);

		String tempo = pagina.substring(inicio, fim).trim();

		// verificar campos span
		if (tempo.trim().equals("")) {
			// busca sgundo corte novamente
			// segundo corte - formatação
			inicio = pagina.indexOf("titulovermelho", inicio);
			// terceiro corte - inicio do valor
			inicio = pagina.indexOf(">", inicio) + 1;
			// quarto corte- fim do campo
			fim = pagina.indexOf("<", inicio);

			tempo = pagina.substring(inicio, fim).trim();

		}

		return limparCampo(tempo);
	}

	private String buscarPontos() {
		// primeiro corte
		int inicio = pagina.indexOf("Dados de Pontua&ccedil;&atilde;o");
		// segundo corte - palavra total
		inicio = pagina.indexOf("Total", inicio);
		// terceiro corte - palavra pontos
		int fim = pagina.indexOf("pontos", inicio) + "pontos".length();
		// quarto corte
		inicio = pagina.lastIndexOf(">", fim) + 1;

		String tempo = pagina.substring(inicio, fim).trim();

		return limparCampo(tempo);
	}

	private String limparCampo(String campo) {
		while (campo.indexOf("  ") > 0) {
			campo = campo.replaceAll("  ", " ");
		}
		return campo;
	}

	private Date toDate(String data){
		try {
			int year= new Integer(data.substring(6));
			int month=new Integer(data.substring(4, 5));
			int dayOfMonth=new Integer(data.substring(0, 2));

			GregorianCalendar gc = new GregorianCalendar(year, month, dayOfMonth);

			return gc.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public CnhPojo getPojo() {
		return pojo;
	}

}
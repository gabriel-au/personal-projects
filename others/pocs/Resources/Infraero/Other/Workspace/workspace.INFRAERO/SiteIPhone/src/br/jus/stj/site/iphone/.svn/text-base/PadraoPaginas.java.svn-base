package br.jus.stj.site.iphone;

import java.util.Map;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.Form;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Input;
import br.jus.stj.site.iphone.ui.P;
import br.jus.stj.site.iphone.ui.Texto;

/**
 * Base para a criação de todas as páginas
 * 
 * @author Gustavo
 * 
 */
public abstract class PadraoPaginas {

	public abstract String gerarPagina() throws SystemException;

	public abstract String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException;

	/**
	 * Gera uma página com uma mensagem
	 * 
	 * @param titulo
	 * @param mensagem
	 * @return
	 * @throws SystemException
	 */
	public String gerarPaginaMensagem(String titulo, String mensagem)
			throws SystemException {
		return gerarPaginaMensagem(titulo, mensagem, null);
	}

	/**
	 * Gera uma página com uma mensagem
	 * 
	 * @param titulo
	 * @param mensagem
	 * @return
	 * @throws SystemException
	 */
	public String gerarPaginaMensagem(String titulo, String mensagem,
			String linkVoltar) throws SystemException {
		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		if (linkVoltar == null) {
			linkVoltar = "";
		}
		{
			Div div = new Div("titulo");
			A at = new A(linkVoltar, true);
			at.add(new Img("imagens/botao-inicio-azul.png"));
			div.add(at);
			div.add(new Div("titulo_texto", new Texto(titulo)));
			body.add(div);
		}
		{
			Div div = new Div("arredondado", new Texto(mensagem));
			body.add(div);
		}
		body.add(Padroes.getEnchimentoPagina(15));
		return pagina.toString();
	}

	/**
	 * Página não encontrada
	 * 
	 * @return
	 * @throws SystemException
	 */
	public String gerarPaginaErro400() throws SystemException {
		String texto = "A página que você procura não está disponível no portal móvel do STJ. Por favor, verifique se você digitou o endereço corretamente e tente outra vez";

		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		{
			Div div = new Div("titulo");
			A at = new A("", true);
			at.add(new Img("imagens/botao-inicio-azul.png"));
			div.add(at);
			div.add(new Div("titulo_texto", new Texto("Erro")));
			body.add(div);
		}
		{
			Div div = new Div("arredondado");
			div.add(new Div("arredondado_titulo", new Texto(
					"A página não foi encontrada")));
			div.add(new Div("arredondado_chamada", new Texto(texto)));
			body.add(div);
		}
		body.add(Padroes.getEnchimentoPagina(15));
		return pagina.toString();
	}

	/**
	 * Erro na execução de alguma tarefa
	 * 
	 * @return
	 * @throws SystemException
	 */
	public String gerarPaginaErro500() throws SystemException {
		String texto = "A página que você procura não está disponível no portal móvel do STJ. Por favor, verifique se você digitou o endereço corretamente e tente outra vez";

		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		{
			Div div = new Div("titulo");
			A at = new A("", true);
			at.add(new Img("imagens/botao-inicio-azul.png"));
			div.add(at);
			div.add(new Div("titulo_texto", new Texto("Erro")));
			body.add(div);
		}
		{
			Div div = new Div("arredondado");
			div.add(new Div("arredondado_titulo", new Texto(
					"Erro na abertura da página")));
			div.add(new Div("arredondado_chamada", new Texto(texto)));
			body.add(div);
		}
		body.add(Padroes.getEnchimentoPagina(15));
		return pagina.toString();
	}

	/**
	 * Página padrão para montar um formulário de consultas
	 * 
	 * @param body
	 * @throws SystemException
	 */
	public void gerarPaginaConsulta(Body body, String metodoAction)
			throws SystemException {
		Div divPrincipal = new Div("arredondado");

		{
			Div div = new Div("arredondado_titulo");
			div.add(new Texto("Consulta"));
			divPrincipal.add(div);
		}
		// montar o formuário
		Form form = new Form("Pagina");
		form.add(new Input(Input.INPUT_HIDDEN, "p", metodoAction));
		form.add(new Input(Input.INPUT_HIDDEN, "m", "consultar"));
		form.setMethod("GET");

		form.add(new Div("arredondado_chamada", new Texto(
				"Parâmetro de pesquisa:")));

		Div div = new Div("arredondado_chamada_simples_center");

		div
				.add(new Input(Input.INPUT_TEXT, "parametro", "input_text", "",
						"3"));
		div
				.add(new Input(Input.INPUT_SUBMIT, "valor", "input_submit", "",
						"50"));
		form.add(div);
		form.add(new P(""));
		divPrincipal.add(form);
		body.add(divPrincipal);
		body.add(Padroes.getEnchimentoPagina(15));
	}


}

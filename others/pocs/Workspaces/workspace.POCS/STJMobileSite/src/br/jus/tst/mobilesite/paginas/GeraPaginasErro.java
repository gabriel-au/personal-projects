package br.jus.tst.mobilesite.paginas;

import br.jus.tst.mobilesite.Padroes;
import br.jus.tst.mobilesite.ui.Body;
import br.jus.tst.mobilesite.ui.Div;
import br.jus.tst.mobilesite.ui.H1;
import br.jus.tst.mobilesite.ui.HTML;
import br.jus.tst.mobilesite.ui.P;
import br.jus.tst.mobilesite.util.CriarArquivo;

public class GeraPaginasErro {
	public static void main(String[] args) {
		gerar();
	}

	public static void gerar() {
		try {
			gerarErro400();
			gerarErro500();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gerarErro400() throws Exception {
		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		String texto = "A p�gina que voc� procura n�o est� dispon�vel no portal m�vel do TST. Por favor, verifique se voc� digitou o endere�o corretamente e tente outra vez";
		Div div = new Div("mensagem");
		H1 titulo = new H1("A p�gina n�o foi encontrada.");
		P mensagem = new P(texto);

		div.add(titulo);
		div.add(mensagem);
		body.add(div);

		CriarArquivo.salvar("erro/400.jsp", pagina);
	}

	public static void gerarErro500() throws Exception {
		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		String texto = "Uma opera��o gerou uma falha na requisi��o desta p�gina. Por favor, verifique se voc� digitou o endere�o corretamente e tente outra vez";
		Div div = new Div("mensagem");
		H1 titulo = new H1("A P�gina n�o foi carregada.");
		P mensagem = new P(texto);

		div.add(titulo);
		div.add(mensagem);
		body.add(div);

		CriarArquivo.salvar("erro/500.jsp", pagina);
	}
}
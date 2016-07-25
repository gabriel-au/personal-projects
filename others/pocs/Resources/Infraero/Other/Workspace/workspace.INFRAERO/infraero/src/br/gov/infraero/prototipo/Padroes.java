package br.gov.infraero.prototipo;

import br.gov.infraero.SystemException;
import br.gov.infraero.site.ui.A;
import br.gov.infraero.site.ui.Body;
import br.gov.infraero.site.ui.Br;
import br.gov.infraero.site.ui.Commentary;
import br.gov.infraero.site.ui.Div;
import br.gov.infraero.site.ui.Head;
import br.gov.infraero.site.ui.ImageLink;
import br.gov.infraero.site.ui.Img;
import br.gov.infraero.site.ui.Link;
import br.gov.infraero.site.ui.Meta;
import br.gov.infraero.site.ui.ScriptCode;
import br.gov.infraero.site.ui.Style;

public class Padroes {

	public static Head getHeader() throws SystemException {
		Head head = new Head(
				"Superior Tribunal de Justiça - O Tribunal da Cidadania");

		// adicionar o comet‡rio
		head.add(new Commentary("by Gustavo Marcelo"));
		// charset
		head.add(new Meta(Meta.CHARSET_ISO88591));
		// viewport
		head.add(new Meta());
		// icone do site
		head.add(new ImageLink("imagens/logo-touch-icon.png"));
		head.add(new Link("shortcut icon","imagens/logo-touch-icon.ico","image/icon"));
		head.add(new Style("css/style.css"));
		head.add(new Style("css/styleComplemento.css"));

		// cria‹o do java script
		String code = "if (navigator.userAgent.indexOf('iPhone') != -1) {"
				+ "\n        addEventListener(\"load\", function() {"
				+ "\n                setTimeout(hideURLbar, 0);"
				+ "\n        }, false);" + "\n}" + "\n"
				+ "\nfunction hideURLbar() {"
				+ "\n        window.scrollTo(0, 1);" + "\n}";
		ScriptCode sc = new ScriptCode(code);
		head.add(sc);

		return head;
	}

	public static Body getBody() throws SystemException {
		Body body = new Body();

		Div div = new Div(true);
		A a = new A("", true);
		a.add(new Img("imagens/topo.png"));
		div.add(a);
		body.add(div);

		return body;
	}

	public static Div getEnchimentoPagina(int quantidadeLinhas) throws SystemException {
		
		Div div = new Div();
		for(int i=0;i<quantidadeLinhas;i++){
			div.add(new Br());
		}

		return div;
	}
	
}

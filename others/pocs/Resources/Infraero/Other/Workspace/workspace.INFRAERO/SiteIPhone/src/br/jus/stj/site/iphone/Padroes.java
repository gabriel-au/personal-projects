package br.jus.stj.site.iphone;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Br;
import br.jus.stj.site.iphone.ui.Commentary;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.Head;
import br.jus.stj.site.iphone.ui.ImageLink;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Link;
import br.jus.stj.site.iphone.ui.Meta;
import br.jus.stj.site.iphone.ui.ScriptCode;
import br.jus.stj.site.iphone.ui.Style;

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

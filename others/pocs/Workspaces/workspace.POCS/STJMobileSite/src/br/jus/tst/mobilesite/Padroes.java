package br.jus.tst.mobilesite;

import br.jus.stj.mobile.SystemException;
import br.jus.tst.mobilesite.ui.A;
import br.jus.tst.mobilesite.ui.Body;
import br.jus.tst.mobilesite.ui.Br;
import br.jus.tst.mobilesite.ui.Commentary;
import br.jus.tst.mobilesite.ui.Div;
import br.jus.tst.mobilesite.ui.Head;
import br.jus.tst.mobilesite.ui.ImageLink;
import br.jus.tst.mobilesite.ui.Img;
import br.jus.tst.mobilesite.ui.Link;
import br.jus.tst.mobilesite.ui.Meta;
import br.jus.tst.mobilesite.ui.ScriptCode;
import br.jus.tst.mobilesite.ui.Style;

public class Padroes {
	public static Head getHeader() throws SystemException {
		Head head = new Head("Tribunal Superior do Trabalho");

		head.add(new Commentary("Prime Mobile Solutions"));

		head.add(new Meta("UTF-8"));

		head.add(new Meta());

		//head.add(new ImageLink("imagens/logo-touch-icon.png"));
		//head.add(new Link("shortcut icon", "imagens/logo-touch-icon.ico", "image/icon"));
		head.add(new Style("css/style.css"));
		head.add(new Style("css/styleComplemento.css"));

		String code = "if (navigator.userAgent.indexOf('iPhone') != -1) {\n" +
				"        addEventListener(\"load\", function() {\n" +
				"                setTimeout(hideURLbar, 0);\n" +
				"        }, false);\n" +
				"}\n" +
				"\n" +
				"function hideURLbar() {\n" +
				"        window.scrollTo(0, 1);\n}";

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

	public static Div getEnchimentoPagina(int quantidadeLinhas)
			throws SystemException {
		/* 62 */Div div = new Div();
		/* 63 */for (int i = 0; i < quantidadeLinhas; i++) {
			/* 64 */div.add(new Br());
		}

		/* 67 */return div;
	}
}
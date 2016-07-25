package br.jus.stj.site.iphone.servlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.ConsultaCalendario;
import br.jus.stj.mobile.bd.pojo.CalendarioPojo;
import br.jus.stj.mobile.util.DateUtil;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.Padroes;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.Form;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Input;
import br.jus.stj.site.iphone.ui.Option;
import br.jus.stj.site.iphone.ui.Select;
import br.jus.stj.site.iphone.ui.Texto;

public class PaginaCalendario extends PadraoPaginas {

	@Override
	public String gerarPagina() throws SystemException {
		return null;
	}

	@Override
	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		// topo das páginas
		Div div = new Div("titulo");
		A at = new A("", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("Calendário de Sessões")));
		body.add(div);

		// montar a página inicial
		if (metodo == null || metodo.equals("gerarPagina")) {
			gerarPaginaInicial(body, null, null);
		} else if (metodo.equals("consultar")) {
			gerarPaginaInicial(body, listaParametros.get("ano"),
					listaParametros.get("mes"));
		} else {
			return gerarPaginaErro400();
		}
		return pagina.toString();
	}

	private void gerarPaginaInicial(Body body, String ano, String mes)
			throws SystemException {

		Div divBusca = new Div("arredondado");
		body.add(divBusca);
		Div divTitulo = new Div("arredondado_titulo");
		divBusca.add(divTitulo);
		divTitulo.add(new Texto("Consulta"));

		// montar o formuário
		Form form = new Form("Pagina");
		form.add(new Input(Input.INPUT_HIDDEN, "p", "Calendario"));
		form.add(new Input(Input.INPUT_HIDDEN, "m", "consultar"));
		form.setMethod("GET");

		{
			Div divCombos = new Div("arredondado_chamada_simples_center");
			Select listaMes = new Select("input_select", "mes");
			String meses[] = DateUtil.meses;
			int x = -1;
			String mesAtual = String.valueOf(DateUtil
					.getMesAsInteger(new Date()));
			if (mes == null || (mes != null && mes.equals("")))
				mes = mesAtual;
			for (String s : meses) {
				x++;
				boolean bool = (mes.equals(String.valueOf(x)));
				listaMes.add(new Option(String.valueOf(x), s, bool));
			}
			listaMes.setStyle("width: 150px;");
			listaMes.setOnchange("submit()");

			Select listaAno = new Select("input_select", "ano");
			listaAno.setOnchange("submit()");
			Integer anoAtual = DateUtil.getAno(new Date());
			if (ano == null || (ano != null && ano.equals("")))
				ano = String.valueOf(anoAtual);
			for (int i = anoAtual + 1; i > (anoAtual - 10); i--) {
				boolean bool = (ano.equals(String.valueOf(i)));
				listaAno.add(new Option(String.valueOf(i), String.valueOf(i),
						bool));
			}
			listaAno.setStyle("width: 80px; margin-right:20px;");
			divCombos.add(listaAno);
			divCombos.add(listaMes);

			form.add(divCombos);
		}

		divBusca.add(form);

		Div divCal = new Div("arredondado");
		body.add(divCal);

		ConsultaCalendario cc = new ConsultaCalendario();
		List<CalendarioPojo> lista = cc.consultaCalendario(mes, ano);
		Date dataFor = new Date(0);
		boolean primeiro = true;
		for (CalendarioPojo pojo : lista) {

			if (!pojo.getData().equals(dataFor)) {
				Div div;
				if (primeiro)
					div = new Div("arredondado_titulo");
				else
					div = new Div("arredondado_titulo_espaco_superior");
				div.add(new Texto(DateUtil.getDataCompleta(pojo.getData())));
				divCal.add(div);
				dataFor = pojo.getData();
				primeiro = false;
			}
			{
				Div div = new Div("arredondado_cal");
				A link = new A("Pagina?p=Calendario&m=consultaPorData", false);
				link.add(new Texto(pojo.getTurma()));
				div.add(link);
				divCal.add(div);
			}

		}

	}
	
	
	
	public String gerarPaginaErro400() throws SystemException {
		String texto = "A página que você procura não está disponível no portal móvel do STJ. Por favor, verifique se você digitou o endereço corretamente e tente outra vez";

		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		{
			Div div = new Div("titulo");
			A at = new A("Pagina?p=Calendario", true);
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

}

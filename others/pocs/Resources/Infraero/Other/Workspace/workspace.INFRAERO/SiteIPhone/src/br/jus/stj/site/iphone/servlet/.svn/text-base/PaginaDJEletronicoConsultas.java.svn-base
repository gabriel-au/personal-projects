package br.jus.stj.site.iphone.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.ConsultaDiarioJustica;
import br.jus.stj.mobile.bd.pojo.DiarioJusticaPojo;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.Input;
import br.jus.stj.site.iphone.ui.P;
import br.jus.stj.site.iphone.ui.Script;
import br.jus.stj.site.iphone.ui.Style;
import br.jus.stj.site.iphone.ui.Texto;

public class PaginaDJEletronicoConsultas {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Div getNumero() throws SystemException {
		return gerarCamposDataTipodata("Número do Processo no STJ:",false);
	}
	
	public Div getNumeroRegistro() throws SystemException {
		return gerarCamposDataTipodata("Número de Registro no STJ",false);
	}

	public Div getNumeroUnico() throws SystemException {
		return gerarCamposDataTipodata("Número único do Processo",false);
	}

	public Div getNumeroOrigem() throws SystemException {
		return gerarCamposDataTipodata("Número na Origem",false);
	}

	public Div getOAB() throws SystemException {
		return gerarCamposDataTipodata("OAB:",true);
	}

	public Div getNomeParte() throws SystemException {
		return gerarCamposDataTipodata("Nome das Partes:",false);
	}

	public Div getNomeAdvogado() throws SystemException {
		return gerarCamposDataTipodata("Nome do Advogado:",true);
	}
	

	public Div getIntegral() throws SystemException {
		ConsultaDiarioJustica consulta = new ConsultaDiarioJustica();

		Div d = new Div();
		// texto
		d.add(new P("Atenção! Cada arquivo PDF contendo a íntegra do " + 
				"DJe tem um tamanho aproximado de 30 MB. Arquivos " +
				"desse tamanho costumam levar cerca de 30 minutos para " +
				"serem copiados (download) em conexões de 256 Kbps, " +
				"podendo demorar mais em função da quantidade de " +
				"usuários que estiverem utilizando o serviço. Caso haja " +
				"lentidão excessiva durante a cópia do arquivo, tente " +
				"efetuá-lo em horários de menor concorrência."));
		
		d.add(new P("O arquivo único representa o resultado de um dos " +
				"critérios de pesquisa. Não tem valor legal por não ser " +
				"assinado digitalmente. O resultado dessa pesquisa é " +
				"mantido durante 60 dias."));
		
		// adicionar as ultimas edições
		List<DiarioJusticaPojo> lista = consulta.listarUltimasEdicoes();

		for (DiarioJusticaPojo pojo : lista) {

			A a = new A(pojo.getLink(), false);
			Div div = new Div();
			div.add(new Div(new Texto("Edição nº " + pojo.getEdicao() + " "
					+ pojo.getLocal())));
			div.add(new Div(new Texto(pojo.getDisponivelEm())));
			div.add(new Div(new Texto(pojo.getPublicadoEm())));
			// div.add(new Div( new Texto(pojo.getData())));

			a.add(div);

			d.add(new Div("arredondado_link_borda", a));
		}

		return d;
	}

	
	
	private Div gerarCamposDataTipodata(String titulo, boolean incluirPeriodo) throws SystemException {
		Div d = new Div();
		d.add(new Style("calendario/jquery-ui-1.7.2.custom.css"));
		d.add(new Script("calendario/calendario.js"));
		d.add(new Script("calendario/jquery-1.3.2.min.js"));
		d.add(new Script("calendario/jquery-ui-1.7.2.custom.min.js"));
		
		if(titulo!=null){
			d.add(new Div("arredondado_chamada", new Texto(titulo)));
			
			Div div = new Div("arredondado_chamada_simples_center");
			div.add(new Input(Input.INPUT_TEXT, "parametro", "input_text","", "50"));
			div.add(new Input(Input.INPUT_SUBMIT, "valor", "input_submit", "","50"));
			d.add(div);
		}else{
			Div div = new Div("arredondado_chamada_simples_center");
			div.add(new Input(Input.INPUT_SUBMIT, "valor", "input_submit", "","50"));
			d.add(div);
		}
		
		{
			d.add(new Div("arredondado_chamada", new Texto("Consulta a edições anteriores. Digite a data:")));
			
			Div div = new Div("arredondado_chamada_simples");
			div.add(new Input(Input.INPUT_TEXT, "calendario", "input_data",sdf.format(new Date()), "10","calendario"));
			d.add(div);
		}
		
		if(incluirPeriodo){
			d.add(new Div("arredondado_chamada", new Texto("Pesquisa por período. Informe a data das edições inicial e final:")));
			{
				Div div = new Div("arredondado_chamada_simples");
				div.add(new Input(Input.INPUT_TEXT, "calendarioInicio", "input_data",sdf.format(new Date()), "10","calendario"));
				d.add(div);
			}
			{
				Div div = new Div("arredondado_chamada_simples");
				div.add(new Input(Input.INPUT_TEXT, "calendarioFinal", "input_data","", "10","calendario"));
				d.add(div);
			}
		}
		
		{
			d.add(new Div("arredondado_chamada", new Texto("Tipo de Data:")));
			{
				Div div = new Div("arredondado_chamada_simples");
				Input campo = new Input(Input.INPUT_RADIO, "tipoData", "input_data","publicado", "0");
				campo.setSelecionado(true);
				div.add(campo);
				div.add(new Texto("publicado em "));
				d.add(div);
			}
			{
				Div div = new Div("arredondado_chamada_simples");
				div.add(new Input(Input.INPUT_RADIO, "tipoData", "input_data","disponivel", "0"));
				div.add(new Texto("disponível em"));
				d.add(div);
			}
		}

		return d;
	}

	
}

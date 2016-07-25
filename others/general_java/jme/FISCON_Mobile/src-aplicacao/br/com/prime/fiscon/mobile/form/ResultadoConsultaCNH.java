package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.util.GravarEstatistica;
import br.com.prime.fiscon.mobile.pojo.CNHPojo;

public class ResultadoConsultaCNH extends MobileDialog {

	
	public ResultadoConsultaCNH(CNHPojo cnh) {
		super("Consultar CNH");
		append(new Label("Nome: " + cnh.getNome()));
		append(new Label("Registro: " + cnh.getRegistro()));
		append(new Label("Data de Nascimento: " + cnh.getDataNascimento()));
		append(new Label("Categoria: " + cnh.getCategoria()));
		append(new Label("Data de Validade: " + cnh.getDataValidade()));
		append(new Label("Tipo: " + cnh.getTipo()));
		append(new Label("Total de Pontos: " + cnh.getTotalPontos()));
	}
	
	public void init() {
		GravarEstatistica.gravarConsulta(0, 0, 1);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}

}

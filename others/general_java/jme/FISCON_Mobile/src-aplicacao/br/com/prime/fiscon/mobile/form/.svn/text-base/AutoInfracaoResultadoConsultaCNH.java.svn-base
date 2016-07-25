package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.GravarEstatistica;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.CNHPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AutoInfracaoResultadoConsultaCNH extends MobileDialog {
	private CNHPojo cnh;

	public AutoInfracaoResultadoConsultaCNH(CNHPojo cnh) {
		super("Consultar CNH");
		this.cnh = cnh;
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
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		AutoInfracaoPojo.getInstance().setCnh(cnh);

		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
			try {
				CRUDNegocio crudNegocio = new CRUDNegocio();
				crudNegocio.gravar(AutoInfracaoPojo.getInstance().getCnh());
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOCODIGOINFRACAO));
				/*BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
				bd.salvarOuAtulizar(AutoInfracaoPojo.getInstance().serializarHashtable());
				new RecuperarAutoInfracao().gravar(new Passo(AutoInfracaoPojo.getInstance().getId(), Passo.AUTOINFRACAOCODIGOINFRACAO));*/
			}catch(BancoDadosException be){
				showMensagemErro(getTitle(), "Falha ao salvar dados localmente", this);
			}catch (Exception e) {
				showMensagemErro(getTitle(), "Falha ao salvar dados locamente", this);
			}
		}

		new AutoInfracaoConsultaCodigoInfracao(this).show();
	}
	public void executarLeft() {
		new AutoInfracaoConsultaCNH().show();
	}
}

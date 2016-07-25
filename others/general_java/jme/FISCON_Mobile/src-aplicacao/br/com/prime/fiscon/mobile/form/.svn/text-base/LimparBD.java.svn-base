package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.BancoDados;

/**
 * Tela para complementar o endereço
 * 
 * @author Gustavo
 * 
 */
public class LimparBD extends MobileDialog {
	Label AUTOINFRACAO;
	Label CNH;
	Label ENDERECO;
	Label FOTO;
	Label GLOBALPOSITION;
	Label INFRACAOTIPO;
	Label PASSO;
	Label USUARIO;
	Label VEICULO;
	Label IMPRESSORA;
	Label RECUPERACAO;

	public LimparBD(MobileDialog dialog) {
		super("Limpar banco de dados");
		setPrevScreen(dialog);
	}

	public void init() {

		try {
			BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
			AUTOINFRACAO = new Label("Auto Infração: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.CNH);
			CNH = new Label("CNH: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.ENDERECO);
			ENDERECO = new Label("Endereço: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.FOTO);
			FOTO = new Label("Foto: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.GLOBALPOSITION);
			GLOBALPOSITION = new Label("Global Position: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.INFRACAOTIPO);
			INFRACAOTIPO = new Label("Infração Tipo: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.PASSO);
			PASSO = new Label("Passo: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.USUARIO);
			USUARIO = new Label("Usuário: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.VEICULO);
			VEICULO = new Label("Veículo: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.IMPRESSORA);
			IMPRESSORA = new Label("Impressora: " + bd.getNumeroRegistro());
			bd = new BancoDados(BancoDados.RECUPERACAO);
			RECUPERACAO = new Label("Recuperação: " + bd.getNumeroRegistro());
			
			append(AUTOINFRACAO);  
			append(CNH);           
			append(ENDERECO);      
			append(FOTO);          
			append(GLOBALPOSITION);
			append(INFRACAOTIPO);  
			append(PASSO);         
			append(USUARIO);       
			append(VEICULO);       
			append(IMPRESSORA);    
			append(RECUPERACAO);   
			
		} catch (Exception e) {
			showMensagemErro(getTitle(),
					"Falha na recuperação do tamanho do BD", this);
		}
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Apagar");
	}

	public void executarLeft() {
		new MenuPrincipal().show();
	}

	public void executarRight() {

		try {

			BancoDados.apagaBD(BancoDados.AUTOINFRACAO);
			BancoDados.apagaBD(BancoDados.CNH);
			BancoDados.apagaBD(BancoDados.ENDERECO);
			BancoDados.apagaBD(BancoDados.FOTO);
			BancoDados.apagaBD(BancoDados.GLOBALPOSITION);
			BancoDados.apagaBD(BancoDados.INFRACAOTIPO);
			BancoDados.apagaBD(BancoDados.PASSO);
			BancoDados.apagaBD(BancoDados.USUARIO);
			BancoDados.apagaBD(BancoDados.VEICULO);
			BancoDados.apagaBD(BancoDados.IMPRESSORA);
			BancoDados.apagaBD(BancoDados.RECUPERACAO);
			showMensagem(getTitle(), "Limpeza do banco de dados concluido",
					this);

		} catch (BancoDadosException e) {
			e.printStackTrace();
		}
	}

}

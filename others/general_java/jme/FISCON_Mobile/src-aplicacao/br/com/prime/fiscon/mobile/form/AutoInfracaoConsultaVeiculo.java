package br.com.prime.fiscon.mobile.form;

import java.util.Date;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.TextPlaca;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
	
	
	
public class AutoInfracaoConsultaVeiculo extends MobileDialog {
	private boolean autoInfracaoSemFoto=false;
	
	public AutoInfracaoConsultaVeiculo() {
		super("Consulta de Veículos");
	}
	
	
	/**
	 * Utilizado para informar que a tela voltará para o menu principal
	 * caso o parametro autoInfracaoSemFoto esteja true e se false volta para a 
	 * tela de confirmação da foto da placa.
	 * @param autoInfracaoSemFoto
	 */
	public AutoInfracaoConsultaVeiculo(boolean autoInfracaoSemFoto) {
		
		
		super("Consulta de Veículos");
		setAutoInfracaoSemFoto(autoInfracaoSemFoto);
		
	}

	public void init() {
		TextPlaca placa = new TextPlaca("Placa");
		append(placa, true);
		
	}
	
	public boolean isAutoInfracaoSemFoto() {
		return autoInfracaoSemFoto;
	}



	public void setAutoInfracaoSemFoto(boolean autoInfracaoSemFoto) {
		this.autoInfracaoSemFoto = autoInfracaoSemFoto;
	}



	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}

	public void executarRight() {
		
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
			AutoInfracaoPojo.getInstance().setDataInfracao(new Date());
			AutoInfracaoPojo.getInstance().setFoto(true);
			AutoInfracaoPojo.getInstance().setFlag_condutor(false);
			AutoInfracaoPojo.getInstance().setFlag_endereco(false);
			AutoInfracaoPojo.getInstance().setFlag_veiculo(false);
			AutoInfracaoPojo.getInstance().setObservacao("");
			// Salvar localmente
			CRUDNegocio crudNegocio = new CRUDNegocio();
			try {
				crudNegocio.gravar(AutoInfracaoPojo.getInstance());
			} catch (BancoDadosException e) {
				showMensagemErro(getTitle(), "Falha ao salvar Auto de Infracao", this);
			}
		}
		
		new AutoInfracaoConsultaVeiculoAguarde(this).show();
	}
	public void executarLeft() {
		if(isAutoInfracaoSemFoto()){
			new MenuAutoInfracao().show();
		}else{
			new AutoInfracaoFoto2Confirma().show();
		}
	}
}
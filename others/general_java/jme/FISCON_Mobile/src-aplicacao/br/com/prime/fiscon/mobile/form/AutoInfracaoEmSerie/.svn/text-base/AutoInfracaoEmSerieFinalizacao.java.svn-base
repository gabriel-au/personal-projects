package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.util.DateConverter;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoEmSerieFinalizacao extends MobileDialog {
	private Picture primeiraFoto;
	private Picture segundaFoto;
	private Label veiculoPojo;
	private Label proprietario;
	private Label infracaoTipoPojo;
	private Label dataInfracao;
	private Label enderecoLogradouro;
	private Label enderecoComplemento;
	private Label enderecoBairro;
	private Label observacao;
	
	public AutoInfracaoEmSerieFinalizacao() {
		super("Finalizar Auto Infração");
	}

	public AutoInfracaoEmSerieFinalizacao(String addressDevice) {
		super("Finalizar Auto Infração");

	}

	public void init() {
		//configureMicroLog();
		//log.debug("Antes try AutoInfracaoFinalizar");
		carregarTela(AutoInfracaoPojo.getInstance().getHashtable());
	}

	protected void definirMenu() {
		setMenuText("Sair", "Salvar"); 
	}

	public void carregarTela(Hashtable objeto) {
		try {
			
			if(AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo()!=null){	
				//log.debug("Carregando foto1");
				Label lPrimeiraFoto = new Label("Foto da placa");
				append(lPrimeiraFoto);

				primeiraFoto = new Picture();
				primeiraFoto.setImage(Image.createImage(AutoInfracaoPojo.getInstance()
						.getPrimeiraFotoPojo().getDado(), 0, AutoInfracaoPojo.getInstance()
						.getPrimeiraFotoPojo().getDado().length));
				append(primeiraFoto);
				
			}
			if(AutoInfracaoPojo.getInstance().getSegundaFotoPojo()!=null){
				//log.debug("Carregando foto2");
				Label lSegundaFoto = new Label("Foto do veículo");
				append(lSegundaFoto);

				segundaFoto = new Picture();
				segundaFoto.setImage(Image.createImage(AutoInfracaoPojo.getInstance()
						.getSegundaFotoPojo().getDado(), 0, AutoInfracaoPojo.getInstance()
						.getSegundaFotoPojo().getDado().length));
				append(segundaFoto);
			}
			
			
			
			
			//log.debug("Carregando Veiculo");
			if(AutoInfracaoPojo.getInstance().getVeiculoPojo()!=null)
				if(AutoInfracaoPojo.getInstance().getVeiculoPojo().getVeiculoPlaca()!=null){
					veiculoPojo = new Label("Placa: "
							+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
							.getVeiculoPlaca());
					append(veiculoPojo);
				}
			if(AutoInfracaoPojo.getInstance().getCnh()!=null)
				if(AutoInfracaoPojo.getInstance().getCnh().getNome()!=null){
					proprietario = new Label("Condutor: "+AutoInfracaoPojo.getInstance().getCnh().getNome());
					append(proprietario);
				}
			/*
			 * usuarioPojo = new Label("Usuário: "s +
			 * Usuario.getInstance().getNome() ); append(usuarioPojo);
			 */
			if(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo()!=null)
				if(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo().getNome()!=null){
					infracaoTipoPojo = new Label("Infração: "
							+ AutoInfracaoPojo.getInstance().getInfracaoTipoPojo()
								.getNome());
					append(infracaoTipoPojo);
				}
			if(AutoInfracaoPojo.getInstance().getDataInfracao()!=null){
				dataInfracao = new Label("Data Infração: "
						+ DateConverter.dateToString(AutoInfracaoPojo.getInstance().getDataInfracao(),"dd/MM/yyyy hh:mm")
							.toString());
				append(dataInfracao);
			}

			if(AutoInfracaoPojo.getInstance().getEndereco()!=null){
				if(AutoInfracaoPojo.getInstance().getEndereco().getEndereco()!=null){
					enderecoLogradouro = new Label("Logradouro: "
						+ AutoInfracaoPojo.getInstance().getEndereco().getEndereco());
					append(enderecoLogradouro);
				}
				if(AutoInfracaoPojo.getInstance().getEndereco().getComplemento()!=null){
					enderecoComplemento = new Label("Complemento: "
						+ AutoInfracaoPojo.getInstance().getEndereco().getComplemento());
					append(enderecoComplemento);
				}
				if(AutoInfracaoPojo.getInstance().getEndereco().getBairro()!=null){
					enderecoBairro = new Label("Bairro: "
						+ AutoInfracaoPojo.getInstance().getEndereco().getBairro());
					append(enderecoBairro);
				}
			}
			if(AutoInfracaoPojo.getInstance().getObservacao()!=null){
				observacao = new Label("Observacao: "
					+ AutoInfracaoPojo.getInstance().getObservacao());
				append(observacao);
			}
		} catch (Exception e) {
			//log.trace("Falha ao construir campos", e);
			showMensagemErro(getTitle(), "Falha na criação dos campos", this);
			
		}

	}
	
	public void executarRight() {
		/**
		 * Enviar dados para o site.
		 */
		new AguardeSalvarAutoInfracaoComFoto(this).show();
		/*EnviarAutoInfracao enviarAutoInfracao = new EnviarAutoInfracao();
		try{
		if (enviarAutoInfracao.enviar(AutoInfracaoPojo.getInstance().serializarHashtable())) {
			new AutoInfracaoImprimir(this).show();
		}else{
			showMensagemErro(getTitle(), "Falha no envio", this);
		}
		}catch (Exception e) {
			showMensagemErro(getTitle(), "Falha no envio exception "+e.getMessage(), this);
		}*/

	}
	public void executarLeft() {
		//new AutoInfracaoObservacaoConfirma(null, AutoInfracaoPojo.getInstance().getObservacao()).show();
	}

}

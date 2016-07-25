package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.MobileMain;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.CameraCanvas;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.FotoNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.FotoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AutoInfracaoFoto2 extends MobileDialog {
	private Display mDisplay;
	private CameraCanvas camera;

	public AutoInfracaoFoto2(MobileDialog prevScreen) {
		super("Capturar a foto do veículo");
		String supports = System.getProperty("video.snapshot.encodings");

		if (supports != null && supports.length() > 0) {
			// append(new Label(""));
		} else
			append(new Label("A câmera não foi encontrada."));
		this.setPrevScreen(prevScreen);
	}

	public void init() {
		camera();
		
	}

	public void show() {
		super.show();
		try {
			camera.inicializarCamera();
		} catch (Exception e) {
			try {
				camera.pararCamera();
			} catch (Exception e1) {
				showMensagemErro(getTitle(), "Falha ao parar a camera", this);
			}
			showMensagemErro("Inicializar Camera.", e.getMessage(), this);
		}
	}

	public void camera() {
		try {

			camera = new CameraCanvas(this);

			mDisplay = Display.getDisplay(MobileMain.instance);

			mDisplay.setCurrent(camera);

		} catch (Exception e) {
			try {
				camera.pararCamera();
			} catch (Exception e1) {
				showMensagemErro(getTitle(), "Falha ao parar a camera", this);
			}
			showMensagemErro(getTitle(), e.getMessage(), this);

		}
		// showCamera();
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Capturar");
	}

	public void executarRight() {
		
		try {
			byte[] imageByte = camera.capturarByte();
			Image image = Image.createImage(imageByte, 0, imageByte.length);

			image = camera.createThumbnail(image);

			// Picture picture = new Picture();
			// picture.setImage(image);
			// picture.setHorizontalAlignment(Graphics.HCENTER);
			//AutoInfracaoPojo.getInstance().setSegundaFoto(imageByte);
			
			if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
				// Salvar localmente
				CRUDNegocio crudNegocio = new CRUDNegocio();
				FotoNegocio fotoNegocio = new FotoNegocio();
				FotoPojo foto = new FotoPojo();
				foto.setTipoFoto(FotoPojo.VEICULO);
				foto.setDado(imageByte);
				//FotoPojo.setId(FotoPojo.getId()+FotoPojo.VEICULO);
				fotoNegocio.gravar(foto);
				AutoInfracaoPojo.getInstance().setSegundaFotoPojo(foto);
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOCONSULTAVEICULO));
				
				
				/*BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
				bd.salvarOuAtulizar(AutoInfracaoPojo.getInstance()
						.serializarHashtable());*/
				//new RecuperarAutoInfracao().gravar();
			}
			
			AutoInfracaoFoto2Confirma confirmacaoFoto2 = new AutoInfracaoFoto2Confirma();
			confirmacaoFoto2.setPrevScreen(this);
			confirmacaoFoto2.show();
			camera.pararCamera();
		}catch(BancoDadosException be){
			showMensagemErro(getTitle(), "Falha ao salvar dados localmente", this);
		} catch (Exception e) {
			try {
				camera.pararCamera();
			} catch (Exception e1) {
				showMensagemErro(getTitle(), "Falha ao parar a camera", this);
			}
			showMensagemErro(getTitle(), "Falha ao parar a camera", this);
		}

	}
	public void executarLeft() {
		try {
			camera.pararCamera();
		} catch (Exception e) {
			showMensagemErro(getTitle(), "Falha ao parar a camera", this);
			e.printStackTrace();
		}
		super.executarLeft();
	}

}

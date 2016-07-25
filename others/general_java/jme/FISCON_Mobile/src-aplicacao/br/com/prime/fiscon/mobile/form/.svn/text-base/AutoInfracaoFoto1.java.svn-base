package br.com.prime.fiscon.mobile.form;

import java.util.Date;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

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
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoFoto1 extends MobileDialog {
	//private static final Logger log = LoggerFactory.getLogger(AutoInfracaoFoto1.class);
	private Display mDisplay;
	private CameraCanvas camera;

	public AutoInfracaoFoto1(MobileDialog prevScreen) {
		super("Capturar a foto da placa");
		String supports = System.getProperty("video.snapshot.encodings");
		if (supports == null || supports.length() <= 0) {
			append(new Label("A câmera não foi encontrada."));
		}
		this.setPrevScreen(prevScreen);

	}

	public void init() {
		camera();
		//setPrevScreen(new MenuPrincipal());
	}

	public void show() {
		
		//configureMicroLog();

		super.show();
		try {
			camera.inicializarCamera();
		} catch (Exception e) {
			try {
				camera.pararCamera();
			} catch (Exception e1) {
				showMensagemErro(getTitle(), "Falha ao parar a camera.", this);
			}
			showMensagemErro(getTitle(), "Falha na inicialização da camera.",
					this.getPrevScreen());
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
				showMensagemErro(getTitle(), "Falha ao parar a camera.", this);
			}
			showMensagemErro(getTitle(), e.getMessage(), this);

		}
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Capturar");
	}

	public void executarRight() {
		try {
			// Image image = camera.capturar();
			if (!AutoInfracaoPojo.getInstance().isFlag_consultaV()) {
				AutoInfracaoPojo.limparCampos();
			} else {
				VeiculoPojo vp = AutoInfracaoPojo.getInstance().getVeiculoPojo();
				AutoInfracaoPojo.limparCampos();
				AutoInfracaoPojo.getInstance().setVeiculoPojo(vp);
			}
			byte[] imageByte = camera.capturarByte();
			Image image = Image.createImage(imageByte, 0, imageByte.length);
			image = camera.createThumbnail(image);

			Picture picture = new Picture();
			picture.setImage(image);
			picture.setHorizontalAlignment(Graphics.HCENTER);

		//	AutoInfracaoPojo.getInstance().setPrimeiraFoto(imageByte);
			AutoInfracaoPojo.getInstance().setDataInfracao(new Date());
			AutoInfracaoPojo.getInstance().setFoto(true);
			AutoInfracaoPojo.getInstance().setFlag_condutor(false);
			AutoInfracaoPojo.getInstance().setFlag_endereco(false);
			AutoInfracaoPojo.getInstance().setFlag_veiculo(false);
			AutoInfracaoPojo.getInstance().setObservacao("");
			if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
				// Salvar localmente
				CRUDNegocio crudNegocio = new CRUDNegocio();
				FotoNegocio fotoNegocio = new FotoNegocio();
				crudNegocio.gravar(AutoInfracaoPojo.getInstance());
				
				FotoPojo foto = new FotoPojo();
				foto.setTipoFoto(FotoPojo.PLACA);
				foto.setDado(imageByte);
				//FotoPojo.setId(FotoPojo.getId()+FotoPojo.PLACA);
				fotoNegocio.gravar(foto);
				AutoInfracaoPojo.getInstance().setPrimeiraFotoPojo(foto);
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.FOTO2));
				
				
			}
			
			AutoInfracaoFoto1Confirma confirmacao = new AutoInfracaoFoto1Confirma(this);
			confirmacao.setPrevScreen(this);
			confirmacao.show();
			camera.pararCamera();
		} catch (BancoDadosException be) {
			
			//log.trace(this.getClass().getName(), be);
			showMensagemErro(getTitle(), "Falha ao salvar dados localmente "
					+ be.getMessage(), this);
			
		} catch (Exception e) {
			try {
				camera.pararCamera();
			} catch (Exception e1) {
				//log.trace(this.getClass().getName(), e1);
				showMensagemErro(getTitle(), "Falha ao parar a camera.", this.getPrevScreen());
			}
			//log.trace(this.getClass().getName(), e);
			showMensagemErro(getTitle(), e.getMessage(), this);

		}

	}

	public void executarLeft() {
		try {
			camera.pararCamera();
		} catch (Exception e) {
			//log.trace(this.getClass().getName(), e);
			showMensagemErro(getTitle(), "Falha ao parar a camera", this);
		}
		super.executarLeft();
	}

}

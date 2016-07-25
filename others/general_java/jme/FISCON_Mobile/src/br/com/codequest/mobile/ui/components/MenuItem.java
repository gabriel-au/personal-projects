package br.com.codequest.mobile.ui.components;

import java.io.IOException;

import javax.microedition.lcdui.Image;

import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.MobileMain;

/**
 * Componente para criar itens de Menu
 * 
 * @author gustavomarcelo
 *
 */
public class MenuItem extends TextBox {

	private MobileDialog mobileDialog;
	private Class clazz;
	private Image imagem;
	private Image imagemHover;
	private String texto;
	
	public Image getImagemHover() {
		return imagemHover;
	}
	public void setImagemHover(Image imagemHover) {
		this.imagemHover = imagemHover;
	}
	public MenuItem(String imagemSrc, String texto ,MobileDialog mobileDialog, MobileDialog mobileDialogPai) {
		this.mobileDialog=mobileDialog;
		this.mobileDialog.setPrevScreen(mobileDialogPai);
		this.texto=texto;
        try {
        	imagem = Image.createImage(imagemSrc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public MenuItem(String imagemSrc, String imageHover, String texto ,MobileDialog mobileDialog, MobileDialog mobileDialogPai) {
		this.mobileDialog=mobileDialog;
		this.mobileDialog.setPrevScreen(mobileDialogPai);
		this.texto=texto;
        try {
        	imagem = Image.createImage(imagemSrc);
        	imagemHover = Image.createImage(imageHover);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public MenuItem(String imagemSrc, String texto ,MobileDialog mobileDialog) {
		this.mobileDialog=mobileDialog;
		this.texto=texto;
		try {
			imagem = Image.createImage(imagemSrc);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public MenuItem(String imagemSrc, String texto) {
		this.mobileDialog=null;
		this.texto=texto;
		try {
			imagem = Image.createImage(imagemSrc);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public MenuItem(String imagemSrc, String texto ,Class clazz) {
		this.clazz=clazz;
		this.texto=texto;
		try {
			imagem = Image.createImage(imagemSrc);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void show(){
		if(mobileDialog!=null){
			mobileDialog.show();
		}else if(clazz!=null){
			
			//PadraoDialog;
			//ImagemDialog;
			try {
				//((MobileDialog)clazz.newInstance()).show();
				((MobileDialog)clazz.newInstance()).show();
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
		}else {
			MobileMain.exit();
		}
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}

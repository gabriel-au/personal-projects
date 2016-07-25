package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

public class ImageLink extends Componente {

	private String imageFile;
	
	public ImageLink(String imageFile) {
		super();
		this.imageFile=UtilString.getFullPath()+imageFile;
	}

	@Override
	public String getCode() {
		String texto = "<link rel=\"apple-touch-icon\" href=\""+imageFile+"\"/> ";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {

	}

}

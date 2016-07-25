package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

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

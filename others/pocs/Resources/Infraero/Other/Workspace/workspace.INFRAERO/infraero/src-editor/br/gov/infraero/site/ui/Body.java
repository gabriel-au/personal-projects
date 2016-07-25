package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;

/**
 * Tratamento das Regras de Body do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Body extends Componente {

	private boolean updateOrientation;
	public Body() {
		super();
	}
	
	public Body(boolean updateOrientation) {
		super();
		this.updateOrientation=updateOrientation;
	}
	
	
	
	@Override
	public String getCode() {
		String texto="<body";
		if(updateOrientation)
			texto+=" onorientationchange=\"updateOrientation()\"";

		texto+=">";
		texto+=getCodeChild();
		texto+="\n";
		texto+="</body>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(Ul.class);
		itensOpcionais.add(H1.class);
		itensOpcionais.add(H2.class);
		itensOpcionais.add(A.class);
		itensOpcionais.add(Img.class);
		itensOpcionais.add(Div.class);
		itensOpcionais.add(Iframe.class);
		itensOpcionais.add(Br.class);
		itensOpcionais.add(Form.class);
	}



}

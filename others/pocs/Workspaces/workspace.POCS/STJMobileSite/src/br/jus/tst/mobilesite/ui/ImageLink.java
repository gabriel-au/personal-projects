package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class ImageLink extends Componente
{
  private String imageFile;

  public ImageLink(String imageFile)
  {
/* 12 */     this.imageFile = (UtilString.getFullPath() + imageFile);
  }

  public String getCode()
  {
/* 17 */     String texto = "<link rel=\"apple-touch-icon\" href=\"" + this.imageFile + "\"/> ";
/* 18 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.ImageLink
 * JD-Core Version:    0.6.0
 */
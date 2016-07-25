package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Style extends Componente
{
  private String cssFile;

  public Style(String cssFile)
  {
/* 18 */     this.cssFile = (UtilString.getFullPath() + cssFile);
  }

  public String getCode()
  {
/* 23 */     String texto = "<style type=\"text/css\" media=\"screen\">@import \"" + this.cssFile + "\";</style>";
/* 24 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Style
 * JD-Core Version:    0.6.0
 */
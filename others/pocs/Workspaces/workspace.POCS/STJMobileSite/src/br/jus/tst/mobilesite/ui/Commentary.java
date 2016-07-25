package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Commentary extends Componente
{
  private String text;

  public Commentary(String text)
  {
/* 18 */     this.text = text;
  }

  public String getCode()
  {
/* 23 */     String texto = "<!-- " + UtilString.converter(this.text) + " -->";
/* 24 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Commentary
 * JD-Core Version:    0.6.0
 */
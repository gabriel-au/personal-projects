package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Img extends Componente
{
  private String src;
  private String title;

  public Img(String src)
  {
/* 16 */     this.src = (UtilString.getFullPath() + src);
  }

  public Img(String src, String title) {
/* 20 */     this.src = src;
/* 21 */     this.title = title;
  }

  public String getCode()
  {
/* 26 */     String texto = "<img src=\"" + this.src + "\"";
/* 27 */     if (this.title != null)
/* 28 */       texto = texto + " title=\"" + this.title + "\"";
/* 29 */     texto = texto + " />";
/* 30 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Img
 * JD-Core Version:    0.6.0
 */
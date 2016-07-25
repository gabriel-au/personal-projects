package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Link extends Componente
{
  private String rel;
  private String href;
  private String type;

  public Link(String rel, String href, String type)
  {
/* 14 */     this.rel = rel;
/* 15 */     this.href = (UtilString.getFullPath() + href);
/* 16 */     this.type = type;
  }

  public String getCode()
  {
/* 21 */     String texto = "<link ";
/* 22 */     texto = texto + " rel=\"" + this.rel + "\"";
/* 23 */     texto = texto + " href=\"" + this.href + "\"";
/* 24 */     texto = texto + " type=\"" + this.type + "\"";
/* 25 */     texto = texto + " /> ";
/* 26 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Link
 * JD-Core Version:    0.6.0
 */
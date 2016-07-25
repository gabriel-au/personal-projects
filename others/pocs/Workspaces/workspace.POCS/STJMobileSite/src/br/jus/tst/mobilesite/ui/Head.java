package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;
import java.util.List;

public class Head extends Componente
{
  private String titulo;

  public Head(String titulo)
  {
/* 12 */     this.titulo = titulo;
  }

  public String getCode()
  {
/* 17 */     String texto = "<head>";
/* 18 */     texto = texto + "\n";
/* 19 */     texto = texto + "<title>" + UtilString.converter(this.titulo) + "</title>";
/* 20 */     texto = texto + getCodeChild();
/* 21 */     texto = texto + "\n";
/* 22 */     texto = texto + "</head>";
/* 23 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 28 */     this.itensOpcionais.add(Style.class);
/* 29 */     this.itensOpcionais.add(Meta.class);
/* 30 */     this.itensOpcionais.add(ImageLink.class);
/* 31 */     this.itensOpcionais.add(Link.class);
/* 32 */     this.itensOpcionais.add(Script.class);
/* 33 */     this.itensOpcionais.add(ScriptCode.class);
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Head
 * JD-Core Version:    0.6.0
 */
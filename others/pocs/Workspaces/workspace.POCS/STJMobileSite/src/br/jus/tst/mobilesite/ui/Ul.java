package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import java.util.List;

public class Ul extends Componente
{
  public String getCode()
  {
/* 20 */     String texto = "<ul>";
/* 21 */     texto = texto + "\n";
/* 22 */     texto = texto + getCodeChild();
/* 23 */     texto = texto + "\n";
/* 24 */     texto = texto + "</ul>";
/* 25 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 30 */     this.itensObrigatorio.add(Li.class);
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Ul
 * JD-Core Version:    0.6.0
 */
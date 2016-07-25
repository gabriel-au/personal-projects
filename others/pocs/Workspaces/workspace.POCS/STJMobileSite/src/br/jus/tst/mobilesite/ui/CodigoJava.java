package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;

public class CodigoJava extends Componente
{
  private String fonte;

  public CodigoJava(String fonte)
  {
/* 17 */     this.fonte = fonte;
  }

  public String getCode()
  {
/* 22 */     String texto = "<%";
/* 23 */     texto = texto + "\n";
/* 24 */     texto = texto + this.fonte;
/* 25 */     texto = texto + "\n";
/* 26 */     texto = texto + "%>";
/* 27 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.CodigoJava
 * JD-Core Version:    0.6.0
 */
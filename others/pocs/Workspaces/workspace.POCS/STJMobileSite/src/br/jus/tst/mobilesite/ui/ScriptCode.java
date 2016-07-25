package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;

public class ScriptCode extends Componente
{
  private String code;

  public ScriptCode(String code)
  {
/* 17 */     this.code = code;
  }

  public String getCode()
  {
/* 23 */     String texto = "<script type=\"application/x-javascript\">";
/* 24 */     texto = texto + "\n";
/* 25 */     texto = texto + this.code;
/* 26 */     texto = texto + "\n";
/* 27 */     texto = texto + "</script>";
/* 28 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.ScriptCode
 * JD-Core Version:    0.6.0
 */
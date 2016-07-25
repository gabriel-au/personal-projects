package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Option extends Componente
{
  private String value;
  private String name;
  private boolean selected;

  public Option(String name, String value)
  {
/* 19 */     this.name = name;
/* 20 */     this.value = value;
/* 21 */     this.selected = false;
  }

  public Option(String name, String value, boolean selected) {
/* 25 */     this.name = name;
/* 26 */     this.value = value;
/* 27 */     this.selected = selected;
  }

  public String getCode()
  {
/* 33 */     String texto = "<option ";
/* 34 */     texto = texto + " value=\"" + this.name + "\"";
/* 35 */     if (this.selected)
/* 36 */       texto = texto + " SELECTED";
/* 37 */     texto = texto + " >";
/* 38 */     texto = texto + UtilString.converter(this.value) + "</option>";
/* 39 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Option
 * JD-Core Version:    0.6.0
 */
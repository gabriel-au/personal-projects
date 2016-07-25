package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import java.util.List;

public class Select extends Componente
{
  private String clazz;
  private String name;
  private String onchange;

  public Select(String clazz, String name)
  {
/* 19 */     this.clazz = clazz;
/* 20 */     this.name = name;
  }

  public String getCode()
  {
/* 27 */     String texto = "<select";
/* 28 */     texto = texto + " class=\"" + this.clazz + "\"";
/* 29 */     texto = texto + " name=\"" + this.name + "\"";
/* 30 */     if (this.onchange != null)
/* 31 */       texto = texto + " onchange=\"" + this.onchange + "\"";
/* 32 */     texto = texto + ">";
/* 33 */     texto = texto + getCodeChild();
/* 34 */     texto = texto + "</select>";
/* 35 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 40 */     this.itensObrigatorio.add(Option.class);
  }

  public String getClazz()
  {
/* 46 */     return this.clazz;
  }

  public void setClazz(String clazz)
  {
/* 52 */     this.clazz = clazz;
  }

  public String getName()
  {
/* 58 */     return this.name;
  }

  public void setName(String name)
  {
/* 64 */     this.name = name;
  }

  public String getOnchange()
  {
/* 70 */     return this.onchange;
  }

  public void setOnchange(String onchange)
  {
/* 76 */     this.onchange = onchange;
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Select
 * JD-Core Version:    0.6.0
 */
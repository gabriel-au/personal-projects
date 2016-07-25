package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;
import java.util.List;

public class P extends Componente
{
  private String conteudo;
  private String clazz;

  public P(String conteudo)
  {
/* 19 */     this.conteudo = conteudo;
  }

  public P(String conteudo, String clazz) {
/* 23 */     this.conteudo = conteudo;
/* 24 */     this.clazz = clazz;
  }

  public String getCode()
  {
/* 29 */     String texto = "<p";
/* 30 */     if (this.clazz != null)
/* 31 */       texto = texto + " class=\"" + this.clazz + "\"";
/* 32 */     texto = texto + ">";
/* 33 */     texto = texto + UtilString.converter(this.conteudo);
/* 34 */     texto = texto + "</p>";
/* 35 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 40 */     this.itensOpcionais.add(A.class);
/* 41 */     this.itensOpcionais.add(H1.class);
/* 42 */     this.itensOpcionais.add(H2.class);
/* 43 */     this.itensOpcionais.add(Img.class);
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.P
 * JD-Core Version:    0.6.0
 */
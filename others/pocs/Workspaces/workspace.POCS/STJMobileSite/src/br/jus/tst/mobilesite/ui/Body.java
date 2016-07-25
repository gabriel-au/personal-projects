package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import java.util.List;

public class Body extends Componente
{
  private boolean updateOrientation;

  public Body()
  {
  }

  public Body(boolean updateOrientation)
  {
/* 20 */     this.updateOrientation = updateOrientation;
  }

  public String getCode()
  {
/* 27 */     String texto = "<body";
/* 28 */     if (this.updateOrientation) {
/* 29 */       texto = texto + " onorientationchange=\"updateOrientation()\"";
    }
/* 31 */     texto = texto + ">";
/* 32 */     texto = texto + getCodeChild();
/* 33 */     texto = texto + "\n";
/* 34 */     texto = texto + "</body>";
/* 35 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 40 */     this.itensOpcionais.add(Ul.class);
/* 41 */     this.itensOpcionais.add(H1.class);
/* 42 */     this.itensOpcionais.add(H2.class);
/* 43 */     this.itensOpcionais.add(A.class);
/* 44 */     this.itensOpcionais.add(Img.class);
/* 45 */     this.itensOpcionais.add(Div.class);
/* 46 */     this.itensOpcionais.add(Iframe.class);
/* 47 */     this.itensOpcionais.add(Br.class);
/* 48 */     this.itensOpcionais.add(Form.class);
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Body
 * JD-Core Version:    0.6.0
 */
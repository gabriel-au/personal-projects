package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import java.util.ArrayList;
import java.util.List;

public class HTML extends Componente
{
/* 10 */   private List<String> javaImport = new ArrayList();

  public String getCode() {
/* 13 */     String texto = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" ";
/* 14 */     texto = texto + getCodeImport();
/* 15 */     texto = texto + "\n";
/* 16 */     texto = texto + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
/* 17 */     texto = texto + "\n";
/* 18 */     texto = texto + "\n";
/* 19 */     texto = texto + "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
/* 20 */     texto = texto + getCodeChild();
/* 21 */     texto = texto + "\n";
/* 22 */     texto = texto + "</html>";
/* 23 */     return texto;
  }

  public String getCodeImport() {
/* 27 */     String texto = "";
/* 28 */     for (String clazz : this.javaImport) {
/* 29 */       texto = texto + "<%@page import=\"";
/* 30 */       texto = texto + clazz;
/* 31 */       texto = texto + "\"%>";
/* 32 */       texto = texto + "\n";
    }
/* 34 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 39 */     this.itensOpcionais.add(Head.class);
/* 40 */     this.itensOpcionais.add(Body.class);
  }

  public void addJavaImport(String clazz) {
/* 44 */     this.javaImport.add(clazz);
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.HTML
 * JD-Core Version:    0.6.0
 */
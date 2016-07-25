package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;
import java.util.List;

public class A extends Componente
{
  private String clazz;
  private String href;
  private String onclick;
  private boolean fullPath;

  public A(String href, boolean fullPath)
  {
/* 21 */     this.href = href;
/* 22 */     this.fullPath = fullPath;
  }

  public A(String href, boolean fullPath, String clazz)
  {
/* 27 */     this.href = href;
/* 28 */     this.fullPath = fullPath;
/* 29 */     this.clazz = clazz;
  }

  public A(String href, boolean fullPath, String clazz, String onclick) {
/* 33 */     this.href = href;
/* 34 */     this.fullPath = fullPath;
/* 35 */     this.clazz = clazz;
/* 36 */     this.onclick = onclick;
  }

  public String getCode()
  {
/* 41 */     if (this.fullPath) {
/* 42 */       this.href = (UtilString.getFullPath() + this.href);
    }
/* 44 */     String texto = "<a ";
/* 45 */     if (this.href != null)
/* 46 */       texto = texto + "href=\"" + this.href + "\" ";
/* 47 */     if (this.clazz != null)
/* 48 */       texto = texto + "clazz=\"" + this.clazz + "\" ";
/* 49 */     if (this.onclick != null) {
/* 50 */       texto = texto + "onclick=\"" + this.onclick + "\" ";
    }
/* 52 */     texto = texto + ">";
/* 53 */     texto = texto + getCodeChild();
/* 54 */     texto = texto + "</a>";
/* 55 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
/* 60 */     this.itensOpcionais.add(Texto.class);
/* 61 */     this.itensOpcionais.add(Img.class);
/* 62 */     this.itensOpcionais.add(Div.class);
/* 63 */     this.itensOpcionais.add(H1.class);
  }

  public String getClazz() {
/* 67 */     return this.clazz;
  }

  public void setClazz(String clazz) {
/* 71 */     this.clazz = clazz;
  }

  public String getHref() {
/* 75 */     return this.href;
  }

  public void setHref(String href) {
/* 79 */     this.href = href;
  }

  public String getOnclick() {
/* 83 */     return this.onclick;
  }

  public void setOnclick(String onclick) {
/* 87 */     this.onclick = onclick;
  }

  public boolean isFullPath() {
/* 91 */     return this.fullPath;
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.A
 * JD-Core Version:    0.6.0
 */
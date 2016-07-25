package br.jus.tst.mobilesite.ui;

import br.jus.tst.mobilesite.Componente;
import br.jus.tst.mobilesite.util.UtilString;

public class Texto extends Componente
{
  private String conteudo;
  private boolean contemQuebraLinha;

  public Texto(String conteudo)
  {
/* 19 */     this.conteudo = conteudo;
  }

  public Texto(String conteudo, boolean contemQuebraLinha)
  {
/* 24 */     this.conteudo = conteudo;
/* 25 */     this.contemQuebraLinha = contemQuebraLinha;
  }

  public String getCode()
  {
/* 30 */     String texto = UtilString.converter(this.conteudo, this.contemQuebraLinha);
/* 31 */     return texto;
  }

  protected void getMontarItensObrigatoriosOpcionais()
  {
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.ui.Texto
 * JD-Core Version:    0.6.0
 */
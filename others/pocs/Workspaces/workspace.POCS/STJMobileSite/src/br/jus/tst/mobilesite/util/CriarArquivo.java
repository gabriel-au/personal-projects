package br.jus.tst.mobilesite.util;

import br.jus.tst.mobilesite.Componente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo
{
  private static String path;

  public static void salvar(String fileName, Componente componente)
  {
    try
    {
/* 19 */       FileWriter writer = new FileWriter(getPath() + fileName);
/* 20 */       writer.write(componente.toString());
/* 21 */       writer.close();
    } catch (IOException e) {
/* 23 */       e.printStackTrace();
    }
  }

  public static void setPath(String path) {
/* 28 */     path = path;
  }

  public static String getPath() {
/* 32 */     if (path == null) {
/* 33 */       path = "WebContent" + File.separator;
    }
/* 35 */     return path;
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.util.CriarArquivo
 * JD-Core Version:    0.6.0
 */
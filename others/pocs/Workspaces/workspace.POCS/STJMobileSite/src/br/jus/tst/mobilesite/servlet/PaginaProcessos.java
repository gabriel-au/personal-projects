package br.jus.tst.mobilesite.servlet;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.PesquisaProcessual;
import br.jus.stj.mobile.bd.pojo.ProcessualDecisoesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualFasesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualNumerosOrigemPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPartesAdvogadosPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPeticoesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPojo;
import br.jus.stj.mobile.bd.pojo.TipoPesquisaProcesso;
import br.jus.tst.mobilesite.PadraoPaginas;
import br.jus.tst.mobilesite.Padroes;
import br.jus.tst.mobilesite.ui.A;
import br.jus.tst.mobilesite.ui.Body;
import br.jus.tst.mobilesite.ui.Div;
import br.jus.tst.mobilesite.ui.Form;
import br.jus.tst.mobilesite.ui.HTML;
import br.jus.tst.mobilesite.ui.Head;
import br.jus.tst.mobilesite.ui.Img;
import br.jus.tst.mobilesite.ui.Input;
import br.jus.tst.mobilesite.ui.Option;
import br.jus.tst.mobilesite.ui.P;
import br.jus.tst.mobilesite.ui.Script;
import br.jus.tst.mobilesite.ui.Select;
import br.jus.tst.mobilesite.ui.Style;
import br.jus.tst.mobilesite.ui.Texto;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class PaginaProcessos extends PadraoPaginas
{
  public String gerarPagina()
    throws SystemException
  {
/*  37 */     return null;
  }

  public String gerarPagina(String metodo, Map<String, String> listaParametros, HttpSession session)
    throws SystemException
  {
/*  44 */     HTML pagina = new HTML();
/*  45 */     Head head = Padroes.getHeader();
/*  46 */     head.add(new Style("css/styleJurisprudencia.css"));
/*  47 */     head.add(new Script("js/script.js"));
/*  48 */     pagina.add(head);
/*  49 */     Body body = Padroes.getBody();
/*  50 */     pagina.add(body);

/*  53 */     Div div = new Div("titulo");
/*  54 */     A at = new A("/", true);
/*  55 */     at.add(new Img("imagens/botao-inicio-azul.png"));
/*  56 */     div.add(at);
/*  57 */     div.add(new Div("titulo_texto", new Texto("Processos")));
/*  58 */     body.add(div);

/*  61 */     if ((metodo == null) || (metodo.equals("gerarPagina"))) {
/*  62 */       gerarPaginaConsultaProcesso(body, "Processos", TipoPesquisaProcesso.NUMERO, "");
/*  63 */     } else if (metodo.equals("consultar")) {
/*  64 */       String parametro = (String)listaParametros.get("parametro");

/*  66 */       if ((parametro == null) || (parametro.trim().equals(""))) {
/*  67 */         pagina.clear();
/*  68 */         return gerarPaginaMensagem("Processos", 
/*  69 */           "O critério de pesquisa não foi informado.", 
/*  70 */           "Pagina?p=Processos");
/*  72 */       }at.setHref("Pagina?p=Processos");

/*  74 */       String opcaoConsulta = (String)listaParametros.get("opcaoConsulta");
      TipoPesquisaProcesso tipo;
      try { tipo = TipoPesquisaProcesso.valueOf(opcaoConsulta);
      }
      catch (Exception e)
      {
/*  80 */         tipo = TipoPesquisaProcesso.NUMERO;
      }

/*  84 */       gerarPaginaConsultaResultado(body, parametro, tipo);
/*  85 */     } else if (metodo.equals("detalhar")) {
/*  86 */       String parametro = (String)listaParametros.get("parametro");
/*  87 */       at.setHref("Pagina?p=Processos");
/*  88 */       gerarPaginaDetalhar(body, parametro);
/*  89 */     } else if (metodo.equals("listar")) {
/*  90 */       String lista = (String)listaParametros.get("lista");
/*  91 */       if (lista != null) {
/*  92 */         pagina.clear();
/*  93 */         pagina.add(body);
/*  94 */         if (lista.equals("numeroOrigem")) {
/*  95 */           getListaNumeroOrigens(body, lista);
        }
/*  97 */         else if (lista.equals("partesAdvogados")) {
/*  98 */           getListaPartesAdvogados(body, lista);
        }
/* 100 */         else if (lista.equals("peticoes")) {
/* 101 */           getListaPeticoes(body, lista);
        }
/* 103 */         else if (lista.equals("fases")) {
/* 104 */           getListaFases(body, lista);
        }
/* 106 */         else if (lista.equals("decisoes")) {
/* 107 */           getListaDecisoes(body, lista);
        }
        else
/* 110 */           return gerarPaginaErro400();
      }
    }
    else {
/* 114 */       return gerarPaginaErro400();
    }

/* 117 */     return pagina.toString();
  }

  public void gerarPaginaConsultaProcesso(Body body, String metodoAction, TipoPesquisaProcesso selecionado, String texto)
    throws SystemException
  {
/* 123 */     Div divPrincipal = new Div("arredondado");
{
/* 126 */     Div div = new Div("arredondado_titulo");
/* 127 */     div.add(new Texto("Consulta"));
/* 128 */     divPrincipal.add(div);
}
/* 131 */     Form form = new Form("Pagina");
/* 132 */     form.add(new Input("hidden", "p", metodoAction));
/* 133 */     form.add(new Input("hidden", "m", "consultar"));
/* 134 */     form.setMethod("GET");

/* 136 */     form.add(
/* 137 */       new Div("arredondado_chamada", 
/* 137 */       new Texto("Parâmetro de pesquisa:")));
{
/* 140 */     Div div = new Div("arredondado_chamada_simples_center");
/* 141 */     Select lista = new Select("input_select", "opcaoConsulta");
/* 142 */     lista.add(
/* 143 */       new Option(TipoPesquisaProcesso.NUMERO + "", 
/* 143 */       "Número do Processo"));
/* 144 */     lista.add(
/* 145 */       new Option(TipoPesquisaProcesso.NUMERO_REGISTRO + "", 
/* 145 */       "Número de REGISTRO"));
/* 146 */     lista.add(
/* 147 */       new Option(TipoPesquisaProcesso.NUMERO_UNICO + "", 
/* 147 */       "Número Único de Processo (NUP)"));
/* 148 */     lista.add(
/* 149 */       new Option(TipoPesquisaProcesso.NUMERO_ORIGEM + "", 
/* 149 */       "Número do Processo na ORIGEM"));
/* 150 */     lista.add(
/* 151 */       new Option(TipoPesquisaProcesso.OAB + "", 
/* 151 */       "OAB do Advogado"));
/* 152 */     lista.add(
/* 153 */       new Option(TipoPesquisaProcesso.NOME_PARTE + "", 
/* 153 */       "Nome da PARTE"));
/* 154 */     lista.add(
/* 155 */       new Option(TipoPesquisaProcesso.NOME_ADVOGADO + "", 
/* 155 */       "Nome do ADVOGADO"));

/* 157 */     div.add(lista);
/* 158 */     form.add(div);
}
/* 161 */     Div div = new Div("arredondado_chamada_simples_center");
/* 162 */     div.add(
/* 163 */       new Input("text", "parametro", "input_text", 
/* 163 */       texto, "50"));
/* 164 */     div.add(
/* 165 */       new Input("submit", "valor", "input_submit", "", 
/* 165 */       "50"));
/* 166 */     form.add(div);

/* 168 */     form.add(new P(""));
/* 169 */     divPrincipal.add(form);

/* 171 */     body.add(divPrincipal);
  }

  private void gerarPaginaConsultaResultado(Body body, String parametro, TipoPesquisaProcesso tipo) throws SystemException
  {
/* 176 */     PesquisaProcessual cp = new PesquisaProcessual();

/* 178 */     for (ProcessualPojo pojo : cp.consultaPaginaLista(parametro, tipo)) {
/* 179 */       Div div = new Div("arredondado");

/* 181 */       Div d1 = new Div("arredondado_link");
/* 182 */       A a1 = new A("Pagina?p=Processos&m=detalhar&parametro=" + 
/* 183 */         pojo.getNumeroRegistro(), true);
/* 184 */       a1.add(
/* 185 */         new Div("arredondado_chamada_negrito_sem_borda",  new Texto(
"Processo / UF")));;
/* 186 */       a1.add(new Texto(pojo.getProcesso()));
/* 187 */       a1.add(new Texto(" - "));
/* 188 */       a1.add(new Texto(pojo.getUf()));
/* 189 */       d1.add(a1);
/* 190 */       div.add(d1);

/* 193 */       Div d2 = new Div("arredondado_link");
/* 194 */       A a2 = new A("Pagina?p=Processos&m=detalhar&parametro=" + 
/* 195 */         pojo.getNumeroRegistro(), true);
/* 196 */       a2.add(
/* 197 */         new Div("arredondado_chamada_negrito", 
/* 197 */         new Texto("Número de Registro")));
/* 198 */       a2.add(new Texto(pojo.getNumeroRegistro()));
/* 199 */       d2.add(a2);
/* 200 */       div.add(d2);

/* 202 */       div.add(new Div("arredondado_chamada_negrito", new Texto("NUP")));
/* 203 */       if ((pojo.getNup() != null) && (!pojo.getNup().equals("")))
/* 204 */         div.add(new Div("arredondado_chamada_simples", new Texto(pojo.getNup())));
      else {
/* 206 */         div.add(new Div("arredondado_chamada_simples", new Texto("---")));
      }

/* 211 */       div.add(
/* 212 */         new Div("arredondado_chamada_negrito", 
/* 212 */         new Texto("Data de Autuação")));
/* 213 */       div.add(
/* 214 */         new Div("arredondado_chamada_simples", 
/* 214 */         new Texto(pojo
/* 214 */         .getDataAutuacao())));

/* 216 */       div
/* 217 */         .add(new Div("arredondado_chamada_negrito", 
/* 218 */         new Texto("Partes")));
/* 219 */       div.add(
/* 220 */         new Div("arredondado_chamada_simples", 
/* 220 */         new Texto(pojo
/* 220 */         .getParteAutor())));
/* 221 */       div.add(
/* 222 */         new Div("arredondado_chamada_simples_espaco", 
/* 222 */         new Texto(pojo
/* 222 */         .getParteReu())));

/* 224 */       body.add(div);
    }
  }

  private void gerarPaginaDetalhar(Body body, String parametro)
    throws SystemException
  {
/* 231 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 232 */     ProcessualPojo pojo = cp.consultaPaginaDetalhe(parametro);
/* 233 */     Div div = new Div("arredondado");

/* 235 */     div.add(
/* 236 */       new Div("arredondado_chamada_negrito_sem_borda",  new Texto(
"Processo / UF")));
/* 237 */     Div d1 = new Div("arredondado_chamada_simples");
/* 238 */     d1.add(new Texto(pojo.getProcesso()));
/* 239 */     d1.add(new Texto(" - "));
/* 240 */     d1.add(new Texto(pojo.getUf()));
/* 241 */     div.add(d1);

/* 243 */     div.add(
/* 244 */       new Div("arredondado_chamada_negrito", 
/* 244 */       new Texto("Número de Registro")));
/* 245 */     div.add(
/* 246 */       new Div("arredondado_chamada_simples", 
/* 246 */       new Texto(pojo
/* 246 */       .getNumeroRegistro())));

/* 248 */     div.add(new Div("arredondado_chamada_negrito", new Texto("NUP")));
/* 249 */     if ((pojo.getNup() != null) && (!pojo.getNup().equals("")))
/* 250 */       div.add(
/* 251 */         new Div("arredondado_chamada_simples", 
/* 251 */         new Texto(pojo
/* 251 */         .getNup())));
    else {
/* 253 */       div.add(new Div("arredondado_chamada_simples", new Texto("---")));
    }

/* 256 */     div.add(
/* 257 */       new Div("arredondado_chamada_negrito", 
/* 257 */       new Texto("Data de Autuação")));
/* 258 */     div.add(
/* 259 */       new Div("arredondado_chamada_simples", 
/* 259 */       new Texto(pojo
/* 259 */       .getDataAutuacao())));

/* 261 */     div.add(new Div("arredondado_chamada_negrito", new Texto("Autor")));
/* 262 */     div.add(
/* 263 */       new Div("arredondado_chamada_simples", 
/* 263 */       new Texto(pojo
/* 263 */       .getParteAutor())));

/* 265 */     div.add(new Div("arredondado_chamada_negrito", new Texto("Reu")));
/* 266 */     div.add(
/* 267 */       new Div("arredondado_chamada_simples_final", 
/* 267 */       new Texto(pojo
/* 267 */       .getParteReu())));
/* 268 */     body.add(div);

/* 271 */     Div dBotoes = new Div("arredondado_center");

/* 273 */     A a1 = new A(
/* 274 */       "javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=numeroOrigem&parametro=" + 
/* 275 */       pojo.getNumeroRegistro() + "');", false);
/* 276 */     A a2 = new A(
/* 277 */       "javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=partesAdvogados&parametro=" + 
/* 278 */       pojo.getNumeroRegistro() + "');", false);
/* 279 */     A a3 = new A(
/* 280 */       "javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=peticoes&parametro=" + 
/* 281 */       pojo.getNumeroRegistro() + "');", false);
/* 282 */     A a4 = new A(
/* 283 */       "javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=fases&parametro=" + 
/* 284 */       pojo.getNumeroRegistro() + "');", false);
/* 285 */     A a5 = new A(
/* 286 */       "javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=decisoes&parametro=" + 
/* 287 */       pojo.getNumeroRegistro() + "');", false);

/* 289 */     a1.add(new Img("imagens/btn_proc_num_origem.png"));
/* 290 */     a2.add(new Img("imagens/btn_proc_partes_advogados.png"));
/* 291 */     a3.add(new Img("imagens/btn_proc_peticoes.png"));
/* 292 */     a4.add(new Img("imagens/btn_proc_fases.png"));
/* 293 */     a5.add(new Img("imagens/btn_proc_decisoes.png"));
{
/* 295 */     Div d = new Div("arredondado_chamada_simples_center");
/* 296 */     d.add(a1);
/* 297 */     d.add(a2);
/* 298 */     dBotoes.add(d);
}
{
/* 301 */     Div d = new Div("arredondado_chamada_simples_center");
/* 302 */     d.add(a3);
/* 303 */     d.add(a4);
/* 304 */     dBotoes.add(d);
}
{
/* 307 */     Div d = new Div("arredondado_chamada_simples_center");
/* 308 */     d.add(a5);
/* 309 */     dBotoes.add(d);
}
/* 312 */     body.add(dBotoes);

/* 314 */     Div divConteudo = new Div("");
/* 315 */     divConteudo.setId("conteudo");

/* 317 */     body.add(divConteudo);
  }

  public void getListaNumeroOrigens(Body body, String parametro)
    throws SystemException
  {
/* 323 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 324 */     body.clear();

/* 326 */     Div div = new Div("arredondado");

/* 329 */     Iterator localIterator = cp
/* 329 */       .getListaNumeroOrigens(parametro).iterator();

/* 328 */     while (localIterator.hasNext()) {
/* 329 */       ProcessualNumerosOrigemPojo pojo = (ProcessualNumerosOrigemPojo)localIterator.next();

/* 331 */       A a = new A("Pagina?p=Processos&m=detalhar&parametro=" + 
/* 332 */         pojo.getId(), true);
/* 333 */       a.add(new Texto(pojo.getNumero()));

/* 335 */       div.add(new Div("arredondado_link", a));
    }
/* 337 */     body.add(div);
  }

  public void getListaPartesAdvogados(Body body, String parametro) throws SystemException
  {
/* 342 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 343 */     body.clear();
/* 344 */     Div div = new Div("arredondado");

/* 346 */     boolean primeiro = true;
/* 347 */     String style = "arredondado_chamada_negrito_sem_borda";

/* 350 */     Iterator localIterator = cp
/* 350 */       .getListaPartesAdvogados(parametro).iterator();

/* 349 */     while (localIterator.hasNext()) {
/* 350 */       ProcessualPartesAdvogadosPojo pojo = (ProcessualPartesAdvogadosPojo)localIterator.next();

/* 352 */       A a = new A("Pagina?p=Processos&m=detalhar&parametro=" + 
/* 353 */         pojo.getNome(), true);
/* 354 */       a.add(new Texto(pojo.getNome()));

/* 356 */       div.add(new Div(style, new Texto(pojo.getCategoria())));
/* 357 */       div.add(new Div("arredondado_link", a));
/* 358 */       if (primeiro) {
/* 359 */         primeiro = false;
/* 360 */         style = "arredondado_chamada_negrito";
      }
    }

/* 364 */     body.add(div);
  }

  public void getListaPeticoes(Body body, String parametro) throws SystemException
  {
/* 369 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 370 */     body.clear();
/* 371 */     for (ProcessualPeticoesPojo pojo : cp.getListaPeticoes(parametro)) {
/* 372 */       Div div = new Div("arredondado");

/* 374 */       div.add(
/* 375 */         new Div("arredondado_chamada_negrito_sem_borda", 
/* 375 */         new Texto("Número")));
/* 376 */       div.add(
/* 377 */         new Div("arredondado_chamada_simples", 
/* 377 */         new Texto(pojo
/* 377 */         .getNumero())));
/* 378 */       div.add(new Div("arredondado_chamada_negrito", new Texto("Tipo")));
/* 379 */       div.add(
/* 380 */         new Div("arredondado_chamada_simples", 
/* 380 */         new Texto(pojo
/* 380 */         .getTipo())));
/* 381 */       div.add(
/* 382 */         new Div("arredondado_chamada_negrito", 
/* 382 */         new Texto("Peticionário")));
/* 383 */       div.add(
/* 384 */         new Div("arredondado_chamada_simples", 
/* 384 */         new Texto(pojo
/* 384 */         .getPeticionario())));
/* 385 */       div.add(
/* 386 */         new Div("arredondado_chamada_negrito", 
/* 386 */         new Texto("Protocolo")));
/* 387 */       div.add(
/* 388 */         new Div("arredondado_chamada_simples", 
/* 388 */         new Texto(pojo
/* 388 */         .getProtocolo())));
/* 389 */       div.add(
/* 390 */         new Div("arredondado_chamada_negrito", 
/* 390 */         new Texto("Processamento")));
/* 391 */       div.add(
/* 392 */         new Div("arredondado_chamada_simples", 
/* 392 */         new Texto(pojo
/* 392 */         .getProcessamento())));

/* 394 */       body.add(div);
    }
  }

  public void getListaFases(Body body, String parametro) throws SystemException
  {
/* 400 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 401 */     body.clear();
/* 402 */     for (ProcessualFasesPojo pojo : cp.getListaFases(parametro)) {
/* 403 */       Div div = new Div("arredondado");

/* 405 */       Div d1 = new Div("arredondado_chamada_negrito_sem_borda");
/* 406 */       d1.add(new Texto(pojo.getData()));
/* 407 */       d1.add(new Texto(" - "));
/* 408 */       d1.add(new Texto(pojo.getHora()));
/* 409 */       div.add(d1);
/* 410 */       div.add(
/* 411 */         new Div("arredondado_chamada_simples", 
/* 411 */         new Texto(pojo
/* 411 */         .getTexto())));

/* 413 */       body.add(div);
    }
  }

  public void getListaDecisoes(Body body, String parametro) throws SystemException
  {
/* 419 */     PesquisaProcessual cp = new PesquisaProcessual();
/* 420 */     body.clear();
/* 421 */     body.clear();
/* 422 */     for (ProcessualDecisoesPojo pojo : cp.getListaDecisoes(parametro)) {
/* 423 */       Div div = new Div("arredondado");

/* 425 */       A a = new A("Pagina?p=DJEletronico&m=detalhar&parametro=" + 
/* 426 */         pojo.getRegistro(), true);
/* 427 */       a.add(new Texto(pojo.getTexto()));

/* 429 */       div.add(new Div("arredondado_link", a));

/* 431 */       body.add(div);
    }
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.servlet.PaginaProcessos
 * JD-Core Version:    0.6.0
 */
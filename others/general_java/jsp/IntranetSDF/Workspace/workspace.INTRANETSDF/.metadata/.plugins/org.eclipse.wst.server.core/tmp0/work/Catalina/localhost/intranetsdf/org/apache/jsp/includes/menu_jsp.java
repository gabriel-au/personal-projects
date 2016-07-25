package org.apache.jsp.includes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.util.List;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    
    int userPerfil = 0;
    
    if ((session.getAttribute("userPerfil")) != null) {
        Integer.parseInt((String) session.getAttribute("userPerfil"));
    }
    

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"></meta>\r\n");
      out.write("<title>INTRANET CONSORCIO SDF</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("var AgntUsr=navigator.userAgent.toLowerCase();\r\n");
      out.write("var AppVer=navigator.appVersion.toLowerCase();\r\n");
      out.write("var DomYes=document.getElementById?1:0;\r\n");
      out.write("var NavYes=AgntUsr.indexOf('mozilla')!=-1&&AgntUsr.indexOf('compatible')==-1?1:0;\r\n");
      out.write("var ExpYes=AgntUsr.indexOf('msie')!=-1?1:0;\r\n");
      out.write("var Opr=AgntUsr.indexOf('opera')!=-1?1:0;\r\n");
      out.write("var Opr6orless=window.opera && navigator.userAgent.search(/opera.[1-6]/i)!=-1 //DynamicDrive.com added code\r\n");
      out.write("if(Opr){NavYes=1;ExpYes=0;}\r\n");
      out.write("var DomNav=DomYes&&NavYes?1:0;\r\n");
      out.write("var DomExp=DomYes&&ExpYes?1:0;\r\n");
      out.write("var Nav4=NavYes&&!DomYes&&document.layers?1:0;\r\n");
      out.write("var Exp4=ExpYes&&!DomYes&&document.all?1:0;\r\n");
      out.write("var Exp6Plus=(AppVer.indexOf(\"msie 6\")!= -1||AppVer.indexOf(\"msie 7\")!= -1)?1:0\r\n");
      out.write("var PosStrt=(NavYes||ExpYes||Opr)&&!Opr6orless?1:0;\r\n");
      out.write("var P_X=DomYes?\"px\":\"\",FHtml=null,ScHtml=null,FCmplnt=0,SCmplnt=0;\r\n");
      out.write("var FrstLoc,ScLoc,DcLoc;\r\n");
      out.write("var ScWinWdth,ScWinHght,FrstWinWdth,FrstWinHght;\r\n");
      out.write("var ScLdAgainWin;\r\n");
      out.write("var FirstColPos,SecColPos,DocColPos;\r\n");
      out.write("var RcrsLvl=0;\r\n");
      out.write("var FrstCreat=1,Loadd=0,Creatd=0,IniFlg,AcrssFrms=1;\r\n");
      out.write("var FrstCntnr=null,CurrntOvr=null,CloseTmr=null;\r\n");
      out.write("var CntrTxt,TxtClose,ImgStr;\r\n");
      out.write("var Ztop=100;\r\n");
      out.write("var ShwFlg=0;\r\n");
      out.write("var M_StrtTp=StartTop,M_StrtLft=StartLeft;\r\n");
      out.write("var StaticPos=0;\r\n");
      out.write("var M_Hide=Nav4?'hide':'hidden';\r\n");
      out.write("var M_Show=Nav4?'show':'visible';\r\n");
      out.write("var Par=parent.frames[0]&&FirstLineFrame!=SecLineFrame?parent:window;\r\n");
      out.write("var Doc=Par.document;\r\n");
      out.write("var Bod=Doc.body;\r\n");
      out.write("var Trigger=NavYes&&!Opr?Par:Bod;\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("var NoOffFirstLineMenus = 12;                               // Número de itens do primeiro nível do menu\r\n");
      out.write("var LowBgColor='#336699';                                   // Cor do fundo quando mouse não estiver sobre o campo\r\n");
      out.write("var LowSubBgColor='#336699';                                // Cor do fundo quando mouse não estiver sobre os campos de subnivel\r\n");
      out.write("var HighBgColor='#235689';                                  // Cor do fundo quando mouse estiver sobre o campo\r\n");
      out.write("var HighSubBgColor='#235689';                               // Cor do fundo quando mouse estiver sobre campos de subnivel\r\n");
      out.write("var FontLowColor='#FFFFFF';                                 // Cor da fonte quando o mouse não esta sobre o campo\r\n");
      out.write("var FontSubLowColor='#FFFFFF';                              // Cor da fonte dos subniveis quando mouse não estiver sobre os campos\r\n");
      out.write("var FontHighColor='#FFFFFF';                                // Cor da fonte quando o mouse estiver sobre o campo\r\n");
      out.write("var FontSubHighColor='#FFFFFF';                             // Cor da fonte dos subniveis quando o mouse estiver sobre o campo\r\n");
      out.write("var BorderColor='#6699cc';                                  // Cor da borda\r\n");
      out.write("var BorderSubColor='#6699cc';                               // Cor da borda nos subniveis\r\n");
      out.write("var BorderWidth=1;                                          // Largura da borda\r\n");
      out.write("var BorderBtwnElmnts=1;                                     // Borda entre os elementos - 1 e 0\r\n");
      out.write("var FontFamily=\"verdana,arial,comic sans ms,technical\"      // Tipo de fonte dos itens de menu\r\n");
      out.write("var FontSize=8;                                             // Tamanho da fonte dos itens de menu\r\n");
      out.write("var FontBold=0;                                             // Itens do menu em negrito - 1 ou 0\r\n");
      out.write("var FontItalic=0;                                           // Itens do menu em italico - 1 ou 0\r\n");
      out.write("var MenuTextCentered='left';                                // Posicao do texto do item 'left', 'center' ou 'right'\r\n");
      out.write("var MenuCentered='left';                                    // Posicao horizontal do menu 'left', 'center' ou 'right'\r\n");
      out.write("var MenuVerticalCentered='top';                             // Posicao vertical do menu 'top', 'middle','bottom' ou static\r\n");
      out.write("var ChildOverlap=.2;                                        // horizontal sobrepoe filho/pai\r\n");
      out.write("var ChildVerticalOverlap=.2;                                // vertical sobrepoe filho/pai\r\n");
      out.write("var StartTop=100;                                           // coordenada x do menu\r\n");
      out.write("var StartLeft=12;                                           // coordenada y do menu\r\n");
      out.write("var VerCorrect=30;                                          // correcoes multiplas dos frames y \r\n");
      out.write("var HorCorrect=0;                                           // correcoes multiplas dos frames x\r\n");
      out.write("var LeftPaddng=2;                                           // Padding da esquerda\r\n");
      out.write("var TopPaddng=2;                                            // Padding do topo\r\n");
      out.write("var FirstLineHorizontal=0;                                  // SETAR 1 PARA MENU HORIZONTAL, 0 PARA VERTICAL\r\n");
      out.write("var MenuFramesVertical=0;                                   // Frames nas colunas ou linhas - 1 or 0\r\n");
      out.write("var DissapearDelay=500;                                     // Atraso antes que o menu retorne\r\n");
      out.write("var TakeOverBgColor=1;                                      // Frame do menu toma cor do do fundoda framedo subitem\r\n");
      out.write("var FirstLineFrame='space';                                 // Frame onde itens do primeiro nivel aparecem\r\n");
      out.write("var SecLineFrame='space';                                   // Frame onde itens do segundo nivel aparecem\r\n");
      out.write("var DocTargetFrame='';                                      // Frame onde os documentos destino (target) aparecem\r\n");
      out.write("var TargetLoc='';                                           // id do span para posicao relativa\r\n");
      out.write("var HideTop=0;                                              // Esconder o primeiro nível quando carregando novo documento - 1 ou 0\r\n");
      out.write("var MenuWrap=1;                                             // habilita/ desabilita wrap do menu - 1 or 0\r\n");
      out.write("var RightToLeft=0;                                          // habilita/ desabilita desdobramento da direira para esquerda - 1 ou 0\r\n");
      out.write("var UnfoldsOnClick=0;                                       // Nivel 1 desdobra onclick/ onmouseover\r\n");
      out.write("var WebMasterCheck=0;                                       // checagem da arvore do menu ligada ou desligada - 1 or 0\r\n");
      out.write("var ShowArrow=0;                                            // Permite o uso de setas 'gifs' quando 1\r\n");
      out.write("var KeepHilite=1;                                           // Mantem o caminho selecionado marcado\r\n");
      out.write("var Arrws=['tri2.gif',5,5];                                 // Caminho da seta, largura (width) e altura (height)\r\n");
      out.write("\r\n");
      out.write("function BeforeStart(){return}\r\n");
      out.write("function AfterBuild(){return}\r\n");
      out.write("function BeforeFirstOpen(){return}\r\n");
      out.write("function AfterCloseAll(){return}\r\n");
      out.write("\r\n");
      out.write("// itens do menu\r\n");


    String[] linha1 = new String[1];
    linha1[0] = "new Array(\"Página Inicial\",\"main.jsp\",\"\",0,18,180);";
    
    String[] linha2 = new String[1];
    linha2[0] = "new Array(\"Arquivos\",\"archiveList.jsp\",\"\",0,18,180);";
    
    String[] linha11 = new String[1];
    linha11[0] = "new Array(\"Compras\",\"comprasList.jsp\",\"\",0,18,180);";
    
    String[] linha12 = new String[1];
    linha12[0] = "new Array(\"Notas Fiscais\",\"nfAdd.jsp\",\"\",0,18,180);";
    
    String[] linha3 = new String[3];
    linha3[0] = "new Array(\"Ordem de Serviço\",\"OSCall.jsp\",\"\",0,18,180);";
    
    String[] linha4 = new String[7];
    linha4[0] = "new Array(\"Manutenção\",\"\",\"\",6,18,180);";
    linha4[1] = "new Array(\"Periódica\",\"checkLists.jsp\",\"\",0,18,220);";
    linha4[2] = "new Array(\"Coleta\",\"reportList.jsp?relatorioTipoId=1&relatorioTipoNome=Coleta\",\"\",0,18,220);";
    linha4[3] = "new Array(\"Corretiva\",\"reportList.jsp?relatorioTipoId=2&relatorioTipoNome=Corretiva\",\"\",0,18,220);";
    linha4[4] = "new Array(\"DTS\",\"reportList.jsp?relatorioTipoId=3&relatorioTipoNome=DTS\",\"\",0,18,220);";
    linha4[5] = "new Array(\"Geral\",\"reportList.jsp?relatorioTipoId=0&relatorioTipoNome=Geral\",\"\",0,18,220);";
    linha4[6] = "new Array(\"Rede SITRAN\",\"main.jsp\",\"\",0,18,220);";

    String[] linha5 = new String[1];
    linha5[0] = "new Array(\"Equipamentos\",\"equipamentoList.jsp?LF=a\",\"\",0,18,180);";

    String[] linha6 = new String[3];
    linha6[0] = "new Array(\"Usuários\",\"\",\"\",2,18,180);";
    linha6[1] = "new Array(\"Listar\",\"userEdit.jsp\",\"\",0,18,180);";
    linha6[2] = "new Array(\"Adicionar\",\"userAdd.jsp\",\"\",0,18,180);";
    //linha6[3] = "new Array(\"Log\",\"main.jsp\",\"\",0,18,180);";

    String[] linha7 = new String[5];
    linha7[0] = "new Array(\"WEB\",\"\",\"\",4,18,180);";
    linha7[1] = "new Array(\"Câmeras\",\"cameras.jsp\",\"\",0,18,180);";
    linha7[2] = "new Array(\"OCR\",\"main.jsp\",\"\",0,18,180);";
    linha7[3] = "new Array(\"LogMeIn\",\"LogMeInCall.jsp\",\"\",0,18,180);";
    linha7[4] = "new Array(\"Skype\",\"skypeView.jsp\",\"\",0,18,180);";
    
    String[] linha8 = new String[3];
    linha8[0] = "new Array(\"Produção\",\"\",\"\",2,18,180);";
    linha8[1] = "new Array(\"Multas por mês/dia (GEO)\",\"main.jsp\",\"\",0,18,180);";
    linha8[2] = "new Array(\"Multas por ponto\",\"main.jsp\",\"\",0,18,180);";
    
    String[] linha9 = new String[2];
    linha9[0] = "new Array(\"Opções\",\"\",\"\",1,18,180);";
    linha9[1] = "new Array(\"Alterar Senha\",\"userEditPass.jsp\",\"\",0,18,180);";

    String[] linha10 = new String[1];
    linha10[0] = "new Array(\"Sair\",\"logout.jsp\",\"\",0,18,180);";

    // Aqui se define os perfis de usuário
    // Expl: O array > $perfil_1 irá conter os itens visiveis para um determinado usuário
    // o array $perfil_2 já irá conter os itens de um outro tipo de usuário, para cada perfil haverá um array diferente

    //1
    List perfil1 = new ArrayList(); 
    perfil1.add(linha1); 
    perfil1.add(linha2);
    perfil1.add(linha11);
    perfil1.add(linha3);
    perfil1.add(linha12);
    perfil1.add(linha4);
    perfil1.add(linha5);
    perfil1.add(linha7);
    perfil1.add(linha8);
    perfil1.add(linha6);
    perfil1.add(linha9);
    perfil1.add(linha10);

    //2	
    List perfil2 = new ArrayList();
    perfil2.add(linha1); 
    perfil2.add(linha2);
    perfil2.add(linha11);
    perfil2.add(linha3);
    perfil2.add(linha12);
    perfil2.add(linha4);
    perfil2.add(linha9);
    perfil2.add(linha10);

    //3
    List perfil3 = new ArrayList();
    perfil3.add(linha1); 
    perfil3.add(linha2);
    perfil3.add(linha11);
    perfil3.add(linha3);
    perfil3.add(linha4);
    perfil3.add(linha9);
    perfil3.add(linha10);
    
    //dependendo do tipo de login, um determinado menu é chamado
    List perfilDefinido = new ArrayList();
    switch (userPerfil){
        case 0: 
                perfilDefinido = perfil1;
                break;
        case 1: 
                perfilDefinido = perfil1;
                break;
        case 2:
                perfilDefinido = perfil1;
                break;
        case 3:
                perfilDefinido = perfil2;
                break;
        case 4:
                perfilDefinido = perfil2;
                break;
        case 5:
                perfilDefinido = perfil2;
                break;
        case 6:
                perfilDefinido = perfil3;
                break;
        default:
                perfilDefinido = perfil3;
      break;
    }	
	
    String conteudo = "";
    conteudo += "NoOffFirstLineMenus = " + perfilDefinido.size() + ";";
    
    for (int i=0; i < perfilDefinido.size(); i++) {
        String[] menu = (String[]) perfilDefinido.get(i);
        
        for (int x=0; x < menu.length; x++) {
            if (menu[x] != null) {
                conteudo += "\n";
                //conteudo += menu[x];
                
                if (x != 0) {
                    conteudo += "Menu" + (i+1) + "_" + x + "=" + menu[x];
                } else {
                    conteudo += "Menu" + (i+1) + "=" + menu[x];
                }
            }
        }
    }
  
    conteudo += "\n";
	/*for (int i=0; i < tamArray1; i++){ 
		int tamArray2 = arrayItem[i];
		for ($j=0; $j<$tam2; $j++){ 
			$conteudo .=("\n"); 
			$tam3 = sizeof($perfil_definido[$j]);
			for ($z=0; $z<$tam3; $z++){
				$m=$j+1;
					if($z!=0){
						$conteudo .=("Menu".$m."_".$z."=");
					}
					else{
						$conteudo .=("Menu".$m."=");
					}
					$conteudo .=(" ".$arrayItem[$i][$j][$z]."\n");
			}
		} 
		conteudo +=("\n"); 
	} */
  

      out.write("\r\n");
      out.write("\r\n");
      out.print( conteudo );
      out.write("\r\n");
      out.write("\r\n");
      out.write("// fim da definição do menu\r\n");
      out.write("\r\n");
      out.write("MenuTextCentered=MenuTextCentered==1||MenuTextCentered=='center'?'center':MenuTextCentered==0||MenuTextCentered!='right'?'left':'right';\r\n");
      out.write("\r\n");
      out.write("WbMstrAlrts=[\"Item não definido: \",\"Item precisa de altura: \",\"Item precisa de largura:\"];\r\n");
      out.write("\r\n");
      out.write("if(Trigger.onload)Dummy=Trigger.onload;\r\n");
      out.write("Trigger.onload=criaMenu;\r\n");
      out.write("\r\n");
      out.write("function Dummy(){return}\r\n");
      out.write("\r\n");
      out.write("function CnclSlct(){return false}\r\n");
      out.write("\r\n");
      out.write("function RePos(){\r\n");
      out.write("FrstWinWdth=ExpYes?FCmplnt?FHtml.clientWidth:FrstLoc.document.body.clientWidth:FrstLoc.innerWidth;\r\n");
      out.write("FrstWinHght=ExpYes?FCmplnt?FHtml.clientHeight:FrstLoc.document.body.clientHeight:FrstLoc.innerHeight;\r\n");
      out.write("ScWinWdth=ExpYes?SCmplnt?ScHtml.clientWidth:ScLoc.document.body.clientWidth:ScLoc.innerWidth;\r\n");
      out.write("ScWinHght=ExpYes?SCmplnt?ScHtml.clientHeight:ScLoc.document.body.clientHeight:ScLoc.innerHeight;\r\n");
      out.write("if(MenuCentered=='justify'&&FirstLineHorizontal){\r\n");
      out.write("\tFrstCntnr.style.width=FrstWinWdth+P_X;\r\n");
      out.write("\tvar LftXtra=(DomNav&&!Opr)||FCmplnt?LeftPaddng:0;\r\n");
      out.write("\tClcJus();\r\n");
      out.write("\tvar P=FrstCntnr.FrstMbr,W=Menu1[5],i;\r\n");
      out.write("\tfor(i=0;i<NoOffFirstLineMenus;i++){P.style.width=W+P_X;P=P.PrvMbr}}\r\n");
      out.write("StaticPos=-1;\r\n");
      out.write("if(TargetLoc)ClcTrgt();\r\n");
      out.write("if(MenuCentered)ClcLft();\r\n");
      out.write("if(MenuVerticalCentered)ClcTp();\r\n");
      out.write("PosMenu(FrstCntnr,StartTop,StartLeft)}\r\n");
      out.write("\r\n");
      out.write("function UnLoaded(){\r\n");
      out.write("if(CloseTmr)clearTimeout(CloseTmr);\r\n");
      out.write("Loadd=0; Creatd=0;\r\n");
      out.write("if(HideTop){\r\n");
      out.write("\tvar FCStyle=Nav4?FrstCntnr:FrstCntnr.style;\r\n");
      out.write("\tFCStyle.visibility=M_Hide}}\r\n");
      out.write("\r\n");
      out.write("function ReDoWhole(){\r\n");
      out.write("if(ScWinWdth!=ScLoc.innerWidth||ScWinHght!=ScLoc.innerHeight||FrstWinWdth!=FrstLoc.innerWidth||FrstWinHght!=FrstLoc.innerHeight)Doc.location.reload()}\r\n");
      out.write("\r\n");
      out.write("function Check(WMnu,NoOf){\r\n");
      out.write("var i,array,ArrayLoc;\r\n");
      out.write("ArrayLoc=parent.frames[0]?parent.frames[FirstLineFrame]:self;\r\n");
      out.write("for(i=0;i<NoOf;i++){\r\n");
      out.write("\tarray=WMnu+eval(i+1);\r\n");
      out.write("\tif(!ArrayLoc[array]){WbMstrAlrt(0,array); return false}\r\n");
      out.write("\tif(i==0){\tif(!ArrayLoc[array][4]){WbMstrAlrt(1,array); return false}\r\n");
      out.write("\t\tif(!ArrayLoc[array][5]){WbMstrAlrt(2,array); return false}}\r\n");
      out.write("\tif(ArrayLoc[array][3])if(!Check(array+'_',ArrayLoc[array][3])) return false}\r\n");
      out.write("return true}\r\n");
      out.write("\r\n");
      out.write("function WbMstrAlrt(No,Xtra){\r\n");
      out.write("return confirm(WbMstrAlrts[No]+Xtra+'   ')}\r\n");
      out.write("// SecLineFrame='space';\t\t\tFrame onde os sub itens aparecem DocTargetFrame='corpo';\r\n");
      out.write("function criaMenu(){\r\n");
      out.write("Dummy();\r\n");
      out.write("if(Loadd||!PosStrt)return;\r\n");
      out.write("BeforeStart();\r\n");
      out.write("Creatd=0; Loadd=1;\r\n");
      out.write("status='Carregando o menu';\r\n");
      out.write("if(FirstLineFrame ==\"\" || !parent.frames[FirstLineFrame]){\r\n");
      out.write("\tFirstLineFrame=SecLineFrame;\r\n");
      out.write("\tif(FirstLineFrame ==\"\" || !parent.frames[FirstLineFrame]){\r\n");
      out.write("\t\tFirstLineFrame=SecLineFrame\r\n");
      out.write("\t\tif(FirstLineFrame ==\"\" || !parent.frames[FirstLineFrame])FirstLineFrame=SecLineFrame=''}}\r\n");
      out.write("if(SecLineFrame ==\"\" || !parent.frames[SecLineFrame]){\r\n");
      out.write("\tSecLineFrame=SecLineFrame;\r\n");
      out.write("\tif(SecLineFrame ==\"\" || !parent.frames[SecLineFrame])SecLineFrame=FirstLineFrame}\r\n");
      out.write("if(WebMasterCheck){\tif(!Check('Menu',NoOffFirstLineMenus)){status='build aborted';return}}\r\n");
      out.write("FrstLoc=FirstLineFrame!=\"\"?parent.frames[FirstLineFrame]:window;\r\n");
      out.write("ScLoc=SecLineFrame!=\"\"?parent.frames[SecLineFrame]:window;\r\n");
      out.write("DcLoc=DocTargetFrame!=\"\"?parent.frames[DocTargetFrame]:window;\r\n");
      out.write("if (FrstLoc==ScLoc) AcrssFrms=0;\r\n");
      out.write("if (AcrssFrms)FirstLineHorizontal=MenuFramesVertical?0:1;\r\n");
      out.write("if(Exp6Plus||Opr){\r\n");
      out.write("\tFHtml=FrstLoc.document.getElementsByTagName(\"HTML\")[0];ScHtml=ScLoc.document.getElementsByTagName(\"HTML\")[0];\r\n");
      out.write("\tFCmplnt=FrstLoc.document.compatMode.indexOf(\"CSS\")==-1?0:1;SCmplnt=ScLoc.document.compatMode.indexOf(\"CSS\")==-1?0:1}\r\n");
      out.write("FrstWinWdth=ExpYes?FCmplnt?FHtml.clientWidth:FrstLoc.document.body.clientWidth:FrstLoc.innerWidth;\r\n");
      out.write("FrstWinHght=ExpYes?FCmplnt?FHtml.clientHeight:FrstLoc.document.body.clientHeight:FrstLoc.innerHeight;\r\n");
      out.write("ScWinWdth=ExpYes?SCmplnt?ScHtml.clientWidth:ScLoc.document.body.clientWidth:ScLoc.innerWidth;\r\n");
      out.write("ScWinHght=ExpYes?SCmplnt?ScHtml.clientHeight:ScLoc.document.body.clientHeight:ScLoc.innerHeight;\r\n");
      out.write("if(Nav4){\tCntrTxt=MenuTextCentered!='left'?\"<div align='\"+MenuTextCentered+\"'>\":\"\";\r\n");
      out.write("\tTxtClose=\"</font>\"+MenuTextCentered!='left'?\"</div>\":\"\"}\r\n");
      out.write("FirstColPos=Nav4?FrstLoc.document:FrstLoc.document.body;\r\n");
      out.write("SecColPos=Nav4?ScLoc.document:ScLoc.document.body;\r\n");
      out.write("DocColPos=Nav4?DcLoc.document:ScLoc.document.body;\r\n");
      out.write("if (TakeOverBgColor)FirstColPos.bgColor=AcrssFrms?SecColPos.bgColor:DocColPos.bgColor;\r\n");
      out.write("if(MenuCentered=='justify'&&FirstLineHorizontal)ClcJus();\r\n");
      out.write("if(FrstCreat){\r\n");
      out.write("\tFrstCntnr=CreateMenuStructure('Menu',NoOffFirstLineMenus);\r\n");
      out.write("\tFrstCreat=AcrssFrms?0:1}\r\n");
      out.write("else CreateMenuStructureAgain('Menu',NoOffFirstLineMenus);\r\n");
      out.write("if(TargetLoc)ClcTrgt();\r\n");
      out.write("if(MenuCentered)ClcLft();\r\n");
      out.write("if(MenuVerticalCentered)ClcTp();\r\n");
      out.write("PosMenu(FrstCntnr,StartTop,StartLeft);\r\n");
      out.write("IniFlg=1;\r\n");
      out.write("Initiate();\r\n");
      out.write("Creatd=1;\r\n");
      out.write("if (AcrssFrms){\r\n");
      out.write("\tScLdAgainWin=ExpYes?ScLoc.document.body:ScLoc;\r\n");
      out.write("\tScLdAgainWin.onunload=UnLoaded}\r\n");
      out.write("Trigger.onresize=Nav4?ReDoWhole:RePos;\r\n");
      out.write("AfterBuild();\r\n");
      out.write("if(MenuVerticalCentered=='static'&&!AcrssFrms)setInterval('KeepPos()',0);\r\n");
      out.write("status='CONSORCIO SITRAN-DATAPROM-FISCAL DF LTDA'}\r\n");
      out.write("\r\n");
      out.write("function KeepPos(){\r\n");
      out.write("var TS=ExpYes?SCmplnt?ScHtml.scrollTop:FrstLoc.document.body.scrollTop:FrstLoc.pageYOffset;\r\n");
      out.write("if(TS!=StaticPos){\r\n");
      out.write("\tvar FCStyle=Nav4?FrstCntnr:FrstCntnr.style;\r\n");
      out.write("\tFrstCntnr.OrgTop=StartTop+TS;StaticPos=TS;\r\n");
      out.write("\tFCStyle.top=FrstCntnr.OrgTop+P_X}}\r\n");
      out.write("\r\n");
      out.write("function ClcJus(){\r\n");
      out.write("var a=BorderBtwnElmnts?1:2,b=BorderBtwnElmnts?BorderWidth:0;\r\n");
      out.write("var Size=Math.round(((FrstWinWdth-a*BorderWidth)/NoOffFirstLineMenus)-b),i,j;\r\n");
      out.write("for(i=1;i<NoOffFirstLineMenus+1;i++){j=eval('Menu'+i);j[5]=Size}\r\n");
      out.write("StartLeft=0}\r\n");
      out.write("\r\n");
      out.write("function ClcTrgt(){\r\n");
      out.write("var TLoc=Nav4?FrstLoc.document.layers[TargetLoc]:DomYes?FrstLoc.document.getElementById(TargetLoc):FrstLoc.document.all[TargetLoc];\r\n");
      out.write("StartTop=M_StrtTp;\r\n");
      out.write("StartLeft=M_StrtLft;\r\n");
      out.write("if(DomYes){\r\n");
      out.write("\twhile(TLoc){StartTop+=TLoc.offsetTop;StartLeft+=TLoc.offsetLeft;TLoc=TLoc.offsetParent}}\r\n");
      out.write("else{\tStartTop+=Nav4?TLoc.pageY:TLoc.offsetTop;StartLeft+=Nav4?TLoc.pageX:TLoc.offsetLeft}}\r\n");
      out.write("\r\n");
      out.write("function ClcLft(){\r\n");
      out.write("if(MenuCentered!='left'&&MenuCentered!='justify'){\r\n");
      out.write("\tvar Size=FrstWinWdth-(!Nav4?parseInt(FrstCntnr.style.width):FrstCntnr.clip.width);\r\n");
      out.write("\tStartLeft=M_StrtLft;\r\n");
      out.write("\tStartLeft+=MenuCentered=='right'?Size:Size/2}}\r\n");
      out.write("\r\n");
      out.write("function ClcTp(){\r\n");
      out.write("if(MenuVerticalCentered!='top'&&MenuVerticalCentered!='static'){\r\n");
      out.write("\tvar Size=FrstWinHght-(!Nav4?parseInt(FrstCntnr.style.height):FrstCntnr.clip.height);\r\n");
      out.write("\tStartTop=M_StrtTp;\r\n");
      out.write("\tStartTop+=MenuVerticalCentered=='top'?Size:Size/2}}\r\n");
      out.write("\r\n");
      out.write("function PosMenu(CntnrPntr,Tp,Lt){\r\n");
      out.write("RcrsLvl++;\r\n");
      out.write("var Cmplnt=RcrsLvl==1?FCmplnt:SCmplnt;\r\n");
      out.write("var LftXtra=(DomNav&&!Opr)||Cmplnt?LeftPaddng:0;\r\n");
      out.write("var TpXtra=(DomNav&&!Opr)||Cmplnt?TopPaddng:0;\r\n");
      out.write("var Topi,Lefti,Hori;\r\n");
      out.write("var Cntnr=CntnrPntr;\r\n");
      out.write("var Mmbr=Cntnr.FrstMbr;\r\n");
      out.write("var CntnrStyle=!Nav4?Cntnr.style:Cntnr;\r\n");
      out.write("var MmbrStyle=!Nav4?Mmbr.style:Mmbr;\r\n");
      out.write("var PadL=Mmbr.value.indexOf('<')==-1?LftXtra:0;\r\n");
      out.write("var PadT=Mmbr.value.indexOf('<')==-1?TpXtra:0;\r\n");
      out.write("var MmbrWt=!Nav4?parseInt(MmbrStyle.width)+PadL:MmbrStyle.clip.width;\r\n");
      out.write("var MmbrHt=!Nav4?parseInt(MmbrStyle.height)+PadT:MmbrStyle.clip.height;\r\n");
      out.write("var CntnrWt=!Nav4?parseInt(CntnrStyle.width):CntnrStyle.clip.width;\r\n");
      out.write("var CntnrHt=!Nav4?parseInt(CntnrStyle.height):CntnrStyle.clip.height;\r\n");
      out.write("var SubTp,SubLt;\r\n");
      out.write("if (RcrsLvl==1 && AcrssFrms)!MenuFramesVertical?Tp=FrstWinHght-CntnrHt+(Nav4?4:0):Lt=RightToLeft?0:FrstWinWdth-CntnrWt+(Nav4?4:0);\r\n");
      out.write("if (RcrsLvl==2 && AcrssFrms)!MenuFramesVertical?Tp=0:Lt=RightToLeft?ScWinWdth-CntnrWt:0;\r\n");
      out.write("if (RcrsLvl==2 && AcrssFrms){Tp+=VerCorrect;Lt+=HorCorrect}\r\n");
      out.write("CntnrStyle.top=RcrsLvl==1?Tp+P_X:0;\r\n");
      out.write("Cntnr.OrgTop=Tp;\r\n");
      out.write("CntnrStyle.left=RcrsLvl==1?Lt+P_X:0;\r\n");
      out.write("Cntnr.OrgLeft=Lt;\r\n");
      out.write("if (RcrsLvl==1 && FirstLineHorizontal){\r\n");
      out.write("\tHori=1;Lefti=CntnrWt-MmbrWt-2*BorderWidth;Topi=0}\r\n");
      out.write("else{\tHori=Lefti=0;Topi=CntnrHt-MmbrHt-2*BorderWidth}\r\n");
      out.write("while(Mmbr!=null){\r\n");
      out.write("\tMmbrStyle.left=Lefti+BorderWidth+P_X;\r\n");
      out.write("\tMmbrStyle.top=Topi+BorderWidth+P_X;\r\n");
      out.write("\tif(Nav4)Mmbr.CmdLyr.moveTo(Lefti+BorderWidth,Topi+BorderWidth);\r\n");
      out.write("\tif(Mmbr.ChildCntnr){\r\n");
      out.write("\t\tif(RightToLeft)ChldCntnrWdth=Nav4?Mmbr.ChildCntnr.clip.width:parseInt(Mmbr.ChildCntnr.style.width);\r\n");
      out.write("\t\tif(Hori){\tSubTp=Topi+MmbrHt+2*BorderWidth;\r\n");
      out.write("\t\t\tSubLt=RightToLeft?Lefti+MmbrWt-ChldCntnrWdth:Lefti}\r\n");
      out.write("\t\telse{\tSubLt=RightToLeft?Lefti-ChldCntnrWdth+ChildOverlap*MmbrWt+BorderWidth:Lefti+(1-ChildOverlap)*MmbrWt+BorderWidth;\r\n");
      out.write("\t\t\tSubTp=RcrsLvl==1&&AcrssFrms?Topi:Topi+ChildVerticalOverlap*MmbrHt}\r\n");
      out.write("\t\tPosMenu(Mmbr.ChildCntnr,SubTp,SubLt)}\r\n");
      out.write("\tMmbr=Mmbr.PrvMbr;\r\n");
      out.write("\tif(Mmbr){\tMmbrStyle=!Nav4?Mmbr.style:Mmbr;\r\n");
      out.write("\t\tPadL=Mmbr.value.indexOf('<')==-1?LftXtra:0;\r\n");
      out.write("\t\tPadT=Mmbr.value.indexOf('<')==-1?TpXtra:0;\r\n");
      out.write("\t\tMmbrWt=!Nav4?parseInt(MmbrStyle.width)+PadL:MmbrStyle.clip.width;\r\n");
      out.write("\t\tMmbrHt=!Nav4?parseInt(MmbrStyle.height)+PadT:MmbrStyle.clip.height;\r\n");
      out.write("\t\tHori?Lefti-=BorderBtwnElmnts?(MmbrWt+BorderWidth):(MmbrWt):Topi-=BorderBtwnElmnts?(MmbrHt+BorderWidth):(MmbrHt)}}\r\n");
      out.write("RcrsLvl--}\r\n");
      out.write("\r\n");
      out.write("function Initiate(){\r\n");
      out.write("if(IniFlg){\tInit(FrstCntnr);IniFlg=0;\r\n");
      out.write("\tif(ShwFlg)AfterCloseAll();ShwFlg=0}}\r\n");
      out.write("\r\n");
      out.write("function Init(CntnrPntr){\r\n");
      out.write("var Mmbr=CntnrPntr.FrstMbr;\r\n");
      out.write("var MCStyle=Nav4?CntnrPntr:CntnrPntr.style;\r\n");
      out.write("RcrsLvl++;\r\n");
      out.write("MCStyle.visibility=RcrsLvl==1?M_Show:M_Hide;\r\n");
      out.write("while(Mmbr!=null){\r\n");
      out.write("\tif(Mmbr.Hilite){Mmbr.Hilite=0;if(KeepHilite)LowItem(Mmbr)}\r\n");
      out.write("\tif(Mmbr.ChildCntnr) Init(Mmbr.ChildCntnr);\r\n");
      out.write("\tMmbr=Mmbr.PrvMbr}\r\n");
      out.write("RcrsLvl--}\r\n");
      out.write("\r\n");
      out.write("function ClearAllChilds(Pntr){\r\n");
      out.write("var CPCCStyle;\r\n");
      out.write("while (Pntr){\r\n");
      out.write("\tif(Pntr.Hilite){\r\n");
      out.write("\t\tPntr.Hilite=0;\r\n");
      out.write("\t\tif(KeepHilite)LowItem(Pntr);\r\n");
      out.write("\t\tif(Pntr.ChildCntnr){\r\n");
      out.write("\t\t\tCPCCStyle=Nav4?Pntr.ChildCntnr:Pntr.ChildCntnr.style;\r\n");
      out.write("\t\t\tCPCCStyle.visibility=M_Hide;\r\n");
      out.write("\t\t\tClearAllChilds(Pntr.ChildCntnr.FrstMbr)}\r\n");
      out.write("\t\tbreak}\r\n");
      out.write("\tPntr=Pntr.PrvMbr}}\r\n");
      out.write("\r\n");
      out.write("function GoTo(){\r\n");
      out.write("if(this.LinkTxt){\r\n");
      out.write("\tstatus='';\r\n");
      out.write("\tvar HP=Nav4?this.LowLyr:this;\r\n");
      out.write("\tLowItem(HP);\r\n");
      out.write("\tthis.LinkTxt.indexOf('javascript:')!=-1?eval(this.LinkTxt):DcLoc.location.href=this.LinkTxt}}\r\n");
      out.write("\r\n");
      out.write("function HiliteItem(P){\r\n");
      out.write("if(Nav4){\r\n");
      out.write("\tif(P.ro)P.document.images[P.rid].src=P.ri2;\r\n");
      out.write("\telse{\tif(P.HiBck)P.bgColor=P.HiBck;\r\n");
      out.write("\t\tif(P.value.indexOf('<img')==-1){\r\n");
      out.write("\t\t\tP.document.write(P.Ovalue);\r\n");
      out.write("\t\t\tP.document.close()}}}\r\n");
      out.write("else{\tif(P.ro){\tvar Lc=P.Level==1?FrstLoc:ScLoc;\r\n");
      out.write("\t\tLc.document.images[P.rid].src=P.ri2}\r\n");
      out.write("\telse{\tif(P.HiBck)P.style.backgroundColor=P.HiBck;\r\n");
      out.write("\t\tif(P.HiFntClr)P.style.color=P.HiFntClr}}\r\n");
      out.write("P.Hilite=1}\r\n");
      out.write("\r\n");
      out.write("function LowItem(P){\r\n");
      out.write("if(P.ro){\tif(Nav4)P.document.images[P.rid].src=P.ri1;\r\n");
      out.write("\telse{\tvar Lc=P.Level==1?FrstLoc:ScLoc;\r\n");
      out.write("\t\tLc.document.images[P.rid].src=P.ri1}}\r\n");
      out.write("else{\tif(Nav4){\tif(P.LoBck)P.bgColor=P.LoBck;\r\n");
      out.write("\t\tif(P.value.indexOf('<img')==-1){\r\n");
      out.write("\t\t\tP.document.write(P.value);\r\n");
      out.write("\t\t\tP.document.close()}}\r\n");
      out.write("\telse{\tif(P.LoBck)P.style.backgroundColor=P.LoBck;\r\n");
      out.write("\t\tif(P.LwFntClr)P.style.color=P.LwFntClr}}}\r\n");
      out.write("\r\n");
      out.write("function OpenMenu(){\t\r\n");
      out.write("if(!Loadd||!Creatd) return;\r\n");
      out.write("var TpScrlld=ExpYes?SCmplnt?ScHtml.scrollTop:ScLoc.document.body.scrollTop:ScLoc.pageYOffset;\r\n");
      out.write("var LScrlld=ExpYes?SCmplnt?ScHtml.scrollLeft:ScLoc.document.body.scrollLeft:ScLoc.pageXOffset;\r\n");
      out.write("var CCnt=Nav4?this.LowLyr.ChildCntnr:this.ChildCntnr;\r\n");
      out.write("var ThisHt=Nav4?this.clip.height:parseInt(this.style.height);\r\n");
      out.write("var ThisWt=Nav4?this.clip.width:parseInt(this.style.width);\r\n");
      out.write("var ThisLft=AcrssFrms&&this.Level==1&&!FirstLineHorizontal?0:Nav4?this.Container.left:parseInt(this.Container.style.left);\r\n");
      out.write("var ThisTp=AcrssFrms&&this.Level==1&&FirstLineHorizontal?0:Nav4?this.Container.top:parseInt(this.Container.style.top);\r\n");
      out.write("var HP=Nav4?this.LowLyr:this;\r\n");
      out.write("CurrntOvr=this;\r\n");
      out.write("IniFlg=0;\r\n");
      out.write("ClearAllChilds(this.Container.FrstMbr);\r\n");
      out.write("HiliteItem(HP);\r\n");
      out.write("if(CCnt!=null){\r\n");
      out.write("\tif(!ShwFlg){ShwFlg=1;\tBeforeFirstOpen()}\r\n");
      out.write("\tvar CCW=Nav4?this.LowLyr.ChildCntnr.clip.width:parseInt(this.ChildCntnr.style.width);\r\n");
      out.write("\tvar CCH=Nav4?this.LowLyr.ChildCntnr.clip.height:parseInt(this.ChildCntnr.style.height);\r\n");
      out.write("\tvar ChCntTL=Nav4?this.LowLyr.ChildCntnr:this.ChildCntnr.style;\r\n");
      out.write("\tvar SubLt=AcrssFrms&&this.Level==1?CCnt.OrgLeft+ThisLft+LScrlld:CCnt.OrgLeft+ThisLft;\r\n");
      out.write("\tvar SubTp=AcrssFrms&&this.Level==1?CCnt.OrgTop+ThisTp+TpScrlld:CCnt.OrgTop+ThisTp;\r\n");
      out.write("\tif(MenuWrap){\r\n");
      out.write("\t\tif(RightToLeft){\r\n");
      out.write("\t\t\tif(SubLt<LScrlld)SubLt=this.Level==1?LScrlld:SubLt+(CCW+(1-2*ChildOverlap)*ThisWt);\r\n");
      out.write("\t\t\tif(SubLt+CCW>ScWinWdth+LScrlld)SubLt=ScWinWdth+LScrlld-CCW}\r\n");
      out.write("\t\telse{\tif(SubLt+CCW>ScWinWdth+LScrlld)SubLt=this.Level==1?ScWinWdth+LScrlld-CCW:SubLt-(CCW+(1-2*ChildOverlap)*ThisWt);\r\n");
      out.write("\t\t\tif(SubLt<LScrlld)SubLt=LScrlld}\r\n");
      out.write("\t\tif(SubTp+CCH>TpScrlld+ScWinHght)SubTp=this.Level==1?SubTp=TpScrlld+ScWinHght-CCH:SubTp-CCH+(1-2*ChildVerticalOverlap)*ThisHt;\r\n");
      out.write("\t\tif(SubTp<TpScrlld)SubTp=TpScrlld}\r\n");
      out.write("\tChCntTL.top=SubTp+P_X;ChCntTL.left=SubLt+P_X;ChCntTL.visibility=M_Show}\r\n");
      out.write("status=this.LinkTxt}\r\n");
      out.write("\r\n");
      out.write("function OpenMenuClick(){\r\n");
      out.write("if(!Loadd||!Creatd) return;\r\n");
      out.write("var HP=Nav4?this.LowLyr:this;\r\n");
      out.write("CurrntOvr=this;\r\n");
      out.write("IniFlg=0;\r\n");
      out.write("ClearAllChilds(this.Container.FrstMbr);\r\n");
      out.write("HiliteItem(HP);\r\n");
      out.write("status=this.LinkTxt}\r\n");
      out.write("\r\n");
      out.write("function CloseMenu(){\r\n");
      out.write("if(!Loadd||!Creatd) return;\r\n");
      out.write("if(!KeepHilite){\r\n");
      out.write("\tvar HP=Nav4?this.LowLyr:this;\r\n");
      out.write("\tLowItem(HP)}\r\n");
      out.write("status='';\r\n");
      out.write("if(this==CurrntOvr){\r\n");
      out.write("\tIniFlg=1;\r\n");
      out.write("\tif(CloseTmr)clearTimeout(CloseTmr);\r\n");
      out.write("\tCloseTmr=setTimeout('Initiate(CurrntOvr)',DissapearDelay)}}\r\n");
      out.write("\r\n");
      out.write("function CntnrSetUp(Wdth,Hght,NoOff){\r\n");
      out.write("var x=RcrsLvl==1?BorderColor:BorderSubColor;\r\n");
      out.write("this.FrstMbr=null;\r\n");
      out.write("this.OrgLeft=this.OrgTop=0;\r\n");
      out.write("if(x)this.bgColor=x;\r\n");
      out.write("if(Nav4){\tthis.visibility='hide';\r\n");
      out.write("\tthis.resizeTo(Wdth,Hght)}\r\n");
      out.write("else{\tif(x)this.style.backgroundColor=x;\r\n");
      out.write("\tthis.style.width=Wdth+P_X;\r\n");
      out.write("\tthis.style.height=Hght+P_X;\r\n");
      out.write("\tthis.style.fontFamily=FontFamily;\r\n");
      out.write("\tthis.style.fontWeight=FontBold?'bold':'normal';\r\n");
      out.write("\tthis.style.fontStyle=FontItalic?'italic':'normal';\r\n");
      out.write("\tthis.style.fontSize=FontSize+'pt';\r\n");
      out.write("\tthis.style.zIndex=RcrsLvl+Ztop}}\r\n");
      out.write("\r\n");
      out.write("function MbrSetUp(MmbrCntnr,PrMmbr,WhatMenu,Wdth,Hght){\r\n");
      out.write("var Location=RcrsLvl==1?FrstLoc:ScLoc;\r\n");
      out.write("var MemVal=eval(WhatMenu+'[0]');\r\n");
      out.write("var t,T,L,W,H,S;\r\n");
      out.write("var a,b,c,d;\r\n");
      out.write("var Cmplnt=RcrsLvl==1?FCmplnt:SCmplnt;\r\n");
      out.write("var LftXtra=(DomNav&&!Opr)||Cmplnt?LeftPaddng:0;\r\n");
      out.write("var TpXtra=(DomNav&&!Opr)||Cmplnt?TopPaddng:0;\r\n");
      out.write("this.PrvMbr=PrMmbr;\r\n");
      out.write("this.Level=RcrsLvl;\r\n");
      out.write("this.LinkTxt=eval(WhatMenu+'[1]');\r\n");
      out.write("this.Container=MmbrCntnr;\r\n");
      out.write("this.ChildCntnr=null;\r\n");
      out.write("this.Hilite=0;\r\n");
      out.write("this.style.overflow='hidden';\r\n");
      out.write("this.style.cursor=ExpYes&&(this.LinkTxt||(RcrsLvl==1&&UnfoldsOnClick))?'hand':'default';\r\n");
      out.write("this.ro=0;\r\n");
      out.write("if(MemVal.indexOf('rollover')!=-1){\r\n");
      out.write("\tthis.ro=1;\r\n");
      out.write("\tthis.ri1=MemVal.substring(MemVal.indexOf(':')+1,MemVal.lastIndexOf(':'));\r\n");
      out.write("\tthis.ri2=MemVal.substring(MemVal.lastIndexOf(':')+1,MemVal.length);\r\n");
      out.write("\tthis.rid=WhatMenu+'i';\r\n");
      out.write("\tMemVal=\"<img src=\\\"\"+this.ri1+\"\\\" name=\\\"\"+this.rid+\"\\\" width=\\\"\"+Wdth+\"\\\" height=\\\"\"+Hght+\"\\\">\"}\r\n");
      out.write("this.value=MemVal;\r\n");
      out.write("if(RcrsLvl==1){\r\n");
      out.write("\ta=LowBgColor;\r\n");
      out.write("\tb=HighBgColor;\r\n");
      out.write("\tc=FontLowColor;\r\n");
      out.write("\td=FontHighColor}\r\n");
      out.write("else{\ta=LowSubBgColor;\r\n");
      out.write("\tb=HighSubBgColor;\r\n");
      out.write("\tc=FontSubLowColor;\r\n");
      out.write("\td=FontSubHighColor}\r\n");
      out.write("this.LoBck=a;\r\n");
      out.write("this.LwFntClr=c;\r\n");
      out.write("this.HiBck=b;\r\n");
      out.write("this.HiFntClr=d;\r\n");
      out.write("this.style.color=this.LwFntClr;\r\n");
      out.write("if(this.LoBck)this.style.backgroundColor=this.LoBck;\r\n");
      out.write("this.style.textAlign=MenuTextCentered;\r\n");
      out.write("if(eval(WhatMenu+'[2]'))this.style.backgroundImage=\"url(\\'\"+eval(WhatMenu+'[2]')+\"\\')\";\r\n");
      out.write("if(MemVal.indexOf('<')==-1){\r\n");
      out.write("\tthis.style.width=Wdth-LftXtra+P_X;\r\n");
      out.write("\tthis.style.height=Hght-TpXtra+P_X;\r\n");
      out.write("\tthis.style.paddingLeft=LeftPaddng+P_X;\r\n");
      out.write("\tthis.style.paddingTop=TopPaddng+P_X}\r\n");
      out.write("else{\tthis.style.width=Wdth+P_X;\r\n");
      out.write("\tthis.style.height=Hght+P_X}\r\n");
      out.write("if(MemVal.indexOf('<')==-1&&DomYes){\r\n");
      out.write("\tt=Location.document.createTextNode(MemVal);\r\n");
      out.write("\tthis.appendChild(t)}\r\n");
      out.write("else this.innerHTML=MemVal;\r\n");
      out.write("if(eval(WhatMenu+'[3]')&&ShowArrow){\r\n");
      out.write("\ta=RcrsLvl==1&&FirstLineHorizontal?3:RightToLeft?6:0;\r\n");
      out.write("\tS=Arrws[a];\r\n");
      out.write("\tW=Arrws[a+1];\r\n");
      out.write("\tH=Arrws[a+2];\r\n");
      out.write("\tT=RcrsLvl==1&&FirstLineHorizontal?Hght-H-2:(Hght-H)/2;\r\n");
      out.write("\tL=RightToLeft?2:Wdth-W-2;\r\n");
      out.write("\tif(DomYes){\r\n");
      out.write("\r\n");
      out.write("\t\tt=Location.document.createElement('img');\r\n");
      out.write("\t\tthis.appendChild(t);\r\n");
      out.write("\t\tt.style.position='absolute';\r\n");
      out.write("\t\tt.src=S;\r\n");
      out.write("\r\n");
      out.write("\t\tt.style.width=W+P_X;\r\n");
      out.write("\t\tt.style.height=H+P_X;\r\n");
      out.write("\t\tt.style.top=T+P_X;\r\n");
      out.write("\t\tt.style.left=L+P_X}\r\n");
      out.write("\telse{\tMemVal+=\"<div style='position:absolute; top:\"+T+\"; left:\"+L+\"; width:\"+W+\"; height:\"+H+\";visibility:inherit'><img src='\"+S+\"'></div>\";\r\n");
      out.write("\t\tthis.innerHTML=MemVal}}\r\n");
      out.write("if(ExpYes){this.onselectstart=CnclSlct;\r\n");
      out.write("\tthis.onmouseover=RcrsLvl==1&&UnfoldsOnClick?OpenMenuClick:OpenMenu;\r\n");
      out.write("\tthis.onmouseout=CloseMenu;\r\n");
      out.write("\tthis.onclick=RcrsLvl==1&&UnfoldsOnClick&&eval(WhatMenu+'[3]')?OpenMenu:GoTo\t}\r\n");
      out.write("else{\tRcrsLvl==1&&UnfoldsOnClick?this.addEventListener('mouseover',OpenMenuClick,false):this.addEventListener('mouseover',OpenMenu,false);\r\n");
      out.write("\tthis.addEventListener('mouseout',CloseMenu,false);\r\n");
      out.write("\tRcrsLvl==1&&UnfoldsOnClick&&eval(WhatMenu+'[3]')?this.addEventListener('click',OpenMenu,false):this.addEventListener('click',GoTo,false)}}\r\n");
      out.write("\r\n");
      out.write("function NavMbrSetUp(MmbrCntnr,PrMmbr,WhatMenu,Wdth,Hght){\r\n");
      out.write("var a,b,c,d;\r\n");
      out.write("if(RcrsLvl==1){\r\n");
      out.write("\ta=LowBgColor;\r\n");
      out.write("\tb=HighBgColor;\r\n");
      out.write("\tc=FontLowColor;\r\n");
      out.write("\td=FontHighColor}\r\n");
      out.write("else {\ta=LowSubBgColor;\r\n");
      out.write("\tb=HighSubBgColor;\r\n");
      out.write("\tc=FontSubLowColor;\r\n");
      out.write("\td=FontSubHighColor\t}\r\n");
      out.write("this.value=eval(WhatMenu+'[0]');\r\n");
      out.write("this.ro=0;\r\n");
      out.write("if(this.value.indexOf('rollover')!=-1){\r\n");
      out.write("\tthis.ro=1;\r\n");
      out.write("\tthis.ri1=this.value.substring(this.value.indexOf(':')+1,this.value.lastIndexOf(':'));\r\n");
      out.write("\tthis.ri2=this.value.substring(this.value.lastIndexOf(':')+1,this.value.length);\r\n");
      out.write("\tthis.rid=WhatMenu+'i';this.value=\"<img src='\"+this.ri1+\"' name='\"+this.rid+\"'>\"}\r\n");
      out.write("if(LeftPaddng&&this.value.indexOf('<')==-1&&MenuTextCentered=='left')this.value='&nbsp\\;'+this.value;\r\n");
      out.write("if(FontBold)this.value=this.value.bold();\r\n");
      out.write("if(FontItalic)this.value=this.value.italics();\r\n");
      out.write("this.Ovalue=this.value;\r\n");
      out.write("this.value=this.value.fontcolor(c);\r\n");
      out.write("this.Ovalue=this.Ovalue.fontcolor(d);\r\n");
      out.write("this.value=CntrTxt+\"<font face='\"+FontFamily+\"' point-size='\"+FontSize+\"'>\"+this.value+TxtClose;\r\n");
      out.write("this.Ovalue=CntrTxt+\"<font face='\"+FontFamily+\"' point-size='\"+FontSize+\"'>\"+this.Ovalue+TxtClose;\r\n");
      out.write("this.LoBck=a;\r\n");
      out.write("this.HiBck=b;\r\n");
      out.write("this.ChildCntnr=null;\r\n");
      out.write("this.PrvMbr=PrMmbr;\r\n");
      out.write("this.Hilite=0;\r\n");
      out.write("this.visibility='inherit';\r\n");
      out.write("if(this.LoBck)this.bgColor=this.LoBck;\r\n");
      out.write("this.resizeTo(Wdth,Hght);\r\n");
      out.write("if(!AcrssFrms&&eval(WhatMenu+'[2]'))this.background.src=eval(WhatMenu+'[2]');\r\n");
      out.write("this.document.write(this.value);\r\n");
      out.write("this.document.close();\r\n");
      out.write("this.CmdLyr=new Layer(Wdth,MmbrCntnr);\r\n");
      out.write("this.CmdLyr.Level=RcrsLvl;\r\n");
      out.write("this.CmdLyr.LinkTxt=eval(WhatMenu+'[1]');\r\n");
      out.write("this.CmdLyr.visibility='inherit';\r\n");
      out.write("this.CmdLyr.onmouseover=RcrsLvl==1&&UnfoldsOnClick?OpenMenuClick:OpenMenu;\r\n");
      out.write("this.CmdLyr.onmouseout=CloseMenu;\r\n");
      out.write("this.CmdLyr.captureEvents(Event.MOUSEUP);\r\n");
      out.write("this.CmdLyr.onmouseup=RcrsLvl==1&&UnfoldsOnClick&&eval(WhatMenu+'[3]')?OpenMenu:GoTo;\r\n");
      out.write("this.CmdLyr.LowLyr=this;\r\n");
      out.write("this.CmdLyr.resizeTo(Wdth,Hght);\r\n");
      out.write("this.CmdLyr.Container=MmbrCntnr;\r\n");
      out.write("if(eval(WhatMenu+'[3]')&&ShowArrow){\r\n");
      out.write("\ta=RcrsLvl==1&&FirstLineHorizontal?3:RightToLeft?6:0;\r\n");
      out.write("\tthis.CmdLyr.ImgLyr=new Layer(Arrws[a+1],this.CmdLyr);\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.visibility='inherit';\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.top=RcrsLvl==1&&FirstLineHorizontal?Hght-Arrws[a+2]-2:(Hght-Arrws[a+2])/2;\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.left=RightToLeft?2:Wdth-Arrws[a+1]-2;\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.width=Arrws[a+1];\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.height=Arrws[a+2];\r\n");
      out.write("\tImgStr=\"<img src='\"+Arrws[a]+\"' width='\"+Arrws[a+1]+\"' height='\"+Arrws[a+2]+\"'>\";\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.document.write(ImgStr);\r\n");
      out.write("\tthis.CmdLyr.ImgLyr.document.close()}}\r\n");
      out.write("\r\n");
      out.write("function CreateMenuStructure(MName,NumberOf){\r\n");
      out.write("RcrsLvl++;\r\n");
      out.write("var i,NoOffSubs,Mbr,Wdth=0,Hght=0;\r\n");
      out.write("var PrvMmbr=null;\r\n");
      out.write("var WMnu=MName+'1';\r\n");
      out.write("var MenuWidth=eval(WMnu+'[5]');\r\n");
      out.write("var MenuHeight=eval(WMnu+'[4]');\r\n");
      out.write("var Location=RcrsLvl==1?FrstLoc:ScLoc;\r\n");
      out.write("if (RcrsLvl==1&&FirstLineHorizontal){\r\n");
      out.write("\tfor(i=1;i<NumberOf+1;i++){\r\n");
      out.write("\t\tWMnu=MName+eval(i);\r\n");
      out.write("\t\tWdth=eval(WMnu+'[5]')?Wdth+eval(WMnu+'[5]'):Wdth+MenuWidth}\r\n");
      out.write("\tWdth=BorderBtwnElmnts?Wdth+(NumberOf+1)*BorderWidth:Wdth+2*BorderWidth;Hght=MenuHeight+2*BorderWidth}\r\n");
      out.write("else{\tfor(i=1;i<NumberOf+1;i++){\r\n");
      out.write("\t\tWMnu=MName+eval(i);\r\n");
      out.write("\t\tHght=eval(WMnu+'[4]')?Hght+eval(WMnu+'[4]'):Hght+MenuHeight}\r\n");
      out.write("\tHght=BorderBtwnElmnts?Hght+(NumberOf+1)*BorderWidth:Hght+2*BorderWidth;Wdth=MenuWidth+2*BorderWidth}\r\n");
      out.write("if(DomYes){\r\n");
      out.write("\tvar MmbrCntnr=Location.document.createElement(\"div\");\r\n");
      out.write("\tMmbrCntnr.style.position='absolute';\r\n");
      out.write("\tMmbrCntnr.style.visibility='hidden';\r\n");
      out.write("\tLocation.document.body.appendChild(MmbrCntnr)}\r\n");
      out.write("else{\tif(Nav4) var MmbrCntnr=new Layer(Wdth,Location)\r\n");
      out.write("\telse{\tWMnu+='c';\r\n");
      out.write("\t\tLocation.document.body.insertAdjacentHTML(\"AfterBegin\",\"<div id='\"+WMnu+\"' style='visibility:hidden; position:absolute;'><\\/div>\");\r\n");
      out.write("\t\tvar MmbrCntnr=Location.document.all[WMnu]}}\r\n");
      out.write("MmbrCntnr.SetUp=CntnrSetUp;\r\n");
      out.write("MmbrCntnr.SetUp(Wdth,Hght,NumberOf);\r\n");
      out.write("if(Exp4){\tMmbrCntnr.InnerString='';\r\n");
      out.write("\tfor(i=1;i<NumberOf+1;i++){\r\n");
      out.write("\t\tWMnu=MName+eval(i);\r\n");
      out.write("\t\tMmbrCntnr.InnerString+=\"<div id='\"+WMnu+\"' style='position:absolute;'><\\/div>\"}\r\n");
      out.write("\tMmbrCntnr.innerHTML=MmbrCntnr.InnerString}\r\n");
      out.write("for(i=1;i<NumberOf+1;i++){\r\n");
      out.write("\tWMnu=MName+eval(i);\r\n");
      out.write("\tNoOffSubs=eval(WMnu+'[3]');\r\n");
      out.write("\tWdth=RcrsLvl==1&&FirstLineHorizontal?eval(WMnu+'[5]')?eval(WMnu+'[5]'):MenuWidth:MenuWidth;\r\n");
      out.write("\tHght=RcrsLvl==1&&FirstLineHorizontal?MenuHeight:eval(WMnu+'[4]')?eval(WMnu+'[4]'):MenuHeight;\r\n");
      out.write("\tif(DomYes){\r\n");
      out.write("\t\tMbr=Location.document.createElement(\"div\");\r\n");
      out.write("\t\tMbr.style.position='absolute';\r\n");
      out.write("\t\tMbr.style.visibility='inherit';\r\n");
      out.write("\t\tMmbrCntnr.appendChild(Mbr)}\r\n");
      out.write("\telse Mbr=Nav4?new Layer(Wdth,MmbrCntnr):Location.document.all[WMnu];\r\n");
      out.write("\tMbr.SetUp=Nav4?NavMbrSetUp:MbrSetUp;\r\n");
      out.write("\tMbr.SetUp(MmbrCntnr,PrvMmbr,WMnu,Wdth,Hght);\r\n");
      out.write("\tif(NoOffSubs) Mbr.ChildCntnr=CreateMenuStructure(WMnu+'_',NoOffSubs);\r\n");
      out.write("\tPrvMmbr=Mbr}\r\n");
      out.write("MmbrCntnr.FrstMbr=Mbr;\r\n");
      out.write("RcrsLvl--;\r\n");
      out.write("return(MmbrCntnr)}\r\n");
      out.write("\r\n");
      out.write("function CreateMenuStructureAgain(MName,NumberOf){\r\n");
      out.write("var i,WMnu,NoOffSubs,PrvMmbr,Mbr=FrstCntnr.FrstMbr;\r\n");
      out.write("RcrsLvl++;\r\n");
      out.write("for(i=NumberOf;i>0;i--){\r\n");
      out.write("\tWMnu=MName+eval(i);\r\n");
      out.write("\tNoOffSubs=eval(WMnu+'[3]');\r\n");
      out.write("\tPrvMmbr=Mbr;\r\n");
      out.write("\tif(NoOffSubs)Mbr.ChildCntnr=CreateMenuStructure(WMnu+'_',NoOffSubs);\r\n");
      out.write("\tMbr=Mbr.PrvMbr}\r\n");
      out.write("RcrsLvl--}\r\n");
      out.write("\r\n");
      out.write("</script>\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

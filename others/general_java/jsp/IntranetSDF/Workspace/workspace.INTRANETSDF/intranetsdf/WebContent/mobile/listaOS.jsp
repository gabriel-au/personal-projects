<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.com.consorciosdf.intranet.modelo.OS"%>
<jsp:useBean id="osVerify" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioAtual" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="br.com.consorciosdf.intranet.modelo.Usuario"%><html>
<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>

<%
	List<OS> listaOS = (List<OS>) request.getSession().getAttribute("listaOSMobile");
	
	int numberRegistros = 0;
	if(listaOS != null){
		numberRegistros = listaOS.size();
	}

	usuarioAtual = (Usuario) request.getSession().getAttribute("usuarioFilter");
	Date dataNow = new Date();
	long dataTimeNow = dataNow.getTime();
	long dataVerificador = 1 * 24 * 60 * 60 * 1000; //1 dias * 24 horas * 60 minutos * 60 segundos * 1000 milisegundos
	long dataVerifyStatus = dataTimeNow - dataVerificador;

	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
	int numberPage =  (Integer)request.getSession().getAttribute("numberPage");
	int pageStart = (Integer)request.getSession().getAttribute("pageStart");
	
	String pageControl = "<table><tr>";
	String pageControlA = "<br>";
		int printlast = 0;
		if(pageStart > 1){
			pageControl += "<td valign='middle'><a href=../controlaListaOS?page=1><img src='../imagens/first.gif' border='0'></a></td>";
			pageControl += "<td valign='middle'><a href=../controlaListaOS?page="+ (pageStart-1) +"><img src='../imagens/back.gif' border='0'></a></td>";
			
			for(int i = (pageStart-4) ; i <= pageStart; i++){
				if(i > 0){
					if(i == pageStart){
						pageControl += "<td valign='bottom'><b>"+i+"</b></td>";								
					} else {
						pageControl += "<td valign='bottom'><a href=../controlaListaOS?page="+ i +">&nbsp;"+i+"&nbsp;</a></td>";
					}
					printlast ++;
				}
			}
		} else{
			pageControl += "<td valign='bottom'><b>1</b></td>";
			printlast++;
		}
		
		for(int i = 1; i <= (9 - printlast); i++){
			int pageNext = pageStart + i;
			if(pageNext <= numberPage){
				pageControl += "<td valign='bottom'><a href=../controlaListaOS?page="+ pageNext +">&nbsp;"+pageNext+"&nbsp;</a></td>";				
			}
		}
		
		if(numberPage > 9 && numberPage != pageStart){
			pageControl += "<td valign='middle'><a href=../controlaListaOS?page="+ (pageStart+1) +"><img src='../imagens/next.gif' border='0'></a></td>";
			/* proximas 10
			if(numberPage-pageStart > 10){
				pageControlA += "<a href=../controlaListaOS?page="+(9+pageStart)+">Próximas 10 ></a>&nbsp;&nbsp;";
			}*/
			if(pageStart != numberPage){
				pageControl += "<td valign='middle'><a href=../controlaListaOS?page="+numberPage+"><img src='../imagens/last.gif' border='0'></a></td>";	
			}
			
		}
	
	
	
	
	
	Calendar dataNowC = Calendar.getInstance();
	int horaNow = dataNowC.get(Calendar.HOUR_OF_DAY);
	String saudacao = "";
	
    if (horaNow >= 0 && horaNow <= 11) {
        saudacao = "Bom dia, ";
    } else if (horaNow >= 12 && horaNow <= 17) {
        saudacao = "Boa tarde, ";
    } else if (horaNow >= 18 && horaNow <= 23) {
        saudacao = "Boa noite, ";
    }
    saudacao += usuarioAtual.getNomeUsuario() +" "+ usuarioAtual.getSobrenomeUsuario();
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SDF MOBILE</title>
<script language="javascript" type="text/javascript" src="../scripts/default.js"></script>
<link rel="stylesheet" type="text/css" href="../estilos/default.css">

</head>
<body onload="proximo_segundo();">

<jsp:include page="../includes/header2.jsp"></jsp:include>
<form method="get" action="../controlaListaOS">
	<center>
			<table width="100%">
				<tr>
					<td class="Title" width="250">&nbsp;<%=saudacao %></td>
					<td width="60" align="right" class="Title"><%= new SimpleDateFormat("HH:mm:ss").format(new Date())%></td>
				</tr>
				<tr>
					<td height="1px" bgcolor="black" colspan="2"></td>
				</tr>
				<tr>
					<td width="15"></td>
				</tr>
				<tr>
					 <td colspan="3" align="center"><%=pageControl%></td>
				</tr>
				<tr>
					<td width="15"></td>
				</tr>
			</table>
<table cellpadding="0" cellspacing="0" width="100%">
	
	<%
	
	int aux = 0;
	if(listaOS != null){
	for(OS ordem:listaOS){
		equipamento = ordem.getEquipamento();
		usuarioAutor = ordem.getUsuarioAutor();
		usuarioDest = ordem.getUsuarioDest();
		String css = "TableResultBody1";
		if(++aux % 2 == 0){
			css = "TableResultBody1";
		} else {
			css = "TableResultBody2";
		}
		String status = ordem.getStatus();
		String statusFechamento = "";
		String statusStyle = "";
		long dataVerifyAbertura = ordem.getDataAbertura().getTime();
		if(status != null){		
		if (status.equals("ab") && dataVerifyAbertura < dataVerifyStatus) {
            statusFechamento = "ABERTO";
            statusStyle = "OSWarning";
        } else if (status.equals("ab")) {
            statusFechamento = "ABERTO";
            statusStyle = "OSOpen";
        } else if (status.equals("an")) {
            statusFechamento = "ANÁLISE";
            statusStyle = "OSWorking";
        } else if (status.equals("fc")) {
            statusFechamento = "FECHADO";
            statusStyle = "OSClose";
        } else if (status.equals("ag")) {
            statusFechamento = "AGUARDANDO";
            statusStyle = "OSStandby";
        }
		}
		
		String prioridadeOS = "";
		String prioridadeOSStyle = "";
		
		 if (ordem.getPrioridade() == 0) {
             prioridadeOS = "Alta";
             prioridadeOSStyle = "OSPrioridadeAlta";
         } else if (ordem.getPrioridade() == 1) {
             prioridadeOS = "Média";
             prioridadeOSStyle = "OSPrioridadeMedia";
         } else if (ordem.getPrioridade() == 2) {
             prioridadeOS = "Baixa";
             prioridadeOSStyle = "OSPrioridadeBaixa";
         }
		
	%>
	<tr>
		<td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>
			<center>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="Title" width="27">&nbsp;Nº: </td>
					<td width="50"><a class="LinkTitle" href="viewOS.jsp?view=<%=ordem.getId() %>&page=<%=pageStart %>"><%= ordem.getId() + ordem.getTipoOS().toUpperCase()  %></a></td>
					<td class="Title" width="45">Equip: </td>
					<td width="55"><a class="LinkTitle" href="viewOS.jsp?view=<%=ordem.getId() %>&page=<%=pageStart %>"><%= equipamento.getNomeEquipamento()%></a></td>
					<td class="Title" width="67">Abertura: </td>
					<td width="80"><a class="LinkTitle" href="viewOS.jsp?view=<%=ordem.getId() %>&page=<%=pageStart %>"><%if(ordem.getDataAbertura() == null){
										out.print("00/00/0000");
									}else{
										out.print(format.format(ordem.getDataAbertura()));
									}%></a>
					</td>
				</tr>
			</table>
			</center>
		</td>
	</tr>
	<tr class="<%= css%>">
		<td><center>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="Title" width="80">Solicitante:</td>
					<td width="90"><%= usuarioAutor.getNomeUsuario()%></td>
					<td class="Title" width="80">Status:</td>
					<td><span class="<%= statusStyle%>"><%= statusFechamento%></span></td>

				</tr>
				<tr>
					<td class="Title">Tecnico:</td>
					<td><%= usuarioDest.getNomeUsuario()%></td>					
					<td class="Title">Prioridade:</td>
					<td><span class="<%= prioridadeOSStyle%>"><%= prioridadeOS%></span></td>
				</tr>
			</table>
			</center>
		</td>
	</tr>

	<%} }else{ out.print("<b>Nenhuma ordem de Serviço</b>");} %>
	<tr>
		<td height="10"></td>
	</tr>
	<tr>
		<td align="center"><%=pageControl%></td>
	</tr>
</table>
<table align="right">
<tr><td height="10"></td></tr>
<tr>
	<td><a href="logout.jsp"><img alt="Sair" border="0" src="../imagens/logoutMobile.gif"></a></td>
	<td width="10"></td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td height="15"></td>
	</tr>
            
           <tr>
       <td colspan="2" class="Footer">
           <table>
               <tr>
                   <td align="center">
                           <!-- include do rodapÃ© -->
                           <jsp:include page="../includes/footer.jsp" />
                           <!-- fim do include do rodapÃ© -->
                   </td>
               </tr>
           </table>
       </td>
   </tr>
</table>
</center>
</form>
</body>
</html>
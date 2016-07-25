<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	List<Usuario> listSkype = new ManterUserControl().listUsers();
 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="br.com.consorciosdf.intranet.modelo.Usuario"%>
<%@page import="br.com.consorciosdf.intranet.controle.ManterUserControl"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTRANET CONSORCIO SDF</title>
<link rel="stylesheet" type="text/css" href="estilos/default.css">
<script language="javascript" type="text/javascript" src="scripts/default.js"></script>
</head>
<jsp:include page="includes/header.jsp"></jsp:include>
<jsp:include page="includes/menu.jsp"></jsp:include>
<jsp:useBean id="skype" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"></jsp:useBean>
<body>
	<table width="600" border="0" cellspacing="0" cellpadding="0">
    	<tr>
        	<td width="220">&nbsp;</td>
            <td width="380" height="180" colspan="2" valign="top">
            	<table width="380" border="0" cellspacing="0" cellpadding="0">
                	<tr>
                    	<td class="Title">
                        	<table width='380' cellspacing='0' cellpadding='0' border='0'>
                            	<tr>
                                	<td class="Title" width="300">Contatos - Skype</td>
                                   	<td width="80" align="right">
                                            <!--  <a href="main.jsp"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>-->
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="1" bgcolor="#000000"></td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                        	<td>
                        		<table width="380" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td width="159.5px" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Nome</td>
                                        <td width="1px" class="TableResultTitle" style='border-bottom: 1px solid #000000;'></td>
                                        <td width="20px" class="TableResultTitle"style='border-bottom: 1px solid #000000;'></td>
                                        <td width="179.5px" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Usuário</td>
                                    </tr>
                                    <% int cssCont = 0;
                                    for(int i = 0; i < listSkype.size();i++){
                                    	skype = listSkype.get(i);
                                    	if(skype.getSkype() != null){
                                    		String css = "";
                                        	if(cssCont%2 == 0){
                                        		css = "TableResultBody1";
                                        	}else{
                                        		css = "TableResultBody2";
                                        	}%>
                                        	<tr class="<%=css %>" height="10px">
                                    			<td height="18" align="right"><img src="imagens/topico.gif" width="15" height="15"></td>
                                    			<td><%= skype.getNomeUsuario() + " " + skype.getSobrenomeUsuario() %></td>
                                    			<td width="1" bgcolor="#000000"></td>
                                    			<td>&nbsp;</td>
                                    			<td><%=skype.getSkype() %></td>
                                    		</tr>
                                    <%    	
                                    	cssCont++;
                                    	}
									}
                                    %>
                        		</table>
                        	</td>
                        </tr>                       
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
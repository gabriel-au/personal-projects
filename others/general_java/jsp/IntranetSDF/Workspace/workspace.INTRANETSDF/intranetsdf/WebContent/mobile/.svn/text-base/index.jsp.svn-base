<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String erro = request.getParameter("erro");
if(erro != null){
    if (erro.equals("1")) {
        erro = "Favor digitar os campos corretamente.";
    } else if (erro.equals("2")) {
        erro = "Usuário e/ou senha incorretos.";
    } else if (erro.equals("3")) {
        erro = "Página não autorizada.";
    } else if (erro.equals("4")) {
        erro = "Área restrita, favor efetuar o Login.";
    }
} else {
	erro = "";
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SDF MOBILE</title>
<link rel="stylesheet" type="text/css" href="../estilos/default.css">
</head>
<body>
<jsp:include page="../includes/header2.jsp"></jsp:include>
<center>
<form method="post" action="../contraLogin">
<table>
	<tr>
		<td class="Title" width="70" align="right">Usuário:</td>
		<td align="center"><input type="text" name="user" size="23" class="Input"></td>
		<td colspan="2" rowspan="2" align="left" height="30px"><input type="submit" value="Entrar" id="Entrar"></td>
	</tr>
	<tr>
		<td class="Title" align="right">Senha:</td>
		<td align="center"><input type="password" name="pass" size="23" class="Input"></td>
	</tr>
	<tr>
			<td colspan="3" align="center" class="Error"><%= erro %></td>
	</tr>
    <tr>
    	<td height="5"></td>
    </tr>
             
    <tr>
    	<td colspan="3" class="Footer">
           <table>
               <tr>
                   <td align="center">
                           <!-- include do rodapé -->
                           <jsp:include page="../includes/footer.jsp" />
                           <!-- fim do include do rodapé -->
                   </td>
               </tr>
           </table>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
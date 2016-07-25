<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Lista de Perfis de Acesso</span> Ultima atualização 22/04/2010</div>
<div id="tabela">
<table>
<tr>
	<th>Situação</th>
	<th>Código</th>
	<th>Nome</th>
	<th colspan="2"></th>
</tr>
<%int i = 0; %>
<s:iterator value="lista">
<s:url id="editar" action="editar" namespace="/perfil">
	<s:param name="pojo.id" value="id"></s:param>
</s:url>
<tr <%i++; if(i%2 == 0){%>class="alt"<%} %>>
	<td><s:property value="id"/> </td>
	<td><s:property value="nome"/> </td>
	<td><s:a href="%{editar}"><img border="0" src="<s:url value='/imagens/edit32.png' encode='false' includeParams='none'/>" alt="Editar" /></s:a></td>
</tr>
</s:iterator>
</table>
</div>

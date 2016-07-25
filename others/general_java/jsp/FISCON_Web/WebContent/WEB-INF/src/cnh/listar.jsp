<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:url id="novo" action="editar" namespace="/atualizacao">
	<s:param name="pojo.id" value="0"></s:param>
</s:url>
<div class="underbanner2"><span>Atualizacao</span> Ultima atualização 22/04/2010</div>
<div id="tabela">
<table>
<tr>
	<th>Descrição</th>
	<th colspan="2"></th>
</tr>
<%int i = 0; %>
<s:iterator value="lista">
<s:url id="editar" action="editar" namespace="/atualizacao">
	<s:param name="pojo.id" value="id"></s:param>
</s:url>
<s:url id="excluir" action="excluir" namespace="/atualizacao">
	<s:param name="pojo.id" value="id"/>
</s:url>
<tr <%i++; if(i%2 == 0){%>class="alt"<%} %>>
	<td><s:property value="descricao"/> </td>
	<td><s:a href="%{editar}"><img border="0" src="<s:url value='/imagens/edit32.png' encode='false' includeParams='none'/>" alt="Editar" /></s:a></td>
	<td><s:a href="%{excluir}"><img border="0" src="<s:url value='/imagens/excluir.png' encode='false' includeParams='none'/>" alt="Excluir" /></s:a></td>
</tr>
</s:iterator>
</table>
<s:a href="%{novo}"><img border="0" src="<s:url value='/imagens/novo.png' encode='false' includeParams='none'/>" alt="Novo Registro"/></s:a>
</div>
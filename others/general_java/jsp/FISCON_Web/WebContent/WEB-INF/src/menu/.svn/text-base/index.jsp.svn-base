<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Ultimas Atualizações</span></div>
<div id="tabela">
<table>
<%int i = 0; %>
<s:iterator value="listaAtualizacao">
<tr <%i++; if(i%2 == 0){%>class="alt"<%} %>>
	<td><b><s:text name="formatar.data">
			<s:param name="value" value="dataInclusao"/>
		</s:text></b></td>
	<td><s:property value="descricao"/> </td>
</tr>
</s:iterator>
</table>
</div>
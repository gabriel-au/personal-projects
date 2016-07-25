<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Lista de Auto de Infração</span> Ultima atualização 22/04/2010</div>
<div id="tabela">
<table>
<tr>
	<th>Data</th>
	<th>Codigo</th>
	<th>Quantidade Registros</th>
</tr>
<%int i = 0; %>
<s:iterator value="listaChave">
<tr <%i++; if(i%2 == 0){%>class="alt"<%} %>>
	<td><s:text name="formatar.dataCompleta">
			<s:param name="value" value="data"/>
		</s:text></td>
	<td><s:property value="codigo"/> </td>
	<td align="right"><s:property value="quantidade"/> </td>
</tr>
</s:iterator>
</table>



<s:if test="%{paginaUltima>1}">
<s:url id="primeiro" action="listarChaves" namespace="/auto_infracao">
	<s:param name="pagina" value="0"/>
</s:url>
<s:url id="anterior" action="listarChaves" namespace="/auto_infracao">
	<s:param name="pagina" value="paginaAnterior"/>
</s:url>
<s:url id="proximo" action="listarChaves" namespace="/auto_infracao">
	<s:param name="pagina" value="paginaProxima"/>
</s:url>
<s:url id="ultimo" action="listarChaves" namespace="/auto_infracao">
	<s:param name="pagina" value="paginaUltima"/>
</s:url>
<table class="tableFooter ">
	<tr>
	<s:if test="%{paginaAnterior==0}">
		<td><img border="0"
				src="<s:url value='/imagens/primeira_inativa.gif' encode='false' includeParams='none'/>">
		</td>
		<td><img border="0"
				src="<s:url value='/imagens/anterior_inativo.gif' encode='false' includeParams='none'/>">
		</td>
	</s:if>
	<s:if test="%{paginaAnterior>0}">
		<td><s:a href="%{primeiro}">
			<img border="0"
				src="<s:url value='/imagens/primeira.gif' encode='false' includeParams='none'/>">
		</s:a></td>
		<td><s:a href="%{anterior}">
			<img border="0"
				src="<s:url value='/imagens/anterior.gif' encode='false' includeParams='none'/>">
		</s:a></td>
	</s:if>
	
	<s:if test="%{paginaProxima>paginaUltima}">
		<td><img border="0"
				src="<s:url value='/imagens/proxima_inativa.gif' encode='false' includeParams='none'/>">
		</td>
		<td><img border="0"
				src="<s:url value='/imagens/ultima_inativa.gif' encode='false' includeParams='none'/>">
		</td>
	</s:if>
	<s:if test="%{paginaProxima<=paginaUltima}">
		<td><s:a href="%{proximo}">
			<img border="0"
				src="<s:url value='/imagens/proxima.gif' encode='false' includeParams='none'/>">
		</s:a></td>
		<td><s:a href="%{ultimo}">
			<img border="0"
				src="<s:url value='/imagens/ultima.gif' encode='false' includeParams='none'/>">
		</s:a></td>
	</s:if>
	</tr>
</table>
</s:if>
</div>

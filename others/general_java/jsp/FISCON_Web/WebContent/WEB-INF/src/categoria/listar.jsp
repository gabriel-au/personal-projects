<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Categoria dos Veículos</span></div>
<div id="tabela">
<table>
<caption>Categoria dos Veículos</caption>
<tr>
	<th>Situação</th>
	<th>Código</th>
	<th>Nome</th>
	<th colspan="2"></th>
</tr>
<%int i = 0; %>
<s:iterator value="lista">
<s:url id="editar" action="editar" namespace="/categoria">
	<s:param name="pojo.id" value="id"></s:param>
</s:url>
<tr <%i++; if(i%2 == 0){%>class="alt"<%} %>>
	<td>
 	<s:if test="%{ativo==true}">
 		Ativo
 	</s:if>
 	<s:if test="%{ativo==false}">
 		Inativo
 	</s:if>
 	</td>
	<td><s:property value="codigo"/> </td>
	<td><s:property value="nome"/> </td>
	<td><s:a href="%{editar}"><img border="0" src="<s:url value='/imagens/edit32.png' encode='false' includeParams='none'/>" alt="Editar" /></s:a></td>
</tr>
</s:iterator>
</table>


<s:if test="%{paginaUltima>1}">
<s:url id="primeiro" action="listar" namespace="/categoria">
	<s:param name="pagina" value="0"/>
</s:url>
<s:url id="anterior" action="listar" namespace="/categoria">
	<s:param name="pagina" value="paginaAnterior"/>
</s:url>
<s:url id="proximo" action="listar" namespace="/categoria">
	<s:param name="pagina" value="paginaProxima"/>
</s:url>
<s:url id="ultimo" action="listar" namespace="/categoria">
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
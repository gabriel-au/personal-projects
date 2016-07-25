<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Cores dos Veículos</span> Ultima atualização 22/04/2010</div>
<div id="tabela">
<table>
<tr>
	<th>Situação</th>
	<th>Cor </th>
	<th>Categoria</th>
	<th>Marca/Modelo</th>
	<th>Tipo</th>
	<th>Município</th>
	<th>Espécie</th>
	<th>Placa</th>
	<th>Modelo/Ano</th>
	<th></th>
</tr>
<%int i = 0; %>
<s:iterator value="lista">
<s:url id="editar" action="editar" namespace="/veiculo">
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
	<td><s:property value="corPojo.nome"/> </td>
	<td><s:property value="categoriaPojo.nome"/> </td>
	<td><s:property value="marcaModeloPojo.nome"/> </td>
	<td><s:property value="tipoPojo.nome"/> </td>
	<td><s:property value="municipioPojo.nome"/> </td>
	<td><s:property value="especiePojo.nome"/> </td>
	<td><s:property value="veiculoPlaca"/> </td>
	<td><s:property value="anoModelo"/>/<s:property value="anoFabricacao"/> </td>
	<td><s:a href="%{editar}"><img border="0" src="<s:url value='/imagens/edit32.png' encode='false' includeParams='none'/>" alt="Editar" /></s:a></td>
</tr>
</s:iterator>
<tr class="tablefooter">
	<td colspan="10" align="center">
	<s:if test="%{paginaUltima>1}">
<s:url id="primeiro" action="listar" namespace="/veiculo">
	<s:param name="pagina" value="0"/>
</s:url>
<s:url id="anterior" action="listar" namespace="/veiculo">
	<s:param name="pagina" value="paginaAnterior"/>
</s:url>
<s:url id="proximo" action="listar" namespace="/veiculo">
	<s:param name="pagina" value="paginaProxima"/>
</s:url>
<s:url id="ultimo" action="listar" namespace="/veiculo">
	<s:param name="pagina" value="paginaUltima"/>
</s:url>
<table style="text-align: center;">
	<tr>
	<s:if test="%{paginaAnterior==0}">
		<td><img src="<s:url value='/imagens/primeira_inativa.gif' encode='false' includeParams='none'/>">
		</td>
		<td><img src="<s:url value='/imagens/anterior_inativo.gif' encode='false' includeParams='none'/>">
		</td>
	</s:if>
	<s:if test="%{paginaAnterior>0}">
		<td><s:a href="%{primeiro}">
			<img src="<s:url value='/imagens/primeira.gif' encode='false' includeParams='none'/>">
		</s:a></td>
		<td><s:a href="%{anterior}">
			<img src="<s:url value='/imagens/anterior.gif' encode='false' includeParams='none'/>">
		</s:a></td>
	</s:if>
	
	<s:if test="%{paginaProxima>paginaUltima}">
		<td><img src="<s:url value='/imagens/proxima_inativa.gif' encode='false' includeParams='none'/>">
		</td>
		<td><img src="<s:url value='/imagens/ultima_inativa.gif' encode='false' includeParams='none'/>">
		</td>
	</s:if>
	<s:if test="%{paginaProxima<=paginaUltima}">
		<td><s:a href="%{proximo}">
			<img src="<s:url value='/imagens/proxima.gif' encode='false' includeParams='none'/>">
		</s:a></td>
		<td><s:a href="%{ultimo}">
			<img src="<s:url value='/imagens/ultima.gif' encode='false' includeParams='none'/>">
		</s:a></td>
	</s:if>
	</tr>
</table>
</s:if>
	</td>
</tr>
</table>
</div>

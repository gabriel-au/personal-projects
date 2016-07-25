<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Consulta Usuários</span></div>
<div class="marginform">
<s:form namespace="/usuario" >
    <s:textfield label="Matrícula" key="pojo.matricula"/>
    <s:textfield label="Nome" key="pojo.nome"/>
    <tr class="trsubform">
		<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Consultar" action="listar"/>
    </td></tr>
</s:form>
<s:url id="novo" action="editar" namespace="/usuario">
	<s:param name="pojo.id" value="0"></s:param>
</s:url>
<s:a href="%{novo}"><img border="0" src="<s:url value='/imagens/novo.png' encode='false' includeParams='none'/>" alt="Novo Registro"/></s:a>
</div>

<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Consulta Marca/Modelo dos Veículos</span></div>
<div class="marginform">
<s:form namespace="/marca_modelo" >
    <s:textfield label="Nome" key="pojo.nome"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Consultar" action="listar"/>
    </td></tr>
</s:form>
</div>
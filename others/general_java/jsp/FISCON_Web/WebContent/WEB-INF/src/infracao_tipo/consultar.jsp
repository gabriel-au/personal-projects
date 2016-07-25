<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Consulta dos Tipos de Infração</span></div>
<div class="marginform">
<s:form namespace="/infracao_tipo" >
    <s:textfield label="Código" key="pojo.codigo"/>
    <s:textfield label="Nome" key="pojo.nome"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Consultar" action="listar"/>
    </td>
    </tr>
</s:form>
</div>
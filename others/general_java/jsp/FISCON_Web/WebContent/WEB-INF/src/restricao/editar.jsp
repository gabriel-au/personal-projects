<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Atualiza��o</span></div>
<div class="marginform">
<s:form namespace="/atualizacao" >
	<s:hidden key="pojo.id"/>
    <s:textfield label="Descri��o" key="pojo.descricao"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    <s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    </td>
    </tr>
</s:form>
</div>
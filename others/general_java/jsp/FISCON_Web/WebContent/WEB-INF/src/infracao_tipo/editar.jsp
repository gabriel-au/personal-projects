<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Tipo de Infração</span></div>
<div class="marginform">
<s:form namespace="/infracao_tipo" >
	<s:hidden key="pojo.id"/>
    <s:textfield label="Código" key="pojo.codigo" readonly="true" />
    <s:textfield label="Nome" key="pojo.nome"/>
    <s:textfield label="Amparo Legal" key="pojo.amparoLegal"/>
    <s:textfield label="Infrator" key="pojo.infrator"/>
    <s:textfield label="Gravidade" key="pojo.gravidade"/>
    <s:textfield label="Órgão" key="pojo.orgao"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    <s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    </td>
    </tr>
</s:form>
</div>
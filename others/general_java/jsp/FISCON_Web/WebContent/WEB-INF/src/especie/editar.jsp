<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Espécie do Veículo</span></div>
<div class="marginform">
<s:form namespace="/especie" >
	<s:hidden key="pojo.id"/>
	<s:select list="#{'true':'Ativo','false':'Inativo'}" label="Ativo" name="pojo.ativo"/>
    <s:textfield label="Código" key="pojo.codigo" readonly="true" />
    <s:textfield label="Nome" key="pojo.nome"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    <s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    </td>
    </tr>
</s:form>
</div>
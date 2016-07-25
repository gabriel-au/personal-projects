<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<sx:head />
<div class="underbanner2"><span>Editar a Atualização</span></div>
<div class="marginform">
<s:form namespace="/atualizacao" >
	<s:hidden key="pojo.id"/>
    <s:textfield label="Descrição" key="pojo.descricao"/>
    <sx:datetimepicker label="Data da Inclusão" key="pojo.dataInclusao" displayFormat="dd/MM/yyyy" 
    iconPath="/imagens/calendar.png" />
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    <s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    </td>
    </tr>
</s:form>
</div>
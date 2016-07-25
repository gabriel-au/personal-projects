<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<sx:head />
<div class="underbanner2"><span>Filtros para o LOG</span></div>
<div class="marginform">
<s:form namespace="/log" >
    <sx:datetimepicker label="Data do Evento" key="pojo.dataEvento" name="pojo.dataEvento" displayFormat="dd/MM/yyyy" value="%{date}" />
    <s:select list="#{'ACESSO_WEB':'Acesso WEB','ACESSO_MW':'Acesso MW'}" label="Tipo" name="pojo.tipo"/>
    <s:select list="listaUsuario" label="Usuário" listKey="id" listValue="nome" name="pojo.idUsuario"/>
    <s:textfield label="IP" key="pojo.ip"/>
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Consultar" action="listar"/>
    </td></tr>
</s:form>
</div>
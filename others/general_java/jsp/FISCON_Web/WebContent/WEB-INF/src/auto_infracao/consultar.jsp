<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<sx:head />
<div class="underbanner2"><span>Auto de Infração</span></div>
<div class="marginform">
<s:form namespace="/auto_infracao" >
	<s:textfield label="Nº do Auto de Infração" key="pojo.numeroAutoInfracao" />
	<s:textfield label="Placa" key="pojo.veiculoPojo.veiculoPlaca" />
	<sx:datetimepicker label="Data da Infração" key="pojo.dataInfracao" displayFormat="dd/MM/yyyy" />
    <tr><td colspan="2" class="trsubform">
    <s:submit theme="simple" cssClass="button" value="Consultar" action="listar"/>
	</td></tr>
</s:form>
</div>
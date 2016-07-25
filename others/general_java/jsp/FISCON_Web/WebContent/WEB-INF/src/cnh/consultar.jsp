<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<sx:head />
<div class="underbanner2"><span>CNH</span></div>
<div class="marginform">
<s:form namespace="/cnh_agente" >
	<s:textfield label="Nº de Registro da CNH" key="pojo.cnh"/>
	<sx:datetimepicker label="Data de Nascimento" key="pojo.dataNascimento" displayFormat="dd/MM/yyyy" />
	<tr class="trsubform">
		<td colspan="2">
    		<s:submit theme="simple" cssClass="button" value="Consultar" action="exibir"/>
    	</td>
    </tr>		
</s:form>
</div>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="underbanner2"><span>Consultar Veículo</span></div>
<div class="marginform">
<s:form namespace="/veiculo" >
	<s:textfield label="Placa" key="pojo.veiculoPlaca"/>
	<tr class="trsubform">
		<td colspan="2">
	    	<s:submit theme="simple" cssClass="button" value="Consultar" action="exibir"/>
	    </td>
    </tr>
</s:form>
</div>
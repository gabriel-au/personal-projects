<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="underbanner2"><span>Editar Veículo</span></div>
<div class="marginform">
<s:form namespace="/cor" >
	<s:hidden key="pojo.id"/>
	<s:select list="#{'true':'Ativo','false':'Inativo'}" label="Ativo" name="pojo.ativo"/>
	<s:textfield label="Placa" key="pojo.veiculoPlaca" readonly="true"/>
	<s:textfield label="Ano do Modelo" key="pojo.anoModelo"/>
	<s:textfield label="Ano de Fabricação" key="pojo.anoFabricacao"/>
    <s:select list="listaCor" label="Cor" listKey="id" listValue="nome" name="pojo.corPojo.id"/>
    <s:select list="listaCategoria" label="Categoria" listKey="id" listValue="nome" name="pojo.categoriaPojo.id"/>
    <s:select list="listaMarcaModelo" label="Marca/Modelo" listKey="id" listValue="nome" name="pojo.marcaModeloPojo.id"/>
    <s:select list="listaTipo" label="Tipo do Veículo" listKey="id" listValue="nome" name="pojo.tipoPojo.id"/>
	<s:select list="listaMunicipio" label="Município" listKey="id" listValue="nome" name="pojo.municipioPojo.id"/>
    <s:select list="listaEspecie" label="Espécie" listKey="id" listValue="nome" name="pojo.especiePojo.id"/>
    <tr class="trsubform">
    	<td colspan="2"><s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    	<s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    	</td>
    </tr>
</s:form>
</div>
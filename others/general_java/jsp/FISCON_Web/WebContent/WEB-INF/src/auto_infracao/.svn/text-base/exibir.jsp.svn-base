<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>


<s:url id="listar" action="listar" namespace="/auto_infracao">
	<s:param name="pagina" value="pagina"/>
</s:url>
<div class="underbanner2"><span>Identificação da Infração</span></div>
<div class="marginform">
<table>
	<tr><td colspan="2"><s:a href="%{listar}">
		<img alt="Voltar" src="<s:url value='/imagens/voltar.png' encode='false' includeParams='none'/>">
	</s:a></td></tr>
	<tr><td colspan="2"><i>Identificação da Infração</i></td></tr>
	<tr class="alt">
		<td><b>Orgão Autuador</b></td>
		<td><s:property value="pojo.orgaoAutuador" /></td>
	</tr>
	<tr>
		<td><b>Código Órgão</b></td>
		<td><s:property value="pojo.codigoOrgaoAutuador" /></td>
	</tr>
	<tr class="alt">
		<td><b>Nº do Auto de Infração</b></td>
		<td><s:property value="pojo.numeroAutoInfracao" /></td>
	</tr>
	<tr><td colspan="2"><i>Identificação do Veículo</i></td></tr>
	<tr class="alt">
		<td><b>Placa</b></td>
		<td><s:property value="pojo.veiculoPojo.veiculoPlaca" /></td>
	</tr>
	<tr>
		<td><b>UF</b></td>
		<td><s:property value="pojo.veiculoPojo.municipioPojo.uf" /></td>
	</tr>
	<tr class="alt">
		<td><b>Marca/Modelo</b></td>
		<td><s:property value="pojo.veiculoPojo.marcaModeloPojo.nome" /></td>
	</tr>
	<tr>
		<td><b>Espécie</b></td>
		<td><s:property value="pojo.veiculoPojo.especiePojo.nome" /></td>
	</tr>
	<tr class="alt"><td colspan="2"><i>Identificação do Proprietário</i></td></tr>
	<tr>
		<td><b>Nome do Proprietário/Arrendatário</b></td>
		<td><s:property value="pojo.cnhPojo.nome" /></td>
	</tr>
	<tr class="alt">
		<td><b>CPF/CNPJ</b></td>
		<td><s:property value="pojo.cnhPojo.cpf" /></td>
	</tr>
	<tr>
		<td><b>Nº de Registro CNH</b></td>
		<td><s:property value="pojo.cnhPojo.cnh" /></td>
	</tr>
	<tr class="alt">
		<td><b>UF</b></td>
		<td><s:property value="pojo.cnhPojo.uf" /></td>
	</tr>
	<tr><td colspan="2"><i>Identificação do Local, Data e Hora do Cometido da	Infração</i></td></tr>
	<tr class="alt">
		<td><b>Local da Infração</b></td>
		<td><s:property value="pojo.enderecoLogradouro" /><br/>
			<s:property value="pojo.enderecoComplemento" /><br/>
			<s:property value="pojo.enderecoBairro" /></td>
	</tr>
	<tr>
		<td><b>Data</b></td>
		<td><s:text name="formatar.data">
			<s:param name="value" value="pojo.dataInfracao"/>
		</s:text></td>
	</tr>
	<tr class="alt">
		<td><b>Hora</b></td>
		<td><s:text name="formatar.hora">
			<s:param name="value" value="pojo.dataInfracao"/>
		</s:text></td>
	</tr>
	<tr>
		<td><b>UF</b></td>
		<td><s:property value="pojo.enderecoUF" /></td>
	</tr>
	<tr class="alt"><td colspan="2"><i>Tipificação a infração</i></td></tr>
	<tr>
		<td><b>Descrição da Infração</b></td>
		<td><s:property value="pojo.infracaoTipoPojo.nome" /></td>
	</tr>
	<tr class="alt">
		<td><b>Código da Infração</b></td>
		<td><s:property value="pojo.infracaoTipoPojo.codigo" /></td>
	</tr>
	<tr>
		<td><b>Desdobramento</b></td>
		<td>*</td>
	</tr>
	<tr class="alt">
		<td><b>Artigo</b></td>
		<td><s:property value="pojo.infracaoTipoPojo.amparoLegal" /></td>
	</tr>
	<tr>
		<td><b>Pontuação</b></td>
		<td><s:property value="pojo.infracaoTipoPojo.gravidade" /></td>
	</tr>
	<tr class="alt">
		<td><b>Equip./Instrumento de Aferição Utilizado</b></td>
		<td><s:property value="pojo.equipInstrumento" /></td>
	</tr>
	<tr>
		<td><b>Certificado de Aferição</b></td>
		<td><s:property value="pojo.certificado" /></td>
	</tr>
	<tr class="alt">
		<td><b>Data Verificação</b></td>
		<td><s:property value="pojo.dataVerificacao" /></td>
	</tr>
	<tr>
		<td><b>Medição Realizada</b></td>
		<td><s:property value="pojo.medicaoRealizada" /></td>
	</tr>
	<tr class="alt">
		<td><b>Limite Regulamentado</b></td>
		<td><s:property value="pojo.limiteRegulamentado" /></td>
	</tr>
	<tr>
		<td><b>Valor Considerado</b></td>
		<td><s:property value="pojo.valorConsiderado" /></td>
	</tr>
	<tr class="alt">
		<td><b>Identificação do Agente</b></td>
		<td><s:property value="pojo.usuarioPojo.matricula" /></td>
	</tr>
	<tr><td colspan="2"><i>Informações Complementares</i></td></tr>
	<tr class="alt">
		<td><b>Código RENAINF</b></td>
		<td><s:property value="pojo.codigoRenainf" /></td>
	</tr>
	<tr>
		<td><b>Data da Expedição da NA</b></td>
		<td><s:property value="pojo.dataExpedicao" /></td>
	</tr>
	<tr class="alt">
		<td><b>Valor(R$)</b></td>
		<td><s:property value="pojo.valor" /></td>
	</tr>
</table>
<div class="underbanner2"><span>Fotos</span></div>
<table>
<s:iterator value="pojo.fotoPojos">
<tr>
	<td><img src="data:image/jpeg;base64,<s:property value="foto"/>"></img> </td>
</tr>
</s:iterator>
</table>
</div>

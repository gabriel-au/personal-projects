<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="br.com.consorciosdf.intranet.util.DataUtil"%>

<%@ page import="java.text.SimpleDateFormat"%>

<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DataUtil data = new DataUtil();
	
	//Obtendo valores das variáveis
	String nome = request.getParameter("nome").toUpperCase();
	String endereco = request.getParameter("endereco").toUpperCase();
	String cidade = request.getParameter("cidade").toUpperCase();
	String estado = request.getParameter("estado").toUpperCase();
	String fone = request.getParameter("fone");
	String dataEmissao = request.getParameter("dt_dia") + "/" + request.getParameter("dt_mes") + "/" + request.getParameter("dt_ano");
	String cnpj = request.getParameter("cnpj");
	String inscricaoEstadual = request.getParameter("inscricao_estadual");
	
	String modelo = request.getParameter("modelo");
	
	String codigo01 = request.getParameter("codigo_01").toUpperCase();
	String quantidade01 = request.getParameter("quantidade_01").toUpperCase();
	String aliquota01 = request.getParameter("aliquota_01").toUpperCase();
	String precoUnitario01 = request.getParameter("preco_unitario_01").toUpperCase();
	String precoTotal01 = request.getParameter("preco_total_01").toUpperCase();
	String descricao01 = request.getParameter("descricao_01").toUpperCase();
	
	String codigo02 = request.getParameter("codigo_02").toUpperCase();
	String quantidade02 = request.getParameter("quantidade_02").toUpperCase();
	String aliquota02 = request.getParameter("aliquota_02").toUpperCase();
	String precoUnitario02 = request.getParameter("preco_unitario_02").toUpperCase();
	String precoTotal02 = request.getParameter("preco_total_02").toUpperCase();
	String descricao02 = request.getParameter("descricao_02").toUpperCase();
	
	String codigo03 = request.getParameter("codigo_03").toUpperCase();
	String quantidade03 = request.getParameter("quantidade_03").toUpperCase();
	String aliquota03 = request.getParameter("aliquota_03").toUpperCase();
	String precoUnitario03 = request.getParameter("preco_unitario_03").toUpperCase();
	String precoTotal03 = request.getParameter("preco_total_03").toUpperCase();
	String descricao03 = request.getParameter("descricao_03").toUpperCase();
	
	String codigo04 = request.getParameter("codigo_04").toUpperCase();
	String quantidade04 = request.getParameter("quantidade_04").toUpperCase();
	String aliquota04 = request.getParameter("aliquota_04").toUpperCase();
	String precoUnitario04 = request.getParameter("preco_unitario_04").toUpperCase();
	String precoTotal04 = request.getParameter("preco_total_04").toUpperCase();
	String descricao04 = request.getParameter("descricao_04").toUpperCase();
	
	String codigo05 = request.getParameter("codigo_05").toUpperCase();
	String quantidade05 = request.getParameter("quantidade_05").toUpperCase();
	String aliquota05 = request.getParameter("aliquota_05").toUpperCase();
	String precoUnitario05 = request.getParameter("preco_unitario_05").toUpperCase();
	String precoTotal05 = request.getParameter("preco_total_05").toUpperCase();
	String descricao05 = request.getParameter("descricao_05").toUpperCase();
	
	String deducoesLegais = request.getParameter("deducoes_legais").toUpperCase();
	String valorTotal = request.getParameter("valor_total").toUpperCase();
	
	//Inserindo variaveis na sessão
	session.setAttribute("nfNome", nome);
	session.setAttribute("nfEndereco", endereco);
	session.setAttribute("nfCidade", cidade);
	session.setAttribute("nfEstado", estado);
	session.setAttribute("nfFone", fone);
	session.setAttribute("nfEmissao", dataEmissao);
	session.setAttribute("nfCnpj", cnpj);
	session.setAttribute("nfInscricaoEstadual", inscricaoEstadual);
	
	session.setAttribute("nfModelo", modelo);
	
	session.setAttribute("nfCodigo01", codigo01);
	session.setAttribute("nfQuantidade01", quantidade01);
	session.setAttribute("nfAliquota01", aliquota01);
	session.setAttribute("nfPrecoUnitario01", precoUnitario01);
	session.setAttribute("nfPrecoTotal01", precoTotal01);
	session.setAttribute("nfDescricao01", descricao01);
	
	session.setAttribute("nfCodigo02", codigo02);
	session.setAttribute("nfQuantidade02", quantidade02);
	session.setAttribute("nfAliquota02", aliquota02);
	session.setAttribute("nfPrecoUnitario02", precoUnitario02);
	session.setAttribute("nfPrecoTotal02", precoTotal02);
	session.setAttribute("nfDescricao02", descricao02);
	
	session.setAttribute("nfCodigo03", codigo03);
	session.setAttribute("nfQuantidade03", quantidade03);
	session.setAttribute("nfAliquota03", aliquota03);
	session.setAttribute("nfPrecoUnitario03", precoUnitario03);
	session.setAttribute("nfPrecoTotal03", precoTotal03);
	session.setAttribute("nfDescricao03", descricao03);
	
	session.setAttribute("nfCodigo04", codigo04);
	session.setAttribute("nfQuantidade04", quantidade04);
	session.setAttribute("nfAliquota04", aliquota04);
	session.setAttribute("nfPrecoUnitario04", precoUnitario04);
	session.setAttribute("nfPrecoTotal04", precoTotal04);
	session.setAttribute("nfDescricao04", descricao04);
	
	session.setAttribute("nfCodigo05", codigo05);
	session.setAttribute("nfQuantidade05", quantidade05);
	session.setAttribute("nfAliquota05", aliquota05);
	session.setAttribute("nfPrecoUnitario05", precoUnitario05);
	session.setAttribute("nfPrecoTotal05", precoTotal05);
	session.setAttribute("nfDescricao05", descricao05);
	
	session.setAttribute("nfDeducoesLegais", deducoesLegais);
	session.setAttribute("nfValorTotal", valorTotal);

	String menuHorizontal = "";
%>

<!-- include de verificação do usuário -->
<%@ include file="includes/sessionVerify.jsp"%>
<!-- fim do include verificação do usuário -->

<html>
<head>
<title>INTRANET CONSORCIO SDF</title>
<link rel="stylesheet" type="text/css" href="estilos/default.css" />

<script language="javascript" type="text/javascript"
	src="scripts/default.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include do menu -->
<jsp:include page="includes/menu.jsp" />
<!-- fim do include do menu -->

</head>

<body>
<!-- include do cabeçalho -->
<jsp:include page="includes/header.jsp" />
<!-- fim do include do cabeçalho -->


<table align="left" width="779" border="0" cellspacing="0"
	cellpadding="0">

	<tr>
		<td width="220">&nbsp;</td>
		<td width="549" height="180" colspan="2" valign="top">
		<table width="549" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="Title">
				<table width='549' cellspacing='0' cellpadding='0' border='0'>
					<tr>
						<td class="Title" width="">Notas Fiscais - Visualização</td>
						<td width="" align="right">
							<a href='javascript:history.back()'>
								<img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'>
							</a>
							<a href='nfPrint.jsp' target="_BLANK">
								<img src='imagens/stock_print-16.gif' border='0' alt='Imprimir'>
							</a>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DESTINATÁRIO/REMETENTE:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Nome:</td>
								<td width="386"><%= nome %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Endereço:</td>
								<td><%= endereco %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Cidade/Estado:</td>
								<td><%= cidade + "/" + estado %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Fone/Fax:</td>
								<td><%= fone %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Data de Emissão:</td>
								<td><%= dataEmissao %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">CNPJ:</td>
								<td><%= cnpj %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Inscrição Estadual:</td>
								<td><%= inscricaoEstadual %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Modelo:</td>
								<td><%= modelo %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DISCRIMINAÇÃO DOS SERVIÇOS - 01:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150px">Código:</td>
								<td width="386"><%= codigo01 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Quantidade:</td>
								<td><%= quantidade01 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Alíquota:</td>
								<td><%= aliquota01 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Preço Unitário:</td>
								<td><%= precoUnitario01 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Preço Total:</td>
								<td><%= precoTotal01 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Descrição:</td>
								<td><%= descricao01 %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DISCRIMINAÇÃO DOS SERVIÇOS - 02:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Código:</td>
								<td width="386"><%= codigo02 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Quantidade:</td>
								<td><%= quantidade02 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Alíquota:</td>
								<td><%= aliquota02 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Preço Unitário:</td>
								<td><%= precoUnitario02 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Preço Total:</td>
								<td><%= precoTotal02 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Descrição:</td>
								<td><%= descricao02 %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DISCRIMINAÇÃO DOS SERVIÇOS - 03:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Código:</td>
								<td width="386"><%= codigo03 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Quantidade:</td>
								<td><%= quantidade03 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Alíquota:</td>
								<td><%= aliquota03 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Preço Unitário:</td>
								<td><%= precoUnitario03 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Preço Total:</td>
								<td><%= precoTotal03 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Descrição:</td>
								<td><%= descricao03 %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DISCRIMINAÇÃO DOS SERVIÇOS - 04:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Código:</td>
								<td width="386"><%= codigo04 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Quantidade:</td>
								<td><%= quantidade04 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Alíquota:</td>
								<td><%= aliquota04 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Preço Unitário:</td>
								<td><%= precoUnitario04 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Preço Total:</td>
								<td><%= precoTotal04 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Descrição:</td>
								<td><%= descricao04 %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>DISCRIMINAÇÃO DOS SERVIÇOS - 05:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Código:</td>
								<td width="386"><%= codigo05 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Quantidade:</td>
								<td><%= quantidade05 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Alíquota:</td>
								<td><%= aliquota05 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Preço Unitário:</td>
								<td><%= precoUnitario05 %></td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18"></td>
								<td class="Title">Preço Total:</td>
								<td><%= precoTotal05 %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Descrição:</td>
								<td><%= descricao05 %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td>
				<table width="549" border="0" cellspacing="0" cellpadding="0"
					bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
					<tr>
						<td colspan="3">
						<table border="0" cellpadding="0" cellspacing="0"
							bgcolor="#FFFFFF">
							<tr>
								<td height="20" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>&nbsp;</td>
								<td colspan="2" class="TableResultTitle"
									style='border-bottom: 1px solid #000000;'>TOTAL:</td>
							</tr>
							<tr class="TableResultBody1">
								<td height="18" width="11"></td>
								<td class="Title" width="150">Deduções Legais:</td>
								<td width="386"><%= deducoesLegais %></td>
							</tr>
							<tr class="TableResultBody2">
								<td height="18"></td>
								<td class="Title">Valor Total:</td>
								<td><%= valorTotal %></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		<td width="10"></td>
	</tr>
	<tr>
		<td colspan="3" height="20">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3" class="Footer">
		<table>
			<tr>
				<td align="center" width="535"><jsp:include
					page="includes/footer.jsp" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</body>
</html>
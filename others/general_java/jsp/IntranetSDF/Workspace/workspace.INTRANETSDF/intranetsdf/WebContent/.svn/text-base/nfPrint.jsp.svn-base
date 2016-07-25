<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%

	//Inserindo variaveis na sessão
	String nome = (String) session.getAttribute("nfNome");
	String endereco = (String) session.getAttribute("nfEndereco");
	String cidade = (String) session.getAttribute("nfCidade");
	String estado = (String) session.getAttribute("nfEstado");
	String fone = (String) session.getAttribute("nfFone");
	String dataEmissao = (String) session.getAttribute("nfEmissao");
	String cnpj = (String) session.getAttribute("nfCnpj");
	String inscricaoEstadual = (String) session.getAttribute("nfInscricaoEstadual");
	
	String modelo = (String) session.getAttribute("nfModelo");
	
	String codigo01 = (String) session.getAttribute("nfCodigo01");
	String quantidade01 = (String) session.getAttribute("nfQuantidade01");
	String aliquota01 = (String) session.getAttribute("nfAliquota01");
	String precoUnitario01 = (String) session.getAttribute("nfPrecoUnitario01");
	String precoTotal01 = (String) session.getAttribute("nfPrecoTotal01");
	String descricao01 = (String) session.getAttribute("nfDescricao01");
	
	String codigo02 = (String) session.getAttribute("nfCodigo02");
	String quantidade02 = (String) session.getAttribute("nfQuantidade02");
	String aliquota02 = (String) session.getAttribute("nfAliquota02");
	String precoUnitario02 = (String) session.getAttribute("nfPrecoUnitario02");
	String precoTotal02 = (String) session.getAttribute("nfPrecoTotal02");
	String descricao02 = (String) session.getAttribute("nfDescricao02");
	
	String codigo03 = (String) session.getAttribute("nfCodigo03");
	String quantidade03 = (String) session.getAttribute("nfQuantidade03");
	String aliquota03 = (String) session.getAttribute("nfAliquota03");
	String precoUnitario03 = (String) session.getAttribute("nfPrecoUnitario03");
	String precoTotal03 = (String) session.getAttribute("nfPrecoTotal03");
	String descricao03 = (String) session.getAttribute("nfDescricao03");
	
	String codigo04 = (String) session.getAttribute("nfCodigo04");
	String quantidade04 = (String) session.getAttribute("nfQuantidade04");
	String aliquota04 = (String) session.getAttribute("nfAliquota04");
	String precoUnitario04 = (String) session.getAttribute("nfPrecoUnitario04");
	String precoTotal04 = (String) session.getAttribute("nfPrecoTotal04");
	String descricao04 = (String) session.getAttribute("nfDescricao04");
	
	String codigo05 = (String) session.getAttribute("nfCodigo05");
	String quantidade05 = (String) session.getAttribute("nfQuantidade05");
	String aliquota05 = (String) session.getAttribute("nfAliquota05");
	String precoUnitario05 = (String) session.getAttribute("nfPrecoUnitario05");
	String precoTotal05 = (String) session.getAttribute("nfPrecoTotal05");
	String descricao05 = (String) session.getAttribute("nfDescricao05");
	
	String deducoesLegais = (String) session.getAttribute("nfDeducoesLegais");
	String valorTotal = (String) session.getAttribute("nfValorTotal");
	
	//Setando as variaveis de largura
	String widthCodigo = "70";
	String widthPrecoUnitario = "85";
	
	if (modelo != null && modelo.equals("2")) {
		widthCodigo = "0";
		widthPrecoUnitario = "110";
	}

%>

<html>
<head>
<title>INTRANET CONSORCIO SDF</title>
<link rel="stylesheet" type="text/css" href="estilos/nfPrint.css" />

</head>

<body>

<table width="879" border="0" cellspacing="0" cellpadding="0" class="table">
	<tr>
		<td width="100" height="240">&nbsp;</td>
		<td></td>
	</tr>
	<tr>
		<td height="35">&nbsp;</td>
		<td><%= nome %></td>
	</tr>
	<tr>
		<td height="35">&nbsp;</td>
		<td>
			<table width="779" border="0" cellspacing="0" cellpadding="0" class="table">
				<tr>
					<td width="590"><%= endereco %></td>
					<td><%= cidade %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="35">&nbsp;</td>
		<td>
			<table width="779" border="0" cellspacing="0" cellpadding="0" class="table">
				<tr>
					<td width="370"><%= estado %></td>
					<td width="220"><%= fone %></td>
					<td><%= dataEmissao %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="35">&nbsp;</td>
		<td>
			<table width="779" border="0" cellspacing="0" cellpadding="0" class="table">
				<tr>
					<td width="450"><%= cnpj %></td>
					<td><%= inscricaoEstadual %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="879" border="0" cellspacing="0" cellpadding="0" class="table">
	<tr>
		<td height="50">&nbsp;</td>
	</tr>
	<tr valign="top">
		<td height="600">
			<table width="879" border="0" cellspacing="0" cellpadding="0" class="table">
				<tr valign="top">
					<td width="50">&nbsp;</td>
					<td width="<%= widthCodigo %>"><%= codigo01 %></td>
					<td width="70"><%= quantidade01 %></td>
					<td width="450">
						<%= descricao01 %>
					</td>
					<td width="25">&nbsp;</td>
					<td width="<%= widthPrecoUnitario %>"><%= precoUnitario01 %></td>
					<td><%= precoTotal01 %></td>
				</tr>
				<tr valign="top">
					<td colspan="7" height="10">&nbsp;</td>
				</tr>
				<tr valign="top">
					<td width="50">&nbsp;</td>
					<td width="<%= widthCodigo %>"><%= codigo02 %></td>
					<td width="70"><%= quantidade02 %></td>
					<td width="450">
						<%= descricao02 %>
					</td>
					<td width="25">&nbsp;</td>
					<td width="<%= widthPrecoUnitario %>"><%= precoUnitario02 %></td>
					<td><%= precoTotal02 %></td>
				</tr>
				<tr valign="top">
					<td colspan="7" height="10">&nbsp;</td>
				</tr>
				<tr valign="top">
					<td width="50">&nbsp;</td>
					<td width="<%= widthCodigo %>"><%= codigo03 %></td>
					<td width="70"><%= quantidade03 %></td>
					<td width="450">
						<%= descricao03 %>
					</td>
					<td width="25">&nbsp;</td>
					<td width="<%= widthPrecoUnitario %>"><%= precoUnitario03 %></td>
					<td><%= precoTotal03 %></td>
				</tr>
				<tr valign="top">
					<td colspan="7" height="10">&nbsp;</td>
				</tr>
				<tr valign="top">
					<td width="50">&nbsp;</td>
					<td width="<%= widthCodigo %>"><%= codigo04 %></td>
					<td width="70"><%= quantidade04 %></td>
					<td width="450">
						<%= descricao04 %>
					</td>
					<td width="25">&nbsp;</td>
					<td width="<%= widthPrecoUnitario %>"><%= precoUnitario04 %></td>
					<td><%= precoTotal04 %></td>
				</tr>
				<tr valign="top">
					<td colspan="7" height="10">&nbsp;</td>
				</tr>
				<tr valign="top">
					<td width="50">&nbsp;</td>
					<td width="<%= widthCodigo %>"><%= codigo05 %></td>
					<td width="70"><%= quantidade05 %></td>
					<td width="450">
						<%= descricao05 %>
					</td>
					<td width="25">&nbsp;</td>
					<td width="<%= widthPrecoUnitario %>"><%= precoUnitario05 %></td>
					<td><%= precoTotal05 %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="879" border="0" cellspacing="0" cellpadding="0" class="table">
	<tr>
		<td height="20">&nbsp;</td>
	</tr>
	<tr valign="top">
		<td>
			<table width="879" border="0" cellspacing="0" cellpadding="0" class="table">
				<tr valign="top">
					<td width="190">&nbsp;</td>
					<td width="500"><%= deducoesLegais %></td>
					<td><%= valorTotal %></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="50">&nbsp;</td>
	</tr>
</table>


</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="includes/head.jsp" %>
<body>

<%@ include file="includes/toolbar.jsp" %>

<!-- Início da Barra de Título -->
<!-- div class="titulo">
<a href="<%=request.getContextPath()%>/" >
<img src="<%=request.getContextPath()%>/imagens/botao-inicio-azul.png" />
</a>
<div class="titulo_texto">Noticias</div>
</div-->
<!-- Fim da Barra de Título -->

<!-- Início do Conteúdo -->
<div class="arredondado">
	<div class="arredondado_titulo_detalhe_espaco">
		DEMONSTRATIVO
	</div>
	<div class="arredondado_chamada_simples_dupla_borda">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="17%">DATA</td>
				<td width="50%">DESCRIÇÃO</td>
				<td style="text-align: right;">CRED/DEB R$</td>
			</tr>
			<tr style="font-size: 12px; font-weight: normal;">
				<td><strong>28/06</strong></td>
				<td><strong>Saldo anterior</strong></td>
				<td style="text-align: right;"><strong>- R$ 647,34</strong></td>
			</tr>
			<tr style="font-size: 12px; font-weight: normal;">
				<td><strong>28/06</strong></td>
				<td><strong>Pagamento efetuado</strong></td>
				<td style="text-align: right;"><strong>+ R$ 647,34</strong></td>
			</tr>
		</table>
	</div>
	<div class="arredondado_chamada_nome">
		<strong>ADRIANA</strong>
	</div>
	<div class="arredondado_chamada_simples_dupla_borda">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="40">
					<img src="<%=request.getContextPath()%>/imagens/logo_cc_mastercard.jpg" style="vertical-align: middle;"/>
				</td>
				<td>XXXX-XXXX-XXXX-3013</td>
			</tr>
		</table>
	</div>
	<div class="arredondado_chamada_nome_right">
		MOVIMENTAÇÕES NACIONAIS
	</div>
	<div class="arredondado_chamada_simples_dupla_borda">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr style="font-size: 12px; font-weight: normal; vertical-align: middle;">
				<td width="17%"><strong>03/04</strong></td>
				<td width="50%"><strong>DELL COMPUTADORES</strong></td>
				<td style="text-align: right;"><strong>- R$ 2.542,34</strong></td>
			</tr>
			<tr style="font-size: 12px; font-weight: normal;">
				<td><strong>10/04</strong></td>
				<td><strong>CASAS BAHIA 304</strong></td>
				<td style="text-align: right;"><strong>- R$ 312,50</strong></td>
			</tr>
		</table>
	</div>
	<div class="arredondado_chamada_nome">
		<strong>ROBERTA</strong>
	</div>
	<div class="arredondado_chamada_simples_dupla_borda">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="40">
					<img src="<%=request.getContextPath()%>/imagens/logo_cc_mastercard.jpg" style="vertical-align: middle;"/>
				</td>
				<td>XXXX-XXXX-XXXX-1702</td>
			</tr>
		</table>
	</div>
	<div class="arredondado_chamada_nome_right">
		MOVIMENTAÇÕES NACIONAIS
	</div>
	<div class="arredondado_chamada_simples_dupla_borda">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr style="font-size: 12px; font-weight: normal; vertical-align: middle;">
				<td width="18%"><strong>03/04</strong></td>
				<td width="50%"><strong>TOP MALHAS 03/03</strong></td>
				<td style="text-align: right;"><strong>- R$ 29,83</strong></td>
			</tr>
			<tr style="font-size: 12px; font-weight: normal;">
				<td><strong>10/04</strong></td>
				<td><strong>BOM B B M INFANTIL</strong></td>
				<td style="text-align: right;"><strong>- R$ 49,90</strong></td>
			</tr>
		</table>
	</div>
	<div class="arredondado_chamada_simples">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="20%"><strong>TOTAL</strong></td>
				<td style="text-align: right;"><strong>R$ 2.934,57</strong></td>
			</tr>
		</table>
	</div>
</div>

<div class="arredondado">
<div class="arredondado_link">
<a href="<%=request.getContextPath()%>/invoices.jsp" >
FATURAS ANTERIORES</a>
</div>
</div>

<!-- Fim do Conteúdo -->

<!-- div style="margin-top:0px; ">
<img src="<%=request.getContextPath()%>/imagens/tabbar.png" />
</div-->

<!-- Início do Espaçamento -->
<p>&nbsp;</p>
<!-- Fim do Espaçamento -->

</body>
</html>
<%@ page
	import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl"%>
<%@ page import="br.com.consorciosdf.intranet.modelo.*"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>

<jsp:useBean id="equipamento"
	class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request" />
<jsp:useBean id="equipamentoVerify"	class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request" />

<!-- include de paginação de resultados - cabeçalho -->
<%@ include file="includes/pageNavigatorHeader.jsp"%>
<!-- fim do include de paginação de resultados - cabeçalho -->

<%
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss dd/MM/yyyy");

	String ativo = "";
	String cidade = "";
	String end = "";
	String equi = "";
	List list = null;
	List listEquipamentos = null;
	ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
	listEquipamentos = manterEquipamentoControl.recuperarEquipamentos();

	List<String> listCidades = null;
	listCidades = manterEquipamentoControl.recuperaEquipamentosCidades();
	
	/*
	//equipamento ativo e inativo
	if ((request.getParameter("ativo")) != null) {
	    ativo = request.getParameter("ativo");
	    
	    if (session.getAttribute("equipamentoAtivo") == null) {
	        session.setAttribute("equipamentoAtivo", ativo);
	    } else {
	        String ativoSession = (String) session.getAttribute("equipamentoAtivo");
	        
	        if (!ativoSession.equals(ativo)) {
	            session.setAttribute("equipamentoAtivo", ativo);
	        }
	    }
	}
	
	if (session.getAttribute("equipamentoAtivo") != null) {
	    ativo = (String) session.getAttribute("equipamentoAtivo");
	}*/

	// Segurar filtro / Limpar filtro.
	if (request.getParameter("LF") == null) {

		ativo = request.getParameter("ativo");
		if (ativo != null && !ativo.trim().equals("")) {
			request.getSession(true).setAttribute("ativoFiltroEquipamento", ativo);
		} else {
			ativo = (String) request.getSession().getAttribute("ativoFiltroEquipamento");
		}

		end = request.getParameter("endereco");
		if (end != null && !end.trim().equals("")) {
			request.getSession(true).setAttribute("endFiltroEquipamento", end);
		} else {
			end = (String) request.getSession().getAttribute("endFiltroEquipamento");
		}

		cidade = request.getParameter("cidade");
		if (cidade != null && !cidade.trim().equals("")) {
			request.getSession(true).setAttribute("cidadeFiltroEquipamento", cidade);
		} else {
			cidade = (String) request.getSession().getAttribute("cidadeFiltroEquipamento");
		}

		equi = request.getParameter("equipamentoFiltro");
		if (equi != null && !equi.trim().equals("")) {
			request.getSession(true).setAttribute("equiFiltroEquipamento", equi);
		} else {
			equi = (String) request.getSession().getAttribute("equiFiltroEquipamento");
		}

		// Manter a ordenação na mudança de pagina
		if (request.getParameter("orderBy") != null) {
			orderBy = request.getParameter("orderBy");
			if(orderBy.trim().equals("nome")){
				request.getSession(true).setAttribute("orderByFiltro",orderBy);
			} else if(orderBy.trim().equals("endereco")){
				request.getSession(true).setAttribute("orderByFiltro",orderBy);
			} else if(orderBy.trim().equals("cidade")){
				request.getSession(true).setAttribute("orderByFiltro",orderBy);	
			}
		} else {
			orderBy = (String)request.getSession().getAttribute("orderByFiltro");
		}


	} else {
		// Caso o Usuário limpe o filtro.
		request.getSession(true).setAttribute("equiFiltroEquipamento", null);
		request.getSession(true).setAttribute("cidadeFiltroEquipamento", null);
		request.getSession(true).setAttribute("endFiltroEquipamento", null);
		request.getSession(true).setAttribute("ativoFiltroEquipamento", null);
		ativo = null;
		request.getSession(true).setAttribute("orderByFiltro",null);
	}

	list = manterEquipamentoControl.recuperarEquipamentosPag(ativo,equi, end, cidade, paginaInicial, qtdRegistros, orderBy,order);

	if (list != null) {
		if (list.size() > 0) {
			equipamentoVerify = (Equipamento) list.get(0);
			paginacao = equipamentoVerify.getPaginacao();
		}
	}
%>

<!-- include de paginação de resultados - rodapé -->
<%@ include file="includes/pageNavigatorFooter.jsp"%>
<!-- fim do include de paginação de resultados - rodapé -->

<!-- include de verificação do usuário -->
<%@ include file="includes/sessionVerify.jsp"%>
<!-- fim do include verificação do usuário -->


<%@page import="java.util.Set"%><html>
<head>
<title>INTRANET CONSORCIO SDF</title>
<link rel="stylesheet" type="text/css" href="estilos/default.css" />

<script language="javascript" type="text/javascript"
	src="scripts/default.js">
</script>

<!-- include do menu -->
<jsp:include page="includes/menu.jsp" />
<!-- fim do include do menu -->

</head>

<body>
<!-- include do cabeçalho -->
<jsp:include page="includes/header.jsp" />
<!-- fim do include do cabeçalho -->
<form method="get" action="">
<table width="779" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<td width="220">&nbsp;</td>
		<td width="549" height="180" colspan="2" valign="top">
		<table width="549" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="Title">
				<table width='549' cellspacing='0' cellpadding='0' border='0'>
					<tr>
						<td class="Title" width="420">Equipamentos - Listagem (<%=paginacao.getResultSize()%>
				Registros - Página <%=pageNumber%> de <%=paginacao.getPageNavigator()%>)</td>
				<td width="129" align="right"><a
					href="main.jsp"><img
					src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
					<a href="equipamentoAdd.jsp"><img
					src="imagens/stock_new-16.gif" border="0" alt="Novo"></a> <a
					href="javascript:window.location.reload()"><img
					src="imagens/stock_refresh-16.gif" border="0" alt="Atualizar"></a>
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
		<table cellspacing='0' cellpadding='0' border='0'>
			<tr>
                <td class="Title" width="110px">Equipamento:</td>
               <td>

                   <%
                   out.println(" <select name='equipamentoFiltro' id='equipamentoFiltro' class='Select' style='width:120px;'>");
				    out.println("<option value='' " + " class='OptionBody1'>-- SELECIONE --</option>");
					if (listEquipamentos != null) {
					    String optionStyle = "OptionBody1";
					    int countResult = 0;					
					    countResult++;
					
					    for (int i = 0; i < listEquipamentos.size(); i++) {
					        equipamento = (Equipamento) listEquipamentos.get(i);
					        countResult++;
					        String select = "";
					        
					        if (equi != null && equi.equals(equipamento.getNomeEquipamento())) {
					            select = "selected";
					        }
					        if (countResult % 2 == 0) {
					            optionStyle = "OptionBody2";
					        } else if (countResult % 2 == 1) {
					            optionStyle = "OptionBody1";
					        }
					
					        out.println("<option value='" + equipamento.getNomeEquipamento() + "' class='" + optionStyle + "' " + select + ">");
					        out.println(equipamento.getNomeEquipamento());
					        out.println("</option>");
					    }
					    
						} 
						out.println("</select>");					
					
         					 %>
         				</td>
         			</tr>			
         			<tr>
	               <td class="Title">Cidade:</td>
	               <td>
	                   <%
	                   out.println(" <select name='cidade' id='cidade' class='Select' style='width:150px;'>");
					    out.println("<option value='' " + " class='OptionBody1'>-- SELECIONE --</option>");
						if (listCidades != null) {
						    String optionStyle = "OptionBody1";
						    int countResult = 0;					
						    countResult++;
						    for (int i = 0; i < listCidades.size(); i++) {
						        countResult++;
						        String select = "";
						        if (cidade != null && cidade.equals(listCidades.get(i))) {
						            select = "selected";
						        }
						        if (countResult % 2 == 0) {
						            optionStyle = "OptionBody2";
						        } else if (countResult % 2 == 1) {
						            optionStyle = "OptionBody1";
						        }
						
						        out.println("<option value='" + listCidades.get(i) + "' class='" + optionStyle + "' " + select + ">");
						        out.println(listCidades.get(i));
						        out.println("</option>");
						    }
						    
							} 
							out.println("</select>");					
						
	         					 %>
         				</td>
         			</tr>		 
					<tr>
						<td class="Title">Endereço:</td>
						<td> <input type="text" name="endereco" class="Input" value="<%if(end!=null){%><%=end %><%} %>"
							maxlength="20" style="width: 150px;"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="radio" value="a"
							name="ativo" <%if (ativo != null && ativo.equals("a")) {%>
							checked="checked" <%}%> checked="checked"/>Todas <input type="radio" value="y"
							name="ativo" class="RadioE"
							<%if (ativo != null && ativo.equals("y")) {%> checked="checked"
							<%}%> />Ativos <input type="radio" value="n" name="ativo"
							class="RadioE" <%if (ativo != null && ativo.equals("n")) {%>
							checked="checked" <%}%> />Inativos</td>
					</tr>
					<tr>
						<td colspan="2" height="15"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="button"
							value="Limpar Filtro" id="Limpar Filtro" class="Button"
							onClick="location.href='equipamentoList.jsp?LF=a'"
							onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)"
							style="width: 100px;">&nbsp;&nbsp;&nbsp; <input
							type="submit" value="Filtrar" id="Filtrar" class="Button"
							onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)"
							style="width: 100px;"></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="20"></td>
			</tr>

			<tr>
				<td>
				<table width='549' cellspacing='0' cellpadding='0' border='0'
					style='border: 1px solid #000000;'>
					<tr>
						<td width='20' height='18' class='TableResultTitle'
							style='border-bottom: 1px solid #000000;'>&nbsp</td>
						<td width='110' class='TableResultTitle'
							style='border-bottom: 1px solid #000000;'><a
							href="?orderBy=nome" class="LinkTitle">Equipamento</a></td>
						<td width='300' class='TableResultTitle'
							style='border-bottom: 1px solid #000000;'><a
							href="?orderBy=endereco" class="LinkTitle">Endereço</a></td>
						<td width='9' class='TableResultTitle'
							style='border-bottom: 1px solid #000000;'>&nbsp;</td>
						<td width='110' class='TableResultTitle'
							style='border-bottom: 1px solid #000000;'><a
							href="?orderBy=cidade" class="LinkTitle">Cidade/UF</a></td>
					</tr>
					<%
						if (list != null) {
							String tableStyle = "TableResultBody1";
							int countResult = 0;

							for (int i = 0; i < list.size(); i++) {
								equipamento = (Equipamento) list.get(i);

								countResult++;

								if (countResult % 2 == 0) {
									tableStyle = "TableResultBody2";
								} else if (countResult % 2 == 1) {
									tableStyle = "TableResultBody1";
								}
					%>

					<tr>
						<td height='16' class='<%=tableStyle%>' align="right"><img
							src="imagens/topico.gif" height="15" width="15" alt=""></td>
						<td class='<%=tableStyle%>'><a
							href="equipamentoView.jsp?id=<%=equipamento.getIdEquipamento()%>"><%=equipamento.getNomeEquipamento()%></a></td>
						<td class='<%=tableStyle%>'><a
							href="equipamentoView.jsp?id=<%=equipamento.getIdEquipamento()%>"><%=equipamento.getEnderecoLogradouroEquipamento()%></a></td>
						<td class='<%=tableStyle%>'>&nbsp;</td>
						<td class='<%=tableStyle%>'><a
							href="equipamentoView.jsp?id=<%=equipamento.getIdEquipamento()%>"><%=equipamento.getEnderecoCidadeEquipamento() + "/"
							+ equipamento.getEnderecoEstadoEquipamento()%></a></td>
					</tr>

					<%
							}
						}
					%>
					<tr>
						<td colspan="5" height="5"></td>
					</tr>
					<tr>
						<td colspan="5" align="center"><%=pageViewNavigator%></td>
					</tr>
					<tr>
						<td colspan="5" height="5"></td>
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
		<td colspan="3" align="center" class="Footer"><!-- include do rodapé -->
		<jsp:include page="includes/footer.jsp" /> <!-- fim do include do rodapé -->
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterComprasControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Equipamento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.ComprasArquivo" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.ComprasArquivoTipo" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="equipamentoPai" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="compras" class="br.com.consorciosdf.intranet.modelo.Compras" scope="request"/>
<jsp:useBean id="comprasArquivo" class="br.com.consorciosdf.intranet.modelo.ComprasArquivo" scope="request"/>
<jsp:useBean id="comprasArquivoTipo" class="br.com.consorciosdf.intranet.modelo.ComprasArquivoTipo" scope="request"/>

<%
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String dataInclusao = "";
        String dataAlteracao = "";
        String nomeEquipamento = "";
         

        String menuHorizontal = "";
        String sucessAdd = "";
        String sucessAddClass = "Error";
        String msgSucess = "";

        List list = null;
        if ((request.getParameter("id")) != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            
 
            ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
            equipamento = manterEquipamentoControl.recuperarEquipamentos(id);

	    if(equipamento.getIdPai()!= 0){

		int idPai = equipamento.getIdPai();
            equipamentoPai = manterEquipamentoControl.recuperarEquipamentos(idPai);

	    }
	    
            dataInclusao = dateFormat.format(equipamento.getDataInclusao());
    }
        
        String strEquipamentoPai = "";
        if(equipamentoPai.getNomeEquipamento()!= null){
        	strEquipamentoPai = equipamentoPai.getNomeEquipamento();
        	}




        int userPerfil = Integer.parseInt((String) session.getAttribute("userPerfil"));

        if ((request.getParameter("sucess")) != null) {
            if (request.getParameter("sucess").equals("4")) {
                sucessAdd = "Equipamento alterado com sucesso.";
                sucessAddClass = "Sucess";
            }else if (request.getParameter("sucess").equals("1")) {
                sucessAdd = "Favor digitar os campos corretamente.";
            } else if (request.getParameter("sucess").equals("2")) {
                sucessAdd = "Houve um problema na transação.";
            }  
        }

        if (userPerfil <= 1) {


            menuHorizontal = "<a href='javascript:history.back()'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a> " +
                    "<a href='#'><img src='imagens/stock_edit-16.gif' border='0' alt='Editar'></a> " +
                    "<a href='archiveDelete.jsp?id=" + equipamento.getIdEquipamento() + "'><img src='imagens/stock_delete-16.gif' border='0' alt='Excluir'></a> " +
                    "<a href='#'><img src='imagens/stock_print-16.gif' border='0' alt='Imprimir'></a>";
        } else {
            menuHorizontal = "<a href='javascript:history.back()'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a> " +
                    "<a href='#'><img src='imagens/stock_print-16.gif' border='0' alt='Imprimir'></a>";
        }

%>

<!-- include de verificação do usuário -->
<%@ include file="includes/sessionVerify.jsp"%>
<!-- fim do include verificação do usuário -->

<html>
    <head>
        <title>INTRANET CONSORCIO SDF</title>
        <link rel="stylesheet" type="text/css" href="estilos/default.css" />

        <script language="javascript" type="text/javascript" src="scripts/default.js">
        </script>

        <!-- include do menu -->
        <jsp:include page="includes/menu.jsp" />
        <!-- fim do include do menu -->

    </head>

    <body>
        <!-- include do cabeçalho -->
        <jsp:include page="includes/header.jsp" />
        <!-- fim do include do cabeçalho -->

        <table width="779" border="0" cellspacing="0" cellpadding="0">

            <tr>
                <td width="220">&nbsp;</td>
                <td width="549" height="180" colspan="2" valign="top">
                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="Title">
                                <table width='549' cellspacing='0' cellpadding='0' border='0'>
                                    <tr>
                                        <td class="Title" width="420">Equipamentos - Visualizar</td>
                                        <td width="129" align="right">
                                            <a href="equipamentoList.jsp"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
                                            <a href="equipamentoEdit.jsp?id=<%= equipamento.getIdEquipamento()%>"><img src="imagens/stock_edit-16.gif" border="0" alt="Alterar"></a>
                                            <a href="equipamentoAdd.jsp"><img src="imagens/stock_new-16.gif" border="0" alt="Novo"></a>
                                            <a href="javascript:window.location.reload()"><img src="imagens/stock_refresh-16.gif" border="0" alt="Atualizar"></a>
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
                                <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do Equipamento:</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="170">N&deg; de Controle:</td>
                                        <td width="10">&nbsp;</td>
                                        <td width="359"><%= equipamento.getIdEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title" >Descrição:</td>
                                        <td>&nbsp;</td>
                                        <td><%= equipamento.getDescricaoEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Nome:</td>
                                        <td>&nbsp;</td>
                                        <td><%= equipamento.getNomeEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Endereço:</td>
                                        <td>&nbsp;</td>
                                        <td><%= equipamento.getEnderecoLogradouroEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Cidade:</td>
                                        <td>&nbsp;</td>
                                        <td><%= equipamento.getEnderecoCidadeEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Estado:</td>
                                        <td>&nbsp;</td>
                                        <td><%= equipamento.getEnderecoEstadoEquipamento()%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Equipamento Origem:</td>
                                        <td>&nbsp;</td>
                                    <td><%=strEquipamentoPai%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Data de Inclusão:</td>
                                        <td>&nbsp;</td>
                                        <td><%= dataInclusao%></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="20"></td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <!--tr>
                <td align="right">
                    <a href="getArchive?file=<%--= arquivo.getFileName() --%>">Baixar Arquivo <img src="imagens/stock_down-16.gif" border="0" alt="Baixar Arquivo" align="top"></a>
                </td>
        </tr-->
                    </table>
                </td>
                <td width="10"></td>
            </tr>
            <tr>
                <td colspan="3" height="20">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="3" align="center" class="Footer">
                    <!-- include do rodapé -->
                    <jsp:include page="includes/footer.jsp" />
                    <!-- fim do include do rodapé -->
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
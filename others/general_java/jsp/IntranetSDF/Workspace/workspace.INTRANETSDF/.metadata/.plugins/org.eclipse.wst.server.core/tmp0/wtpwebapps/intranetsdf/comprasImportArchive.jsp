<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterComprasControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.ComprasArquivoTipo" %>

<%@ page import="java.util.List" %>

<jsp:useBean id="comprasArquivoTipo" class="br.com.consorciosdf.intranet.modelo.ComprasArquivoTipo" scope="request" />

<%
    
    String idCompra = request.getParameter("id");
    String buttonSubmit = "";
    
    List listaTipos;
    ManterComprasControl manterComprasControl = new ManterComprasControl();
    listaTipos = manterComprasControl.recuperarTipos();
    
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
            <form method="POST" action="comprasUploadArchive.jsp">
            <table width="549" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="Title">Processo de Compra - Arquivos - Importar (Etapa 1 de 2)</td>
                </tr>
                <tr>
                    <td height="1" bgcolor="#000000"></td>
                </tr>
                <tr>
                    <td height="10"></td>
                </tr>
                <tr>
                    <td valign="top">
                        <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                            <tr>
                                <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                <td colspan="4" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do arquivo:</td>
                            </tr>
                            <tr>
                                <td colspan="5" height="5"></td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td width="120" height="18" class="Title">Descrição:</td>
                                <td width="9"></td>
                                <td width="400"><input type="text" name="descricao" class="Input"
                                   maxlength="255"
                                   style="width:250px;"></td>
                                <td width="10"></td>
                            </tr>
                            <tr>
                                <td colspan="5" height="5"></td>
                            </tr>
                            <tr class="TableResultBody1">
                                <td height="18">&nbsp;</td>
                                <td class="Title">Tipo de Arquivo:</td>
                                <td>&nbsp;</td>
                                <td>
                                    <%
                                
                                        if (listaTipos != null) {
                                            String optionStyle = "OptionBody1";
                                            int countResult = 0;

                                            out.println("<select name='tipo_arquivo' id='tipo_arquivo' class='Select' style='width:250px;'>");
                                            out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                                            countResult++;

                                            for (int i=0; i < listaTipos.size(); i++) {
                                                comprasArquivoTipo = (ComprasArquivoTipo) listaTipos.get(i);
                                                countResult++;

                                                if (countResult % 2 == 0) {
                                                    optionStyle = "OptionBody2";
                                                } else if (countResult % 2 == 1) {
                                                    optionStyle = "OptionBody1";
                                                }

                                                out.println("<option value='" + comprasArquivoTipo.getId() + "' class='" + optionStyle + "'>");
                                                out.println(comprasArquivoTipo.getDescricao());
                                                out.println("</option>");
                                            }

                                            out.println("</select>");
                                        } else {
                                            out.println("<select name='tipo_arquivo' class='Select' style='width:150px;' disabled='disabled'>");
                                            out.println("<option>Não há tipos de arquivos cadastrados.</option>");
                                            out.println("</select>");
                                        }

                                    %>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="5" height="10"></td>
                            </tr>
                            <tr>
                                <td width="10">&nbsp;</td>
                                <td colspan="3" align="right">
                                    <input type="hidden" name="idCompra" value="<%= idCompra %>">
                                    <input type='submit' value='Próximo' id='Próximo' class='Button' onMouseOut='buttonOn(this)' onMouseOver='buttonOver(this)' style='width: 100px;'>
                                </td>
                                <td width="10">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="5" height="10"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="10"></td>
                </tr>
            </table>
            </form>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.com.consorciosdf.intranet.controle.ManterOSControl"%>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"></jsp:useBean>
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioAtual" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

    <head>
        <title>SDF MOBILE</title>
        <link rel="stylesheet" type="text/css" href="../estilos/default.css" />

        <script language="javascript" type="text/javascript" src="../scripts/default.js">
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- include do menu -->
        <%--<jsp:include page="includes/menu.jsp" />--%>
        <!-- fim do include do menu -->

    </head>

    <body>
        <!-- include do cabeÃ§alho -->
        <jsp:include page="../includes/header2.jsp" />
        <!-- fim do include do cabeÃ§alho -->

                    <%
                    int pageList = Integer.parseInt(request.getParameter("page"));
                    if(request.getParameter("view") != null){
						
                    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    	DataUtil data = new DataUtil();
                    	int id = Integer.parseInt(request.getParameter("view"));
                    	ManterOSControl osControl = new ManterOSControl();
                    	os = osControl.recuperarOS(id);
                    	
                    	
                    	
                                int idOs = 0;

                                String userAutor = "";
                                String userDest = "";
                                String nomeEquipamento = "";
                                String endEquipamento = "";
                                String numSerie = "";
                                String numUPR = "";
                                String numCamera = "";
                                String numFlash = "";
                                String descDefeito = "";
                                String descObservacao = "";
                                String descReparo = "";
                                String descServExec = "";
                                String descFechamento = "";
                                String dataAnomalia = "";
                                String dataAbertura = "";
                                String dataServico = "";
                                String dataFechamento = "";
                                String difAnomaliaAbertura = "";
                                String difAberturaServico = "";
                                String difServicoFechamento = "";
                                String difAnomaliaFechamento = "";
                                String status = "";
                                String statusFechamento = "";
                                String statusStyle = "";
                                String prioridadeOS = "";
                                String prioridadeOSStyle = "";

                                String styleButtonEnabled = "class='Button'";
                                String styleButtonDisabled = "class='ButtonDisabled' disabled='disabled'";

                                String buttonAccept = styleButtonEnabled;
                                String buttonEdit = styleButtonEnabled;
                                String buttonClose = styleButtonEnabled;

                                if (os != null) {
                                    checkEquipamento = os.getCheckEquipamento();
                                    equipamento = os.getEquipamento();
                                    usuarioAutor = os.getUsuarioAutor();
                                    usuarioDest = os.getUsuarioDest();

                                    idOs = os.getId();
                                    userAutor = usuarioAutor.getNomeUsuario() + " "
                                            + usuarioAutor.getSobrenomeUsuario();
                                    nomeEquipamento = equipamento.getNomeEquipamento();
                                    endEquipamento = equipamento.getEnderecoLogradouroEquipamento();


                                    dataAbertura = dateFormat.format(os.getDataAbertura());

                                    if (os.getDataAnomalia() != null) {
                                        dataAnomalia = dateFormat.format(os.getDataAnomalia());
                                        difAnomaliaAbertura = data.diferecaData(os.getDataAnomalia(), os.getDataAbertura());
                                    }

                                    if (equipamento.getNumSerie() != 0) {
                                        numSerie = String.valueOf(equipamento.getNumSerie());
                                    }

                                    if (equipamento.getNumUPR() != 0) {
                                        numUPR = String.valueOf(equipamento.getNumUPR());
                                    }

                                    if (equipamento.getNumCamera() != 0) {
                                        numCamera = String.valueOf(equipamento.getNumCamera());
                                    }

                                    if (equipamento.getNumFlash() != null) {
                                        numFlash = equipamento.getNumFlash();
                                    }

                                    //quebra galho
                                    buttonClose = styleButtonEnabled;

                                    if (os.getDescDefeito() != null) {
                                        descDefeito = os.getDescDefeito();
                                    }

                                    if (os.getDescObservacao() != null) {
                                        descObservacao = os.getDescObservacao();
                                    }

                                    if (os.getDescReparo() != null) {
                                        descReparo = os.getDescReparo();
                                    }

                                    if (os.getDescServicoExecutado() != null) {
                                        descServExec = os.getDescServicoExecutado();
                                    }
                                    if (os.getDescFechamento() != null) {
                                        descFechamento = os.getDescFechamento();
                                    }

                                    if (os.getStatus() != null) {
                                        status = os.getStatus();

                                        if (status.equals("ab")) {
                                            statusFechamento = "ABERTO";
                                            statusStyle = "OSOpen";
                                        } else if (status.equals("an")) {
                                            statusFechamento = "ANÁLISE";
                                            statusStyle = "OSWorking";
                                        } else if (status.equals("ag")) {
                                            statusFechamento = "AGUARDANDO";
                                            statusStyle = "OSStandby";
                                        } else if (status.equals("fc")) {
                                            statusFechamento = "FECHADO";
                                            statusStyle = "OSClose";
                                            buttonEdit = styleButtonDisabled;
                                            buttonClose = styleButtonDisabled;
                                        }
                                    }
                                    

                                    if (os.getPrioridade() >= 0) {
                                        prioridadeOS = "";
                                        if (os.getPrioridade() == 0) {
                                            prioridadeOS = "Alta";
                                            prioridadeOSStyle = "OsPrioridadeAlta";
                                        } else if (os.getPrioridade() == 1) {
                                            prioridadeOS = "Média";
                                            prioridadeOSStyle = "OsPrioridadeMedia";
                                        } else if (os.getPrioridade() == 2) {
                                            prioridadeOS = "Baixa";
                                            prioridadeOSStyle = "OsPrioridadeBaixa";
                                        }

                                    }

                                    if (os.getDataFechamento() != null) {
                                        dataFechamento = dateFormat.format(os.getDataFechamento());
                                        difServicoFechamento = data.diferecaData(os.getDataServico(), os.getDataFechamento());
                                        difAnomaliaFechamento = data.diferecaData(os.getDataAnomalia(), os.getDataFechamento());

                                    }
                                    if (os.getDataServico() != null) {
                                        dataServico = dateFormat.format(os.getDataServico());
                                        difAberturaServico = data.diferecaData(os.getDataAbertura(), os.getDataServico());
                                    }

                                    /*
                                    if (os.getDataAbertura() != null && os.getDataServico() != null) {
                                    long difAberturaServicoTemp = os.getDataServico().getTime() - os.getDataAbertura().getTime();
                                    Date difAberturaServico = new Date(difAberturaServicoTemp);
                                    strDifAberturaServico = dateFormatDif.format(difAberturaServico);
                                    }
                                    if (os.getDataAnomalia() != null && os.getDataAbertura() != null) {
                                    long difServicoFechamentoTemp = os.getDataFechamento().getTime() - os.getDataServico().getTime();
                                    Date difServicoFechamento = new Date(difServicoFechamentoTemp);
                                    strDifServicoFechamento = dateFormatDif.format(difServicoFechamento);
                                    }
                                    if (os.getDataAnomalia() != null && os.getDataAbertura() != null) {
                                    long difAnomaliaFechamentoTemp = os.getDataFechamento().getTime() - os.getDataAnomalia().getTime();
                                    Date difAnomaliaFechamento = new Date(difAnomaliaFechamentoTemp);
                                    strDifAnomaliaAbertura = dateFormatDif.format(difAnomaliaFechamento);
                                    }*/





                                    /*if (os.getDataFechamento() == null) {
                                    statusFechamento = "ABERTO";
                                    statusStyle = "OSOpen";
                                    } else {
                                    dataFechamento = dateFormat.format(os.getDataFechamento());
                                    statusFechamento = "FECHADO";
                                    statusStyle = "OSClose";

                                    buttonEdit = styleButtonDisabled;
                                    buttonClose = styleButtonDisabled;
                                    }*/
                                }
                    %>
                    <center>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    	<tr>
                    		<td class="Title" align="center" colspan="2">
                    			<table>
                    				<tr>
                    					<td class="Title">Ordem de Serviço - Visualizar</td>
                    				</tr>
                    			</table>
                    		</td>
                    	</tr>
                    	
                    	<tr>
                            <td height="1" bgcolor="#000000" colspan="2"></td>
                        </tr>
                        
                        <tr>
                            <td height="8"></td>
                        </tr>
                        
                        <tr>
                        	<td width="3"></td>
                        	<td>
	                        	<table border="0" width="100%" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
		                	        <tr>
		                                <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
		                                <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados da OS:</td>
		                            </tr>
	                        	</table>
                        	</td>
                        </tr>
                        
                        <tr>
                        	<td width="3"></td>
                        	<td>
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                        		
                        		<tr class="TableResultBody1">      
                                        <td height="18" width="5">&nbsp;</td>
                                        <td class="Title" width="60">N&deg; de OS:</td>
                                        <td width="89"><%= idOs + os.getTipoOS().toUpperCase()%></td>
                                        <td class="Title" width="75">Status:</td>
                                        <td class="<%= statusStyle%>"> <%= statusFechamento%></td>                                    
                        		</tr>
                        		<tr class="TableResultBody2">
                        			<td height="18" width="5">&nbsp;</td>
                        			<td colspan="4">
                        				<table width="100%" border="0" cellspacing="0" cellpadding="0">
                        					<tr>
			                        			<td class="Title" width="75">Prioridade:</td>
			                        			<td class="<%=prioridadeOSStyle%>" width="75"> <%=prioridadeOS%></td>
			                        			<td class="Title" width="74">
                                            		Abertura:
                                        		</td>
                                        		<td>
                                            		<%=dataAbertura%>
                                        		</td>
		                        			</tr>
                        				</table>	
                        			</td>
                        		</tr>
                        	</table>
                        	</td>
                        </tr>
                        
                        <tr>
                        	<td height="8"></td>
                        </tr>
                        
                        <tr>
                        	<td width="3"></td>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Defeito:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descDefeito%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        
                        <tr>
                            <td height="8"></td>
                        </tr>
                        
                        <tr>
                        	<td width="3"></td>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Observações:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descObservacao%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        
                        <tr>
                            <td height="8"></td>
                        </tr>
                        
                        <tr>
                        	<td width="3"></td>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Materiais Utilizados:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descReparo%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="8"></td>
                        </tr>
                        
                        <tr>
                        <td width="3"></td>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Serviços Executados:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descServExec%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="8"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                       </table>
          				<table align="right">
							<tr><td height="10"></td></tr>
							<tr>
								<td><a href="../controlaListaOS?page=<%=pageList %>"><img alt="Sair" border="0" src="../imagens/BackArrowMobile.png"></a></td>
								<td width="10"></td>
							</tr>
						</table>
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td height="15"></td>
							</tr>
            
           					<tr>
						       <td colspan="2" class="Footer">
						           <table>
						               <tr>
						                   <td align="center">
						                           <!-- include do rodapÃ© -->
						                           <jsp:include page="../includes/footer.jsp" />
						                           <!-- fim do include do rodapÃ© -->
						                   </td>
						               </tr>
						           </table>
       							</td>
   							</tr>
						</table>
					</center>
    </body>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.consorciosdf.intranet.util.DataUtil"%></html>
<%}%>
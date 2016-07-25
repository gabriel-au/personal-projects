<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <td class="Title">Câmeras de Monitoramento de Trânsito</td>
            </tr>
            <tr>
                <td height="1" bgcolor="#000000"></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td>
                    <table width='549' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>
                        <tr>
                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>
                            <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Câmeras</td>
                        </tr>
                        <tr>
                            <td height='16' class='TableResultBody1' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                            <td class='TableResultBody1'><a href="#" onclick="openWindow('http://200.196.108.78:8080', 'cam_taguatinga', '800', '500')">Taguatinga | Centro de Convenções | Ponte JK | CONIC | Núcleo Bandeirante</a></td>
                        </tr>
                     <!--    <tr>
                            <td height='16' class='TableResultBody2' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                            <td class='TableResultBody2'><a href="#" onclick="openWindow('http://server2.primetecnologia.net:8082', 'cam_centro_conv', '800', '500')">Centro de Convenções</a></td>
                        </tr>
                        <tr>
                            <td height='16' class='TableResultBody1' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                            <td class='TableResultBody1'><a href="#" onclick="openWindow('http://server2.primetecnologia.net:8083', 'cam_ponte_jk', '800', '500')">Ponte JK</a></td>
                        </tr>
                        <tr>
                            <td height='16' class='TableResultBody2' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                            <td class='TableResultBody2'><a href="#" onclick="openWindow('http://server2.primetecnologia.net:8084', 'cam_conic', '800', '500')">CONIC</a></td>
                        </tr>
                        <tr>
                            <td height='16' class='TableResultBody1' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                            <td class='TableResultBody1'><a href="#" onclick="openWindow('http://server1.primetecnologia.net:8082', 'cam_bandeirante', '800', '500')">Núcleo Bandeirante</a></td>
                        </tr> -->
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
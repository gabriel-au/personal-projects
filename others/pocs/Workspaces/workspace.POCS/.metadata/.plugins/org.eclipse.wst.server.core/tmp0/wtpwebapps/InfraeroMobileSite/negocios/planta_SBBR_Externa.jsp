<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/includes/head.jsp" %>
<body>

<%@ include file="/includes/toolbar.jsp" %>

<div class="titulo">
<a href="<%=request.getContextPath()%>/negocios/planta_SBBR.jsp" >
<img src="<%=request.getContextPath()%>/imagens/botao-inicio-azul.png" /></a>
<div class="titulo_texto">Negócios</div>
</div>

<div class="arredondado">
<div class="arredondado_titulo"> TERMINAL DE PASSAGEIROS OU ÁREA EXTERNA</div>
<div class="arredondado_titulo">Área externa</div>

<p><i>
A imagem abaixo é de caráter ilustrativo. Clique na área desejada para visualizar o mapa. Como exemplo, você poderá visualizar a fotomontagem de um ponto qualquer de publicidade disponível no aeroporto.
</i> </p>


<img src="<%=request.getContextPath()%>/imagens/mp_central_p.png" 
alt="Imagem para definição de áreas (Terminal de Passageiros e área externa)" usemap="#Map" height="251" width="240" />
<map name="Map" id="Map">
  <area shape="rect" coords="0,75,240,251" href="planta_SBBR_Externa.jsp" alt="Área Externa" />
</map>

</div>




</body>
</html>
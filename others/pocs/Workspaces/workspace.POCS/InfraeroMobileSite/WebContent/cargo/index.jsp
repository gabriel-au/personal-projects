<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/includes/head.jsp" %>
<body>

<%@ include file="/includes/toolbar.jsp" %>

<div class="titulo">
<a href="<%=request.getContextPath()%>/" >
<img src="<%=request.getContextPath()%>/imagens/botao-inicio-azul.png" /></a>
<div class="titulo_texto">Infraero Cargo</div>
</div>

<div class="arredondado">

<div class="arredondado_chamada_simples_center">
<img src="<%=request.getContextPath()%>/imagens/brasil.png"/>
</div>

<div class="arredondado">
<div class="arredondado_link">
<a href="<%=request.getContextPath()%>/cargo/awb.jsp" >Localizar Awb</a>
</div>
</div>

<div class="arredondado">
<div class="arredondado_link">
<a href="<%=request.getContextPath()%>/cargo/carga.jsp" >Pesquisa de cargas</a>
</div>
</div>

</div>


</body>
</html>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="underbanner2"><span>Atualização</span></div>
<div class="marginform">
<s:form namespace="/atualizacao" >
	<s:hidden key="pojo.id"/>
   	<s:select list="#{'true':'Ativo','false':'Inativo'}" label="Ativo" name="pojo.ativo"/>
    <s:textfield label="Matrícula" key="pojo.matricula"/>
    <s:textfield label="Nome" key="pojo.nome"/>
    <s:textfield label="sobrenome" key="pojo.sobrenome"/>
    <s:textfield label="Usuário" key="pojo.username"/>
    <s:password label="Senha Internet" key="pojo.passwordWeb" showPassword="false" />
    <s:password label="Senha Internet" key="passwordWeb" showPassword="false"/>
    <s:password label="Senha Celular" key="pojo.passwordMob" showPassword="false"/>
    <s:password label="Senha Celular" key="passwordMob" showPassword="false"/>
    <s:textfield label="E-mail" key="pojo.email"/>
    
    <s:checkboxlist list="listaPerfil" listKey="id" listValue="nome"
    key="listaPerfilId" label="Perfis" 
    />
    
    <tr class="trsubform">
	<td colspan="2">
    <s:submit theme="simple" cssClass="button" value="Voltar" action="listar"/>
    <s:submit theme="simple" cssClass="button" value="Salvar" action="salvar"/>
    </td>
    </tr>
</s:form>
</div>
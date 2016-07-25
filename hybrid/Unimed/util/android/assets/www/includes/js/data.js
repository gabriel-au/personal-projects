
var objUsuario;

var listaEmpresas;

jQuery.ajaxCall = function() {
    var args = arguments[0] || {}; 
    var url = args.url;
    var callback = args.callback;
    window.location = "brq://network.requestAssincrono/" + callback + "/" + url;
};            

function supports_html5_storage() {
  try {
    return 'localStorage' in window && window['localStorage'] !== null;
  } catch (e) {
    return false;
  }
}

function login(){

	// VALIDA A UTILIZACAO DO LOCALSTORAGE
	if(!supports_html5_storage()){return false;};

	var doc = document;
	
	var usu = doc.getElementById('usuario').value;
	var sen = doc.getElementById('senha').value;
		   
	var json = $.ajaxCall({
			url: "http://convecaounimed2011.appspot.com/convencaounimed2011/login?user="+usu+"&pass="+sen, 
			callback:"getUsuario"
		});
}


function getUsuario(json_escaped){

	var json = decodeURIComponent(json_escaped).toString();
	
    objUsuario = eval(json);
	
	// VERIFICA SE O USUARIO ESTA PREENCHIDO
	if(objUsuario != null && objUsuario.statusText != "error"){
		if(objUsuario[0].id != "0"){
		
			localStorage['idUsuario'] = objUsuario[0].id;
			localStorage['usuario']   = objUsuario[0].usuario;
			
			$("#dadosUsuario").css("display","none");

			$("#listaDeEmpresas").html("");
			
			$("#listaDeEmpresas").css("display","");
			$("#listaDeEmpresas").removeClass("msg-login");
			
			$("#sy").css("display","");
		
			listarEmpresas();
			
		}else{
		
			$("#listaDeEmpresas").html(objUsuario[0].usuario);
			$("#listaDeEmpresas").addClass("msg-login");
		
		}
		
	}else{
	
		$("#dadosUsuario").css("display","");
		$("#listaDeEmpresas").css("display","");
		$("#listaDeEmpresas").html("erro");
	
	}
	
}

function listarEmpresas(){
	
	var json = $.ajaxCall({
			url: "http://convecaounimed2011.appspot.com/convencaounimed2011/empresas", 
			callback:"getEmpresas"
		});
	
}

function getEmpresas(json_escaped){
	
	var json = decodeURIComponent(json_escaped).toString();
	
    var empresas = eval(json);
	
	var out = "<ul class='lista-negocios'>";

	listaEmpresas = new Array(empresas.length);
	try{
	for(var i = 0;i < empresas.length;i++){
		
		listaEmpresas[i] = new Array(5); 
		listaEmpresas[i][0] = empresas[i].id;
		listaEmpresas[i][1] = empresas[i].nomeUsuario;
		listaEmpresas[i][2] = empresas[i].data;
		listaEmpresas[i][3] = empresas[i].empresa;
		listaEmpresas[i][4] = empresas[i].checked;
	
		if(empresas[i].checked == "S"){
			out += "<li class='active' id='lista_"+empresas[i].id+"' onclick=\"enviarCheck('"+i+"');\"><a href='javascript:void(0);'>"+empresas[i].empresa+"</a></li>";
			out += "<div class='dotted-border-lista'></div>";
		}else{
			out += "<li id='lista_"+empresas[i].id+"'  onclick=\"enviarCheck('"+i+"');\"><a href='javascript:void(0);'>"+empresas[i].empresa+"</a></li>";
			out += "<div class='dotted-border-lista'></div>";
		}
	}
	}catch(e){}
    
	out += "</ul>"; 
	
	$("#listaDeEmpresas").html(out.toString());

}

function enviarCheck(parm1,parm2){

	localStorage['idEmpresa']		 = listaEmpresas[parm1][0];
	localStorage['usuarioDoCheckin'] = listaEmpresas[parm1][1];
	localStorage['data']			 = listaEmpresas[parm1][2];
	localStorage['empresa']			 = listaEmpresas[parm1][3];
	localStorage['checked']			 = listaEmpresas[parm1][4];
	
	document.forms[0].action="check-in.html";
	document.forms[0].submit();
	
}

function executarCheck(){

	var c = "checkout";  
	
	if(localStorage['checked'] == "N"){
		c = "checkin";
	}

	var json = $.ajaxCall({
			url: "http://convecaounimed2011.appspot.com/convencaounimed2011/empresas/"+c+"/"+localStorage['idEmpresa']+"/"+localStorage['usuario'], 
			callback:"paginaNegocio"
		});

}

function paginaNegocio(){
	
	document.forms[0].submit();
}

function atualizarEmpresas(){
	
	var json = $.ajaxCall({
				url: "http://convecaounimed2011.appspot.com/convencaounimed2011/empresas", 
				callback:"setCheckEmpresa"
		});
}

function setCheckEmpresa(json_escaped){
	
	var json = decodeURIComponent(json_escaped).toString();
	
    var empresas = eval(json);
	
	try{
	for(var i = 0;i < empresas.length;i++){
		
		listaEmpresas[i][0] = empresas[i].id;
		listaEmpresas[i][1] = empresas[i].nomeUsuario;
		listaEmpresas[i][2] = empresas[i].data;
		listaEmpresas[i][3] = empresas[i].empresa;
		listaEmpresas[i][4] = empresas[i].checked;
		
		if(empresas[i].checked == "S"){
			$("#lista_"+empresas[i].id).addClass("active");
		}else{
			$("#lista_"+empresas[i].id).removeClass("active");
		}
	}
	}catch(e){}
	
	timedRefresh(30000);
	
}

function timedRefresh(timeoutPeriod) {
	
	setTimeout("atualizarEmpresas()",timeoutPeriod);
	
}
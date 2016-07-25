/*iniciar o uso do Ajax*/
function ajaxInit() {
	var req;
	try {	
		req = new ActiveXObject("Msxml2.XMLHTTP");
	} catch(ex) {
		try {
			req = new XMLHttpRequest();
		} catch(exc) {
			alert("Esse browser n�o tem recursos para uso do Ajax");
			req = null;
		}
	}
	return req;
}
function wmCarrega(webmorphos){
	var wmDiv = document.getElementById("conteudo");
	ajax = ajaxInit(); /*Inicia o Ajax*/
	ajax.open("GET", webmorphos, true); /*Envia a requisi��o ao servidor*/
	ajax.onreadystatechange=function() {
		/* readyState==1 Indica que est� carregando, nessa hora que colocamos aquele Loading...*/
		if (ajax.readyState==1){
			wmDiv.innerHTML = "<p style='text-decoration: blink;'>Carregando...</p>"; /*se preferir fa�a um gif piscando sei la*/
		}
		if (ajax.readyState==4){
		/*readyState==4 Indica que est� carregada a p�gina*/
			wmDiv.innerHTML = ajax.responseText; /*envia � div o conte�do*/
		}
	}
	ajax.send(null);
}
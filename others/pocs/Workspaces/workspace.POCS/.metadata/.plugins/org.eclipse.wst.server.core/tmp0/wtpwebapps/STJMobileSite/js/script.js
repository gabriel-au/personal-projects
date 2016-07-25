/*iniciar o uso do Ajax*/
function ajaxInit() {
	var req;
	try {	
		req = new ActiveXObject("Msxml2.XMLHTTP");
	} catch(ex) {
		try {
			req = new XMLHttpRequest();
		} catch(exc) {
			alert("Esse browser não tem recursos para uso do Ajax");
			req = null;
		}
	}
	return req;
}
function wmCarrega(webmorphos){
	var wmDiv = document.getElementById("conteudo");
	ajax = ajaxInit(); /*Inicia o Ajax*/
	ajax.open("GET", webmorphos, true); /*Envia a requisição ao servidor*/
	ajax.onreadystatechange=function() {
		/* readyState==1 Indica que está carregando, nessa hora que colocamos aquele Loading...*/
		if (ajax.readyState==1){
			wmDiv.innerHTML = "<p style='text-decoration: blink;'>Carregando...</p>"; /*se preferir faça um gif piscando sei la*/
		}
		if (ajax.readyState==4){
		/*readyState==4 Indica que está carregada a página*/
			wmDiv.innerHTML = ajax.responseText; /*envia à div o conteúdo*/
		}
	}
	ajax.send(null);
}
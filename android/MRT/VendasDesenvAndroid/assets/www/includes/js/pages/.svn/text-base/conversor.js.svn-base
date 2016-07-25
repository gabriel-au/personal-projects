/**
 * Dispatcher Default
 */
var _DISPATCHER = "ConversorDispatcher";

var interval = 1000;
var intervalObject = null;

/**
 * Fun��o de Falha
 */
function _failExec(object) {
	clearInterval(intervalObject);
}

/**
 * Fun��o para iniciar o processo do conversor
 */
function jsStartConverter() {
	BRQMob.log("[conversor] [java] startConverter");
	BRQMob.exec("successStartConverter", "_failExec", _DISPATCHER,
			"startConverter", []);
}

/**
 * Ap�s iniciar o processo do conversor com sucesso, coloca a fun��o
 * recoveryProgress() para que seja executa de acordo com a vari�vel {interval}
 * com o objetivo de apresentar o progresso do conversor
 */
function successStartConverter(object) {
	BRQMob.log("[conversor] recoveryProgress() ");
	intervalObject = window.setInterval("recoveryProgress()", interval);
}

/**
 * Fun��o respons�vel por verificar o andamento do processo de convers�o
 */
function recoveryProgress() {
	BRQMob.log("[conversor] [java] recoveryProgress");
	BRQMob.exec("successProgress", "_failExec", _DISPATCHER,
			"recoveryProgress", []);
}

/**
 * Fun��o respons�vel por apresentar o andamento do process de convers�o
 */
function successProgress(object) {
	BRQMob.log("[conversor] [apresenta lista de monitoramento]");

	var ul = document.getElementById('scroll');
	BRQMob.log("[conversor] " + JSON.stringify(object.jreturn));

	var progress = object.jreturn;

	for ( var i = 0; i < progress.length; i++) {

		var msg = progress[i].msg;
		var arq = progress[i].arq;
		var per = progress[i].per;

		if (msg != undefined) {

			if (msg != '[end]') {

				var li = document.createElement("li");
				li.innerHTML = msg;
				ul.appendChild(li);

			} else {

				clearInterval(intervalObject);
				document.getElementById('p-progress').innerHTML = '<strong>Processo Finalizado</strong>';
				
			}
		}

		if (arq != undefined && per != undefined) {
			document.getElementById('strong-arq').innerHTML = arq;
			document.getElementById('strong-per').innerHTML = per;
		}
	}
}

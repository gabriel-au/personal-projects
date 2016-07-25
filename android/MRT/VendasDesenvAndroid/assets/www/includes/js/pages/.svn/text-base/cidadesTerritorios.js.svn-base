$(document).ready(
	function(){
		execListaCidadesTerritorios();			
	}
);

function execListaCidadesTerritorios() {
	BRQMob.log("Executando a funcao - executarListaTerritorios.");		
	BRQMob.exec("sucesso", "falha", "VendasDispatcher","listaTerritorios", []);
}
		
function falha(result) {
	BRQMob.log("falha: " + result);
}

function sucesso(result){
	retornoListaTerritorios(result.cidades)
}

function retornoListaTerritorios(cidades) {
	
	BRQMob.log("entrando na funcao retornoListaTerritorios");
	
	var tbody_condicoes_pagamento = $("#bodyCidades");
	
	$.each(cidades, function(i,obj){
	
		if(i%2==0){
			tr= $('<tr/>', {});
		}else{
			tr= $('<tr/>', {"class":"off"});
		}			
		
		td_codigo= $('<td/>', {"style": "text-align: center"});
		$($('<label/>', {"class":"grid",text:obj.codigoTerritorio})).appendTo(td_codigo);
		$(td_codigo).appendTo(tr);
		
		td_nom_cid= $('<td/>', {});
		$($('<label/>', {"class":"left grid",text:obj.nomeCidade})).appendTo(td_nom_cid);
		$(td_nom_cid).appendTo(tr);
		
		td_uf= $('<td/>', {});
		$($('<label/>', {"class":"left grid",text:obj.unidadeFederal})).appendTo(td_uf);
		$(td_uf).appendTo(tr);
		
		$(tr).appendTo(tbody_condicoes_pagamento);	
	});

}	
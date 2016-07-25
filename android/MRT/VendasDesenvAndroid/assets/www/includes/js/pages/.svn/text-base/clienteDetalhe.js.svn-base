/*
* Executado ao iniciar a tela
*/
$(document).ready(function() {

	BRQNavegacao.adicionarPilhaNavegacao('clienteDetalhe');
	
	
	$("#favoritos").click(
		function() {
			atualizarFavorito(objStorageClientes.codigoCliente);
	});
	
	execDetalharCliente(getCliente().codigoCliente);
});

/*
* Método que recupera as informações do cliente
* @return codigoCliente - código do cliente
*/
function execDetalharCliente(codigoCliente) {
	BRQMob.log("Lendo dados execDetalharCliente");
	BRQMob.exec("retornoDetalharCliente", "falha", "RelacionamentoDispatcher", "detalharCliente", [ codigoCliente ]);
}

/*
* Método que carrega as informaçoes de cliente
* @return cliente - retorno do json
*/
function retornoDetalharCliente(retorno){
	BRQMob.log("Lendo dados retornoDetalharCliente");
	
	//Atualizando botão de favoritos
	if(retorno.Detalhe.cliente.indicativoFavorito == "1"){
		$("#favoritos").addClass("highlight");
	}
	
	$("#nomeCliente").html(retorno.Detalhe.cliente.nomeCliente);
	$("#nomeFantasia").html(retorno.Detalhe.cliente.nomeFantasia);
	$("#situacao").html(retorno.Detalhe.cliente.situacao);
	$("#telefone").html(retorno.Detalhe.cliente.telefone);
	$("#segmento").html(retorno.Detalhe.cliente.descricaoSegmento);
	$("#positivar").html(retorno.Detalhe.cliente.positivar);
	$("#territorio").html(retorno.Detalhe.cliente.codigoTerritorio);
	$("#endereco").html(retorno.Detalhe.cliente.tipoLogradouro +" "+ retorno.Detalhe.cliente.descricaoLogradouro);
	$("#bairro").html(retorno.Detalhe.cliente.nomeBairro);
	$("#cidade").html(retorno.Detalhe.cliente.nomeCidade);
	$("#uf").html(retorno.Detalhe.cliente.uf);
	$("#email").html(retorno.Detalhe.cliente.email);
	
	$('#descricaoJustificativaCliente').html(retorno.Detalhe.justificativaNaoAtendimento.descricaoJustificativa);	
	$('#dataJustificativaCliente').html(retorno.Detalhe.justificativaNaoAtendimento.dataJustificativa);
	
	//Verificando se foi realizado o cadastro de justificativa com sucesso ou falha
	var objStorageJustificativa = sessionStorage.getObject("objStorageJustificativa");
	if(objStorageJustificativa != null){
		if(objStorageJustificativa.isCadastroSucesso){
			$('#liSucesso').css('display', 'inline');
		}else{
			$('#liErro').css('display', 'inline');
		}
		$('#divMensagemJustificativa').css('display', 'inline');
		sessionStorage.removeItem("objStorageJustificativa");
	}
	
}

/*
* Método para atualizar se o cliente é favorito ou não
* @return codigoCliente - código do cliente
*/
function atualizarFavorito(codigoCliente){
	BRQMob.log("Lendo dados atualizarFavorito");
	BRQMob.exec("retornoAtualizarFavorito", "falhaFavorito", "RelacionamentoDispatcher", "atualizarFavorito", [ codigoCliente , retornarFavorito() ]);
}

/*
* Método que idetifica se favorito está selecionado ou não
* @return favorito - retorna S (selecionado) ou N (não está selecionado)
*/
function retornarFavorito(){
	return ($("#favoritos").hasClass("highlight") ? "S" : "N");
}

/**
* Retorno da atualização de cliente favorito
* @param result - sucesso na atualização
*/
function retornoAtualizarFavorito(result){
	BRQMob.log("retornoAtualizarFavorito");
	
	//Limpar Div Erro
	limparDivMensagem();
	
	var favorito = retornarFavorito();
	//Atualizar a  modificação na lista do storage
	var objStorageClientes = getCliente();
	for(var index=0;index<objStorageClientes.clientes.length;index++){
		if(objStorageClientes.clientes[index].codigoCliente == objStorageClientes.codigoCliente){
			if(favorito == "S"){
				objStorageClientes.clientes[index].indicativoFavorito = "1";
			}else{
				objStorageClientes.clientes[index].indicativoFavorito = "";
			}
			sessionStorage.setObject(CONST_CLIENTE, objStorageClientes);
			break;
		}
	}
}

/**
* Método para tratamento de falha no retorno das consultas
* @param result - mensagem de falha
*/
function falha(result){
	BRQMob.log("falha: " + result);
	$("#mensagem").html(result);
	$("#divMensagem").css('display', 'inline');
}

/**
* Método para tratamento de falha no retorno das consultas
* @param result - mensagem de falha
*/
function falhaFavorito(result){
	//Manter o estado do botão
	var favorito = retornarFavorito();
	if(favorito == "S"){
		$("#favoritos").removeClass("highlight");
	}else{
		$("#favoritos").addClass("highlight");
	}
	
	falha(result);
}

/**
* Método para limpar erro na tela
*/
function limparDivMensagem(){
	$("#divMensagem").css('display', 'none');
	$("#falha").html('');
}

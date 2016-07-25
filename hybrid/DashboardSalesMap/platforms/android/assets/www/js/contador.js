$().ready(function() {
	
	var canalNome = Array("","Internet","Chat","Balcão Caixa","ATM","Lotericos","Internet Banking","Comercializador");
	var produtoNome = Array("","Capitalização","Previdência","Consórcio","Seguro de vida","Seguro de riscos diversos","Seguro saúde","Seguro crédito");
	var estadoNome = Array("", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
	var regiaoNome = Array("","Norte","Nordeste","Centro Oeste","Sul","Sudeste");
	
	var regiao = new Array([1,3,4,14,22,23],[2,5,6,10,15,17,18,20,26],[7,9,11,12,27],[16,21,24],[8,13,19,25]);
	
	var estadoAux;
	var canalAux;
	var produtoAux;
	
	var soma = 0;
	var meta = 500000;
	var countVendaSoma = 36; //numero de vendas
	
	atualizaMeta(meta);
	
	var somaFiltro = 0;
	
	var barraMetaNivelMax = 19; //19
	var barraMetaNivelMin = 430; //165
	var barraMetaMediaNivel = 45 / 10;
	
	var barraMeta = barraMetaNivelMax + barraMetaNivelMin; //450 - 27 - 45; //450 = 0% / -27 = 10% / -45 = 20%
	
	$("#barra_meta").css('clip', 'rect(' + barraMeta + 'px, 60px, 450px, 0px)'); //rect(19px,60px,450px,0px);
	
	var venda = new Array(27);
	var vendaEstado = new Array(27);
	var vendaCanal = new Array(7);
	var vendaProduto = new Array(7);
	
	//estado
	for (estadoAux=0; estadoAux<27; estadoAux++) {
		//canal
		venda[estadoAux] = new Array(7);
		for (canalAux=0; canalAux <7;canalAux++) {
			//produtoAux
			venda[estadoAux][canalAux] = new Array(7);
			for (produtoAux=0; produtoAux <7;produtoAux++) {
				//valor
				venda[estadoAux][canalAux][produtoAux] = 0;
			}
		}
	}
	
	function calculaVenda(estado, canal, produto) {
		var x;
		var xa = 0;
		var xx = (venda.length) - 1;
		var y;
		var ya = 0;
		var yy = (venda[0].length) - 1;
		var z;
		var za = 0;
		var zz = (venda[0][0].length) - 1;
		
		var somaVenda = 0;
		
		if (estado != null) {
			xa = estado;
			xx = estado;
		}
		
		if (canal != null) {
			ya = canal;
			yy = canal;
		}
		
		if (produto != null) {
			za = produto;
			zz = produto;
		}
		
		for (x=xa; x<=xx; x++) {
			for (y=ya; y<=yy;y++) {
				for (z=za; z<=zz; z++) {
					somaVenda = somaVenda + venda[x][y][z];
				}
			}
		}

		return somaVenda;
	}
	
	function vender() {
		var disponivel = 4000;
		
		if (meta < soma) {
			disponivel = (meta / 5) - 10000;
		}
		
		var randEstado = rand(27);
		var randCanal = rand(7);
		var randProduto = rand(7);
		
		var valorVenda = (rand(disponivel)+10000);
		
		venda[randEstado][randCanal][randProduto] = venda[randEstado][randCanal][randProduto] + valorVenda;
		soma = soma + venda[randEstado][randCanal][randProduto];
		
		if (vendaEstado[randEstado] == undefined) {
			vendaEstado[randEstado] = valorVenda;
		} else {
			vendaEstado[randEstado] = vendaEstado[randEstado] + valorVenda;
		}
		
		if (vendaCanal[randCanal] == undefined) {
			vendaCanal[randCanal] = valorVenda;
		} else {
			vendaCanal[randCanal] = vendaCanal[randCanal] + valorVenda;
		}
		
		if (vendaProduto[randProduto] == undefined) {
			vendaProduto[randProduto] = valorVenda;
		} else {
			vendaProduto[randProduto] = vendaProduto[randProduto] + valorVenda;
		}
		
		/*console.log('###########################################');
		console.log('--> VENDAS');
		console.log('Estado -> ' + estadoNome[randEstado+1]);
		console.log('Canal -> ' + canalNome[randCanal+1]);
		console.log('Produto -> ' + produtoNome[randProduto+1]);
		console.log('Valor -> ' + valorVenda);
		console.log('###########################################');*/
		
		filtro();
		alertaVenda(estadoNome[randEstado+1], randCanal);
		
		//console.log('somafiltro: ' + somaFiltro);
		
		
	}
	
	function alertaVenda(estado, canal) {
		var address = {'address': estado + ', Brazil'};
		var marker;
		var icon = 'img/venda_' + canal + '.png'; //Icon_Venda.png
		
		new geocoder.geocode(address, function(results, status) {
			marker = new google.maps.Marker({
				position: results[0].geometry.location,
				icon: icon,
				clickable: false,
				map: map
			  });
		});
		
		setTimeout(function(){
			marker.setMap();
			},3000);
			
		//console.log('alerta estado: ' + estado);
	
	}
	
	$("input[name=mais]").click(function(){
		somaFiltro = somaFiltro + 50000;
		
		filtro();
	});
	
	$("input[name=menos]").click(function(){
		if (somaFiltro > 0) {
			somaFiltro = somaFiltro - 50000;
		}
		
		filtro();
	});
	
	function filtro() {
		var canalCount = 0;
		var produtoCount = 0;
		
		if ($("input[name=pais][value=br]").is(":checked")) {
			somaFiltro = soma;
		} else {
			somaFiltro = 0;
			$("input[name=estado]:checked").each(function()	{
				if (vendaEstado[(parseInt($(this).attr("value")))-1] != undefined) {
					somaFiltro = somaFiltro + vendaEstado[(parseInt($(this).attr("value")))-1];
					
				}
			});
		}
		
		$("input[name=canal]:checked").each(function()	{
			canalCount++;
		});
		
		$("input[name=produto]:checked").each(function()	{
			produtoCount++;
		});
		
		alteraValorBarra(somaFiltro.toString() + '00');
		alteraBarraMeta(somaFiltro,meta);
		atualizaMetaMapa();
		alteraBarraProdutos(somaFiltro);
	}
	
	function rand(size) {
		var rand = Math.floor(Math.random() * size);
		
		if (rand > 0) {
			rand = rand -1;	
		}
		
		return rand;
	}

	function alteraValorBarra(valor) {
		var i;
		var valorLength = valor.length;
		
		
		if (valorLength > 0) {
			valor = replaceAll(valor, '.', '');
			valor = replaceAll(valor, ',', '');
			
			for (i=0; i<(11-valorLength); i++) {
				valor = "0" + valor;
			}
			
			var valorArray = valor.split("");
			
			for (i=1; i<=11; i++) {
				$('#dig' + i).attr("src",'img/' + valorArray[i-1] + '.jpg');
			}
		}
			
	}
	
	function alteraBarraMeta(valorRealizado,valorMeta) {
		//console.log('##############################');
		//console.log('meta: ' + valorMeta);
		//console.log('realizado: ' + valorRealizado);
		//console.log('meta realizada: ' + ((valorRealizado * 100) / valorMeta));
		
		var metaRealizada = parseInt((valorRealizado * 100) / valorMeta);
		var barraMetaNivel = 0;
		
		if (metaRealizada > 10 && metaRealizada <= 100) {
			barraMetaNivel = barraMeta - parseInt((barraMetaMediaNivel * metaRealizada)) + 20;// - 37;
		} else if (metaRealizada <= 10 && valorRealizado > 0) {
			barraMetaNivel = barraMeta - 25;
		}
		
		//console.log('metaRealizada: ' + metaRealizada);
		//console.log('nivel: ' + barraMetaNivel);

		$("#barra_meta").css('clip', 'rect(' + barraMetaNivel + 'px, 60px, 450px, 0px)');
		
	}
	
	function atualizaMetaMapa(valorRealizado) {
		var i,x;
		var metaRealizada = new Array(5);
		var style = new Array();
		
		/*style.push({
			polygonOptions: {
			fillColor: "#00FF00",
			fillOpacity: 0.1
			}
		});*/
		
		for (i=0; i<regiao.length; i++) {
			for (x=0; x<regiao[i].length; x++) {
				if (vendaEstado[regiao[i][x]] == undefined) {
					vendaEstado[regiao[i][x]] = 0;
				}
				
				if (metaRealizada[i] == undefined) {
					metaRealizada[i] = 0;
				}
				
				metaRealizada[i] = metaRealizada[i] + vendaEstado[regiao[i][x]];
				
				//console.log('regiao: ' + (i+1) + " " + regiao[i][x]);
			}
			
			var metaMapa = parseInt((metaRealizada[i] * 100) / (meta / 5));
			
			//var metaMapa = parseInt(((valorRealizado / 5) * 100) / (meta / 5));
			
			//console.log('--------------------------------------------------------------------------');
			//console.log('| REGIÃO: ' + regiaoNome[i+1] + ' | META REALIZADA: ' + metaRealizada[i] + ' | %: ' + metaMapa + ' | META REGIAO: ' + (meta / 5) + ' |');
			
			//console.log('#####################################################');
			
			if (metaMapa >= 0 && metaMapa < 10) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d9061b",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 10 && metaMapa < 20) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d9191a",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 20 && metaMapa < 30) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d94217",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 30 && metaMapa < 40) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d97a13",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 40 && metaMapa < 50) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d8a911",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 50 && metaMapa < 60) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#d5d010",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 60 && metaMapa < 70) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#b8d412",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 70 && metaMapa < 80) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#92d417",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 80 && metaMapa < 90) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#61d21e",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 90 && metaMapa < 100) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#38c623",
							fillOpacity: 0.2
							}
					});
				} else if (metaMapa >= 100) {
					style.push({
						where: "REGION_NAME = '" + regiaoNome[i+1] + "'",
							polygonOptions: {
							fillColor: "#23c227",
							fillOpacity: 0.2
							}
					});
				}
		}
		
		layer.setOptions({
		  query: {
			select: 'Geometry',
			from: '1zKNAHdkAPYXQtGou4TPiLRoNDxpJJ7ydkw78T9E'
		  },
		  styles: style
		});
		
		layer.set('styles', style);
		
		if (layerSup.getMap() == undefined && mapFilter) {
			layer.setMap(map);
		}
		
		//console.log('#####################################################');
	}
	
	function alteraBarraProdutos(valor) {
		var produtoBarra = Array(7);
		var i;
		
		if (valor > 0) {
			$("input[name=produto]:checked").each(function() {
				produtoBarra[(parseInt($(this).attr("value")))-1] = valor;
			});
			
			/*produtoBarra[0] = parseInt(valor * 0.085); //capitalização
			produtoBarra[1] = parseInt(valor * 0.18); //previdencia
			produtoBarra[2] = parseInt(valor * 0.13); //consórcio
			produtoBarra[3] = parseInt(valor * 0.11); //seguro de vida
			produtoBarra[4] = parseInt(valor * 0.065); //seguros diversos
			produtoBarra[5] = parseInt(valor * 0.2); //seguro saúde
			produtoBarra[6] = parseInt(valor * 0.23); //seguro crédito*/
			
			for (i=0; i<produtoBarra.length; i++) {
				if (produtoBarra[i] == undefined) {
					produtoBarra[i] = 0;
				} else {
					switch (i) {
						case 0:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.085);
							break;
						case 1:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.18);
							break;
						case 2:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.13);
							break;
						case 3:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.11);
							break;
						case 4:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.065);
							break;
						case 5:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.2);
							break;
						case 6:
							produtoBarra[i] = parseInt(produtoBarra[i] * 0.23);
							break;
					}
					
				}
				
				$('#slide' + (i+1)).html(formataValores(produtoBarra[i].toString()));
				
				//console.log('Valor produto ' + i + ' ' + formataValores(produtoBarra[i].toString()));
			}
		}
	}
	
	function replaceAll(string, token, newtoken) {
		while (string.indexOf(token) != -1) {
			string = string.replace(token, newtoken);
		}
		return string;
	}
	
	function formataValores(valor) {
		//valor = valor.toString();
		var valorNew = "";
		
		if (valor.length > 0) {
			var valorAux = parseInt(valor.length / 3);
			var valorSobra = parseInt(valor.length % 3);
			
			var i;
			
			for (i=0; i<valor.length; i++) {
				valorNew = valorNew + valor.substr(i,1);
				
				if (((i+1) == valorSobra || (((i+1)-valorSobra)%3) == 0) && ((i+1) != valor.length)) {
					valorNew = valorNew + ".";
				}
			}
		}
		
		valorNew = valorNew + ",00"; //centavos
		//console.log('formatar valor: ' + valorNew);
		
		return valorNew;
	}
	
	function atualizaMeta(valor) {
		$('#metaValor').html(formataValores(valor.toString()));
	}
	
	$("input[name=teste]").click(function(){
		calculaVenda(null,null,4);
	});
	
	
	
	$("input[name=pais][value=br]").click(function(){
		filtro();
	});
	
	$("input[name=estado]").click(function(){
		filtro();
	});
	
	$("input[name=canal]").click(function(){
		filtro();
	});
	
	$("input[name=produto]").click(function(){
		filtro();
	});

	setInterval(function(){vender()},5000); //5 segundos
	
});
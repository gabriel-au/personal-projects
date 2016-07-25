$().ready(function() {

	
	var canalNome = Array("","Internet","Chat","Balcão Caixa","ATM","Lotericos","Internet Banking","Comercializador");
	var produtoNome = Array("","Capitalização","Previdência","Consórcio","Seguro de vida","Seguro de riscos diversos","Seguro saúde","Seguro crédito");
	var estadoNome = Array("", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
	
	//verifica dispositivo e o tamanho da tela
	/*var ua = navigator.userAgent.toLowerCase();
	var isAndroid = ua.indexOf("android") > -1;
	
	if(isAndroid) {
		//alert('SCREEN: ' + screen.availHeight + ' DOCUMENT: ' + $(document).height() + ' WINDOW: ' + $(window).height() + ' DIV: ' + ($('#map_canvas').height()));
		//var screenAvailble = $(window).height();
		
		//$('#map_canvas').height((screenAvailble - 62));
		//$('meta').css('margin-top', (screenAvailble - 73) + 'px');
		//$('#PanZoomBtn').css('margin-top', (screenAvailble -  68) + 'px');
		//html('ANDROID: ' + $(document).height());
	} else {
		//$('#txt').html('NON-ANDROID: ' + $(document).height());
		//var screenAvailble = screen.availHeight;
		//$('#map_canvas').height((screenAvailble - 85));
		
		//console.log('SCREEN: ' + screenAvailble);
		//console.log('DOCUMENT: ' + $(document).height());
		//console.log('WINDOW: ' + $(window).height());
		//console.log('DIV: ' + ($('#map_canvas').height()));
	}*/
	
	$("input[name=estado]").attr("checked", $("input[name=pais][value=br]").is(":checked"));
	//atualizaValorCampoRegiao();
	
	$("input[name=pais][value=br]").click(function(){
		$("input[name=estado]").attr("checked", $("input[name=pais][value=br]").is(":checked"));
		
		if ($("input[name=pais][value=br]").is(":checked")) {
			layerSup.setMap();
			
			if (mapFilter) {
				layer.setMap(map);
			}
			
			map.setCenter(mapCenter);
			map.setZoom(4);
		}
		
		atualizaValorCampoRegiao();
	});
	
	$("input[name=estado]").click(function(){
		if ($("input[name=pais][value=br]").is(":checked")) {
			$("input[name=estado]").attr("checked", false);
			$("input[name=pais][value=br]").attr("checked", false);
			$(this).attr("checked", true);
		}
		
		//if ($(this).is(":checked")) {
			//setLatLngBounds(estadoNome[parseInt($(this).attr("value"))]);
		//}
		
		atualizaValorCampoRegiao();
	});
	
	$("input[name=canal]").click(function(){
		atualizaValorCampoCanal();
	});
	
	$("input[name=produto]").click(function(){
		atualizaValorCampoProduto();
	});

	$("#metaFilter").click(function(){
		if ($(this).attr("class") == "switchOn") {
			$(this).attr("class", "switchOff");
			layer.setMap();
			
			mapFilter = false;
		} else {
			$(this).attr("class", "switchOn");
			if (layerSup.getMap() == undefined) {
				layer.setMap(map);
			}
			
			mapFilter = true;
		}
	});
	
	$("#PanZoomBtn").click(function(){
		estadoSelect = '';
		
		map.panTo(mapCenter);
		map.setCenter(mapCenter);
		
		map.setZoom(4);
		
		layerSup.setMap();
		
		if (mapFilter) {
			layer.setMap(map);
		}
		
		$(this).css("visibility", "hidden");
		infoBox.close();
		clearMarkers();
	});
	
	function atualizaValorCampoRegiao() {
		var regiao = Array();
		var regiaoCount = 0;
		var htmlRegiao = "";
		var brChecked = false;
		
		$("input[name=estado]").each(function()	{
			regiaoCount++;
		});
		
		$("input[name=estado]:checked").each(function()	{
			regiao.push(estadoNome[parseInt($(this).attr("value"))]);
			
			if (!($("input[name=pais][value=br]").is(":checked"))) {
				setLatLngBounds(estadoNome[parseInt($(this).attr("value"))]);
			}
		});
		
		if (regiaoCount == regiao.length && !($("input[name=pais][value=br]").is(":checked"))) {
			$("input[name=pais][value=br]").attr("checked", true);
		} else if (regiao.length > 0 && regiaoCount != regiao.length && $("input[name=pais][value=br]").is(":checked")) {
			$("input[name=pais][value=br]").attr("checked", false);
		}
		
		var x = 0;
		
		countBound = regiao.length;
		
		if (regiao.length == 0) {
			htmlRegiao = "Selecione";
		} else if(regiaoCount == regiao.length) {
			htmlRegiao = "Brasil";
		} else {
			var i;
	
			for (i=0; i< regiao.length; i++) {
				if (htmlRegiao == "") {
					htmlRegiao = htmlRegiao + regiao[i];
				}
				else {
					if ((htmlRegiao.length + regiao[i].length) < 23) {
						htmlRegiao = htmlRegiao + ", " + regiao[i];
					} else {
						x++;
					}
				}
				//console.log('regiao: ' + $(this).attr("value"));
			}
		}
		
		if (x > 0) {
			htmlRegiao = htmlRegiao + " + " + x;
		}
		
		$(".valor_campo3").html(htmlRegiao);
		
	}
	
	function atualizaValorCampoCanal() {
		var canal = Array();
		var canalCount = 0;
		var htmlCanal = "";
		
		$("input[name=canal]").each(function()	{
			canalCount++;
		});
		
		$("input[name=canal]:checked").each(function()	{
			canal.push($(this).attr("value"));
		});
		
		var x = 0;
		
		if (canal.length == 0) {
			htmlCanal = "Selecione";
		} else if(canal.length == canalCount) {
			htmlCanal = "Todos";
		} else {
			var i;
	
			for (i=0; i< canal.length; i++) {
				if (htmlCanal == "") {
					htmlCanal = htmlCanal + canalNome[canal[i]];
				}
				else {
					if ((htmlCanal.length + canalNome[canal[i]].length) < 20) {
						htmlCanal = htmlCanal + ", " + canalNome[canal[i]];
					} else {
						x++;
					}
				}
				
			}
		}
		
		if (x > 0) {
			htmlCanal = htmlCanal + " + " + x;
		}
		
		$(".valor_campo4").html(htmlCanal);
		
	}
	
	function atualizaValorCampoProduto() {
		var produto = Array();
		var produtoCount = 0;
		var htmlProduto = "";
		
		$("input[name=produto]").each(function()	{
			produtoCount++;
		});
		
		$("input[name=produto]:checked").each(function()	{
			produto.push($(this).attr("value"));
		});
		
		var x = 0;
		
		if (produto.length == 0) {
			htmlProduto = "Selecione";
		} else if(produto.length == produtoCount) {
			htmlProduto = "Todos";
		} else {
			var i;
	
			for (i=0; i< produto.length; i++) {
				if (htmlProduto == "") {
					htmlProduto = htmlProduto + produtoNome[produto[i]];
				}
				else {
					if ((htmlProduto.length + produtoNome[produto[i]].length) < 20) {
						htmlProduto = htmlProduto + ", " + produtoNome[produto[i]];
					} else {
						x++;
					}
				}
				
			}
		}
		
		if (x > 0) {
			htmlProduto = htmlProduto + " + " + x;
		}
		
		$(".valor_campo5").html(htmlProduto);
		
	}
	
	atualizaValorCampoRegiao();
	atualizaValorCampoCanal();
	atualizaValorCampoProduto();

});


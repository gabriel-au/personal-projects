
var chart;
var chartDrill;
var chartData;            

var erro = "Ocorreu um erro interno no Sistema.";

// INICIO CENTRAL DE CRÉDITO SEGURO


function initCCSO(){ 		
    
    var codigoCampanha = document.getElementById("comboCampanha").value;
    
    document.getElementById("chartdiv3").innerHTML = "<table style='width:100%; height:100%;'><tr><td style='width:50%; height:100%;'><div id='ccso1' style='width:100%; height:100%;'></div></td><td style='width:50%; height:100%;'><div id='ccso2' style='width:100%; height:100%;'></div></td></tr></table>";	
    
    $.ajaxCal({
        url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsOportSit.do?codigoCampanha="+codigoCampanha,
        callback: "endCCSO"
    });    
}


function endCCSO(json_escaped){ 	
	
    var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);
    
    try{	
        
        chart = new AmCharts.AmPieChart();
        chart.dataProvider = chartData;
        chartDrill = chart;
        
        chart.titleField = "titulo";
        chart.valueField = "valor";
        chart.depth3D = 5;
        chart.startEffect = ">";
        chart.angle = 5;
        chart.innerRadius = "30%";
        chart.startDuration = 2;
        chart.labelRadius = 15;
        
        chart.labelRadius = 30;
        chart.labelText = "[[percents]]%";
        
        var legend = new AmCharts.AmLegend();
        legend.align = "bottom";
        legend.markerType = "circle";
        chart.addLegend(legend);
        
        chart.addListener("clickSlice",getTopCorretores);				
        
        document.getElementById("ccso1").innerHTML = "";
        chart.write("ccso1");
        
        getSituacaoDaOportunidadePorCampanha();
        
    }catch(e){	
        $("#ccso1").html(erro);
    }
}


function getTopCorretores(event){

	var codigoCampanha = chartDrill.dataProvider[event.dataItem.index].codigoCampanha;
    var codigoSituacao =  chartDrill.dataProvider[event.dataItem.index].codigoSituacao;
			
	
	$.ajaxCal({
			  url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsCorrQtdOport.do?codigoCampanha="+codigoCampanha+"&codigoSituacao="+codigoSituacao,
			  callback: "getTopCorretoresEnd"
	});   
	
}

function getTopCorretoresEnd(json_escaped){
	
    var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);
	
	chart = new AmCharts.AmSerialChart();
	
	document.getElementById("comboSituacao").style.visibility = "hidden";
	
	chart.dataProvider = chartData;
	chart.categoryField = "nome";
	
	var graph = new AmCharts.AmGraph();
	graph.valueField = "quant";
	graph.type = "column";
	graph.fillColors = ["#990000","#FF0000"];
	chart.addGraph(graph);	
	
	document.getElementById("chartdiv3").innerHTML = "";
	chart.write("chartdiv3");
}


function getSituacaoDaOportunidadePorCampanha(){

	document.getElementById("comboSituacao").style.visibility = "";	
	
	var codigoCampanha = document.getElementById("comboCampanha").value;
    var codigoSituacao = document.getElementById("comboSituacao").value;
	
    $.ajaxCal({
        url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsQtdOport.do?codigoCampanha="+codigoCampanha+"&codigoSituacao="+codigoSituacao,
        callback: "getSituacaoDaOportunidadePorCampanhaEnd"
    });   

}

function getSituacaoDaOportunidadePorCampanhaEnd(json_escaped){
    
	var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);           
	
    chart = new AmCharts.AmSerialChart();
	chart.dataProvider = chartData;
	chart.categoryField = "nomeMes";
    
	var graph = new AmCharts.AmGraph();
	graph.valueField = "quant";
	graph.type = "column";
	graph.fillColors = ["#990000","#FF0000"];
	chart.addGraph(graph);	
	
	document.getElementById("ccso2").innerHTML = "";
	chart.write("ccso2");
}

function getSituacaoPorCampanha(){

	document.getElementById("chartdiv3").innerHTML = "<table style='width:100%; height:100%;'><tr><td style='width:50%; height:100%;'><div id='ccso1' style='width:100%; height:100%;'></div></td><td style='width:50%; height:100%;'><div id='ccso2' style='width:100%; height:100%;'></div></td></tr></table>";		
	
	document.getElementById("comboSituacao").style.visibility = "";	
	
	var codigoCampanha = document.getElementById("comboCampanha").value;
   
	$.ajaxCal({
			  url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsOportSit.do?codigoCampanha="+ codigoCampanha,
			  callback: "getSituacaoPorCampanhaEnd"
	});   
}


function getSituacaoPorCampanhaEnd(json_escaped){
	
	var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);    
	
	try{	
		chart = new AmCharts.AmPieChart();
		chart.dataProvider = chartData;
		chart.titleField = "titulo";
		chart.valueField = "valor";
		chart.depth3D = 5;
		chart.startEffect = ">";
		chart.angle = 5;
		chart.innerRadius = "30%";
		chart.startDuration = 2;
		chart.labelRadius = 15;
		chart.addListener("clickSlice",getTopCorretores);				
		
		chart.labelRadius = 30;
		chart.labelText = "[[percents]]%";
		
		var legend = new AmCharts.AmLegend();
		legend.align = "bottom";
		legend.markerType = "circle";
		chart.addLegend(legend);
		
		document.getElementById("ccso1").innerHTML = "";
		chart.write("ccso1");				
		
		getSituacaoDaOportunidadePorCampanha();
		
	}catch(e){
		
		$("#ccso1").html(erro);
	}
}


function getSituacaoPorMesesDaCampanha(){

	var codigoCampanha = document.getElementById("comboCampanha").value;
    var codigoSituacao = document.getElementById("comboSituacao").value;
	
	chart = new AmCharts.AmSerialChart();
	
	var html = $.ajax({
				  type:"POST",
				  async:false,
				  cache:false,				  
				 url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsQtdOport.do?codigoCampanha="+codigoCampanha+"&codigoSituacao="+codigoSituacao				  
	});             
	
	$.ajaxCal({
			  url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataConsQtdOport.do?codigoCampanha="+codigoCampanha+"&codigoSituacao="+codigoSituacao,
			  callback: "getSituacaoPorMesesDaCampanhaEnd"
	});  

}

function getSituacaoPorMesesDaCampanhaEnd(json_escaped){
	
	var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);   
	
	chart = new AmCharts.AmSerialChart();

	chart.dataProvider = chartData;
	chart.categoryField = "nomeMes";
	
	var graph = new AmCharts.AmGraph();
	graph.valueField = "quant";
	graph.type = "column";
	graph.fillColors = ["#990000","#FF0000"];
	chart.addGraph(graph);	
	
	document.getElementById("ccso2").innerHTML = "";
	chart.write("ccso2");
	
}



// FIM CENTRAL DE CRÉDITO SEGURO


// ANALISES DO SISTEMA DWADP

function loadDwadp(){ 	

	$("#labelDetalhe").html("");
	$("#chartdiv2").html("Carregando");

    var mes = document.getElementById("comboMes").value;
          
	
	$.ajaxCal({
			  url:"http://portalgovernanca.brq.com:9081/DWGP-PainelAcompanhamento/dataProdSupex.do?mes="+ mes,
			  callback: "loadDwadpEnd"
	});  
	
}

function loadDwadpEnd(json_escaped){ 	
	
	var json = decodeURIComponent(json_escaped).toString();
    chartData = eval(json);   
	
	chart = new AmCharts.AmSerialChart();
	
	chart.dataProvider = chartData;
	chart.categoryField = "nomeSupex";
	chart.rotate = true;
	chart.marginLeft= 15;
	//	chart.touchEventEnabled = true;
	
	var catAxis = chart.categoryAxis;
	catAxis.labelsEnabled  = false;
	catAxis.gridAlpha = 0;
	catAxis.gridPosition = "middle";
	
	var valAxis = new AmCharts.ValueAxis();
	valAxis.gridAlpha = 0;
	chart.addValueAxis(valAxis);
	
	var graph = new AmCharts.AmGraph();
	graph.valueField = "producaoAuto";
	graph.type = "column";
	graph.fillColors = ["#DADADA","#000000"];
	graph.gradientOrientation = "horizontal";
	graph.labelText = "[[category]]";
	graph.labelPosition = "bottom";
	graph.balloonText="[[category]]: R$ [[value]]";
	graph.pointPosition = "middle";
	chart.addGraph(graph);
	
	$("#chartdiv2").html("");
	chart.write("chartdiv2");
	
}



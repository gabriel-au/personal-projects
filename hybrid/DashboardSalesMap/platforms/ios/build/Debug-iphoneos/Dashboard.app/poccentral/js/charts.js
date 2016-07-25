var dataUltMov;

$(document).ready(function() {

                  
                  Highcharts.setOptions({
                                        global: {
                                        useUTC: false
                                        }
                  });
                  
				// DEFINE O GRFICO DE NVEL DE SERVIO
				var nivelServicoChart = {
			
					chart: {
						renderTo: 'container'
					},
					
					title: {
						//text: 'Planejamento de Tráfego'
						text: null
					},
					
					subtitle: {
						//text: 'Nível de Serviço - Maio/2011'
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos Percentuais
							title: {
							text: 'Percentual'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {
						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},	
					credits:false,					
					series: [{
						name: 'N.S Previsto',						
						marker: {
							radius: 4
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'N.S Recebido',						
						lineWidth: 4					

					},{
						name: 'N.S Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'						

					}]
				};
				
				// DEFINE O GRAFICO DE VOLUME
				var volumeChart = {
			
					chart: {
						renderTo: 'containerVolume'
					},
					
					title: {
						//text: 'Volume - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},					
					credits:false,
					series: [{
						name: 'Vol. Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'Vol. Recebido',						
						lineWidth: 2					

					},{
						name: 'Vol. Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'					

					}]
				};
				
				// DEFINE O GRFICO DE TMA
				var TMAChart = {
			
					chart: {
						renderTo: 'containerTMA'
					},
					
					title: {
						//text:  'TMA - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							},
							point: {
								events: {
									click: function() {											
											showTMA();
									}
								}
							},
						}
					},					
					credits:false,
					series: [{
						name: 'TMA Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'TMA Recebido',						
						lineWidth: 2					

					} ,{
						name: 'TMA Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'					

					}]
				};
				
				// DEFINE O GRFICO DE TRFEGO
				var trafegoChart = {
			
					chart: {
						renderTo: 'containerTrafego'
					},
					
					title: {
						//text: 'Tráfego - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},					
					credits:false,
					series: [{
						name: 'Traf. Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'Traf. Recebido',						
						lineWidth: 2					

					},{
						name: 'Traf. Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'				

					} ]
				};

				nivelServicoChart.series[0].data = valor1;
				nivelServicoChart.series[1].data = valor2;
				nivelServicoChart.series[2].data = valor9;
					
				volumeChart.series[0].data = valor3;
				volumeChart.series[1].data = valor4;				
				volumeChart.series[2].data = valor10;				
					
				TMAChart.series[0].data = valor5;
				TMAChart.series[1].data = valor6;
				TMAChart.series[2].data = valor11;
				
				trafegoChart.series[0].data = valor7;
				trafegoChart.series[1].data = valor8;
				trafegoChart.series[2].data = valor12;
								
				chart = new Highcharts.Chart(nivelServicoChart);
				//chart = new Highcharts.Chart(volumeChart);
				chart = new Highcharts.Chart(TMAChart);					
				chart = new Highcharts.Chart(trafegoChart);
				
//##################################################################################################################
				
				var volumeChart = {
				
				chart: {
					renderTo: 'containerVolume',
					defaultSeriesType: 'spline',
					marginRight: 10,
					events: {
						load: function() {
        
							// set up the updating of the chart each second
							var series = this.series[0];
							setInterval(function() {
								var x = (new Date()).getTime(), // current time
									y = Math.random();
								series.addPoint([x, y], true, true);
                
								dataUltMov = (new Date()).getTime();							
								$("#viewDataUltMov").html(Highcharts.dateFormat('<b>%H:%M:%S <br> %d/%m/%Y</b>', dataUltMov));	
								
								if(Highcharts.numberFormat(y, 2) > Highcharts.numberFormat(0.70, 2)){
									$("#viewIndicadorVolume").html("<span class='indicadorUP'></span>" + Highcharts.numberFormat(y, 2));
								}else{																									
									$("#viewIndicadorVolume").html("<span class='indicadorDown'></span>" + Highcharts.numberFormat(y, 2));
								}
								
							}, 4000);														
						}
					}
				},
				title: {
					//text: 'Volume'
					text: null
				},
				xAxis: {
					type: 'datetime',
					tickPixelInterval: 150
				},
				yAxis: {
					title: {
						text: 'Value'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					formatter: function() {
			                return '<b>'+ this.series.name +'</b><br/>'+
							Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ 
							Highcharts.numberFormat(this.y, 2);
					}
				},
				legend: {
					enabled: false
				},
				exporting: {
					enabled: false
				},
				credits:false,
				series: [{
					name: 'Random data',
					data: (function() {
						
						var data = [],
							time = (new Date()).getTime(),
							i;						
						for (i = -19; i <= 0; i++) {													
							data.push({
								x: time + i * 5000,								 
								y: Math.random()
							});
						}
						
						return data;
					})()
				}]
			};
			
			chart = new Highcharts.Chart(volumeChart);								
	});

function init(){

				$("#chartArea2").show();				

				$("#chartArea3").removeClass("box t8x t2y");	
				$("#chartArea3").addClass("box t4x t2y");					
				
				$("#chartArea5").removeClass("box t4x t2y");				
				$("#chartArea5").addClass("container t4x t2y");
				
				$("#chartArea5").html("<div class='box t2x t1y'><h1>Nível de Serviço</h1><span class='indicadorUP'></span><span class='indicadorValue'><strong>99</strong>,00 %</span></div><div class='box t2x t1y lastX'><h1>Volume</h1><span class='indicadorValue'><strong><div id='viewIndicadorVolume'></div></strong></span></div><div class='box t2x t1y'><h1>TMA</h1><span class='indicadorStill'></span><span class='indicadorValue'><strong>498</strong>,00</span></div><div class='box t2x t1y lastY lastX'><h1>Tráfego</h1><span class='indicadorUP'></span><span class='indicadorValue'><strong>9999</strong>,99</span></div>");	

				$("#titChart1").html("Planejamento de Tráfego - Nível de Serviço");
				$("#titChart2").html("Volume");
				$("#titChart3").html("TMA");
				$("#titChart4").html("Tráfego");
				
// DEFINE O GRFICO DE NVEL DE SERVIO
				var nivelServicoChart = {
			
					chart: {
						renderTo: 'container'
					},
					
					title: {
						//text: 'Planejamento de Tráfego'
						text: null
					},
					
					subtitle: {
						//text: 'Nível de Serviço - Maio/2011'
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos Percentuais
							title: {
							text: 'Percentual'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {
						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},			
					credits:false,					
					series: [{
						name: 'N.S Previsto',						
						marker: {
							radius: 4
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'N.S Recebido',						
						lineWidth: 4					

					},{
						name: 'N.S Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'						

					}]
				};										
			
				// DEFINE O GRFICO DE TMA
				var TMAChart = {
			
					chart: {
						renderTo: 'containerTMA'
					},
					
					title: {
						//text:  'TMA - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							},
							point: {
								events: {
									click: function() {											
											showTMA();
									}
								}
							},
						}
					},					
					credits:false,
					series: [{
						name: 'TMA Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'TMA Recebido',						
						lineWidth: 2					

					} ,{
						name: 'TMA Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'					

					}]
				};
				
				// DEFINE O GRFICO DE TRAFEGO
				var trafegoChart = {
			
					chart: {
						renderTo: 'containerTrafego'
					},
					
					title: {
						//text: 'Tráfego - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},					
					credits:false,
					series: [{
						name: 'Traf. Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'Traf. Recebido',						
						lineWidth: 2					

					},{
						name: 'Traf. Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'				

					} ]
				};

				nivelServicoChart.series[0].data = valor1;
				nivelServicoChart.series[1].data = valor2;
				nivelServicoChart.series[2].data = valor9;														
				
				TMAChart.series[0].data = valor5;
				TMAChart.series[1].data = valor6;
				TMAChart.series[2].data = valor11;
				
				trafegoChart.series[0].data = valor7;
				trafegoChart.series[1].data = valor8;
				trafegoChart.series[2].data = valor12;
								
				chart = new Highcharts.Chart(nivelServicoChart);				
				chart = new Highcharts.Chart(trafegoChart);			
				chart = new Highcharts.Chart(TMAChart);			
};

function showTMA(){			
				
				$("#chartArea2").hide();
				
				$("#chartArea3").addClass("box t8x t2y");												
				$("#chartArea5").addClass("box t4x t2y");
				
    
    
    var tabela = "<table cellspacing='0'> "+
    "<thead> "+
    "<tr> " + 
    "<th style='width:50%;'>Processos</th> "+
    "<th style='width:50%; text-align:right;'>Percentual</th> "+
    "</tr> "+
    "<thead> "+
    "<tbody> "+
    "<tr> "+
    "<td>Local Referenciado</td> "+
    "<td style='text-align:right'>45%</td> "+
    "</tr> "+
    "<tr class='off'> "+
    "<td>Reebolso</td> "+
    "<td style='text-align:right'>26.8%</td> "+
    "</tr> "+
    "<tr> "+
    "<td>Cálculo de Reembolso</td> "+
    "<td style='text-align:right'>12.8%</td> "+
    "</tr> "+
    "<tr class='off'> "+
    "<td>Posição                                                                       / Financeira</td> "+
    "<td style='text-align:right'>8.5%</td> "+
    "</tr> "+
    "<tr> "+
    "<td>AAMH</td> "+
    "<td style='text-align:right'>6.2%</td> "+
    "</tr> "+
    "</tbody> "+
    "</table> ";
    
				$("#chartArea5").html(tabela);																				
				
				$("#titChart1").html("TMA");				
				$("#titChart3").html("Processos");
				$("#titChart4").html("SABS");
				
			// DEFINE O GRFICO DE TMA
				var TMAChartContainer = {
			
					chart: {
						renderTo: 'container'
					},
					
					title: {
						//text:  'TMA - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},					
					credits:false,
					series: [{
						name: 'TMA Previsto',						
						marker: {
							radius: 2
						},
						dashStyle: 'shortdot'	
						
					},{
						name: 'TMA Recebido',						
						lineWidth: 2					

					},{
						name: 'TMA Projetado',						
						lineWidth: 2,
						dashStyle: 'shortdot'					

					} ]
				};
				TMAChartContainer.series[0].data = valor5;
				TMAChartContainer.series[1].data = valor6;
				TMAChartContainer.series[2].data = valor11;
				
				chart = new Highcharts.Chart(TMAChartContainer);
			
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'containerTrafego',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						//text: 'SABS'
						text: null
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
						}
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: '#FFFFFF',
								connectorColor: '#FFFFFF',
								formatter: function() {
									return this.point.name +': '+ this.y +' %';
								}
							}
						}
					},
					credits:false,
				    series: [{
						type: 'pie',
						name: 'SABS ',
						data: [
							['Local<br> Referenciado',   45.0],
							['Reembolso', 26.8],
							{
								name: 'Cálculo de<br> Reembolso',    
								y: 12.8,
								sliced: true,
								selected: true
							},
							['Posição<br> Financeira',    8.5],
							['AAMH',     6.2]							
						]
					}]
				});												
		
			
				var TMAChartContainerDetalhe = {
			
					chart: {
						renderTo: 'containerTMA'
					},
					
					title: {
						//text:  'Processos - Maio/2011'
						text: null
					},
					
					subtitle: {
						text: null
					},
					
					xAxis: {
						type: 'datetime',
						categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 
						'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],

						tickInterval: 7 * 24 * 3600 * 1000, // one week
						tickWidth: 0,
						gridLineWidth: 1,
						labels: {
							align: 'left',
							x: 3,
							y: 11 
						}
					},
					
					yAxis: [{ // Eixo dos valores
							title: {
							text: 'Valores'
							},
							labels: {
								align: 'left',
								x: 3,
								y: 16,
								formatter: function() {
									return Highcharts.numberFormat(this.value, 0);
								}
							},
							showFirstLabel: false,
							opposite: true
					}],									
					
					tooltip: {
						shared: true,
						crosshairs: true						
					},
					
					plotOptions: {						
						series: {
							cursor: 'pointer',							
							marker: {
								lineWidth: 1
							}
						}
					},					
					credits:false,
					series: [{
						name: 'Local Referenciado',						
						marker: {
							radius: 2
						}												
					},{
						name: 'Reembolso',						
						lineWidth: 2					

					},{
						name: 'Cálculo de Reembolso',						
						lineWidth: 2					

					},{
						name: 'Posição Financeira',						
						lineWidth: 2					

					},{
						name: 'AAMH',						
						lineWidth: 2					

					} ]
				};		

				TMAChartContainerDetalhe.series[0].data = valor5;
				TMAChartContainerDetalhe.series[1].data = valor1;				
				TMAChartContainerDetalhe.series[2].data = valor3;
				TMAChartContainerDetalhe.series[3].data = valor3;				
				TMAChartContainerDetalhe.series[4].data = valor7;
				
				chart = new Highcharts.Chart(TMAChartContainerDetalhe);
			}	
var agenciaEstado = Array("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
var areaEstado;

var mapCenter = new google.maps.LatLng(-17.224758,-63.984375);
var icone = new google.maps.LatLng(-15.790932,-47.885284);
var marker;
var markersArray = new Array();
var map;
var geocoder = new google.maps.Geocoder();
var layer;
var layerSup;
var estadoSelect = '';
var latlngbounds = new google.maps.LatLngBounds();
var countBound = 0;
var layerStyle = '';

var mapFilter = true; //true - ligado / false - desligado
var infoWindow = new google.maps.InfoWindow();
var agenciaIcon = "img/Pin_Icon.png";

var agencias = new Array();

agencias[6] = [
	['Capital',-15.804167,-47.884254,1],
	['Brasilia Shopping',-15.78544,-47.886357,2],
	['CRS 510',-15.811372,-47.909789,3],
	['QS 03 - Taguatinga',-15.872186,-48.010082,4],
	['QE 40 - Guara II',-15.838664,-47.981029,5]
];

agencias[24] = [
   	['Av. Washington Luis, 6971',-23.547622,-46.633072,6],
   	['Brooklin',-22.896418,-47.062912,6],
   	['Osasco',-22.705255,-47.639923,7],
   	['Hortolândia',-21.164563,-47.812557,8],
   	['Jundiai',-23.180764,-46.896744,9]
];

var infoBox = new InfoBox();

var infoWindowHTML1 = '<div class="topo">' +
    	'<h1>Agência Santa Rosa - 345</h1>' +
        '<p>Av. Goitacases, 1605 Castanheiras - Rio Branco AC</p>' +
        '<div class="topo tel"><h1>Telefone</br>(73) 3020.3593</h1></div>' +
    '</div>' +
   '<div class="miolo">' +
        	'<p><b>Total em Vendas</b> <span>R$ <b>124.849.948,00</b></span> </p>' +
   '</div>' +
   '<div class="idtabela">' +
   		'<p><b>05/06/2012</b> <span><b>Histórico de Vendas</b></span> </p>' +
        '<div class="detalhes">' +
        	'<ul>' +
            	'<li class="i1">Hora</li>' +
                '<li class="i2">Produto</li>' +
                '<li class="i3">Vendedor</li>' +
                '<li class="i4">Valor</li>' +
            '</ul>' +
        '</div>' +
   '</div>' +
   '<div class="tabelao">' +
   		    '<table class="bordasimples" width="373px" height="90px">' +
                '<tr>' +
                '<td bgcolor="#e7e7e7" width="62px">4:20</td>' +
                '<td bgcolor="#e7e7e7" width="77px">Seg. Saúde</td>' +
                '<td bgcolor="#e7e7e7" width="86px">Lucas Azevedo</td>' +
                '<td bgcolor="#e7e7e7">R$ 4.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">4:20</td>' +
                '<td bgcolor="#e7e7e7">Captalização</td>' +
                '<td bgcolor="#e7e7e7">Carolina Ribeiro</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<td bgcolor="#e7e7e7">4:20</td>' +
                '<td bgcolor="#e7e7e7">Seg. Auto</td>' +
                '<td bgcolor="#e7e7e7">Andreson Silva</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">4:20</td>' +
                '<td bgcolor="#e7e7e7">Captalização</td>' +
                '<td bgcolor="#e7e7e7">Carolina Ribeiro</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">4:20</td>' +
                '<td bgcolor="#e7e7e7" >Captalização</td>' +
                '<td bgcolor="#e7e7e7">Carolina Ribeiro</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
            '</table>' +
   '</div>';
   
 var infoWindowHTML2 = '<div class="topo2">' +
    	'<p>Voltar <span>Venda de Capitalização</span></p>' +
   '</div>' +
   '<div class="miolo2">' +
        	'<p><b>Cliente:</b> Antônio Ricardo Oliveira</br>' +
			'<b>Produto:</b> Título de Capitalização XPTO</br>' +
			'<b>Vendedor: Lucas Azevedo </b></br>' +
			'<b>Valor:  R$ 30.000,00</b></p>' +
   '</div>' +
   '<div class="idtabela2">' +
   		'<p><b>Vendas de Lucas</b> <span>R$ 1.150.000,00</span></p>' +
   '</div>' +
   '<div class="tabelao">' +
   		    '<table class="bordasimples" width="374px" height="90px">' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">Capitalização</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">Seg. Mulher</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<td bgcolor="#e7e7e7">Capitalização</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">Seg. Auto</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
                '<tr>' +
                '<td bgcolor="#e7e7e7">Seg. Auto</td>' +
                '<td bgcolor="#e7e7e7">R$ 2.000,00</td>' +
                '</tr>' +
            '</table>' +
   '</div>'
  '</div>';

function initialize() {
	var mapOptions = {
		zoom: 4,
		mapTypeId: google.maps.MapTypeId.HYBRID,
		disableAutoPan:false,
		streetViewControl:false,
		mapTypeControl:true,
		mapTypeControlOptions: {
	        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
	        position: google.maps.ControlPosition.TOP_CENTER
	    }
	};
	
	map = new google.maps.Map(document.getElementById("map_canvas"),
	mapOptions);
	
	map.setCenter(mapCenter);
	
	layer = new google.maps.FusionTablesLayer();
	//layer.setMap(map);
	
	layerSup = new google.maps.FusionTablesLayer();
	//layerSup.setMap(map);
	
	layer.setOptions({
	  suppressInfoWindows: true
	});
	
	layerSup.setOptions({
	  suppressInfoWindows: true
	});
	
	/*google.maps.event.addListener(map, 'zoom_changed', function() {
		if (map.getZoom() < 5) {
			layerSup.setMap();
			layer.setMap(map);
		}
	});*/
	
	var areaOptions = {
      strokeColor: "#FF0000",
      strokeOpacity: 0,
      strokeWeight: 1,
      fillColor: "#FF0000",
      fillOpacity: 0,
      map: map,
      center: new google.maps.LatLng(-15.790932,-47.885284),
      radius: 150000
    };
    areaEstado = new google.maps.Circle(areaOptions);

	
	google.maps.event.addListener(map, 'click', function(e) {
		getEstado(e.latLng);
	});
	
	google.maps.event.addListener(map, 'zoom_changed', function(e) {
		var panZoomBtn = document.getElementById('PanZoomBtn');
		panZoomBtn.style.visibility = "visible";
	});
	
	google.maps.event.addListener(layer, 'click', function(e) {
		getEstado(e.latLng);
	});
	
	google.maps.event.addListener(layerSup, 'click', function(e) {
		infoBox.close();
		getEstado(e.latLng);
	});
	
	google.maps.event.addListener(areaEstado, 'click', function(e) {
		infoBox.close();
		if (estadoSelect != '' && map.getZoom() > 4) {
			getEstado(e.latLng);
		} else {
			addressZoom('DF');
		}
	});
	
}

function setPan() {
	map.panBy(-90,0);
}

function toggleBounce() {
	if (marker.getAnimation() != null) {
		marker.setAnimation(null);
	} else {
		marker.setAnimation(google.maps.Animation.BOUNCE);
	}
}

function getEstado(latLong) {
	var estado = '';
	
	new geocoder.geocode({'latLng': latLong}, function(results, status) {
		if(status == google.maps.GeocoderStatus.OK) {
			var latitude = results[0].geometry.location.lat();
			var longitude = results[0].geometry.location.lng();
			estado = results[0].address_components.short_name;
			
			for (var component in results[0]['address_components']) {
				for (var i in results[0]['address_components'][component]['types']) {
					if (results[0]['address_components'][component]['types'][i] == "administrative_area_level_1") {
						estado = results[0]['address_components'][component]['short_name'];
						
						if (estado == "São Paulo") {
							estado = "SP";
						}
						
						addressZoom(estado);
					}
					
					//console.log('endereço: ' + results[0]['formatted_address']);
				}
			}
		}
	});
}

function setLatLngBounds(estado) {
	var address = {'address': estado + ', Brazil'};
	new geocoder.geocode(address, function(results, status) {
		
		latlngbounds.extend(results[0].geometry.location);
		
		map.setCenter(latlngbounds.getCenter());
		map.panToBounds(latlngbounds);
		
		if (countBound > 0 && countBound < 4) {
			map.setZoom(6);
		} else {
			map.setZoom(5);
		}
	});	
}

function addressZoom(estado) {
	if (estadoSelect == '' && map.getZoom() <= 4) {
		estadoSelect = estado;
		
		var address = {'address': estado + ', Brazil'};
		new geocoder.geocode(address, function(results, status) {
			layerSup.setOptions({
				query: {
				select: 'Geometry',
				from: '1zKNAHdkAPYXQtGou4TPiLRoNDxpJJ7ydkw78T9E'
				},
			styles: [{
				polygonOptions: {
				fillColor: "#000000",
				fillOpacity: 0.3
				}
			},
			{
			where: "PROVINCE_FLAG = '" + estado + "'",
				polygonOptions: {
				fillColor: "#00FF00",
				fillOpacity: 0.01
				}
			}]
			});
			
			map.panTo(results[0].geometry.location);
			map.setCenter(results[0].geometry.location);
			
			if (estado == 'DF') {
				map.setZoom(10);
			} else {
				map.setZoom(6);
			}
			
			layer.setMap();
			layerSup.setMap(map);
			
			var m, a;
			markersArray = new Array();
			
			for (a=0; a < agenciaEstado.length; a++) {
				if (agenciaEstado[a] == estado) {
					break;
				}
			}
			
			if (agencias[a] != undefined) {
				for (m=0; m < agencias[a].length; m++) { 
					//var location = [[agencias[a][m][1]],[agencias[a][m][2]]];
				 
					  marker = new google.maps.Marker({
						position: new google.maps.LatLng(agencias[a][m][1], agencias[a][m][2]),
						icon:agenciaIcon,
						map: map
					  });
					  
					  //marker.set('location', location);
					  
					  markersArray.push(marker);
					  
					  var boxText = document.createElement("div");
						//boxText.style.cssText = "border: 1px solid black; margin-top: 8px; background: white; padding: 5px;";
					  	boxText.id = "tooltip1";
						boxText.className = "tool1";
						boxText.innerHTML = infoWindowHTML1; //"TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>TESTE<br>";
						
						var boxTextView = 0;
						
						var myOptions = {
								 content: boxText
								,disableAutoPan: false
								,maxWidth: 0
								,pixelOffset: new google.maps.Size(15,-80)
								,zIndex: null
								,boxStyle: { 
								  background: null
								  ,opacity: 1
								  ,width: "425px"
								  ,height: "265px"
								 }
								,closeBoxMargin: "10px 12px 2px 2px"
								,closeBoxURL: "http://www.google.com/intl/en_us/mapfiles/close.gif"
								,infoBoxClearance: new google.maps.Size(120, 80)
								,isHidden: false
								,pane: "floatPane"
								,enableEventPropagation: false
						};
						
						//infoBox.setOptions(myOptions);
    					//infoBox.open(map, marker);
					  
					  google.maps.event.addListener(marker, 'click', (function(marker, m) {
						return function() {
							infoBox.setOptions(myOptions);
        					infoBox.open(map, marker);
        					boxTextView++;
						}
					  })(marker, m));
					  
					  google.maps.event.addDomListener(boxText,'click',function(){
						  if (boxTextView == 1) {
							  boxText.id = "tooltip2";
							  boxText.className = "tool2";
							  boxText.innerHTML = infoWindowHTML2;
							  
							  infoBox.setOptions({
								  content: boxText
							  });
							  boxTextView--;
						  } else if (boxTextView == 0) {
							  boxText.id = "tooltip1";
							  boxText.className = "tool1";
							  boxText.innerHTML = infoWindowHTML1;
							  
							  infoBox.setOptions({
								  content: boxText
							  });
							  boxTextView++;
						  }
					  });
					  
					  
				 }
			}
			
			//console.log('Address: ' + estado);
			//console.log('Zoom: ' + map.getZoom());
		});
	} else if (estadoSelect != '' && estadoSelect != estado && map.getZoom() > 4) {
		estadoSelect = '';
		
		map.panTo(mapCenter);
		map.setCenter(mapCenter);
		
		map.setZoom(4);
		
		layerSup.setMap();
		
		if (mapFilter) {
			layer.setMap(map);
		}
		
		clearMarkers();
	}
	
}

function clearMarkers() {
	if (markersArray) {
		for (var i=0; i < markersArray.length; i++ ) {
			markersArray[i].setMap(null);
		}
	}
}

google.maps.event.addDomListener(window, 'load', initialize);

/* BACKUP
	
	navigationControl:false,
		navigationControlOptions: {
			style:google.maps.NavigationControlStyle.SMALL, 
			position:google.maps.ControlPosition.TOP_RIGHT
		},
	
	
	geocoder = new google.maps.Geocoder();
	geocoder.geocode({address: 'Brazil'}, function(results, status) {
		var latitude = results[0].geometry.location.lat();
		var longitude = results[0].geometry.location.lng();

		map.setCenter(results[0].geometry.location);
	});
	
	//mapCenter = new google.maps.LatLng(latitude,longitude);
	
	marker = new google.maps.Marker({
		map:map,
		draggable:false,
		animation: google.maps.Animation.DROP,
		position: icone
	});
	
	layer.setOptions({
		styles: [{
	    polygonOptions: {
	      fillColor: "#00FF00",
	      fillOpacity: 0.3
	    }
	  },
	  {
	    where: "REGION_NAME = 'Nordeste'",
	    polygonOptions: {
	      fillColor: "#0000FF",
		  fillOpacity: 0.2
	    }
	  },
	  {
	    where: "REGION_NAME = 'Norte'",
	    polygonOptions: {
	      fillColor: "#FF0000",
		  fillOpacity: 0.4
	    }
	  },
	  {
	    where: "REGION_NAME = 'Sudeste'",
	    polygonOptions: {
	      fillOpacity: 0.6
	    }
	  },
	  {
	    where: "REGION_NAME = 'Sul'",
	    polygonOptions: {
	      fillOpacity: 0.5
	    }
	  }]
	});
	
	  layerSup.setOptions({
	  query: {
	    select: 'Geometry',
	    from: '1zKNAHdkAPYXQtGou4TPiLRoNDxpJJ7ydkw78T9E'
	  },
	  styles: [{
	    polygonOptions: {
	      fillColor: "#00FF00",
	      fillOpacity: 0.1
	    }
	  }]
	});
	
	//alert('Teste ' + e.infoWindowHtml + ' --- ' + e.latLng);
	
	//clickable: false

	//marker.setAnimation(google.maps.Animation.BOUNCE);

    //google.maps.event.addListener(marker, 'click', toggleBounce);
   	//google.maps.event.addDomListener(window, 'load', toggleBounce);
	
	//setTimeout(function(){setPan()},1000);

*/
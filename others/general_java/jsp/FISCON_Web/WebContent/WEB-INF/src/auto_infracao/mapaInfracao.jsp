<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<sx:head />

  <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAAm2oS1xAApLJJDDjty4fCpRStmsYpinEPJFIBNKltOpAgI37abxTLmt3G1lz2iJST0xbZAlxXR0TP9A" type="text/javascript"></script>    
<script type="text/javascript" src="/fiscon/scripts/markerclusterer.js"></script>



    <script type="text/javascript"><!--
    function initialize() {
    	//var div = document.getElementById('map');
    	//div.style.height = 0.80*document.getElementById('corpo').offsetHeight +'px';
    	//div.style.width = 0.98*document.getElementById('corpo').offsetWidth + 'px';
    	//document.getElementById("formulario").style.top = document.getElementById('corpo').offsetHeight -100 ;
        //document.getElementById("formulario").style.left = document.getElementById('corpo').offsetWidth - 245;
		showZooms();
    	<s:property value="pojo.conteudo" escape="false"/>;  	


    
    	
        if(GBrowserIsCompatible()) {
        	 var opts = {
        	            zoomInBtnTitle: "Aumentar Zoom",
        	            zoomOutBtnTitle: "Reduzir Zoom",
        	            moveNorthBtnTitle: "Vista panorámica para cima",
        	            moveSouthBtnTitle: "Vista panorámica para baixo",
        	            moveEastBtnTitle: "Vista panorámica para a direita",
        	            moveWestBtnTitle: "Vista panorámica para a esquerda",
        	            homeBtnTitle: "Posição Inicial"
        	        };
          var map = new GMap2(document.getElementById('map'));

          map.enableInfoWindow();
          map.enableContinuousZoom();
          map.enableScrollWheelZoom();
          map.setCenter(new GLatLng(-15.780059,-47.929237), 11);
          GEvent.addListener(map,"move",showZooms);
          
          map.addControl(new GLargeMapControl());
          map.addMapType(G_PHYSICAL_MAP);
          map.addMapType(G_HYBRID_MAP);
          map.setMapType(G_HYBRID_MAP); 
          var icon = new GIcon(G_DEFAULT_ICON);
          icon.image = "http://chart.apis.google.com/chart?cht=mm&chs=24x32&chco=FFFFFF,008CFF,000000&ext=.png";
          var markers = [];
          for (var i = 0; i < data.infracoes.length; ++i) {
			var html = "";
	


			html+="<html>";                        
			html+="<body>                                                     ";
			html+="		<table style=\"font-size: 9px;\">             ";
			html+="			<tr>                                              ";
			html+="				<td align=\"center\" style=\"font-size: 11px;\">                           ";
			html+="					<b>Auto Infração </b> ";
			html+="				</td>                                         ";
			html+="				                                              ";
			html+="			</tr>                                             ";
			html+="			<tr>                                              ";
			html+="				<td>                           ";
			html+="					<b>Número Auto Infração:</b> ";
			html+="				</td>                                         ";
				html+="			<tr>                                              ";
			html+="				<td>                           ";
			html+="<a href=\"/fiscon/auto_infracao/consultar?pojo.numeroAutoInfracao="+data.infracoes[i].numeroinfracao+"\">"+data.infracoes[i].numeroinfracao+"</a>";
			html+="				</td>                                         ";
			html+="				                                              ";
			html+="			</tr>                                             ";
			html+="				                                              ";
			html+="			</tr>                                             ";
			html+="			<tr>                                              ";
			html+="				<td>                                          ";
			html+="					<b>Placa:</b>                 ";
			html+="        					</td>                             ";
			html+="			</tr>                                             ";
			html+="			<tr>                                              ";
			html+="				<td>                                          ";
			html+=data.infracoes[i].veiculoPlaca;
			html+="				</td>                                         ";
			html+="			</tr>                                             ";
			html+="			<tr>                                              ";
			html+="				<td>                                          ";
			html+="					<b>Marca/Modelo:</b>                      ";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+=data.infracoes[i].marcamodelo;
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
            html+="                                                           ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+="					<b>Data da Infração:</b>                  ";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+=data.infracoes[i].datainfracao;
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+="					<b>Codigo da Infração:</b>                ";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+=data.infracoes[i].codigoinfracao;
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			                                                  ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+="					<b>Infração:</b>                          ";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+=data.infracoes[i].nomeinfracao;
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+="					<b>Gravidade:</b>                         ";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+=data.infracoes[i].gravidadeinfracao;
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        for (var j = 0; j < data.infracoes[i].fotos.length; ++j) {
        	html+="			<tr>                                              ";
        	html+="				<td>                                          ";
        	html+="<img width=\"60\" height=\"60\" src=\"data:image/jpeg;base64,"+data.infracoes[i].fotos[j].foto+"\">";
        	html+="				</td>                                         ";
        	html+="			</tr>                                             ";
        }
        	html+="		</table>                                              ";
        	html+="	</body>";
        	html+="</html>";





        	  
            var latlng = new GLatLng(data.infracoes[i].latitude, data.infracoes[i].longitude);
            var marker1 = createMarker(latlng, html,data.infracoes[i].numeroinfracao, icon); 
            markers.push(marker1);
          }
          var markerCluster = new MarkerClusterer(map, markers);
        }
      }

    function createMarker(point, html, title, icon){
        var marker = new GMarker(point, {title: title, icon: icon});    
        GEvent.addListener(marker, "click", function() {
            
            marker.openInfoWindowHtml(html);
            
        });    
        return marker;
    }
    function showZooms(){
    	//var AlturaDisponivel = document.getElementById('corpo').offsetHeight;
    	//var LarguraDisponivel = document.getElementById('corpo').offsetWidth;
    	//document.getElementById("formulario").style.top = document.getElementById('corpo').offsetHeight -100 ;
        //document.getElementById("formulario").style.left = document.getElementById('corpo').offsetWidth - 245;
    	var div = document.getElementById('map');
    	div.style.height = 0.65*document.getElementById('corpo').offsetHeight +'px';
    	div.style.width = 0.98*document.getElementById('corpo').offsetWidth + 'px';
    }

    

    if (window.attachEvent) { 
    	window.attachEvent("onresize",function(){ //IE
    	showZooms();
    	});
    }else {
    	window.addEventListener("resize", function() {showZooms();}, false);
    }
    		    	
    	    
        
   --></script>
   
    <div class="underbanner2"><span>Mapa de Auto Infração</span></div>
	<div class="marginform">
		<s:form namespace="/gerarMapaInfracao" >
	    	<s:textfield label="Nº do Auto de Infração" key="pojo.numeroAutoInfracao"/>
			<s:textfield label="Placa" key="pojo.veiculoPojo.veiculoPlaca"/>
			<sx:datetimepicker label="Data inicio" key="pojo.dataInicio" displayFormat="dd/MM/yyyy" />
			<sx:datetimepicker label="Data fim" key="pojo.dataFim" displayFormat="dd/MM/yyyy" />
    		<tr class="trsubform">
			<td colspan="2">
    		<s:submit theme="simple" cssClass="button" value="Consultar" action="gerarMapaInfracao"/>
    		</td>
    		</tr>
		</s:form>

	<div id="map" style="width: 760px; height: 400px;"></div>

	<SCRIPT type="text/javascript">
		initialize();
	</SCRIPT>
  </div>
  






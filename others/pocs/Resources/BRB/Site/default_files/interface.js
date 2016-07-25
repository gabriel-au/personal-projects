<!--
//Abre nova janela do navegador (POP-UP)
function openwindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function externalLinks() {
 if (!document.getElementsByTagName) return;
 var anchors = document.getElementsByTagName("a");
 for (var i=0; i<anchors.length; i++) {
   var anchor = anchors[i];
   if (anchor.getAttribute("href") && anchor.getAttribute("rel") == "external")
     anchor.target = "_blank";
 }
}
window.onload = externalLinks;

/*************************
FUNÇÃO PARA ABRIR POPUP CENTRALIZADA NA TELA DO USUÁRIO
*************************/
function popup(url, largura, altura, resize) {
	var w = screen.availWidth;
	var h = screen.availHeight;
	var ww = largura;
	var wh = altura;
	var left = (w-ww)/2;
	var top = (h-wh)/2;
	window.open(url, '', 'width=' + ww + ',height=' + wh + ',top=' + top + ',left=' + left + ',resizable=' + resize);
}


//Sites BRB
function abrir(obj)
{
window.open(obj.value,'janela');
}


//Quick menus
function abre(f) 
{
	var link = f.options[f.selectedIndex].value;
	window.open(link, 'comboLinks', 'toolbar=yes,location=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width=800,height=600, left=0,top=0');
	return 0;
}

var win = null;
function NewWindow(mypage,myname,w,h,scroll,status){
	LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
	TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
	settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable',status='+status+';
	win = window.open(mypage,myname,settings)
	}

function menudesalto() {
	destino = document.Canais.acessoRapido.options[document.Canais.acessoRapido.selectedIndex].value;
		if (destino != "") {window.location.href=destino;}
			document.Canais.acessoRapido.selectedIndex = 0;
	return true;
	}
	

//redimensiona iframe
function resize_iframe()
{
	if(document.getElementById("glu") == null) {
		return 0;
	}
	var height=window.innerWidth;//Firefox
	if (document.body.clientHeight)
	{
		height=document.body.clientHeight;//IE
	}
	//redimensiona o iframe de acordo com o tamanho da janela
	document.getElementById("glu").style.height=parseInt(height-document.getElementById("glu").offsetTop-0)+"px";
}

// redimensiona toda vez que a janela muda de tamanho
window.onresize=resize_iframe; 

//usar no body: 
//	<body onresize="resize_iframe()">


// Validao para o fromulrio da Central de relacionamento
function FrontPage_Form1_Validator(theForm)
{

  var checkOK = "0123456789-";
  var checkStr = theForm.Conta.value;
  var allValid = true;
  var decPoints = 0;
  var allNum = "";
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    allNum += ch;
  }
  if (!allValid)
  {
    alert("Digite somente dgito caracteres no campo \"Conta\".");
    theForm.Conta.focus();
    return (false);
  }

  if (theForm.Assunto.value == "")
  {
    alert("Digite um valor para o campo \"Assunto\".");
    theForm.Assunto.focus();
    return (false);
  }

  if (theForm.Email.value == "")
  {
    alert("Digite um valor para o campo \"Email\".");
    theForm.Email.focus();
    return (false);
  }

  if (theForm.Email.value.length < 1)
  {
    alert("Digite pelo menos 1 caracteres no campo \"Email\".");
    theForm.Email.focus();
    return (false);
  }

  if (theForm.Email.value.length > 40)
  {
    alert("Digite no mximo 40 caracteres no campo \"Email\".");
    theForm.Email.focus();
    return (false);
  }

  var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-@._";
  var checkStr = theForm.Email.value;
  var allValid = true;
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
  }
  if (!allValid)
  {
    alert("Digite somente letra, dgito e \"@._\" caracteres no campo \"Email\".");
    theForm.Email.focus();
    return (false);
  }
  return (true);
}


// Validao do formulrio de ouvidoria
function Confirma() {
	//Conta
	//if (document.form1.Conta.value.length < 8)
	//{ 
	//	alert("Por favor, informe a Conta."); 	
	//	document.form1.Conta.focus();
	//	return false; 
	//}
	
	//Protocolo
	if (!/\d{7}\/\d{4}/.test(document.form1.Protocolo.value)) {
		alert("Por favor, informe o Protocolo no padrão 9999999/9999.");
		document.form1.Protocolo.focus(); return false;
	}
//	  else {
//		var parts = document.form1.Protocolo.value.split('/');
//		var d = new Date();
//		var y = d.getFullYear();
//		
//		if (parseInt(parts[1]) != y) {
//			alert("O ano do Protocolo informado é inválido.");
//			document.form1.Protocolo.focus(); return false;	
//		}
//	}
	if (document.form1.Assunto.value.length < 3) { 
		alert("Por favor, informe o Assunto."); 
		document.form1.Assunto.focus(); return false; 
	}
	//Nome
	if (document.form1.Nome.value.length < 3) { 
		alert("Por favor, informe o Nome."); 
		document.form1.Nome.focus(); return false; 
	}
	//Email
	if(document.form1.Email.value.length > 0)
	{
      i = document.form1.Email.value.indexOf("@")
      j = document.form1.Email.value.indexOf(".", i)
      k = document.form1.Email.value.indexOf(",")
      kk = document.form1.Email.value.indexOf(" ")
      jj = document.form1.Email.value.lastIndexOf(".") + 1
      len = document.form1.Email.value.length

      if((i > 0) && (j > (1 + 1)) && (k == -1) && (kk == -1) && (len -jj >= 2) && (len - jj <= 3))
      {
      }
      else {
          alert("Por favor, entre com e-mail válido.\n" + document.form1.Email.value + "  inválido.");
			document.form1.Email.focus();
			return false; 
      }
    }
    else 
    {
       alert("Por favor, entre com e-mail válido.");
		document.form1.Email.focus();
		return false; 
    } 
}


//Menu Correspondentes Bancrios
function MostrarOcultar(item){
    if (item.style.display=='none'){
        item.style.display='block';

    } else {
        item.style.display='none'

    }
}

function mae()
  {
        remote = window.open("http://portal.cartaobrb.com.br/promocoes/hotsite_dia_das_maes/index.htm","","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=no,resizable=no,top=100, left=100, WIDTH=650,HEIGHT=400");
  }

//Script Toggle para as tabelas dos caixas eletrnicos.
//http://www.brb.com.br/novo-site/para-voce/comodidade-e-atendimento/caixas-eletronicos/caixas-eletronicos-outros-locais.shtml	
var ultima = "";
function mostraDiv(id){
 if(ultima.length > 0)
  escondeDiv(ultima);
  ultima = id;
  document.getElementById(id).className = "displayBlock"; 
  var spans = document.getElementsByTagName('span');
	for (var i=0; i<spans.length; i++) {
		if (spans[i].className == 'msg_aviso') {
			spans[i].style.display = 'block';
		}
	}
}
 
function escondeDiv(id){
 document.getElementById(id).className = "displayNone";
}
var ultima2 = "";
function mostraDiv2(id){
 if(ultima2.length > 0)
  escondeDiv2(ultima2);
  ultima2 = id;
  document.getElementById(id).className = "displayBlock"; 
  var spans = document.getElementsByTagName('span');
	for (var i=0; i<spans.length; i++) {
		if (spans[i].className == 'msg_aviso') {
			spans[i].style.display = 'block';
		}
	}
}
 
function escondeDiv2(id){
 document.getElementById(id).className = "displayNone";
}

// FUNO QUE TIRA O CONTROLE DE APLICATIVO PARA ARQUIVOS SWF
// ADICIONADO EM 12/12/2007 POR GOR PRADO RAMOS
/**
 * SWFObject v1.4.1: Flash Player detection and embed - http://blog.deconcept.com/swfobject/
 *
 * SWFObject is (c) 2006 Geoff Stearns and is released under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 *
 * **SWFObject is the SWF embed script formerly known as FlashObject. The name was changed for
 *   legal reasons.
 */
if(typeof deconcept=="undefined"){var deconcept=new Object();}
if(typeof deconcept.util=="undefined"){deconcept.util=new Object();}
if(typeof deconcept.SWFObjectUtil=="undefined"){deconcept.SWFObjectUtil=new Object();}
deconcept.SWFObject=function(_1,id,w,h,_5,c,_7,_8,_9,_a,_b){
if(!document.createElement||!document.getElementById){return;}
this.DETECT_KEY=_b?_b:"detectflash";
this.skipDetect=deconcept.util.getRequestParameter(this.DETECT_KEY);
this.params=new Object();
this.variables=new Object();
this.attributes=new Array();
if(_1){this.setAttribute("swf",_1);}
if(id){this.setAttribute("id",id);}
if(w){this.setAttribute("width",w);}
if(h){this.setAttribute("height",h);}
if(_5){this.setAttribute("version",new deconcept.PlayerVersion(_5.toString().split(".")));}
this.installedVer=deconcept.SWFObjectUtil.getPlayerVersion(this.getAttribute("version"),_7);
if(c){this.addParam("bgcolor",c);}
var q=_8?_8:"high";
this.addParam("quality",q);
this.setAttribute("useExpressInstall",_7);
this.setAttribute("doExpressInstall",false);
var _d=(_9)?_9:window.location;
this.setAttribute("xiRedirectUrl",_d);
this.setAttribute("redirectUrl","");
if(_a){this.setAttribute("redirectUrl",_a);}};
deconcept.SWFObject.prototype={setAttribute:function(_e,_f){
this.attributes[_e]=_f;
},getAttribute:function(_10){
return this.attributes[_10];
},addParam:function(_11,_12){
this.params[_11]=_12;
},getParams:function(){
return this.params;
},addVariable:function(_13,_14){
this.variables[_13]=_14;
},getVariable:function(_15){
return this.variables[_15];
},getVariables:function(){
return this.variables;
},getVariablePairs:function(){
var _16=new Array();
var key;
var _18=this.getVariables();
for(key in _18){_16.push(key+"="+_18[key]);}
return _16;
},getSWFHTML:function(){
var _19="";
if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){
if(this.getAttribute("doExpressInstall")){this.addVariable("MMplayerType","PlugIn");}
_19="<embed type=\"application/x-shockwave-flash\" src=\""+this.getAttribute("swf")+"\" width=\""+this.getAttribute("width")+"\" height=\""+this.getAttribute("height")+"\"";
_19+=" id=\""+this.getAttribute("id")+"\" name=\""+this.getAttribute("id")+"\" ";
var _1a=this.getParams();
for(var key in _1a){_19+=[key]+"=\""+_1a[key]+"\" ";}
var _1c=this.getVariablePairs().join("&");
if(_1c.length>0){_19+="flashvars=\""+_1c+"\"";}
_19+="/>";}else{if(this.getAttribute("doExpressInstall")){this.addVariable("MMplayerType","ActiveX");}
_19="<object id=\""+this.getAttribute("id")+"\" classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" width=\""+this.getAttribute("width")+"\" height=\""+this.getAttribute("height")+"\">";
_19+="<param name=\"movie\" value=\""+this.getAttribute("swf")+"\" />";
var _1d=this.getParams();
for(var key in _1d){_19+="<param name=\""+key+"\" value=\""+_1d[key]+"\" />";}
var _1f=this.getVariablePairs().join("&");
if(_1f.length>0){_19+="<param name=\"flashvars\" value=\""+_1f+"\" />";}_19+="</object>";}
return _19;},write:function(_20){
if(this.getAttribute("useExpressInstall")){
var _21=new deconcept.PlayerVersion([6,0,65]);
if(this.installedVer.versionIsValid(_21)&&!this.installedVer.versionIsValid(this.getAttribute("version"))){
this.setAttribute("doExpressInstall",true);
this.addVariable("MMredirectURL",escape(this.getAttribute("xiRedirectUrl")));
document.title=document.title.slice(0,47)+" - Flash Player Installation";
this.addVariable("MMdoctitle",document.title);}}
if(this.skipDetect||this.getAttribute("doExpressInstall")||this.installedVer.versionIsValid(this.getAttribute("version"))){
var n=(typeof _20=="string")?document.getElementById(_20):_20;
n.innerHTML=this.getSWFHTML();return true;
}else{if(this.getAttribute("redirectUrl")!=""){document.location.replace(this.getAttribute("redirectUrl"));}}return false;}};
deconcept.SWFObjectUtil.getPlayerVersion=function(_23,_24){
var _25=new deconcept.PlayerVersion([0,0,0]);
if(navigator.plugins&&navigator.mimeTypes.length){
var x=navigator.plugins["Shockwave Flash"];
if(x&&x.description){_25=new deconcept.PlayerVersion(x.description.replace(/([a-z]|[A-Z]|\s)+/,"").replace(/(\s+r|\s+b[0-9]+)/,".").split("."));}
}else{try{var axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
for(var i=3;axo!=null;i++){axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+i);_25=new deconcept.PlayerVersion([i,0,0]);}}
catch(e){}
if(_23&&_25.major>_23.major){return _25;}
if(!_23||((_23.minor!=0||_23.rev!=0)&&_25.major==_23.major)||_25.major!=6||_24){
try{_25=new deconcept.PlayerVersion(axo.GetVariable("$version").split(" ")[1].split(","));}
catch(e){}}}return _25;};
deconcept.PlayerVersion=function(_29){
this.major=parseInt(_29[0])!=null?parseInt(_29[0]):0;
this.minor=parseInt(_29[1])||0;
this.rev=parseInt(_29[2])||0;};
deconcept.PlayerVersion.prototype.versionIsValid=function(fv){
if(this.major<fv.major){return false;}
if(this.major>fv.major){return true;}
if(this.minor<fv.minor){return false;}
if(this.minor>fv.minor){return true;}
if(this.rev<fv.rev){return false;}return true;};
deconcept.util={getRequestParameter:function(_2b){
var q=document.location.search||document.location.hash;
if(q){var _2d=q.indexOf(_2b+"=");
var _2e=(q.indexOf("&",_2d)>-1)?q.indexOf("&",_2d):q.length;
if(q.length>1&&_2d>-1){return q.substring(q.indexOf("=",_2d)+1,_2e);}}
return "";}};
deconcept.SWFObjectUtil.cleanupSWFs=function(){
var _2f=document.getElementsByTagName("OBJECT");
for(var i=0;i<_2f.length;i++){
for(var x in _2f[i]){if(typeof _2f[i][x]=="function"){_2f[i][x]=null;}}}};
if(typeof window.onunload=="function"){
var oldunload=window.onunload;
window.onunload=function(){deconcept.SWFObjectUtil.cleanupSWFs();oldunload();};
}else{window.onunload=deconcept.SWFObjectUtil.cleanupSWFs;}
if(Array.prototype.push==null){
Array.prototype.push=function(_32){
this[this.length]=_32;
return this.length;};}

var getQueryParamValue = deconcept.util.getRequestParameter;
var FlashObject = deconcept.SWFObject; // for backwards compatibility
var SWFObject = deconcept.SWFObject;

// FUNCAO GENERICA PARA ABRIR DIVS
function fimdeano() {
	document.getElementById("floater").style.display = "block";
}

// FUNCAO PARA O TOOGLE DE DUVIDAS FREQUENTES - TABELAS DE TARIFAS
function showdd(dd) {
	var dds = new Array();
	var dlPai = document.getElementById('dl-duvidas');
	var childsMax = dlPai.childNodes.length;
	for (i = 0; i < childsMax; i++)
	{
		if (dlPai.childNodes[i].tagName == 'DD')
		{
			dds.push(dlPai.childNodes[i].id);
		}
	}
	for (var i=0; i < dds.length; i++) {
		if(dds[i] == dd){ continue; }
		document.getElementById(dds[i]).style.display = 'none';
	}
	var el = document.getElementById(dd);
	el.style.display = el.style.display == 'block' ? 'none' : 'block';
	top.location = top.location.href.replace(/#.*?$/, '') + '#' + dd;
	window.scrollBy(0,-40);
}

// FUNCAO PARA MOSTRAR DIVS BRB SOLIDARIO
function displayDiv(paramDiv)
{
	var divs = new Array();
	var divPai = document.getElementById('textos');
	var childsMax = divPai.childNodes.length;
	for (i = 0; i < childsMax; i++)
	{
		if (divPai.childNodes[i].tagName == 'DIV')
		{
			divs.push(divPai.childNodes[i].id);
		}
	}
	for (var i=0; i < divs.length; i++) {
		if(divs[i] == div){ continue; }
		document.getElementById(divs[i]).style.display = 'none';
	}
	var div = document.getElementById(paramDiv);
	div.style.display = div.style.display == 'block' ? 'none' : 'block';
	
	
	
	divOverlay = document.getElementById('overlay');
	
	if ((div.className == 'displayNone') && (divOverlay))
	{
	divOverlay.style.height = "1px";
	}
}

// FUNÇÃO PARA CONTROLAR O TAMANHO DA FONTE DO SITE
var CurrentFontSize = parseInt('0');
window.onload = function() {
	var cookieFontSize = getCookie("fs_brb");
	if (cookieFontSize)
	{
		mudarTamanho('cookie');
	}
	else
	{
		var CurrentFontSize = parseInt('0');
	}
};

function mudarTamanho(estado)
{
	var tamanhos = ['100%', '120%', '125%'];
	var conteudo = document.getElementById('conteudo').style;
	var up = document.getElementById('up');
	var down = document.getElementById('down');
	switch(estado)
	{
		case 'up':
			if (CurrentFontSize < tamanhos.length-1)
			{
				CurrentFontSize++;
				conteudo.fontSize = tamanhos[CurrentFontSize];
			}
			break;
		case 'down':
			if (CurrentFontSize > 0)
			{
				CurrentFontSize--;
				conteudo.fontSize = tamanhos[CurrentFontSize];
			}
			break;
		case 'cookie':
			var cookieFontSize = getCookie("fs_brb");
			CurrentFontSize = cookieFontSize;
			conteudo.fontSize = tamanhos[CurrentFontSize];
			break;
	}
	setCookie("fs_brb", CurrentFontSize, "", '/novo-site/');
}



// FUNÇOES PARA CONTROLE DE COOKIE

function setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) {
	var sCookie = sName + "=" + encodeURIComponent(sValue);
	if (oExpires) {
		sCookie += "; expires=" + oExpires.toGMTString();
	}
	if (sPath) {
		sCookie += "; path=" + sPath;
	}
	if (sDomain) {
		sCookie += "; domain=" + sDomain;
	}
	if (bSecure) {
		sCookie += "; secure";
	}
	document.cookie = sCookie;
}

function getCookie(sName) {
	var sRE = "(?:; )?" + sName + "=([^;]*);?";
	var oRE = new RegExp(sRE);
	if (oRE.test(document.cookie)) {
		return decodeURIComponent(RegExp["$1"]);
	} else {
		return null;
	}
}

function deleteCookie(sName, sPath, sDomain) {
	setCookie(sName, "", new Date(0), sPath, sDomain);
}
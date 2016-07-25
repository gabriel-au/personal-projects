function buttonOn(button) {
    button.className='Button';
}

function buttonOver(button) {
    button.className='ButtonOn';
}

function openWindow(url, janela, width, height) {
    window.open(url, janela, "toolbar=no,location=no,directories=no,status=no," + 
    "menubar=no,scrollbars=yes,fullscreen=no,resizable=yes,menubar=no,width=" +
    width + ",height=" + height);
}

function openWindowScreen(url, janela) {
    var w = screen.width - 50;
    var h = screen.height - 125;
    window.open(url, janela, "toolbar=no,location=no,directories=no,status=no," +
    "menubar=no,scrollbars=yes,fullscreen=no,resizable=yes,menubar=no,top=0,left=0,width=" +
    w + ",height=" + h);
}

function editUserSelected(userSelected) {
    if (userSelected != null) {
        location.href = "?userSelected=" + userSelected[userSelected.selectedIndex].value;
    } else {
        alert('Selecione um usu�rio.');
    }
}

function viewEquipamento(idEquipamento) {
    if (idEquipamento != null) {
        location.href = "?equipamento=" + equipamento[equipamento.selectedIndex].value;
    } else {
        alert('Selecione um Equipamento.');
    }
}

function perfilChecagem(perfil) {
    if (perfil != null) {
        location.href = "checkListEquipamento.jsp?perfilChecagem=" + perfil[perfil.selectedIndex].value;
    } else {
        alert('Selecione um perfil.');
    }
}

function deleteArchive(id) {
    var confirmation = confirm('Deseja excluir o arquivo selecionado?');

    if (confirmation == true) {
        location.href = "deleteArchive?id=" + id;
    }
}
//redireciona para pagina principal
var contador = 10;
function conta() {
document.getElementById('tempo').innerHTML="<font color='red'>"+contador+"</font>";
	if(contador == 0) {
		window.location.href="main.jsp";
	}
	
	if (contador != 0){
		contador = contador-1;
		setTimeout("conta()", 1000);
	}
}
// atualiza com 25 minutos.
var contadorMain = 1500;
function contaMain() {
	if(contadorMain == 0) {
		window.location.reload();
	}
	
	if (contadorMain != 0){
		contadorMain = contadorMain-1;
		setTimeout("contaMain()", 1000);
	}
}

function proximo_segundo(){
	var hoje = new Date;
	var hora = hoje.getHours();
	var minutos = hoje.getMinutes();
	var segundos = hoje.getSeconds();
	if(minutos.toString().length == 1){
		minutos = "0"+ minutos;
	}
	if(segundos.toString().length == 1){
		segundos = "0"+ segundos;
	}
	document.getElementById("relogio").innerHTML="<b>"+hora +":"+minutos+":"+segundos+"</b>";
	setTimeout("proximo_segundo()",1000);
}

function Number(e){
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla > 47 && tecla < 58)) return true;
    else{
    if (tecla != 8) return false;
    else return true;
    }
}

var listaPaginas = new Array(5);

listaPaginas[0] = new Array(2);

listaPaginas[0][0] = "Mapa";
listaPaginas[0][1] = "mapa2.html";

function carregar(parm){
	
	 $.ajax({
			url: listaPaginas[parm][1],
			dataType:'text',
			success: function(data){
						$('#content').html(data);
					}
			});
	
	$("#header").removeClass('header-evento');
	$("#header").addClass('header');
	$("#header").html("<div class='title'>"+listaPaginas[parm][0]+"</div><div class='sombra'></div>  ");
	

}





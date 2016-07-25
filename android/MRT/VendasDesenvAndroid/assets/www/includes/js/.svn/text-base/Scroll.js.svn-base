		
		//SCROLL
		var hasTouch = 'ontouchstart' in window;
		var scrollDiv;
		var wrapScrol;
		var scrolling = false;
		var offset;
		var startX = -1;
		var startY = -1;
		var posX = 0;
		var posY = 0;
		var stepX = 1;
		var stepY = 5;
		var scrollVer = false;
		var scrollHor = false;
		var direction = -1; //0 - vertical; 1 - horizontal
		
		function scrollRegistry(contScrollId, scrollVerActive, scrollHorActive){
			if (!hasTouch) return;
			
			scrollVer = scrollVerActive;
			scrollHor = scrollHorActive;
			
			//Cadastra os eventos
			var	startEvent = hasTouch ? 'touchstart' : 'mousedown';
			var	moveEvent = hasTouch ? 'touchmove' : 'mousemove';
			var	endEvent = hasTouch ? 'touchend' : 'mouseup';
			var	cancelEvent = hasTouch ? 'touchcancel' : 'mouseup'; 			  		
			  	
			contScroll = $("#"+contScrollId);
			wrapScroll = contScroll.parent();
			
			var wrapScrollDiv = wrapScroll.get(0);	
			wrapScrollDiv.addEventListener(startEvent, startDrag, false);
			wrapScrollDiv.addEventListener(moveEvent, moveDrag, false);
			wrapScrollDiv.addEventListener(endEvent, stopDrag, false);
			wrapScrollDiv.addEventListener(cancelEvent, stopDrag, false);
			
		}
		
		//Ajusta o tamanho da area de scroll
		function scrollAreaResize(wrapperScrollId){
			$('#'+wrapperScrollId).height($(window).height()-$('#' + wrapperScrollId).offset().top);	
		}
		
		//posiciona o scroll no topo a esquerda
		function scrollTop(){
			posX = 0;
			posY = 0;
			contScroll.css('webkitTransform', 'translate3d(' + posX + 'px,' + posY + 'px,0)');
		}
		
	    //Evento de Inicio de Drag
	    function startDrag(event){
	    	console.log("Inicia drag");
	    	//event.preventDefault();
	    	//event.stopPropagation();
	    	
	       	offset = wrapScroll.offset();
	        scrolling = true;
	        var point = hasTouch ? event.touches[0] : event;
	        startX = point.pageX;
	        startY = point.pageY;
	    }
	    
	    //Evento quando o Drag � solto
	    function stopDrag(event){
	    	//console.log("Stop drag");
	    	event.preventDefault();
	    	
	    	scrolling = false;
	    	startX = -1;
	    	startY = -1
	    }
	    
	    //Evento quando se move o cursor/dedo durante o Drag
	    function moveDrag(event){
	    	//console.log("move drag");
	    	if (scrolling == false) return;    	
	    
	    	var point = hasTouch ? event.touches[0] : event;
	    	
	    	//console.log("OffSet X: " + offset.left + "  -   Y: " + offset.top);
	       	//console.log("Point X: " + point.pageX + "  -   Y: " + point.pageY);		
	       	
	       	//console.log("Width content: " + contScroll.width());
	       	//console.log("Width wrapper: " + wrapScroll.width());
	       	//console.log("PosX: " + posX + "  -   PosY: " + posY);
	       	//console.log("ScrollVer: " + scrollVer + "  -   ScrollHor: " + scrollHor);
	        
	    	if(Math.abs(startY-point.pageY)>Math.abs(startX-point.pageX)) 
	    		direction = 0;
	    	else
	    		direction = 1;
	    	
	    	var accX = Math.abs(startX-point.pageX);
	    	var accY = Math.abs(startY-point.pageY);
	    	
	    	if(accX>100) accX*2;
	    	if(accY>100) accY*3;
	    	
	    	if (scrollVer && direction !=1 && point.pageY > (startY+stepY)) {
	    		posY -= (stepY - accY*2);
	    		if (posY > 0) posY = 0;
	    	} else if (scrollVer && direction !=1 && point.pageY < (startY-stepY)) {
	    		if ((wrapScroll.height()-contScroll.height()) >0) return;
	    		posY += (stepY - accY*2);
	    		var maxPosY = wrapScroll.height()-contScroll.height();
	    		if (posY<maxPosY) posY = maxPosY;  
	    	} else if (scrollHor && direction !=0 && point.pageX > (startX+stepX)) {
	    		posX -= (stepX - accX*2);
	    		if (posX > 0) posX = 0;
	    	} else if (scrollHor && direction !=0 && point.pageX < (startX-stepX)) {
	    		if ((wrapScroll.width()-contScroll.width()) >0) return;
	    		posX += (stepX - accX*2);
	    		var maxPosX = wrapScroll.width()-contScroll.width();
	    		if (posX<maxPosX) posX = maxPosX;
	    	} else return; 

	    	event.preventDefault();	
	    	
	        startX = point.pageX;
	        startY = point.pageY;

	    	contScroll.css('webkitTransform', 'translate3d(' + posX + 'px,' + posY + 'px,0)');
	  	}	
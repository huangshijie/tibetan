initCheckBox();
function initCheckBox(){
	
	var gap = 8;
	
	var rowNum = (data.length - data.length % gap) / gap + 1;
	
	for(var m = 0; m< rowNum ; m++){
		var rowDiv = document.createElement('div');
		addClass(rowDiv, 'row');
		
		for(var n= 0; n < gap; n++ ){
			if(m*8+n<data.length){
				var button = document.createElement('button');
				button.setAttribute('id',m*8+n);
				button.setAttribute('value',data[m*8+n]);
				button.setAttribute('class','button button-3d button-box button-jumb button-highlight compare-button-large');
				button.innerText = data[m+n];
				rowDiv.appendChild(button);
			}
		}
		
		container.appendChild(rowDiv);
	}
};

$('button').click(function (){
	if(hasClass(this, 'compare-button-pressdown')){
		removeClass(this , 'compare-button-pressdown');
	}else{
		addClass(this , 'compare-button-pressdown');
	}
	console.log(this.id);
});
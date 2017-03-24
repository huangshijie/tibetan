var selectedList  = new Array();

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
				button.innerText = data[m*8+n];
				rowDiv.appendChild(button);
			}
		}
		
		container.appendChild(rowDiv);
	}
};

function addToSelectedList (item){
	selectedList.push(item);
	var result = "";
	for(var i =0; i<selectedList.length;i++){
		result += data[selectedList[i]] + ",";
	}
	curSelectedListLabel.value = "";
	curSelectedListLabel.value = result;
}

function removeFromSelectedList (item){
	var result = "";
	for(var i =0; i<selectedList.length;i++){
		if(data[selectedList[i]] == data[item]){
			selectedList.splice(i,1); 
			break;
		}
	}
	
	for(var i =0; i<selectedList.length;i++){
		result += data[selectedList[i]] + ",";
	}
	curSelectedListLabel.value = "";
	curSelectedListLabel.value = result;
}

function finishSelected(){
	curSelectedListLabel.readOnly = true;
	curInputListLabel.readOnly = true;
}

$('button').click(function (){
	if(hasClass(this, 'compare-button-pressdown')){
		removeClass(this , 'compare-button-pressdown');
		removeFromSelectedList (this.id);
	}else{
		addClass(this , 'compare-button-pressdown');
		addToSelectedList (this.id);
	}
	console.log(this.id);
});
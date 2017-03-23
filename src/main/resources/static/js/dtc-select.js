if(dialectsList != null){
	
	for(var i= 0; i< dialectsList.length;i++){
		var dialectOption = new Option();
		dialectOption.value = dialectsList[i].ID;
		dialectOption.text = dialectsList[i].languagePoint;
		dialectSelect.options.add(dialectOption);
	}
	$("#dialect-select").selectpicker('refresh');
}

if(uploadLabel != null && uploadID!=null){
	currentLabel.innerHTML = uploadLabel;
	currentID.innerHTML = uploadID;	
	createUploadButton(uploadID)
}

if(compareType == "diaCompTi"){
	title.innerHTML = "方言与古藏语比较";
}
if(compareType == "tiCompDia"){
	title.innerHTML = "古藏语与方言比较";
}

$("#dialect-select").change( function (){
	var index = dialectSelect.selectedIndex ;  
	if(dialectSelect.options[index].value != "NULL"){
		currentLabel.innerHTML = dialectSelect.options[index].text;
		currentID.innerHTML = dialectSelect.options[index].value;
		createUploadButton(dialectSelect.options[index].value);
	}
});

function createUploadButton(uploadID, uploadLabel) {
	if(compareType == "diaCompTi"){
		onsetDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getQueryListHTML?did="+ uploadID +"&dlocation="+uploadLabel+"&comparetype="+ compareType +"&compareentity=onSet\">声母比较</a>";
		finalDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getQueryListHTML?did="+ uploadID +"&dlocation="+uploadLabel+"&comparetype="+ compareType +"&compareentity=final\">韵母比较</a>";
	}
	if(compareType == "tiCompDia"){
		onsetDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getQueryListHTML?did="+ uploadID +"&dlocation="+uploadLabel+"&comparetype="+ compareType +"&compareentity=onSetWille\">声母比较</a>";
		finalDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getQueryListHTML?did="+ uploadID +"&dlocation="+uploadLabel+"&comparetype="+ compareType +"&compareentity=finalWille\">韵母比较</a>";
	}
}
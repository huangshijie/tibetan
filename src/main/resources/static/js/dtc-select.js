if(uploadLabel != null && uploadID!=null){
	currentLabel.innerHTML = uploadLabel;
	currentID.innerHTML = uploadID;	
	createUploadButton(uploadID, uploadLabel)
}

if(compareType == "diaCompTi"){
	title.innerHTML = "方言与古藏语比较";
}
if(compareType == "tiCompDia"){
	title.innerHTML = "古藏语与方言比较";
}

if(dialectsList != null){
	for(var i= 0; i< dialectsList.length;i++){
		var dialectOption = new Option();
		dialectOption.value = dialectsList[i].ID;
		dialectOption.text = dialectsList[i].languagePoint;
		dialectSelect.options.add(dialectOption);
	}
	$("#dialect-select").selectpicker('refresh');
}

//$("button").click( function (){
//	var select = document.getElementById('dialect-select');
//	var parent = select.parentElement;
//	var divPlane = select.parentElement.childNodes[1];
//	if(divPlane.childElementCount>=2){
//		divPlane.removeChild(divPlane.lastChild)
//	}
//	var pageUp = document.createElement('a')
//	var pageDown = document.createElement('a');
//	var pageP = document.createElement('p');
//	var pagerDiv = document.createElement('div');
//	
//	pageUp.text = '上一页';
//	pageDown.text = '下一页';
//	pageP.text = '';
//	
//	pageUp.setAttribute('onClick', 'pageUp()');
//	pageDown.setAttribute('onClick', 'pageDown()');
//	
//	pageUp.class = '';
//	pageDown.class = '';
//	pageP.class = '';
//	
//	pagerDiv.appendChild(pageUp);
//	pagerDiv.appendChild(pageP);
//	pagerDiv.appendChild(pageDown);
//	
//	pagerDiv.appendChild(pageDown);
//	divPlane.appendChild(pagerDiv);
//});

function pageUp(){
	var select = document.getElementById('dialect-select');
	select.parentElement.class = 'btn-group bootstrap-select show-tick form-control open';
}

function pageDown(){
	var select = document.getElementById('dialect-select');
	select.parentElement.class = 'btn-group bootstrap-select show-tick form-control open';
}

$("#dialect-select").change( function (){
	var index = dialectSelect.selectedIndex ;  
	if(dialectSelect.options[index].value != "NULL"){
		currentLabel.innerHTML = dialectSelect.options[index].text;
		currentID.innerHTML = dialectSelect.options[index].value;
		var locationDesLabel = document.getElementById('click-upload-link');
		locationDesLabel.text = '点击';
		createUploadButton(dialectSelect.options[index].value, dialectSelect.options[index].text);
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

$("#confirmUpload").click(function (event) {
    event.preventDefault();
    uploadLocalFile();
});

function uploadLocalFile() {
    var form = $('#uploadLocalClusterFileForm')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadLocalClusterFile",
        data: data,
        processData: false, 
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.parse(data); 
            var label = document.getElementById('locationDes');
            var inputFile = document.getElementById('upload-file-input');
            var locationDesLabel = document.getElementById('click-upload-link');
            var currentLocationDesID = document.getElementById('current-chosen-id');
            var currentLocationDesLabel = document.getElementById('current-chosen-label');
            locationDesLabel.text = json.dlocation;
            currentLocationDesID.innerText = json.did;
            currentLocationDesLabel.innerText = json.dlocation;
            label.value = '';
            inputFile.value='';
            $('#uploadLocalFile').modal('hide');
            createUploadButton(json.did, json.dlocation);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

};

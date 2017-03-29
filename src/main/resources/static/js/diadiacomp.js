init();
var curItemNum;
var chosenIDList = new Array();
var chosenLabelList = new Array();
var chosenRowNumList = new Array();

var onsetDIV = document.getElementById("onset-compare-div");
var finalDIV = document.getElementById("final-compare-div");

$("#confirmUpload").click(function (event) {
    event.preventDefault();
    uploadLocalFile();
});

function selectChange(item){
	console.log(item);
};

function init(){
	curItemNum = 1;
	addNewRow();
};

function createSelect(){
	var select = document.createElement('select');
	select.setAttribute('id', 'dialect-select-'+curItemNum);
	select.setAttribute('class', 'show-tick form-control');
	select.setAttribute('onchange', 'selectChange('+curItemNum+')');
	var dialectOption = new Option();
	dialectOption.value = 'NULL';
	dialectOption.text = '==请选择==';
	select.options.add(dialectOption);
	if(dialectsList != null){
		for(var i= 0; i< dialectsList.length;i++){
			var dialectOption = new Option();
			dialectOption.value = dialectsList[i].ID;
			dialectOption.text = dialectsList[i].languagePoint;
			select.options.add(dialectOption);
		}
		$('#dialect-select'+curItemNum).selectpicker('refresh');
	}
	return select;
};

function addNewRow(){
	var row = document.getElementById(curItemNum);
	var databaseDIV = document.createElement('div');
	databaseDIV.setAttribute('class', 'col-md-2');
	databaseDIV.innerHTML = '<label>选择已有数据：</label>';
	
	var selectDIV = document.createElement('div');
	var select = createSelect();
	selectDIV.setAttribute('class', 'col-md-2');
	selectDIV.appendChild(select);
	
	var orDIV = document.createElement('div');
	orDIV.setAttribute('class', 'col-md-2');
	orDIV.innerHTML = "<label>或</label>";
	
	var localDIV = document.createElement('div');
	localDIV.setAttribute('class', 'col-md-2');
	localDIV.innerHTML = "<label>选择本地数据：</label>";
	
	var uploadDIV = document.createElement('div');
	uploadDIV.setAttribute('class', 'col-md-2');
	uploadDIV.innerHTML = "<label id=\"locationID-"+curItemNum+"\" style=\"visibility: hidden;\"></label><a href=\"\" onclick=\"showModal("+curItemNum+")\" id=\"click-"+curItemNum+"\">点击</a>";
	
	var actionDIV = document.createElement('div');
	actionDIV.setAttribute('class', 'col-md-2');
	actionDIV.innerHTML = "<a href=\"#\" onclick=\"lock("+curItemNum+")\" id=\"confirm-"+curItemNum+"\">确定</a>/<a href=\"#\" id=\"remove-"+curItemNum+"\" onclick=\"removeRow("+curItemNum+")\">删除</a>";
	
	curItemNum++;
	
	var newRow = document.createElement('div');
	newRow.setAttribute('id', curItemNum);
	newRow.setAttribute('class', 'row');
	newRow.setAttribute('style', "margin-top: 50px");
	
	row.appendChild(databaseDIV);
	row.appendChild(selectDIV);
	row.appendChild(orDIV);
	row.appendChild(localDIV);
	row.appendChild(uploadDIV);
	row.appendChild(actionDIV);
	row.parentNode.appendChild(newRow);
};

function lock(itemNum){
	var select =  document.getElementById("dialect-select-"+itemNum);
	var link = document.getElementById("click-"+itemNum);
	var confirm = document.getElementById('confirm-'+itemNum);
	var locationID = document.getElementById('locationID-'+itemNum);
	var selectIndex = document.getElementById('dialect-select-'+itemNum).selectedIndex;
	var chosenLabel = document.getElementById('current-chosen-label');
	if(locationID.text != null || select.options[selectIndex].value != 'NULL'){
		link.removeAttribute('href');  
		link.removeAttribute('onclick');
		confirm.removeAttribute('onclick');
		confirm.removeAttribute('href');  
		select.setAttribute("disabled","disabled"); 
		if(locationID.text != null){
			chosenIDList.push(locationID.text);
			chosenLabelList.push(document.getElementById('click-'+itemNum).text);
			chosenRowNumList.push(itemNum);
			chosenLabel.innerText = chosenLabelList.toString();
		}
		if(select.options[selectIndex].value != 'NULL'){
			chosenIDList.push(select.options[selectIndex].value);
			chosenLabelList.push(select.options[selectIndex].text);
			chosenRowNumList.push(itemNum);
			chosenLabel.innerText = chosenLabelList.toString();
		}
	}
}

function removeRow(itemNum){
	if(contains(chosenRowNumList, itemNum)){
		var index = chosenRowNumList.indexOf(itemNum);
		chosenRowNumList.splice(index,1);
		chosenIDList.splice(index,1);
		chosenLabelList.splice(index,1);
		var deleteRow = document.getElementById(itemNum);
		if (deleteRow != null)
			deleteRow.parentNode.removeChild(deleteRow);
	}
};

function contains(arr, obj) {  
    var i = arr.length;  
    while (i--) {  
        if (arr[i] === obj) {  
            return true;  
        }  
    }  
    return false;  
} 

function showModal(item){
	event.preventDefault();
	var label = document.getElementById('current-selected-local-id');
    label.value = item;
	$('#uploadLocalFile').modal('show');
	console.log(item);
};

function uploadLocalFile() {
    var form = $('#uploadLocalClusterFileForm')[0];
    var data = new FormData(form);
    var currentSelectedRowid = document.getElementById('current-selected-local-id').value;
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadLocalClusterFileDia",
        data: data,
        processData: false, 
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.parse(data); 
            var label = document.getElementById('locationDes');
            var inputFile = document.getElementById('upload-file-input');
            var locationDesID = document.getElementById('locationID-'+currentSelectedRowid);
            var locationDesLabel = document.getElementById('click-'+currentSelectedRowid);
            locationDesLabel.text = json.dlocation;
            locationDesID.text = json.did;
            label.value = '';
            inputFile.value='';
            $('#uploadLocalFile').modal('hide');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

};

function createUploadButton() {
	var confirm = document.getElementById('add-Dia');
	confirm.removeAttribute('onclick');
	confirm.removeAttribute('href');
	onsetDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getDiaDiaCompQueryListHTML?did="+ chosenIDList +"&dlocation="+chosenLabelList+"&compareentity=onSet\">声母比较</a>";
	finalDIV.innerHTML = "<a class=\"btn btn-primary btn-lg\" style=\" width: 100%;\" href=\"/getDiaDiaCompQueryListHTML?did="+ chosenIDList +"&dlocation="+chosenLabelList+"&compareentity=final\">韵母比较</a>";
}
init();
var curItemNum;

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
	select.setAttribute('id', 'dialect-select'+curItemNum);
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
	uploadDIV.innerHTML = "<a href=\"\" onclick=\"showModal("+curItemNum+")\">点击</a>";
	
	var actionDIV = document.createElement('div');
	actionDIV.setAttribute('class', 'col-md-2');
	actionDIV.innerHTML = "<a href=\"#\" onclick=\"lock()\">确定</a>/<a href=\"#\" onclick=\"removeRow("+curItemNum+")\">删除</a>";
	
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

function removeRow(curItemNum){
	console.log(curItemNum);
};

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
    data.append("curItemNum", curItemNum);
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
            console.log("SUCCESS : ", data);
            var label = document.getElementById('locationDes');
            var inputFile = document.getElementById('upload-file-input');
            label.value = '';
            inputFile.value='';
            $('#uploadLocalFile').modal('hide');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

};
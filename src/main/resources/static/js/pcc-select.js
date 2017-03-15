var data = {
	"aaa": [
			{"code":"baa","name": "青海省"},
			{"code":"caa","name": "甘肃省"},
			{"code":"daa","name": "四川省"},
			{"code":"eaa","name": "西藏"},
			{"code":"faa","name": "云南省"}
		],
		"baa": [
			{"code":"bba", "name": "果洛藏族自治州"},
			{"code":"bca", "name": "海西蒙古族藏族自治州"},
			{"code":"bda", "name": "海南藏族自治州"},
			{"code":"bea", "name": "海北藏族自治州"},
			{"code":"bfa", "name": "黄南藏族自治州"},
			{"code":"bga", "name": "海东市"},
			{"code":"bha", "name": "玉树藏族自治州"}
		],
		"bba": [
			{"code":"bbb", "name": " "}
		],
		"bca": [
			{"code":"bcb", "name": " "}
		],
		"bda": [
			{"code":"bdb", "name": " "}
		],
		"bea": [
			{"code":"beb", "name": " "}
		],
		"bfa": [
			{"code":"bfb", "name": " "}
		],
		"bga": [
			{"code":"bgb", "name": "化隆回族自治县"},
			{"code":"bgc", "name": "循化撒拉族自治县"},
			{"code":"bgd", "name": "乐都县"}
		],
		"bha": [
			{"code":"bhb", "name": " "}
		],
		"caa": [
			{"code":"cba", "name": "武威市"},
			{"code":"cca", "name": "甘南藏族自治州"}
		],
		"cba": [
			{"code":"cbb", "name": "天祝藏族自治县"}
		],
		"cca": [
			{"code":"ccb", "name": "卓尼县"},
			{"code":"ccc", "name": "迭部县"},
			{"code":"ccd", "name": "舟曲县"}
		],
		"daa": [
			{"code":"dba", "name": "阿坝藏族羌族自治州"},
			{"code":"dca", "name": "甘孜藏族自治州"},
			{"code":"dda", "name": "昌都地区"}
		],
		"dba": [
			{"code":"dbb", "name": " "}
		],
		"dca": [
			{"code":"dcb", "name": "德格"},
			{"code":"dcc", "name": "甘孜"},
			{"code":"dcd", "name": "康定"},
			{"code":"dce", "name": "雅江"},
			{"code":"dcf", "name": "理塘"},
			{"code":"dcg", "name": "巴塘"},
			{"code":"dch", "name": "乡城"},
			{"code":"dci", "name": "稻城"}
		],
		"dda": [
			{"code":"ddb", "name": "昌都"},
			{"code":"ddc", "name": "丁青县"}
		],
		"eaa": [
			{"code":"eba", "name": "昌都地区"},
			{"code":"eca", "name": "那曲地区"},
			{"code":"eda", "name": "林芝地区东部"},
			{"code":"eea", "name": "阿里地区"},
			{"code":"efa", "name": "拉萨"},
			{"code":"ega", "name": "山南地区"},
			{"code":"eha", "name": "林芝市"},
			{"code":"eia", "name": "日喀则市"}
		],
		"eba": [
			{"code":"ebb", "name": " "}
		],
		"eca": [
			{"code":"ecb", "name": "聂荣"},
			{"code":"ecc", "name": "巴青"},
			{"code":"ecd", "name": "索县"},
			{"code":"ece", "name": "比如"},
			{"code":"ecf", "name": "嘉黎"},
			{"code":"ecg", "name": "申扎"},
			{"code":"ech", "name": "班戈"}
		],
		"eda": [
			{"code":"edb", "name": "察隅"},
			{"code":"edc", "name": "波密"},
			{"code":"edd", "name": "墨脱"}
		],
		"eea": [
			{"code":"eeb", "name": "改则"}
		],
		"efa": [
			{"code":"efb", "name": " "}
		],
		"ega": [
			{"code":"egb", "name": " "}
		],
		"eha": [
			{"code":"ehb", "name": "林芝市"},
			{"code":"ehc", "name": "工布江达县"},
			{"code":"ehd", "name": "米林县"},
			{"code":"ehe", "name": "朗县"}
		],
		"eia": [
			{"code":"eib", "name": " "}
		],
		"faa": [
			{"code":"fba", "name": "迪庆藏族自治州"}
		],
		"fba": [
			{"code":"fbb", "name": " "}
		]
	}
var provinceSelect = document.getElementById("province");
var citySelect = document.getElementById("city");
var countySelect = document.getElementById("county");

var provinceList = data.aaa;

for(var i = 0; i<provinceList.length; i++ ){
	var provinceOption = new Option();
	provinceOption.value = provinceList[i].code;
	provinceOption.text = provinceList[i].name;
	provinceSelect.options.add(provinceOption);
}

$("#province").selectpicker(); 
$("#city").selectpicker(); 
$("#county").selectpicker(); 

$("#province").change( function (){
	
	citySelect.options.length=0;
	
	var index = provinceSelect.selectedIndex ;  
	var selectedtext = provinceSelect.options[index].text;
	var selectedvalue = provinceSelect.options[index].value;
	
	var cityList = data[selectedvalue];
	
	for(var i = 0; i<cityList.length; i++ ){
		var provinceOption = new Option();
		provinceOption.value = cityList[i].code;
		provinceOption.text = cityList[i].name;
		citySelect.options.add(provinceOption);
	}
	
	console.log("text:"+selectedtext+" value:"+selectedvalue);
	
	$("#city").selectpicker('refresh');
	
	
	countySelect.options.length=0;
	var selectedvalue = citySelect.options[0].value;
	
	var countyList = data[selectedvalue];
	
	for(var i = 0; i<countyList.length; i++ ){
		var provinceOption = new Option();
		provinceOption.value = countyList[i].code;
		provinceOption.text = countyList[i].name;
		countySelect.options.add(provinceOption);
	}
	
	$("#county").selectpicker('refresh');
});

$("#city").change( function (){
	
	countySelect.options.length=0;
	var index = citySelect.selectedIndex ;  
	var selectedtext = citySelect.options[index].text;
	var selectedvalue = citySelect.options[index].value;
	
	var countyList = data[selectedvalue];
	
	for(var i = 0; i<countyList.length; i++ ){
		var provinceOption = new Option();
		provinceOption.value = countyList[i].code;
		provinceOption.text = countyList[i].name;
		countySelect.options.add(provinceOption);
	}
	
	$("#county").selectpicker('refresh');
});

function readyToUplaod() {
	var provinceSelect = document.getElementById("province");
	var citySelect = document.getElementById("city");
	var countySelect = document.getElementById("county");
	
	var languagePoint = document.getElementById("languangePoint").value;
	
	var locationProvinceText = provinceSelect.options[provinceSelect.selectedIndex].text;
	var locationCityText = citySelect.options[citySelect.selectedIndex].text;
	var locationCountyText = countySelect.options[countySelect.selectedIndex].text;
	
	var locationProvinceValue = provinceSelect.options[provinceSelect.selectedIndex].value;
	var locationCityValue = citySelect.options[citySelect.selectedIndex].value;
	var locationCountyValue = countySelect.options[countySelect.selectedIndex].value;
	
	var locationTown = document.getElementById("town").value;
	var locationVillage = document.getElementById("village").value;
	var locationDetial = document.getElementById("detial-location").value;
	var coPersonName = document.getElementById("collected-person-name").value;
	var coPersonBirthday = document.getElementById("collected-person-birthday").value;
	var coPersonSex = document.getElementById("collected-person-sex").value;
	var coPersonEducation = document.getElementById("collected-person-education").value;
	var coPersonUsedLanguage = document.getElementById("collected-person-language").value;
	var coPersonTel = document.getElementById("collected-person-phone").value;
	var coPersonProfession = document.getElementById("collected-person-occupation").value;
	var coPersonContactAddress = document.getElementById("collected-person-address").value;
	var coPersonRemarks = document.getElementById("collected-person-more").value;
	
	var result = new Object();
	var alertStr = "";
	
	if(languagePoint == ""){alertStr += "语言点 ";}
	if(locationProvinceValue == "NULL" && locationCityValue == "NULL" && locationCountyValue=="NULL" && locationTown == "" && locationVillage == "" && locationDetial == ""){
		alertStr += "、地理位置";
	}
	if(coPersonName == ""){alertStr += "、姓名";}
	if(coPersonBirthday == ""){alertStr += "、出生日期";}
	if(coPersonTel == ""){alertStr += "、联系电话";}
	if(coPersonProfession == ""){alertStr += "、职业";}
	if(coPersonContactAddress == ""){alertStr += "、联系地址";}
	
	if(alertStr != ""){
		var alertMessage = document.getElementById("detail-message-alert");
		var warningBlock = document.createElement("div");
		warningBlock.setAttribute("class", "alert alert-danger alert-dismissible fade in");
		warningBlock.setAttribute("id", "warning-block");
		warningBlock.setAttribute("role", "alert");
		
		var button = document.createElement("button");
		button.setAttribute("class", "close");
		button.setAttribute("data-dismiss", "alert");
		button.setAttribute("aria-label", "Close");
		
		var span = document.createElement("span");
		span.setAttribute("aria-hidden", "true");
		span.innerHTML = "x";
		
		var p = document.createElement("p");
		p.innerHTML = "请检查一下信息："+alertStr+"，是否填写完毕。";
		
		button.appendChild(span);
		warningBlock.appendChild(button);
		warningBlock.appendChild(p);
		alertMessage.appendChild(warningBlock);
	}else{
		result.languagePoint = languagePoint;
		result.locationProvince = locationProvinceText;
		result.locationCity = locationCityText;
		result.locationCounty = locationCountyText;
		result.locationTown = locationTown;
		result.locationVillage = locationVillage;
		result.locationDetial = locationDetial;
		result.name = coPersonName;
		result.birthday = coPersonBirthday;
		result.sex = coPersonSex;
		result.education = coPersonEducation;
		result.usedLanguage = coPersonUsedLanguage;
		result.tel = coPersonTel;
		result.profession = coPersonProfession;
		result.contactAddress = coPersonContactAddress;
		result.remarks = coPersonRemarks;
		
		var locationDes = document.getElementById("locationDes");
		locationDes.value = JSON.stringify(result);
		console.log(JSON.stringify(result));
		
		var uploadFileDiv = document.getElementById("sylable-file-upload");
		uploadFileDiv.style.display = "block";
		
		var collectedPersionDetialsDiv = document.getElementById("collected-person-detials");
		collectedPersionDetialsDiv.style.display = "none";
		
		var buttonList = document.getElementById("detail-collected-button");
		buttonList.style.display = "none";
	}
	
};

$('.form_date').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0
});

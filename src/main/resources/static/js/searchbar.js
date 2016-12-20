function getClass(className) { 
	var tagname = document.getElementsByTagName('*');  
	var tagnameAll = [];    
	for(i=0;i<tagname.length;i++){ 
		if (tagname[i].className.indexOf(className)>=0){    
			tagnameAll[tagnameAll.length] = tagname[i];
		}
	}
	return tagnameAll;
}
window.onload=function(){
	var btn=getClass('tab_btn');
	var div=getClass('tab_div');
	for(i=0;i<btn.length;i++){
		btn[i].onclick=function(){
		var index=(this.getAttribute('index')-0);
		if(btn[index].className.indexOf('curr_btn')>=0) return;
			for(i=0;i<btn.length;i++){
				if(index==i){
					btn[i].className='tab_btn curr_btn front_textOne';
					div[i].className='tab_div curr_div front_textOne';
				}else{
					btn[i].className='tab_btn front_textOne';
					div[i].className='tab_div front_textOne';
				}
			}
		}
	}
	
	if(document.getElementById("divOneLeft").clientHeight<document.getElementById("divOneRight").clientHeight){
		document.getElementById("divOneLeft").style.height=document.getElementById("divOneRight").offsetHeight+"px";
	}
	else{
		document.getElementById("divOneRight").style.height=document.getElementById("divOneLeft").offsetHeight+"px";
	}
	
	if(document.getElementById("divTwoLeft").clientHeight<document.getElementById("divTwoRight").clientHeight){
		document.getElementById("divTwoLeft").style.height=document.getElementById("divTwoRight").offsetHeight+"px";
	}
	else{
		document.getElementById("divTwoRight").style.height=document.getElementById("divTwoLeft").offsetHeight+"px";
	}
	
};
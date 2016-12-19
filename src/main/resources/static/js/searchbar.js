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
					btn[i].className='tab_btn curr_btn';
					div[i].className='tab_div curr_div';
				}else{
					btn[i].className='tab_btn';
					div[i].className='tab_div';
				}
			}
		}
	}
};
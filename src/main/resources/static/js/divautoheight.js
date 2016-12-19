window.onload=window.onresize=function(){
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
}
		var columnSplitTitleArray = new Array();
		var columnSplitFieldArray = new Array();
		var columSplitField = 'dialectText';
		for(var i=0;i<locationDes.length;i++){
			columnSplitTitleArray.push(locationDes[i]+"声母");
			columnSplitTitleArray.push(locationDes[i]+"方言读音");
			columnSplitFieldArray.push(columSplitField+"-"+"dia"+"-"+did[i]);
			columnSplitFieldArray.push(columSplitField+"-"+"ti"+"-"+did[i]);
		}
		if(compareEntity == "onSet"){
			title.innerHTML = "( "+ locationDes+" )声母比较";
			$table.bootstrapTable({
				data: roughList,
				dataType: "json",
				toolbar: '#toolbar',                //工具按钮用哪个容器
				striped: true,                      //是否显示行间隔色
				singleSelect: false,
				pagination: true, 					//分页
				pageNumber:1,                       //初始化加载第一页，默认第一页
				pageSize: 10,                       //每页的记录行数（*）
				pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
				search: false, 						//显示搜索框
				sidePagination: "client", 			//服务端处理分页
				showExport: true,
				columnSplitTitleArray: columnSplitTitleArray,
				columnSplitFieldArray: columnSplitFieldArray,
				columSplitField: columSplitField,
				exportDataType: "all",
				columns: [{
						field: 'willeText',
						title: '古藏语声母',
						align: 'center'
					}, {
						field: 'orignRepresentationText',
						title: '藏文例词',
						align: 'center',
						class: 'representationTextClass'
					},  {
						field: 'orignWltranscriptionText',
						title: '例词威利转写',
						align: 'center'
					}, {
						field: 'dialectText',
						title: '方言比较',
						align: 'center'						
					}, {
						field: 'translationText',
						title: '例词汉语释义',
						align: 'center',
						formatter:function(value,row,index){ 
							var str = value;
							var pattern =new RegExp("\\((.| )+?\\)","igm");
							if(str.match(pattern)!=null){
								result = str.replace(str.match(pattern).toString(), '');
							}else{
								result = str;
							}
							return result;
						}
					}
				]
			});
		}
		
		if(compareEntity == "final"){
			title.innerHTML = "( "+ locationDes+" )韵母比较";
			$table.bootstrapTable({
				data: roughList,
				dataType: "json",
				toolbar: '#toolbar',                //工具按钮用哪个容器
				striped: true,                      //是否显示行间隔色
				singleSelect: false,
				pagination: true, 					//分页
				pageNumber:1,                       //初始化加载第一页，默认第一页
				pageSize: 10,                       //每页的记录行数（*）
				pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
				search: false, 						//显示搜索框
				sidePagination: "client", 			//服务端处理分页
				showExport: true,
				columnSplitTitleArray: columnSplitTitleArray,
				columnSplitFieldArray: columnSplitFieldArray,
				columSplitField: columSplitField,
				exportDataType: "all",
				columns: [  {
						field: 'willeText',
						title: '古藏语韵母',
						align: 'center'
					}, {
						field: 'orignRepresentationText',
						title: '藏文例词',
						align: 'center',
						class: 'representationTextClass'
					}, {
						field: 'orignWltranscriptionText',
						title: '例词威利转写',
						align: 'center'
					}, {
						field: 'dialectText',
						title: '方言比较',
						align: 'center'
					}, {
						field: 'translationText',
						title: '例词汉语释义',
						align: 'center',
						formatter:function(value,row,index){ 
							var str = value;
							var pattern =new RegExp("\\((.| )+?\\)","igm");
							var result;
							if(str.match(pattern)!=null){
								result = str.replace(str.match(pattern).toString(), '');
							}else{
								result = str;
							}
							return result;
						}
					}
				]
			});
		}

var backButton = document.getElementById('back-to-up');
backButton.href = "/getDiaDiaCompQueryListHTML?did="+did+"&dlocation="+locationDes+"&compareentity="+compareEntity;
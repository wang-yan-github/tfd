$(function(){
	var REQUEST_URL_SOURCE_LIST=contextPath+"/fixedasset/act/FixedassetSourceAct/sourceList.act";
	
	
	
	function loadSourceList(){
		$("#source-list").datagrid({
			url:REQUEST_URL_SOURCE_LIST,
	        columns:[
	             [
					{field:"sort",title:"排序号",width:"20%"},
					{field:"source",title:"来源处",width:"80%"}
	             ]
            ],
            pagination:true,
            pageNumber:2,
            displayMsg:"第{from}-{to}，总条数：{total}",
            
            toolbar:"#tool-bar"
		});
		var dg=$("#source-list").datagrid("getPager");
		dg.pagination({
			pageNumber:2,
			beforePageText:"第",
			afterPageText:"页,共{pages}页",
			displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}
	function initPageData(){
		loadSourceList();
	}
	function initPageOpt(){
		$("#opt-add").click(function(){
			$.
		});
		
	}
	initPageData();
	initPageOpt();
});
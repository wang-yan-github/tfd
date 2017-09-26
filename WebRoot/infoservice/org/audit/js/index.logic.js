
$(function(){
	var REQUEST_URL_SEARCH_OF_PAGING=contextPath+"/infoservice/org/act/InfoserviceOrgAct/searchOfPaging.act"
	
	
	
	$("#org-list").datagrid({
		url:REQUEST_URL_SEARCH_OF_PAGING,
        columns:[
             [
				{field:"org_name",title:"企业名称",width:"40%"},
				{field:"not_audit_service_item_count",title:"企业服务项",width:"20%"},
				{field:"submit_date",title:"提交时间",width:"20%"},
				{field:"optbar",title:"操作",width:"20%"}
             ]
        ],
        pagination:true,
        pageNumber:1,
        displayMsg:"第{from}-{to}，总条数：{total}"
	});
	$("#org-list").datagrid("getPager").pagination({
		pageNumber:1,
		beforePageText:"第",
		afterPageText:"页,共{pages}页",
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$(document).on("click",".opt-audit",function(){
		alert("正在审核。。。");
	});
	
	$("#search-advanced").on("click",function(){
		$("#modal-search-advanced").modal("show");
	});
});
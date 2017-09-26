function readNews(newsId)
{
	window.location=contextPath+"/news/read/readnews.jsp?newsId="+newsId;
}
function editNews(newsId,status)
{
	window.location=contextPath+"/news/manage/edit.jsp?newsId="+newsId+"&status="+status;
	}
	
	function readsNews(newsId){
			 var url=contextPath+"/news/manage/readsNum.jsp?newsId="+newsId;
			window.location=url;
	 	}
	
function delNews()
{	
	var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NEWS_ID);
	    });
	    if(runIds.length!=0){
	    	if(confirm("确认删除？")){
	var url=contextPath+"/news/act/NewsAct/delNewsAct.act";
	$.ajax({
	url:url,
	dataType:"text",
	traditional: true,
	data:{"newsId":runIds},
	async:false,
	error:function(e){
		
	},
	success:function(data){
		if(data!=0)
			{
			getnews();
			}
	}
});
}
}else{
	 	parent.layer.msg('请选择数据行',function(){});
	 	}
}
	$(function(){
		getnews(); 
		getSelect("news","newstype","");
	    $("#newstype").change(function (){
	    	getnews();
	    });
	});	
	function getnews(){
		$("#myTable").datagrid({
	        width: document.body.clientWidth,
			rows:5,
	        collapsible: true,
	        url: contextPath+"/news/act/NewsAct/getNewsListAct.act",
	        method: 'POST',
	        loadMsg: "数据加载中...",
	        pagination:true,
	        striped: true,
	        singleSelect:false,  
	        remoteSort:true, 
	        queryParams:{
	        	newstype:$("#newstype").val()
	        },
	        onLoadSuccess:function(data){ 
	        	if(data.total == 0){
	        		$(".datagrid").hide();
	        		$(".MessageBox").show();
	  			}else{
	  				var tableview=$('#myTable').datagrid('getPager');
        			tableview.show();
	  				$("#newSelect").show();
	        		$("#btnitem").show(); 
	  			}
	        },
	        onBeforeLoad:function(param){
	        	var tableview=$('#myTable').datagrid('getPager');
        		tableview.hide();
	        },
	        columns:[[
	       	 	{title: '全选', field: '', width: '2%', align: 'center',checkbox:true},
	           	{title: '标题', field: 'TITLE', width: '15%', align: 'center',sortable:true},
	           	{title: '类型', field: 'TYPE', width: '10%', align: 'center',sortable:true},
	            {title: '发布时间', field: 'CREATE_TIME', width: '14%', align: 'center',sortable:true},
	            {title: '结束时间', field: 'END_TIME', width: '14%', align: 'center',sortable:true,
	            		formatter: function (value, rowData, rowIndex) {
	            			if(rowData.END_TIME=='0'){
	            				return '';
	            			}else{
	            				return rowData.END_TIME;
	            			}
	            		}
	            
	            },
	            {title: '发布状态', field: 'STATUS', width: '10%', align: 'center',
	            	formatter: function (value, rowData, rowIndex) {
	            		if(rowData.STATUS==1)
	            			{
	            					var myDate = new Date();
	           		var time=rowData.END_TIME;	  
	           		if(time!=undefined){
	           			if(time=="0"){
	           				return '生效';
	           			}else{
	           		time=new Date(time.replace(/-/g,"/"));
	           	if(time.getTime()>myDate.getTime()||time=="0"){
	                	   return '生效';
	                   }else{
							return '已终止';
	                   }
	                }	
	               }
	            			}else{
	            				return "未发布";	
	            			}
	                }
	            },
	            {title: '点击数', field: 'ONCLICK_COUNT', width: '8%', align: 'center'},
	            {title:'审批结果',field:'APPROVAL_STATUS',width:'12%',align:'center',
	            	formatter: function (value, rowData, rowIndex) {
	                   if(rowData.APPROVAL_STATUS==1){
	                	   return '通过';
	                   }else{
	                	   if(rowData.APPROVAL_STATUS==0||rowData.APPROVAL_STATUS==null){
	                		   return '待审批';
	                	   }
	                	   else{
	                	   		return '不通过';
	                	   }
	                   }
	                }	
	           	},
	            {title: '操作', field: 'OPT', width: '15%', align: 'center',
	            	formatter: function (value, rowData, rowIndex) {
	            		if(rowData.APPROVAL_STATUS==1)
	            			{
	            				var myDate = new Date();
	           					var time=rowData.END_TIME;
	           					if(time=="0"){
	           				return "<a href=\"javascript:readNews('"+rowData.NEWS_ID+"')\">查看</a>&nbsp;<a href=\"javascript:endNews('"+rowData.NEWS_ID+"')\">终止</a>&nbsp;<a href=\"javascript:readsNews('"+rowData.NEWS_ID+"')\">查阅情况</a>";
	           			}else{
	           					time=new Date(time.replace(/-/g,"/"));
	            				if(time.getTime()>myDate.getTime()){
	            			return "<a href=\"javascript:readNews('"+rowData.NEWS_ID+"')\">查看</a>&nbsp;<a href=\"javascript:endNews('"+rowData.NEWS_ID+"')\">终止</a>&nbsp;<a href=\"javascript:readsNews('"+rowData.NEWS_ID+"')\">查阅情况</a>";
	            			}else{
	            				return "<a href=\"javascript:readNews('"+rowData.NEWS_ID+"')\">查看</a>&nbsp;<a href=\"javascript:btnmyModalLabel('"+rowData.NEWS_ID+"');\">生效</a>&nbsp;<a href=\"javascript:readsNews('"+rowData.NEWS_ID+"')\">查阅情况</a>";
	            			}
	            			}
	            			}else{
	            				var myDate = new Date();
	           					var time=rowData.END_TIME;	         
	           					if(time!=undefined){  	
	           						
	           					if(time=="0"){
	           				return rowData.OPT;	
	           			}else{		
	           					time=new Date(time.replace(/-/g,"/"));
	            				if(time.getTime()>myDate.getTime()){
	            				return rowData.OPT;	
	            				}else{
	            					return "<a href=\"javascript:editNews('"+rowData.NEWS_ID+"','"+rowData.STATUS+"')\">修改</a>&nbsp;<a href=\"javascript:readNews('"+rowData.NEWS_ID+"');\">查看</a>&nbsp;&nbsp;"+
	            							"<a href=\"javascript:btnmyModalLabel('"+rowData.NEWS_ID+"');\">生效</a>";
	            				}
	            				}
	            			}
	            			}
	                }
	            }
	        ]]
	    });
	     
	    var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为5  
	        pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
	}
	function endNews(newsId){
	var url = contextPath + "/news/act/NewsAct/endNewsAct.act";
	$.ajax({
		url : url,
		dataType : "text",
		data : {
			newsId : newsId,
			endTime:$("#endTime").val()
		},
		async : false,
		error : function(e) {
		},
		success : function(data) {
			if (data !=0) {
				getnews();
			} 
		}
	}); 
	}
	
	function btnmyModalLabel(newsId){
		$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
		$("#newsId").val(newsId);
		$("#statusbtn").unbind('click').removeAttr('onclick').click(function (){
	    	updateendNews();
	    });
	}
	function updateendNews(){
		if($("#endTime").val()!=""){
		var url = contextPath + "/news/act/NewsAct/endNewsAct.act";
	$.ajax({
		url : url,
		dataType : "text",
		data : {
			newsId : $("#newsId").val(),
			endTime:$("#endTime").val()
		},
		async : false,
		error : function(e) {

		},
		success : function(data) {
			if (data !=0) {
				$("#myModal").modal('hide'); 
				getnews();
				$("#newsId").val("");
				$("#endTime").val("");
			} 
		}
	}); 
	}else{		
		parent.layer.msg('请选择终止时间',function(){});
	}
	}
	function batchends(endStatus){
		var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NEWS_ID);
	    });
	    if(runIds.length!=0){
	    	if(endStatus==1){
	    		endsNews(endStatus);
	    	}else{
		$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
		$("#endStatus").val(endStatus);
		$("#statusbtn").unbind('click').removeAttr('onclick').click(function (){
	    	endsNews(endStatus);
	    });
	    }
	    }
	    else{
	    	parent.layer.msg('请选择数据行',function(){});
	    }
	}
	function endsNews(endStatus){
		var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NEWS_ID);
	    });
	    if(endStatus==1){
	    	var url=contextPath+"/news/act/NewsAct/endsNewsAct.act";
	$.ajax({
	url:url,
	dataType:"text",
	traditional: true,
	data:{"newsId":runIds,endTime:$("#endTime").val()},
	async:false,
	error:function(e){
	},
	success:function(data){
		if(data!=0)
			{
			$("#myModal").modal('hide'); 
			getnews();
			$("#newsId").val("");
				$("#endTime").val("");
			}
	}
});
	    }else{
	    if($("#endTime").val()!=""){
	var url=contextPath+"/news/act/NewsAct/endsNewsAct.act";
	$.ajax({
	url:url,
	dataType:"text",
	traditional: true,
	data:{"newsId":runIds,endTime:$("#endTime").val()},
	async:false,
	error:function(e){
	},
	success:function(data){
		if(data!=0)
			{
			getnews();
			$("#newsId").val("");
				$("#endTime").val("");
				$("#myModal").modal('hide'); 
			}
	}
});
}else{
	parent.layer.msg('请选择时间',function(){});
}
}
}

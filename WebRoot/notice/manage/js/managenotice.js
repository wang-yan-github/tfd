$(function(){
	getSelect("notice","noticeType","");
	getnotice();
	$("#noticeType").change(function (){
		getnotice();
	});
});	
function readsnotice(noticeId){
	 		var url=contextPath+"/notice/manage/readsNum.jsp?noticeId="+noticeId;
			window.location=url;
	 	}

function getnotice(){
	$("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: contextPath+"/notice/act/NoticeAct/getnoticelistAct.act",
        method: 'POST',
        loadMsg: "数据加载中...",
        pagination:true,
        striped: true,
        singleSelect:false,  
        sortName:'CREATE_TIME',
      	sortOrder:'desc',
        remoteSort:true, 
        queryParams:{
	        	noticeType:$("#noticeType").val()
	        },
	        onLoadSuccess:function(data){  
	           		if(data.total == 0){
	           		$(".datagrid").hide();
	        		$(".MessageBox").show();
	  			}else{
	  				var tableview=$('#myTable').datagrid('getPager'); 
        			tableview.show();
	  				$("#noticeSelect").show();
	        		$("#btnitem").show(); 
	  			}
	        },
	        onBeforeLoad:function(param){
        		var tableview=$('#myTable').datagrid('getPager'); 
        		tableview.hide();
	        },
        columns:[[
       		{title: '全选', field: '', width: '2%', align: 'center',checkbox:true},
           	{title: '标题', field: 'NOTICE_TITLE', width: '18%', align: 'center',sortable:true},
           	{title: '类型', field:'NOTICE_TYPE', width: '8%' ,align :'center' ,sortable:true },
            {title: '发布时间', field: 'CREATE_TIME', width: '14%', align: 'center',sortable:true},
            {title: '结束时间', field: 'END_TIME', width: '14%', align: 'center',sortable:true,
            formatter: function (value, rowData, rowIndex) {
            		if(rowData.END_TIME=='0'){
            			return '';
            		}else{
            		 return	rowData.END_TIME ;
            		}
            }
            },
            {title: '状态', field: 'NOTICE_STATUS', width: '6%', align: 'center',sortable:true,
            	formatter: function (value, rowData, rowIndex) {
            		if(rowData.NOTICE_STATUS==1)
            			{
            			return "已发布";
            			}else{
            				return "未发布";	
            			}
                }
            },
            {title: '审批状态', field: 'APPROVAL_STATUS', width: '8%', align: 'center',sortable:true,
            	formatter: function (value, rowData, rowIndex) {
            		if(rowData.APPROVAL_STATUS==1)
            			{
            			return "审批通过";
            			}else{
            				if(rowData.APPROVAL_STATUS==2){
            					return "审批未通过";
            				}
            				else{
            				return "未审批";	
            				}
            			}
                }
            },
            {title: '点击数', field: 'ONCLICK_COUNT', width: '5%', align: 'center',sortable:true},
            {title: '状态', field: 'ID', width: '5%', align: 'center',
            formatter: function (value, rowData, rowIndex) {
            			var myDate = new Date();
	           		var time=rowData.END_TIME;	           		
	           		if(time!=undefined){
	           			if(time=="0"){
	           				return '生效';
	           			}else{
	           		time=new Date(time.replace(/-/g,"/"));
	           	if(time.getTime()>myDate.getTime()){
	                	   return '生效';
	                   }else{
							return '已终止';
	                   }
	                }
	               }
	                	
                }
            
            },
            {title: '操作', field: 'OPT', width: '18%', align: 'center',sortable:true,
            	formatter: function (value, rowData, rowIndex) {
            		if(rowData.APPROVAL_STATUS==1){
            			var myDate = new Date();
	           					var time=rowData.END_TIME;
            			if(time=="0"){
	           				return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:readnotice('"+rowData.NOTICE_ID+"');\">查看</a>&nbsp;&nbsp;<a href=\"javascript:endNotice('"+rowData.NOTICE_ID+"');\">终止</a>&nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
	           			}else{
	           					time=new Date(time.replace(/-/g,"/"));
	            				if(time.getTime()>myDate.getTime()){
            			return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:readnotice('"+rowData.NOTICE_ID+"');\">查看</a>&nbsp;&nbsp;<a href=\"javascript:endNotice('"+rowData.NOTICE_ID+"');\">终止</a> &nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
            			}else{
            				return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:readnotice('"+rowData.NOTICE_ID+"');\">查看</a>&nbsp;&nbsp;<a href=\"javascript:showMoadl('"+rowData.NOTICE_ID+"');\">生效</a> &nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
            			}
            			
            			}
	            				
            			}
            		else{
            					var myDate = new Date();
	           					var time=rowData.END_TIME;
            			if(rowData.APPROVAL_STATUS==2){
            				if(time=="0"){
            				return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:updatenotice('"+rowData.NOTICE_ID+"');\">修改</a>&nbsp;&nbsp;<a href=\"javascript:endNotice('"+rowData.NOTICE_ID+"');\">终止</a>&nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
            				}else{
            					time=new Date(time.replace(/-/g,"/"));
            					if(time.getTime()>myDate.getTime()){
            						return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:updatenotice('"+rowData.NOTICE_ID+"');\">修改</a>&nbsp;&nbsp;<a href=\"javascript:endNotice('"+rowData.NOTICE_ID+"');\">终止</a>&nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
            					}else{
            						return "<a href=\"javascript:selectapproval('"+rowData.NOTICE_ID+"')\">审批意见</a>&nbsp;&nbsp;<a href=\"javascript:updatenotice('"+rowData.NOTICE_ID+"');\">修改</a>&nbsp;&nbsp;<a href=\"javascript:showMoadl('"+rowData.NOTICE_ID+"');\">生效</a>&nbsp;&nbsp;<a href=\"javascript:readsnotice('"+rowData.NOTICE_ID+"');\">查阅情况</a>";
            					}
            				}
            			}else{
	           					if(time=="0"){
	           				return rowData.OPT;	
	           			}else{
	           					time=new Date(time.replace(/-/g,"/"));
	            				if(time.getTime()>myDate.getTime()){
	            				return rowData.OPT;	
	            				}else{
	            					return "<a href=\"javascript:updatenotice('"+rowData.NOTICE_ID+"');\">修改</a>&nbsp;&nbsp;<a href=\"javascript:readnotice('"+rowData.NOTICE_ID+"');\">查看</a>&nbsp;&nbsp;<a href=\"javascript:showMoadl('"+rowData.NOTICE_ID+"');\">生效</a>";
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
//批量 改变终止时间
function endsnotice(endStatus){
	var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NOTICE_ID);
	    });
	    if(runIds.length!=0){
	if(endStatus==0){
		$(document).ready(function(){ 
		$('#myModaltime').modal({backdrop: 'static', keyboard: false});
		$("#myModaltime").modal('show'); 
		});
		$("#btnsub").unbind('click').removeAttr('onclick').click(function (){
	    	starNotice();
	    });
		
	}else{
		var url=contextPath+"/notice/act/NoticeAct/endsnoticeAct.act";
	$.ajax({
	url:url,
	dataType:"text",
	traditional: true,
	data:{"noticeId":runIds,endTime:$("#endTime").val()},
	async:false,
	error:function(e){
	},
	success:function(data){
		if(data!=0)
			{
			getnotice();
			}
	}
});
}
}else{
	parent.layer.msg('请选择数据行',function(){});
}
}
function starNotice(){
	var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NOTICE_ID);
	    });
	var url=contextPath+"/notice/act/NoticeAct/endsnoticeAct.act";
	$.ajax({
	url:url,
	dataType:"text",
	traditional: true,
	data:{"noticeId":runIds,endTime:$("#endTime").val()},
	async:false,
	error:function(e){
	},
	success:function(data){
		if(data!=0)
			{
			getnotice();
			$("#myModaltime").modal('hide'); 
				getnotice();
				$("#noticeId").val("");
				$("#endTime").val("");
			}
	}
});
}

function endNotice(noticeId){
	var url=contextPath+"/notice/act/NoticeAct/endNoticeAct.act";
		$.ajax({
			url:url,
			dataType:"text",
			data:{noticeId:noticeId,
				endTime:$("#endTime").val()
				},
			async:false,
			error:function(e){
				
			},
			success:function(data){
				if(data!=0)
					{
					getnotice();
					}
			}
		});
}
function showMoadl(noticeId){
	$(document).ready(function(){ 
		$('#myModaltime').modal({backdrop: 'static', keyboard: false});
		$("#myModaltime").modal('show'); 
		});
		$("#noticeId").val(noticeId);
		$("#btnsub").unbind('click').removeAttr('onclick').click(function (){
	    	updateendnotice();
	    });
}
function updateendnotice(){
	if($("#endTime").val()!=""){
		var url=contextPath+"/notice/act/NoticeAct/endNoticeAct.act";
	$.ajax({
		url : url,
		dataType : "text",
		data : {
			noticeId : $("#noticeId").val(),
			endTime:$("#endTime").val()
		},
		async : false,
		error : function(e) {

		},
		success : function(data) {
			if (data!=0) {
				$("#myModaltime").modal('hide'); 
				getnotice();
				$("#noticeId").val("");
				$("#endTime").val("");
			} 
		}
	}); 
	}else{
		parent.layer.msg('请选择终止时间',function(){});
	}
}
function updatenotice(noticeId)
{
	window.location=contextPath+"/notice/manage/editnotice.jsp?noticeId="+noticeId;
	}
function delnotice(){
	var checkedItems = $('#myTable').datagrid('getChecked');
	    var runIds = [];
	    $.each(checkedItems, function(index, item){
	    	runIds.push(item.NOTICE_ID);
	    });
	    if(runIds.length!=0){
	if(confirm("确认删除？")){
		var url=contextPath+"/notice/act/NoticeAct/delnoticeAct.act";
		$.ajax({
			url:url,
			dataType:"text",
			traditional: true,
			data:{"noticeId":runIds},
			async:false,
			error:function(e){
				
			},
			success:function(data){
				if(data!=0)
					{
					getnotice();
					}
			}
		});
	}
	}else{
		parent.layer.msg('请选择数据行',function(){});
	}
	}
	function readnotice(noticeId)
	{
		window.location =contextPath+"/notice/readnotice/readnotice.jsp?noticeId="+noticeId; 
	}
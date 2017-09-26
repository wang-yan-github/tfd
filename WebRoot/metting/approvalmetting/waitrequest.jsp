<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看待批会议</title>
<script type="text/javascript" >
$(function(){
    $("#myTable").datagrid({
        width: document.body.clientWidth,
		rows:5,
        collapsible: true,
        url: contextPath+"/meeting/act/MeetingrequestAct/waitrequestAct.act",
        method: 'POST',
        sortName:'ID',
      	sortOrder:'DESC',
        loadMsg: "数据加载中...",
        pagination:true,
        striped: true,
        rownumbers:true, 
        singleSelect:true,  
        remoteSort:true, 
        columns:[[
				{title: '名称', field: 'MEETING_NAME', width: '25%', align: 'center',sortable:true},
					{title: '申请人', field:'USER_NAME', width: '15%' ,align :'center' ,sortable:true },
				{title: '开始时间', field: 'MEETING_STARTTIME', width: '20%', align: 'center',sortable:true},
				{title: '会议室', field: 'BOARDROOM_NAME', width: '15%', align: 'center',sortable:true    },
				{title: '操作', field: 'OPT', width: '20%', align: 'center'}
        ]]
    });
    var p = $('#myTable').datagrid('getPager');  
        $(p).pagination({  
        pageSize: 10,
        pageList: [10, 20, 30 ,50],
        beforePageText: '第', 
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });  
});
function updatemeeting(requestId){
	var url=contextPath+"/metting/requestmetting/updaterequest.jsp?requestId="+requestId;
	window.location=url;
}
function passmeeting(requestId){
	var url=contextPath+"/meeting/act/MeetingrequestAct/getIdrequestAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{requestId:requestId},
		async:false,
		error:function(e){
		},
		success:function(data){
		var	meetingStarttime=data.meetingStarttime;
		var	meetingEndtime=data.meetingEndtime;
		var	meetingName=data.meetingName;
		var	meetingTheme=data.meetingTheme;
		var	accountId=data.createUser;
		var	meetingType=data.meetingType;
		var	cycType=data.cycType;
		var	attendStaff=data.attendStaff;
		var	isSms=JSON.stringify(data.isSms);
		var	warnTime=data.warnTime;
		var url=contextPath+"/meeting/act/MeetingrequestAct/adoptmeetingAct.act";
		$.ajax({
			   url:url,
			   type:'post',
			   traditional: true,
		   		async:false,
		   		data:{requestId:requestId,
		   			meetingStarttime:meetingStarttime,
		   			meetingEndtime:meetingEndtime,
		   			meetingName:meetingName,
		   			meetingTheme:meetingTheme,
		   			accountId:accountId,
		   			warnTime:warnTime,
		   			meetingType:meetingType,
		   			cycType:cycType,
		   			attendStaff:attendStaff,
		   			isSms:isSms
		   		},
		   		success:function(data){
		   			if(data!=0){
		   			parent.layer.msg('通过成功！');
		   			window.location.reload();
		   			}
		   		}
		   });
	}
	});
}

function notpassmeeting(requestId){
	var url=contextPath+"/meeting/act/MeetingrequestAct/notadoptmeetingAct.act";
	$.ajax({
		   url:url,
		   type:'post',
	   		async:false,
	   		data:{requestId:requestId},
	   		success:function(data){
	   			if(data!=0){
	   			parent.layer.msg('不通过成功！');
	   			window.location.reload();
	   			}
	   		}
	   });
}
function approvalmeeting(requestId){
	var url=contextPath+"/metting/approvalmetting/approvalrequest.jsp?requestId="+requestId;
	window.location=url;
}
</script>
</head>
<body>
<div id="myTable" name="myTable"></div>
</div>
</body>
</html>
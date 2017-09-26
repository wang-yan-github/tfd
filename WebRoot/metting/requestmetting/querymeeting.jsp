<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议查询</title>
<script type="text/javascript" charset="utf-8"
	src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
	<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript">
function getboardroomName(){
	var url=contextPath+"/meeting/act/BoardroomAct/getaccountAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			for(var i=0;i<data.length;i++){
				var boardroomName="<option value='"+data[i].boardroomId+"'>"+data[i].boardroomName+"</option>";
				$("#boardroomId").append(boardroomName);
			}
	}
	});
}
$(function (){
	getboardroomName();
});
	function queryboardroomdevice() {
		$("#bigdiv").hide();
		$('#table').show();
		var url = contextPath+"/meeting/act/MeetingrequestAct/termquertmeetingAct.act";
		$("#myTable").datagrid({
			width : document.body.clientWidth,
			rows : 5,
			collapsible : true,
			url : url,
			queryParams : {
				meetingName:$("#meetingName").val(),
				createUser:$("#createUser").val(),
				starttime:$("#starttime").val(),
				endtime:$("#endtime").val(),
				attendStaff:$("#attendStaff").val(),
				boardroomId:$("#boardroomId").val(),
				meetingStatus:$("#meetingStatus").val()
			},
			method : 'POST',
			sortName : 'ID',
			sortOrder:'desc',
			loadMsg : "数据加载中...",
			pagination : true,
			striped : true,
			rownumbers:true,
			singleSelect : true,
			remoteSort : true,
			 columns:[[
			           	{title: '名称', field: 'MEETING_NAME', width: 300, align: 'center',sortable:true},
			           	{title: '申请人', field:'USER_NAME', width: 150 ,align :'center' ,sortable:true },
			            {title: '开始时间', field: 'MEETING_STARTTIME', width: 300, align: 'center',sortable:true},
			            {title: '会议室', field: 'BOARDROOM_NAME', width: 150, align: 'center',sortable:true},
			            {title: '操作', field: 'OPT', width: '20%', align: 'center'}
			        ]]
		});

		var p = $('#myTable').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为5  
			pageList : [ 10, 20, 30, 50 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});

	}
	function selectmeeting(requestId){
		$("#left").hide();
		document.getElementById("right").src=contextPath+"/metting/requestmetting/lookgetId.jsp?requestId="+requestId;
		$("#detailright").show();
	}
	function returnquery(){
		$('#table').hide();
		$('#bigdiv').show();
	}
	function returnq(){
		$("#left").show();
		$("#detailright").hide();
	}
</script>
<style type="text/css">
html,body{height:100%;}
</style>
</head>
<body style="width:100%;height: 99%;">
<div id="left">
	<div align="center">
		<div id="bigdiv" style="width: 70%;">
			<div class="list-group-item"  style="padding: 0px;cursor: auto;">
<a style="cursor: auto;" class="list-group-item active">会议查询</a>
					<form enctype="multipart/form-data" id="form1" name="form1">
						<table class="table table-striped table-condensed">
							<tr>
								<td nowrap class="TableData title">会议名称：</td>
								<td>
								<div class="col-xs-5">
										<input type="text" id="meetingName" name="meetingNam" class="form-control "  >
										</div></td>
							</tr>
							<tr>
								<td>申请人:</td>
								<td>
								<div class="col-xs-5">
								<textarea rows="3" cols="40" name="createUser" id="createUser" style="display: none;" ></textarea>
								<textarea rows="3" cols="40" name="userName" id="userName" class="form-control" readonly="readonly"></textarea></div>
								<div style="margin-top: 30px;">
								<a href="javascript:void(0);" onclick="userinit(['createUser','userName'],true);">选择</a>
								</div>
								</td>
								</tr>
								<tr>
								<td >发布日期：</td>
								<td>
									<div class="col-xs-5">
										<input class="form-control " type="text" id="starttime"
											name="starttime" size="12" maxlength="10" value=""
											onClick="WdatePicker()" />
									</div>
									<div style="float: left;">&nbsp;至：&nbsp;</div>
									<div class="col-xs-5">
										<input class="form-control " style="float: left;" type="text"
											id="endtime" name="endtime" size="12" maxlength="10"
											class="BigInput" value="" onClick="WdatePicker()" />
									</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>会议室：</td>
								<td>
																		<div class="col-xs-5">
									<select id="boardroomId" name="boardroomId" class="form-control ">
									<option value="" selected="selected">全部</option>
									</select>
									</div>
								</td>
							</tr><tr>
								<td>会议状态：</td>
								<td>
																		<div class="col-xs-5">
									<select id="meetingStatus" name="meetingStatus" class="form-control ">
									<option value="" selected="selected">全部</option>
									<option value="0" >待批</option>
									<option value="1" >已准</option>
									<option value="2" >未批准</option>
									</select>
									</div>
								</td>
							</tr>
							  <td> 参会人员：</td>
								<td>
						    	<div class="col-xs-5">
						<textarea rows="3" cols="40" name="attendStaff" id="attendStaff" style="display: none;" ></textarea>
						<textarea rows="3" cols="40" name="userName" id="userName" readonly="readonly" class="form-control"></textarea></div>
						<div style="margin-top: 30px;">
						<a href="javascript:void(0);" onclick="userinit(['attendStaff','userName']);">添加人员</a>
						</div>
						    </td>
						  </tr>
						    <tr>
							<tr align="center">
								<td colspan="2" ><input
									type="button" value="确定" class="btn btn-primary"
									onclick="queryboardroomdevice();">&nbsp;&nbsp; <input type="reset"
									value="重填" class="btn btn-default">&nbsp;&nbsp;</td>
							</tr>
						</table>
					</form>
			</div>
		</div>
	</div>
	<div id="table" style="display: none;">
		<div>
			<input type="button" value="返回查询页面" class="btn btn-primary"
				onclick="returnquery();">
			<div>&nbsp;</div>
		</div>
		<div id="myTable" name="myTable"></div>
	</div>
	<div id="modaldialog"></div>
	</div>
	<div id="detailright" style="display: none;width:100%;height: 99%;">
	<iframe id="right" scrolling='auto' frameborder='0'  style="border: none;width: 100%;height: 99%;"></iframe>
	</div>
</body>
</html>
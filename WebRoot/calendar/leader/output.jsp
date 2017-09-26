<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日程安排</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>

<link rel="stylesheet" type="text/css" href="<%=stylePath%>/calendar/calendar.css"></link>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style>

body,html {
	height: 100%;
	width:100%;
	margin:0px;
	padding:0px;
}

.content-div{
	width:50%;height:300px;position:absolute;top:100px;left:25%;
}

</style>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" style="float:left;" >查询/导出</span>
</div>
</div>
	<div class="content-div" >
		   <div class="panel-body" style="border:none;box-shadow:none;" >
		   <table class="table table-striped"  >
		   <tr>
		   <td width="15%" >开始时间:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" readonly="readonly"  name="startDate" style="float:left;"  id="startDate" class="form-control BigInput" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></div></td>
		   </tr>
		   <tr>
		   <td>结束时间:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" readonly="readonly"  name="endDate" style="float:left;"  id="endDate" class="form-control BigInput" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></div></td>
		   </tr>
		   <tr>
		   <td>优先级别:</td>
		   <td><div class="col-xs-12 form-group" >
		   			<select style="float:left;" class="form-control BigSelect" name="calLevel" id="calLevel">
		   			<option value="" selected="selected">所有</option>
                   <option value="1">紧急/重要</option>
						<option value="2">紧急/不重要</option>
						<option value="3">不紧急/重要</option>
						<option value="4">不紧急/不重要</option>
            </select>
		   		</div>
		   </td>
		   </tr>
		    <tr>
		   <td>日程状态:</td>
		   <td><div class="col-xs-12 form-group" >
		   			<select class="form-control" id="status">
							<option value="" selected="selected">所有</option>
							<option value="0">未完成</option>
							<option value="1">已完成</option>
							<option value="3">进行中</option>
							<option value="4">已超时</option>
					</select>
		   		</div>
		   </td>
		   </tr>
		    <tr>
		   <td>日程内容:</td>
		   <td><div class="col-xs-12 form-group" ><input type="text" id="content" name="content" class="form-control"  /></div></td>
		   </tr>
		    <tr>
		   <td>部门:</td>
		   <td><div class="col-xs-12 form-group" ><input type="hidden" name="deptPriv" id="deptPriv" /><input type="text" readonly="readonly" id="deptName" name="deptName" class="form-control" onclick="deptinit(['deptPriv','deptName']);" /></div></td>
		   </tr>
		</table>
		</div>
		<div align="center" style="margin-top:30px;" >
			<input type="button"  value="查询" class="btn btn-primary" onclick="javascript:queryCal();" id="btn_query" />
			<input type="button"  value="导出" style="margin-left:30px;" onclick="javascript:outputCal();" class="btn btn-info" id="btn_outport" />
		</div>
	</div>
	<div id="modaldialog"></div>
</body>
<script type="text/javascript">
	function queryCal(){
		var par = "?startDate=" + $("#startDate").val() + "&endDate="
			+ $("#endDate").val() + "&status=" + $("#status").val()
			+ "&content="
			+ $("#content").val()+"&calLevel="+$("#calLevel").val()+"&deptPriv="+$("#deptPriv").val();
		doSearch(par);
	}
	function outputCal(){
		var par = "?startDate=" + $("#startDate").val() + "&endDate="
		+ $("#endDate").val() + "&status=" + $("#status").val()
		+ "&content="
		+ $("#content").val()+"&calLevel="+$("#calLevel").val()+"&deptPriv="+$("#deptPriv").val();
		
		window.location.href = contextPath + "/calendar/act/CalendarAct/CalendarExport2.act" + par;
	}
	function doSearch(par){
		window.location.href = contextPath + "/calendar/leader/query.jsp"+par;
	}
</script>

</html>

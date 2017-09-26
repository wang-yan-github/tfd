<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/charts/index.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/charts/js/setting.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/quickflip/quickflip.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>
<title>设置中心</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
html{
overflow: hidden;
}
.add_content{height:80%;width:600px;position:absolute;top:100px;left:300px;}
</style>
</head>
<body onload="doinit()" >
	<div class="charts_top" >
		<div class="charts_logo" >
			<img src="<%=imgPath %>/charts/setting.png" width="48" height="48" />
		</div>
		<div class="logo_name" >图表设置</div>
		<div class="charts_modules"  align="center" >
		</div>
		<div class="charts_switch" >
			<img id="charts_more" width="30" height="25" src="<%=imgPath %>/charts/switch.png" ></img>
		</div>
	</div>
	<div class="charts_main" >
		<div class="add_content" >
   <div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
   	<input type="hidden" id="chartsConfigId" />
      <h5 class="list-group-item-heading" id="module_title" >
         设置全部权限
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
  <table class="table table-striped"  >
   <tr>
<td width="15%">授权部门:</td>
<td>
<textarea  name="deptPriv" id="deptPriv" style="display: none;"></textarea>
<textarea  name="deptName" readonly="readonly" id="deptName"  class="form-control" style="height:100px;width:330px;float:left;" ></textarea>
<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="添加部门" onclick="deptinit(['deptPriv','deptName']);" />
<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="清空" onclick="clearDept();"/></td>
</tr>
<tr>
<td width="15%">授权角色:</td>
<td><textarea   id="userPriv" name="userPriv" style="display:none;"></textarea>
<textarea  id="userPrivName" name="userPrivName" readonly="readonly" class="form-control" style="height:100px;width:330px;float:left;" ></textarea>
<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="添加角色" onclick="privinit(['userPriv','userPrivName']);" />
<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="清空" onclick="clearPriv();"/></td>
</tr>
<tr>
<td width="15%">授权人员:</td>
<td><textarea  name="accountId" id="accountId" style="display: none;" ></textarea>
<textarea  name="userName" readonly="readonly" id="userName" class="form-control" style="height:100px;width:330px;float:left;" ></textarea>
	<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="添加人员" onclick="userinit(['accountId','userName']);"/>
	<input type="button" style="margin-top:66px;margin-left:10px;float:left;" class="btn btn-default" value="清空" onclick="clearUser();"/></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <button type="button" id="btn_ok" onclick="javascript:setPrivByModule();" class="btn btn-success">确定</button>
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
</div>
<div id="modaldialog"></div>
	</div>
</body>
</html>
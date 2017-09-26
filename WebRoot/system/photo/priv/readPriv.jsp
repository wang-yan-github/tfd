<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String photoId = request.getParameter("photoId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/publicdisk/js/disk.js"></script>
<title>设置权限</title>
<style type="text/css">
	.add_content{height:80%;width:60%;position:absolute;top:50px;left:20%;}
</style>
</head>
<body>
	<div class="add_content" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         设置访问权限
      </h5>
   </a>
    <div class="panel-body" style="border:none;box-shadow:none;" >
  <table class="table table-striped"  >
   <tr>
<td width="15%">授权部门:</td>
<td>
<div class="col-xs-9">	<textarea rows="3" cols="40" name="deptPriv" id="deptPriv" style="display: none;"></textarea>
<textarea rows="6" cols="40" name="deptName" readonly="readonly" id="deptName"  class="form-control" style="height:100px;" ></textarea></div>
<input type="button" style="margin-top:66px;" class="btn btn-default" value="添加部门" onclick="deptinit(['deptPriv','deptName']);" />
<input type="button" style="margin-top:66px;margin-left:10px;" class="btn btn-default" value="清空" onclick="clearDept();"/></td>
</tr>
<tr>
<td width="15%">授权角色:</td>
<td><div class="col-xs-9"><textarea rows="3" cols="40"  id="userPriv" name="userPriv" style="display:none;"></textarea>
<textarea rows="6" cols="40" id="userPrivName" name="userPrivName" readonly="readonly" class="form-control" style="height:100px;" ></textarea></div>
<input type="button" style="margin-top:66px;" class="btn btn-default" value="添加角色" onclick="privinit(['userPriv','userPrivName']);" />
<input type="button" style="margin-top:66px;margin-left:10px;" class="btn btn-default" value="清空" onclick="clearPriv();"/></td>
</tr>
<tr>
<td width="15%">授权人员:</td>
<td><div class="col-xs-9"><textarea rows="3" cols="40" name="accountId" id="accountId" style="display: none;" ></textarea>
<textarea rows="6" cols="40" name="userName" readonly="readonly" id="userName" class="form-control" style="height:100px;" ></textarea></div>
	<input type="button" style="margin-top:66px;" class="btn btn-default" value="添加人员" onclick="userinit(['accountId','userName']);"/>
	<input type="button" style="margin-top:66px;margin-left:10px;" class="btn btn-default" value="清空" onclick="clearUser();"/></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <button type="button" id="btn_ok" class="btn btn-success">确定</button>
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
</div>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
var photoId = "<%=photoId%>";
$(function(){
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoPrivAct.act";
	$.ajax({
			url:requestUrl,
			data:{photoId:photoId,privId:'1'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#photoId').val(data.photoId);
				$('#deptPriv').val(data.readDept);
				$('#deptName').val(data.readDpetName);
				$('#userPriv').val(data.readPriv);
				$('#userPrivName').val(data.readPrivName);
				$('#accountId').val(data.readUser);
				$('#userName').val(data.readUserName);
			}
	});
	$('#btn_ok').click(function(){
		var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/updatePhotoPrivAct.act";
 		$.ajax({
 				url:requestUrl,
 				data:{
 					photoId:photoId,
 					privId:'1',
 					Dept:$('#deptPriv').val(),
 					Priv:$('#userPriv').val(),
 					User:$('#accountId').val()
 				},
 				dataType:"json",
 				async:false,
 				error:function(e){
 					alert(e.message);
 				},
 				success:function(data){
 					if(data=='1'){
 						top.layer.msg('设置成功');
 					}
 				}
 		});
 	});
	$('#btn_back').click(function(){
		history.go(-1);
		return false;
	})
});
</script>
</html>
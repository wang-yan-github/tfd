<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String folderId = request.getParameter("folderId");
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
	.add_content{height:80%;width:600px;position:absolute;top:50px;left:10%;}
</style>
</head>
<body>
	<div class="add_content" >
		<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         批量设置权限<input type="hidden" id="folderId" />
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
<tr>
<td width="15%">权限范围:</td>
<td><div class="col-xs-9">
	<input type="checkbox" value="1" checked="checked" class="privType" />访问权限
	<input type="checkbox" value="2" checked="checked" class="privType" />编辑权限
	<input type="checkbox" value="3" checked="checked" class="privType" />新建权限<br/>
	<input type="checkbox" value="4" checked="checked" class="privType" />下载/打印权限
	<input type="checkbox" value="5" checked="checked" class="privType" />删除权限
	<input type="checkbox" value="6" checked="checked" class="privType" />评论权限</div>
</td>
</tr>
<tr>
<td width="15%">操作:</td>
<td><div class="col-xs-9">
	<input type="radio" value="1" checked="checked" name="type" />添加权限
	<input type="radio" value="2" name="type"  />移除权限
</div></td>
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
var folderId = "<%=folderId%>";
$(function(){
	$('#btn_ok').click(function(){
		$("input[type=radio]").each(function(){
		    if(this.checked){
		    	if($(this).val()=='1'){
		    		var type = $(this).val();
		    		var i = 0;
		    		var j = 0;
			    	$("input[type=checkbox]").each(function(){
					    if(this.checked){
					    	j++;
					    	var privType = $(this).val();
					    	var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/addPriv.act';
					 		$.ajax({
					 				url:requestUrl,
					 				data:{
					 					folderId:folderId,
					 					privId:privType,
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
					 						i++;
					 					}
					 				}
					 		});
					    }
					   
					});
			    	 if(i==j){
			    		 top.layer.msg("设置成功！");
					    }
		    	}else{
		    		var type = $(this).val();
		    		var i = 0;
		    		var j = 0;
			    	$("input[type=checkbox]").each(function(){
					    if(this.checked){
					    	j++;
					    	var privType = $(this).val();
					    	var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/delPriv.act';
					 		$.ajax({
					 				url:requestUrl,
					 				data:{
					 					folderId:folderId,
					 					privId:privType,
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
					 						i++;
					 					}
					 				}
					 		});
					    }
					});
			    	if(i==j){
			    		top.layer.msg("设置成功！");
					}
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
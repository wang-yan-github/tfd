<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String userPrivId=request.getParameter("userPrivId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统权限设置</title>
<script type="text/javascript">
var userPrivId='<%=userPrivId%>';
$(function(){
	var requestUrl=contextPath+"/tfd/system/unit/userpriv/act/UserPrivAct/getUserPrivByUserPrivIdAct.act";
	var paramData={userPrivId:userPrivId};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:paramData,
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			
			for(var name in data){
				$("#"+name).html(data[name]);
			}
		}
	});
});
</script>
</head>
<body>
<div  style="width: 100%;margin-top: 5%;" align="center">
<div class="panel panel-info" style="width:60%;">
   <div class="panel-heading">
      <h3 class="panel-title">
     查看权限组
      </h3>
   </div>
   <table class="table table-striped">
<tr>
<td width="20%;">权限组编号:</td>
<td><div id="userPrivLeave" name="userPrivLeave"></div></td>
</tr>
<tr>
<td>权限组名称:</td>
<td><div id="userPrivName" name="userPrivName"></div></td>
</tr>
<tr>
<td>所属机构:</td>
<td><div id="orgId" name="orgId"></div> </td>
</tr>
</table>
   </div>
   <div align="center"><button type="button" onclick="javascript :history.back(-1);" class="btn btn-primary" >返回</button></div>
</div>
</body>
</html>
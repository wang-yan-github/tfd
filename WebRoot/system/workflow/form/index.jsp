<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建表单</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/addform.css"></link> 
<script type="text/javascript">
$(document).ready(function(){
	$("#layout").layout({auto:true});
});
function formselect()
{
	$("#edit").prop("src",contextPath+"/system/workflow/form/formlist.jsp?type=1");
	}
</script>
<style>
html,body{
height: 100%;
}
html{
overflow: hidden;
}
</style>
</head>
<body style="overflow-y: hidden;margin:0px">
<div id="layout">

<div layout="north" id="north" min=20  height=50 style="height:50px;">
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name">新建表单</span>
</div>
<div class="top_info_right">
				<div style="float: left;"><input type="text" id="formName" name="formName" class="form-control" placeholder="表单名称..."  ></div>
				<div style="float: right;"><button class="btn btn-warning" onclick="formselect();">查询</button></div>
			</div>

</div>
</div>


	<div layout="west" id="west" min=20 width=250 style="width:250px;">
	<iframe name="left" height="100%" width="100%" frameborder="0" scrolling="auto" src="<%=contextPath%>/system/workflow/form/formtree.jsp"></iframe>
	</div>
<div layout="center">
	<iframe name="edit" height="100%" width="100%" frameborder="0" id="edit"  src="<%=contextPath%>/system/workflow/form/add.jsp"></iframe>
	</div>
</div>
</body>
</html>
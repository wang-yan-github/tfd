<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%String accountId=request.getParameter("accountId"); %>
<%String userName=request.getParameter("userName"); %>
<% String beforestaff = session.getAttribute("USER_ID").toString(); %> 

<title>查询日志</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/diary/rightuserdiary.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/dateformat.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/otherdiary/js/rightuserdiary.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>



<script type="text/javascript">
var accountId="<%=accountId%>";
var beforestaff="<%=beforestaff%>";
var userName="<%=userName%>";
</script>
</head>
<body  style="background-color: white;">
<div id="main" style="display: none;">
	<div id="left" align="center">
	<div style="width: 700px;">
	<div id="content" class="feedlist">
	
	</div>
	</div>
	
	</div>
	<div id="right">
		<div class="list-group" style="width:100%;" >
   <a style="cursor: auto;" class="list-group-item active">
      <h5 class="list-group-item-heading">
         <div class='titleName username'></div>
      </h5>
   </a>
			<div id="diarytime" style="height: 240px;width:100%;"></div>
			<div id="time">
				<div class="presenttime form-control">
					<span id="presenttime"></span>&nbsp;<a
						href="javascript:cleartime();">×</a>
				</div>
			</div>
			</div>
			</div>
</div>
 <div class="panel panel-info" id="cue" style="width: 60%;margin-left: 20%;margin-top: 10%;">
				<div class="panel-heading">
					<h3 class="panel-title">
						提示
					</h3>
				</div>
				<div style="margin-left: 10px;height: 80px; vertical-align:middle;">
				<h1 class="panel-title" style="margin-top: 10px;" >
				<sapn id="cuecontent">请选择用户，只能查询您管理范围内的角色序号在您之后的用户</sapn>
					</h1>
					</div>
			</div>
</body>
</html>
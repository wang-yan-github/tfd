<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String runId=request.getParameter("runId"); %>
<%String prcsId=request.getParameter("prcsId"); %>
<%String tableName=request.getParameter("tableName"); %>
<%String flowId=request.getParameter("flowId"); %>
<%String runPrcsId=request.getParameter("runPrcsId"); %>
<%String prcsName=request.getParameter("prcsName"); %>
<%String nodedata=request.getParameter("nodedata"); %>
<!DOCTYPE html>
<html>
<head>
<title>下一步相关信息确认</title>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/formvalidation/css/formValidation.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css"/>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/system/styles/css/style1/workflow/nextprcs.css"/>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/system/jsall/workflowselect/selectuser/selectuser.css"/>


<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/formValidation.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/formvalidation/js/framework/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layout/layout.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/workflowselect/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/workflow/workflownext.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/dowork/nextprcs/js/index.js"></script>
<script type="text/javascript">
	var nodedataStr='<%=nodedata%>';
	var nodedata=eval('(' + nodedataStr + ')');
	var nextData=JSON.stringify(nodedata.nextnode);
	var flowId="<%=flowId%>";
	var runId="<%=runId%>";
	var tableName="<%=tableName%>";
	var prcsId="<%=prcsId%>";
	var prcsName="<%=prcsName%>";
	var runPrcsId ="<%=runPrcsId%>";
</script>
</head>
<body>
	<div id="layout">
		<div layout="west" id="west" min=20 width=250 style="width: 250px;">
		</div>
		<div layout="center" id="center">
			<div id="content"></div>
			<div class="list-group-item" style="height: 55px;">
				<span style='width: 100px; float: left; line-height: 30px;'>提醒方式：</span>
				<div id="smsdiv" name="smsdiv" align="left" style="margin-top: 5px;"></div>
			</div>
			<div align="center" style="margin-top: 20px;">
				<button type="button" class="btn btn-warning" onclick="goback();"style="margin-right: 20px;">
					编辑表单
				</botton>
				<button type="button" onclick="go();" class="btn btn-primary">
					确认
				</botton>
			</div>
		</div>
	</div>
	
</body>
</html>
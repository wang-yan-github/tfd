<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String flowId=request.getParameter("flowId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起流程</title>
<script type="text/javascript" src="<%=contextPath%>/system/workflow/newwork/js/new.js"></script>
<script type="text/javascript">
var flowId="<%=flowId%>";
</script>
<style>
.title_span {
  margin-top: 15px;
  line-height: 50px;
  font-weight: 900;
  font-weight: bold;
  font-size: 18px;
  color: #3f9bca;
}
</style>
</head>
<body onload="doint();">

<div style="float:left;width: 40%;margin-left:1%;">
	<span class="title_span" style="color: black;" id="flowName" name="flowName">当前流程：</span><br>
	<span class="title_span">请填写流程文号\标题：</span>
	<div><input type="text" style="height: 32px;"  id="title" name="title"  class="form-control" placeholder="请输入流程标题..." /></div>
	<br>
	<div align="center">
		<button class="btn btn-default" onclick="creatWork();">新建流程</button> 
		<button class="btn btn-primary" onclick="history.go(-1)">返回</button>
	</div>
</div>

<div style="float:left;width: 58%;;margin-left:1%;">
<span  class="title_span">流程说明及步骤列表</span>
<div id="prcstable" name="prcstable" style="width: 100%">
</div>

	<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseOne">
          流程说明
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body" id="flowFunction" name="flowFunction">
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseTwo">
          原始表单&流程图
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
      <div class="panel-body" align="center">
      <button class="btn btn-default" onclick="goFormView('<%=flowId%>');">原始表单</button>
      <button class="btn btn-default" onclick="goFlowView('<%=flowId%>');">设计流程图</button>
      </div>
    </div>
  </div>
  </div>
</div>
</body>
</html>
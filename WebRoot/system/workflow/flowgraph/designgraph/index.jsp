<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String flowId=request.getParameter("flowId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>设计流程图</title>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/lib/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/lib/jsBezier-0.6.js"></script>        
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-util.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-dom-adapter.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-drag.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-endpoint.js"></script>                
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-connection.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-anchors.js"></script>        
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-defaults.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-connectors-statemachine.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-connectors-flowchart.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-renderers-svg.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-renderers-canvas.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jsPlumb-renderers-vml.js"></script>	
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/src/jquery.jsPlumb.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/flowdesigner/flowdesigner1.0.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/flowdesigner/res/style.css" />
<script>
var designer;//获取设计器
var startNode;
var endNode;
var flowId="<%=flowId%>";
window.onload=function(){
	designer = new Designer("notepad");
	repaint();
}
function repaint(){
	$("#notepad").html("");
	//获取流程步骤
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getFlowProcessList.act";
	var prcsList;
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{flowId:flowId},
		async:false,
		success:function(data){
			prcsList=data;
		}
	});
	if(!prcsList){
		layer.msg("未定义的流程步骤");
		return;
	}
	//绘制步骤
	for(var i=0;i<prcsList.length;i++){
		var data = prcsList[i];
		var sid = data.sid;
		var prcsId = data.prcsId;
		var x = data.x;
		var y = data.y;
		
		if(data.prcsType==1){//开始节点
			startNode = designer.drawStart(x,y,data);
			//设置开始节点菜单
		}else if(data.prcsType==2){//结束节点
			endNode = designer.drawEnd(x,y,data);
		}else if(data.prcsType==3){//普通节点
			var node = designer.drawSimpleNode(x,y,data);
		}else if(data.prcsType==4){//并发节点
			var node = designer.drawParallelNode(x,y,data);
		}else if(data.prcsType==5){//聚合节点
			var node = designer.drawAggregationNode(x,y,data);
		}else if(data.prcsType==6){//子流程节点
			var node = designer.drawChildNode(x,y,data);
		}
}
	
	//绘制步骤
	for(var i=0;i<prcsList.length;i++){
		var data = prcsList[i];
		var sid = data.sid;
		var nexts = data.params.nextPrcs;
		var node = $("#"+sid);
		for(var j=0;j<nexts.length;j++){
			var cur = $("#"+nexts[j]);
			node.addNextNode(cur);
		}
	}
	designer.doLineTo();
}
</script>
</head>
<body style="margin:0px;background-image:url(res/bg.png)">
<div style="height: 100%;">
	<div layout="center" id="notepad">
	</div>
</div>
</body>
</html>
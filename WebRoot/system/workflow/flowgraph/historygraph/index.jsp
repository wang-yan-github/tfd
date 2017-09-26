<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String runId=request.getParameter("runId"); %>
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
var runId="<%=runId%>";
$(function () {
	designer = new Designer("notepad");
	repaint();
	$("[data-toggle='popover']").popover();
});
function repaint(){
	var startNode;
	var endNode;
	$("#notepad").html("");
	//获取流程步骤
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowutility/UitiltyAct/getHistoryGraphAct.act";
	var prcsList;
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{runId:runId},
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
		var prcsName = data.prcsName;
		var prcsId = data.prcsId;
		var x = data.x;
		var y = data.y;
		
		if(data.prcsType==1){//开始节点
			startNode = designer.drawStart(x,y,data);
		}else if(data.prcsType==2){//结束节点
			endNode = designer.drawEnd(x,y,data);
		}else if(data.prcsType==3){//普通节点
			var node = designer.drawSimpleNode(x,y,data);
		}
		if(startNode){
			startNode[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					designer.doLineTo();
				}
			};
		}
		if(endNode){
			endNode[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					designer.doLineTo();
				}
			};
		}
		if(node){
			node[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					designer.doLineTo();
				}
			};
		}
}
	
	//绘制步骤
	for(var i=0;i<prcsList.length;i++){
		var data = prcsList[i];
		var sid = data.sid;
		var node = $("#"+sid);
		var content = "<div style=\"width:250px;\">"+
										"<div>步骤号："+data.prcsId+"</div>"+
										"<div>步骤名称："+data.prcsName+"</div>"+
										"<div>办理人员："+data.userName+"</div>"+
										"<div>接收时间："+data.createTime+"</div>"+
										"<div>办结时间："+data.endTime+"</div>"+
										"<div>审批状态："+data.pass+"</div>"+
										"<div>审批意见："+data.ideaText+"</div>"+
								"</div>";
		node.attr("data-container","body");
		node.attr("data-toggle","popover");
		if(sid%2==0)
			{
			node.attr("data-placement","top");
			}else
				{
			node.attr("data-placement","bottom");
				}
		node.attr("data-html","html");
		node.attr("title","审批过程");
		node.attr("data-content",content);
		var nexts = data.params.nextPrcs;
		for(var j=0;j<nexts.length;j++){
			var cur = $("#"+nexts[j]);
			if(cur.length>0)
				{
				node.addNextNode(cur);
				}
		}
	}
	designer.doLineTo();
}
</script>
</head>
<body style="margin:0px;background-image:url(res/bg.png)">
<div style="height: 100%; ">
	<div layout="center" id="notepad">
	</div>
</div>
</body>
</html>
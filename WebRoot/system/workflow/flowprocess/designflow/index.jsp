<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%String flowId=request.getParameter("flowTypeId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>设计流程</title>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/jsPlumb/lib/jquery-ui-1.9.2-min.js"></script>
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
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/tooltip/jquery.tooltip.min.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/tooltip/jquery.tooltip.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/popupmenu/res/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/flowdesigner/res/style.css" />
	
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/popupmenu/popupmenu.js"></script>
<script>
var designer;//获取设计器
var startNode;
var endNode;
var flowId="<%=flowId%>";
window.onload=function(){
	designer = new Designer("notepad");
	repaint();
	top.flowId = flowId;
}
var prcsList;
function repaint(){
	$("#notepad").html("");
	//获取流程步骤
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getFlowProcessList.act";
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
			startNode.mousedown(startEvent);
		}else if(data.prcsType==2){//结束节点
			endNode = designer.drawEnd(x,y,data);
		}else if(data.prcsType==3){//普通节点
			var node = designer.drawSimpleNode(x,y,data);
			node.mousedown(simpleNodeEvent);
		}else if(data.prcsType==4){//并发节点
			var node = designer.drawParallelNode(x,y,data);
			node.mousedown(parallelEvent);
		}else if(data.prcsType==5){//聚合节点
			var node = designer.drawAggregationNode(x,y,data);
			node.mousedown(aggregationEvent);
		}else if(data.prcsType==6){//子流程节点
			var node = designer.drawChildNode(x,y,data);
			node.mousedown(childEvent);
		}
		if(startNode){
			startNode[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					saveLayout(this);
				}
			};
		}
		if(endNode){
			endNode[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					saveLayout(this);
				}
			};
		}
		if(node){
			node[0].onmouseup = function(e){
				var e = window.event||e;
				if(e.button!=2 && e.button!=3){
					saveLayout(this);
				}
			};
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
/**
 * 添加并发节点
 */
function addParallelNode(node){
	var x = node.position().left;
	var y = node.position().top+node.height()+30;
	var id = node.attr("id");
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/addParalleFlowAct.act";
	var nodestr = {flowId:flowId,prcsType:'4',parentId:id,x:x,y:y};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{flowId:flowId,prcsType:4,parentId:id,x:x,y:y},
		async:false,
		success:function(data){
			nodestr=data;
		}
	});	
	
	if(nodestr){
		var childNode = designer.drawChildNode(x,y,nodestr);
		node.addNextNode(childNode);
		designer.doLineTo();
		repaint();
		childNode.mousedown(childNode);
		
	}else{
		layer.msg("添加并发流程结点失败！");
	}
}
/**
* 添加子流程节点
*/
function addChildNode(node){
	var x = node.position().left;
	var y = node.position().top+node.height()+30;
	var id = node.attr("id");
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/addChildFlowAct.act";
	var nodestr = {flowId:flowId,prcsType:6,parentId:id,x:x,y:y};
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{flowId:flowId,prcsType:6,parentId:id,x:x,y:y},
		async:false,
		success:function(data){
				nodestr=data;
		}
	});	
	
	if(nodestr){
		var childNode = designer.drawChildNode(x,y,nodestr);
		node.addNextNode(childNode);
		designer.doLineTo();
		repaint();
		childNode.mousedown(childNode);
		
	}else{
		layer.msg("添加子流程结点失败！");
	}
}

function parallelEvent(event){
	if(event.which!=3){
		return;
	}
	$(this).popupEmbedMenu([{text:'节点设置',title:'',func:setNodeinfo},
	     	 	            {text:'添加普通节点',title:'',func:addSimpleNode},
	    		            {text:'删除节点',title:'',func:remove}]);
}

function startEvent(event){
	if(event.which!=3){
		return;
	}
	$(this).popupEmbedMenu([{text:'节点设置',title:'',func:setNodeinfo},
	                      	{text:'添加普通节点',title:'',func:addSimpleNode},
	                      	{text:'添加并发节点',title:'',func:addParallelNode},
	                      	{text:'添加子流程节点',title:'',func:addChildNode}]);
}
/***
 * 子节点基本信息设置
 */

function setChildNodeinfo(node)
{
	var id= node.attr("id");
	var url = contextPath+"/system/workflow/flowprocess/designflow/setprops/childindex.jsp?prcsId="+id+"&flowId="+flowId;
	$("#modaliframe").attr("src",url);
	$("#myModalLabel").html("第"+id+"步-基本信息设置");
	$('#modal').modal({backdrop: 'static', keyboard: false});
	$('#modal').modal('show');
}
 
function setNodeinfo(node)
{
	var id= node.attr("id");
	var url = contextPath+"/system/workflow/flowprocess/designflow/setprops/index.jsp?prcsId="+id+"&flowId="+flowId;
	$("#modaliframe").attr("src",url);
	$("#myModalLabel").html("第"+id+"步-基本信息设置");
	$('#modal').modal({backdrop: 'static', keyboard: false});
	$('#modal').modal('show');
 } 
 /*
 * 添加普通步骤
 */
 
function addSimpleNode(node){
	var x = node.position().left;
	var y = node.position().top+node.height()+80;
	var id = node.attr("id");
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/addSimpleNodeAct.act";
	var nodestr ="";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{flowId:flowId,prcsType:3,parentId:id,x:x,y:y},
		async:false,
		success:function(data){
			nodestr=data;
		}
	});	
	if(nodestr){
		var simpleNode = designer.drawSimpleNode(x,y,nodestr);
		node.addNextNode(simpleNode);
		//designer.doLineTo();
		repaint();
		simpleNode.mousedown(simpleNodeEvent);
	}else{
		layer.msg("创建失败!");
	}
}

function simpleNodeEvent(event){
	if(event.which!=3){
		return;
	}
	var menuItems = new Array();
	menuItems.push({text:'节点设置',title:'',func:setNodeinfo});
	menuItems.push({text:'添加普通节点',title:'',func:addSimpleNode});
	//从该节点往上查找并发步骤及聚合步骤
	var patternNode = searchFirstPatternedNode($(this));
	histroy = new Array();
	if(patternNode){
		if(patternNode.attr('parallel')==''){//并发节点
			menuItems.push({text:'添加聚合节点',title:'',func:addAggregationNode});
		}else if(patternNode.attr('aggregation')==''){//聚合节点
			menuItems.push({text:'添加并发节点',title:'',func:addParallelNode});
			menuItems.push({text:'添加子流程节点',title:'',func:addChildNode});
			menuItems.push({text:'指向结束',title:'',func:toEnd});
		}
	}else{
		menuItems.push({text:'添加子流程节点',title:'',func:addChildNode});
		menuItems.push({text:'添加并发节点',title:'',func:addParallelNode});
		menuItems.push({text:'指向结束',title:'',func:toEnd});
	}
	menuItems.push({text:'删除节点',title:'',func:remove});
	$(this).popupEmbedMenu(menuItems);
}

/**
 * 搜索第一个匹配里程碑节点（主要搜索聚合节点和并发节点）
 */
var histroy = new Array();
var loop = 0;
function searchFirstPatternedNode(node){
	if(loop>100){
		loop = 0;
		return undefined;
	}
	for(var i=0;i<histroy.length;i++){
		if(histroy[i].attr("id")==node.attr("id")){
			histroy = new Array();
			return undefined;
		}
	}
	var prevNodes = node.getPrevNodes();
	if(!prevNodes || prevNodes==null){
		histroy = new Array();
		return undefined;
	}else if(prevNodes.length==0){
		histroy = new Array();
		return undefined;
	}
	for(var i=0;i<prevNodes.length;i++){
		var tmp = prevNodes[i];
		histroy.push(tmp);
		if(tmp.attr('aggregation')=='' || tmp.attr('parallel')==''){
			histroy = new Array();
			return tmp;
		}else{
			histroy = new Array();
			loop++;
			return searchFirstPatternedNode(tmp);
		}
	}
}

/**
 * 添加聚合节点
 */
function addAggregationNode(node){
	var x = node.position().left;
	var y = node.position().top+node.height()+30;
	var id = node.attr("id");
	var url = contextPath+"/flowProcess/createProcess.action";
	var json = tools.requestJsonRs(url,{flowId:flowId,prcsType:5,parentId:id,x:x,y:y});
	if(json.rtState){
		var aggregationNode = designer.drawAggregationNode(x,y,json.rtData);
		node.addNextNode(aggregationNode);
		designer.doLineTo();
		repaint();
		aggregationNode.mousedown(aggregationEvent);
		
	}else{
		layer.msg(json.rtMsg);
	}
	
}

function aggregationEvent(event){
	if(event.which!=3){
		return;
	}
	$(this).popupEmbedMenu([{text:'节点设置',title:'',func:setNodeinfo},
 	                      	{text:'添加普通节点',title:'',func:addSimpleNode},
	                      	{text:'添加并发节点',title:'',func:addParallelNode},
	                      	{text:'指向结束',title:'',func:toEnd},
	                      	{text:'删除节点',title:'',func:remove}]);
}

function childEvent(event){
	if(event.which!=3){
		return;
	}
	var menuItems = new Array();
	menuItems.push({text:'节点设置',title:'',func:setChildNodeinfo});
	menuItems.push({text:'删除节点',title:'',func:remove});
	$(this).popupEmbedMenu(menuItems);
}

/**
 * 删除节点
 */
function remove(node){
	if(window.confirm("确定删除该节点吗？")){
		var prcsId = node.attr("id");
		var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/removeNodeAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{flowId:flowId,prcsId:prcsId},
			async:false,
			success:function(data){
				layer.msg("删除步骤成功!");
				location.reload();
			}
		});		
	}
}

/**
 * 指向结束节点
 */
function toEnd(node){
	var prcsId = node.attr("id");
	var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/nextToEndPrcsAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:{flowId:flowId,prcsId:prcsId},
		async:false,
		success:function(data){
			if(data=="OK")
				{
				layer.msg("操作成功！");
				}
			node.addNextNode(endNode);
			designer.doLineTo();
			repaint();
			
		}
	});	
}

//保存布局
function saveLayout(obj){
	var nodes = $("#notepad [node='']");
	//var arr = new Array();
	var layoutArr="";
	nodes.each(function(i,obj){
	layoutArr=layoutArr+"prcsId:"+$(this).attr("id")+",x:"+$(this).position().left+",y:"+$(this).position().top+"#";
	});
	layoutArr=layoutArr.substring(0,layoutArr.length-1);
	var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/updateFlowProcessLayoutAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{layoutArr:layoutArr,flowId:flowId},
		async:false,
		success:function(data){
			if(obj==null){
			    layer.msg("保存布局成功!");
			}
		}
	});
}
</script>
<style type="text/css" media=print>
	.noprint{display : none }
</style>
<style>
	#modal{top:0px !important;}
</style>
</head>
<body style="margin:0px;background-image:url(res/bg.png)" oncontextmenu="return false " >
<div id="layout"  style="height: 100%;">
	<div align="right" class="noprint" layout="north" min=20 style="height:40px;background:#f0f0f0;padding-top:3px;padding-bottom:3px;padding-left:10px;border-bottom:1px solid #f0f0f0">
		<input class="btn btn-default" value="保存布局" type="button" onclick="saveLayout()"/>
		<input class="btn btn-default" value="刷新" type="button" onclick="window.location.reload()"/>
		<input class="btn btn-default" value="打印" type="button" onclick="window.print();"/>
	</div>
	<div layout="center" id="notepad" min='20' style="overflow:hidden;">
	</div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="modal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:auto;">
   <div class="modal-dialog" style="width: 805px;">
      <div class="modal-content">
         <div class="modal-header" style="padding-bottom: 10px;padding-top: 10px;">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h4 class="modal-title" id="myModalLabel">
            </h4>
         </div>
         <div class="modal-body" style="padding: 1px;" >
            <iframe id="modaliframe"  name="modaliframe" style="width: 800px;height: 610px;" frameborder="0"></iframe>
         </div>
<!--          <div class="modal-footer" style="margin-top: 0px;padding-bottom: 0px;padding-top: 0px;"> -->
<!--             <button type="button" class="btn btn-default"  data-dismiss="modal">关闭  </button> -->
<!--          </div> -->
      </div><!-- /.modal-content -->
</div><!-- /.modal -->


</body>
</html>
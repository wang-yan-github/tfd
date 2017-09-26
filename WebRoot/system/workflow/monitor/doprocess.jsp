<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<% String runId=request.getParameter("runId");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/workflow/monitor/js/doprocess.js"></script>

<link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/doprocess.css"></link>
<title>流程处理</title>
<script type="text/javascript">
var runId = "<%=runId%>";
Number.prototype.formatTime=function(){
    // 计算
    var h=0,i=0,s=parseInt(this);
    if(s>60){
        i=parseInt(s/60);
        s=parseInt(s%60);
        if(i > 60) {
            h=parseInt(i/60);
            i = parseInt(i%60);
        }
    }
    // 补零
    var zero=function(v){
        return (v>>0)<10?"0"+v:v;
    };
    return [zero(h),zero(i),zero(s)].join(":");
};
 
</script>
</head>
<body onload="doinit();">

<div class="well with-header with-footer">
<div class="header bg-warning">历史审批步骤</div>
   <table class="table table-striped table-condensed">
      <tr>
	      <td align="center">步骤序号</td>
	      <td align="center">步骤名称</td>
	      <td align="center">办理人</td>
	      <td align="center">办理时间</td>
	      <td align="center">操作</td>
      </tr>
      <tbody id="tbody" name="tbody">
      </tbody>
      </table>
   </div>

<div align="center"><button  type="button" class="btn btn-warning" onclick="history.back();">返回</button></div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="modal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" style="width: 550px;">
      <div class="modal-content">
         <div class="modal-header" style="padding-bottom: 10px;padding-top: 10px;">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h4 class="modal-title" id="myModalLabel">
            </h4>
         </div>
         <div class="modal-body" style="padding: 1px;" >
	         <table class="table table-striped  table-condensed" >
	         	<tr>
	         		<td width="150px">交转步骤：</td>
	         		<td><div class="col-xs-8"> <select class="form-control" id="goprcsId" name="goprcsId"></select></div></td>
	         	</tr>
	         	<tr>
	         		<td width="150px">接收人：</td>
	         		<td><div class="col-xs-8">
	         		<input type="hidden" class="form-control" id="gonextuser" name="gonextuser">
	         		<input type="text" class="form-control" id="userName" name="userName"></div><div style="float: left;margin-top:5px;"><a style="cursor: pointer;" onclick="userinit(['gonextuser','userName'],'true');">添加人员</a>
</div></td>
	         	</tr>
	         </table>
         </div>
         <div class="modal-footer" style="margin-top: 0px;padding-bottom: 0px;padding-top: 0px;">
         	<button id="gonext"  type="button" class="btn btn-default"  data-dismiss="modal">转交</button>
            <button type="button" class="btn btn-default"  data-dismiss="modal">关闭 </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->

<div id="modaldialog"></div>
</body>
</html>
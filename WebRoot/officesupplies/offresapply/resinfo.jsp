<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%String resId=request.getParameter("resId"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品信息表</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offresapply/js/resinfo.js"></script>
<script type="text/javascript">
var resId="<%=resId%>";
</script>
</head>
<body>
<body>
<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 15%;width: 70%;">
<a style="cursor: auto;" class="list-group-item active">办公用品信息</a>
<table class="table table-striped table-condensed">
<tr>
<td  width="30%">办公用品名称:</td>
<td><div class="col-xs-4" id="resName"></div>
</tr>
<tr>
<td>规格型号:</td>
<td><div class="col-xs-4" id="resFormat">
</div></td>
</tr>
<tr>
<td>办公用品类别:</td>
<td>
<div class="col-xs-4" id="classifyName">
</div>
</td>
</tr>
<tr>
<td>单价:</td>
<td>
<div class="col-xs-4" id="resPrice">
			
</div>
</td>
</tr>
<tr>
<td>供应商:</td>
<td>
<div class="col-xs-4" id="resSupplier">

</div></td>
</tr>
<tr>
<td>最低警戒库存:</td>
<td>
<div class="col-xs-4" id="minStock">

</div>
</td>
</tr>
<tr>
<td>最高警戒库存:</td>
<td>	
<div class="col-xs-4" id="maxStock">

</div>
</td>
</tr>
<tr>
<td> 
当前库存：
</td>
<td> 
<div class="col-xs-4" id="beforeStock">

</div>
</td>
</tr>
<tr>
<td> 
当前库存总价：
</td>
<td> 
<div class="col-xs-4" id="stockPrice">
</div>
</td>
</tr>
<tr>
<td>附件：</td>
<td>
<div id="attachDiv" name="attachDiv"></div>
</td>
</tr>
</table>
   </div>
   <div align="center" style="margin-top: 5px;">
  <button type="button" name="send" value="" class="btn btn-primary" onclick="ask();">申请</button>
 </div>
</body>
</html>
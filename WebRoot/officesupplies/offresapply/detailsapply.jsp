<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String applyId=request.getParameter("applyId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品申领详情</title>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/offresapply/js/detailsapply.js"></script>
<script type="text/javascript">
var applyId="<%=applyId%>";
</script>
</head>
<body>
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:70%;margin-left: 15%;margin-top: 1%;">
<a style="cursor: auto;" class="list-group-item active">办公用品申领详情</a>
   <input type="hidden" id="dispatchStaff" name="dispatchStaff"/>
<table class="table table-striped table-condensed">
<tr>
<td width="25%">办公用品库:</td>
<td>
<div class="col-xs-8" id="libraryId">
</div>
</td>
</tr>
<tr>
<td>办公用品类别:</td>
<td>
<div class="col-xs-8" id="classifyId">
</div>
</td>
</tr>
<tr>
<td>办公用品:</td>
<td>
<div class="col-xs-8" id="resId">
</div>
</td>
</tr>
<tr>
<td>申领类型:</td>
<td>
<div class="col-xs-8" id="resType">
</div>
</td>
</tr>
<tr>
<td>申请数量:</td>
<td>
<div class="col-xs-8" id="applyNum">
</div>
</td>
</tr>
<tr>
<tr>
<td>备注:</td>
<td>
<div class="col-xs-8" id="applyRemary">
</div>
</td>
</tr>
</table>
   </div>
   <div align="center">
<button type="button" name="save" onclick="history.back();" class="btn btn-default">返回</button>
 </div>
</body>
</html>
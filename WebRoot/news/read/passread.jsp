<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已读新闻</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/news/news.css"></link>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/code.js"></script>
<script type="text/javascript" src="<%=contextPath%>/news/read/js/passread.js"></script>
</head>
<body>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover" style="width: 600px;">
<span class="title_name" >已读新闻
</span>
<div class="col-xs-12 form-group" style="float: left;margin-top:8px; width: 150px;">
<div id="newstype"></div>
</div>
<span style="float: left;">发布日期：</span>
<div class="col-xs-12 form-group" style="float: left;margin-top:8px; width: 150px;">
<input type="text" name="createTime" id="createTime" size="20" readonly="readonly" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
			</div>
			<span style="float: left;">
			<button type="button" id="querybtn" name="querybtn" class="btn btn-default btn-sm" onclick="getNews();">确定</button>
			</span>
</div>
</div>
<div id="myTable" name="myTable"></div>
</body>
</html>
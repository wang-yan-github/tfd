<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看会议室设备</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/metting/metting.css"></link>
<script type="text/javascript" src="<%=contextPath%>/metting/boardroomdevice/js/lookboardroomdevice.js"></script>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >会议室设备
</span>
</div>
<div style=" float: right;margin-top:15px;margin-right: 20px;" id="btnitem">
       <button type="button" class="btn btn-primary btn-sm" id="newsdevice" onclick="Newsdevice();">新建设备</button>
</div>
</div>
<div id="myTable" name="myTable"></div>
</div>
</body>
</html>
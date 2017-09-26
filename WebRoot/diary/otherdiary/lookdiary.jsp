<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String accountId = session.getAttribute("USER_ID").toString(); %> 
<% String accountName = session.getAttribute("USER_NAME").toString(); %> 
<title>查看日志</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/diary/diary.css"></link>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/diary/lookdiary.css"></link>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/dateformat.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/otherdiary/js/lookdiarylogic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/tools.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/js/diary.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript">
var beforestaff="<%=accountId%>";
var accountName="<%=accountName%>";
</script>
</head>
<body style="background-color: white;">
<div style="position: fixed;width: 100%;background-color: white;z-index: 9999;">
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >全部日志</span>
</div>
<div style=" float: right;margin-top:6px;margin-right: 20px;" id="btnitem">
       <button type="button" class="btn btn-primary"  onclick="Newdiary();">新建日志</button>
</div>
</div>
</div>
	<div id="left" align="center">
	<div style="width: 800px;">
	<div id="content" class="feedlist">
	
	</div>
	</div>
	</div>
	<div id="right">
		<div>
				<div class="list-group">
   <a style="cursor: auto;" class="list-group-item active">
      <h5 class="list-group-item-heading">
         <div class='titleName username'></div>
      </h5>
   </a>
				<table class="table table-striped">
					<tr>
						<td class=" title">
						<a href="/tfd/diary/otherdiary/lookdiary.jsp">
						<div id="allCount">
						</div>
						</a>
						</td>
						<td class="title">
						<a href='/tfd/diary/personaldiary/personaldiary.jsp'>
						<div  id="myCount"></div>
						</a>
						</td>
						<td class="title" >
						<a href='/tfd/diary/otherdiary/otherdiary.jsp'>
						<div id="otherCount"></div>
						</a>
						</td>
					</tr>
					<tr>
					<td colspan="3" style="padding: 0px 0px;">
					<div id="diarytime" style="width:100%;height: 200px;"></div>
			<div id="time">
				<div class="presenttime form-control">
					<span id="presenttime"></span>&nbsp;<a
						href="javascript:cleartime();">×</a>
				</div>
			</div>
		</div>
	</div></td>
					</tr>
				</table>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String diaryId=request.getParameter("diaryId"); %>
<% String accountId = session.getAttribute("USER_ID").toString(); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日志详情</title>
<script type="text/javascript" src="<%=contextPath%>/diary/dateformat.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/personaldiary/js/diarydetailslogic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript">
var diaryId="<%=diaryId%>";
var beforestaff="<%=accountId%>";
</script>
<style >
.presenttime{
font-weight:bold;
border: 1px solid #95B8E7;
width: 200px;
margin-left:5px;
margin-bottom:5px;
padding-top:0px;
}
.presenttime a{
color: #D3D3D3;
}
.presenttime a:LINK {
	font-size:20px;
	font-weight:bold;	
	text-decoration: none;
	color: #D3D3D3;
}
.presenttime a:HOVER {
	color: #A7A7A7;
}
#diarycontent{
width: 100%;
 word-break:break-all;
}
</style>
</head>
<body style="height: 100%;">
		<div class="panel panel-info" style="width: 70%;margin-left: 15%;">
			<div class="panel-heading" align="left">
				<h3 class="panel-title">
					<div id='titleName' style="float: left;"></div><div id='diaryDatetime' style="color:#000;float: right;"></div>
				</h3>
			</div>
					<table class='table table-striped' style="width: 100%;height: 100%;">
						<tr>
							<td>
								<div id='tonediary' style="float: left;">
									<a href=javascript:tonediary();>上一篇</a>
								</div>
								<div id='downdiary' style="float: right;">
									<a href=javascript:downdiary();>下一篇</a>
								</div>
							</td>
						</tr>
						<tr>
							<td id="diaryname" style="font-weight:bold;font-size: 16px;"></td>
						</tr>
						<tr>
							<td>
							<div id='diarycontent'></div>
							<div id="attachDiv" name="attachDiv"></div>
							</td>
						</tr>
						<tr>
						<td id="staffName">
						暂无浏览人员
						</td>
						</tr>
						<tbody id="diaryComments" style="display: none;">
						<tr>
						<td>
						<div id="commtable" name="commtable" style="width: 100%;"></div>
						</td>
						</tr>
						<tr>
						<td>
						<input id="commPid" name="commPid" type="hidden"></input>
						<div id="staffdiv" style="display: none;"><div class="presenttime form-control">回复：<span id="staff"></span>&nbsp;&nbsp;&nbsp;<a href="javascript:clearstaff();">X</a></div></div>
						<textarea rows="4" style="width:100%;margin-top: 5px;" id="commContect" name="commContect" class="form-control"></textarea>
						<div style="float: left;margin-top: 15px;"><span style="float: left;">提醒方式：</span><div id="smsdiv" name="smsdiv" style='float: left;'></div></div>
						<div style="float: right;margin-top: 5px;"><button type="button" class="btn btn-primary" onclick="commsubmit();">评论</button></div>
						</td>
						</tr>
						</tbody>
					</table>
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<% 
String attachId=request.getParameter("attachId");
String privFlag = request.getParameter("privFlag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>TXT文档浏览</title>
 <script type="text/javascript">
 var privFlag = "<%=privFlag%>";
 var attachId = "<%=attachId%>";
 function getTextConent()
 {
	 var requestUrl = contextPath + "/com/system/filetool/UpFileTool/getTextConentAct.act";
		$.ajax({
			data:{attachId:attachId},
			url:requestUrl,
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$("#fileName").html("文件名："+data.fileName);
				$("#conent").html(data.conent);
			}
		});
 }
 </script>
</head>
<body onload="getTextConent();" width="80%" >
<div >
	<div id="fileName" align="center" ></div>
	<div id="conent"  ></div>
</div>
</body>
</html>
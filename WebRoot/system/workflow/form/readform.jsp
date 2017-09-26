<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String formId=request.getParameter("formId"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<script type="text/javascript">
var formId="<%=formId%>";
$(function (){
	var requestUrl ="<%=contextPath%>/tfd/system/workflow/form/act/WorkFlowFormAct/getFormCodeByFormIdAct.act";
			$.ajax({
	 			url:requestUrl,
	 			dataType:"text",
	 			data:{formId:formId},
	 			async:false,
	 			success:function(data){
	 				$("#formDiv").html(data);
	 			}
	 		});
});
</script>
<body>
<div id="formDiv" name="formDiv"></div>
</body>
</html>
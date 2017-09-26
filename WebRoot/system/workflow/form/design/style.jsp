<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/system/returnapi/api.jsp" %>
<%String formId=request.getParameter("formId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CSS样式表</title>
<style type="text/css">
</style>
<script>
	var formId = "<%=formId%>";
	function doInit(){
		var requestUrl = contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getFormStyleByFormIdAct.act";
		$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{formId:formId},
			async:false,
			error:function(e){
			},
			success:function(data){
				$("#css").val(data.formStyle);
			}
		});	
	}

	function getCssContent(){
		return $("#css").val();
	}

	function commit(){
		var requestUrl = contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/updateFormStyleByFormIdAct.act";
		var Style=$("#css").val();
		$.ajax({
			url:requestUrl,
			dataType:"text",
			data:{formId:formId,css:Style},
			async:false,
			error:function(e){
			},
			success:function(data){
				if(data==1)
					{
					layer.msg("保存成功！");
					}
			}
		});			
	}
	</script>
</head>
<body style="margin: 0px;" onload="doInit()">
	<table style="width: 100%; height: 400px;">
		<tr>
		<td height=100%><textarea id="css" style="border: 0px currentColor; width: 100%; height: 100%;">.wf td{
padding:5px;
}
</textarea></td>
		</tr>
	</table>
</body></html>
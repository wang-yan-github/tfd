<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %> 
    <%String formId=request.getParameter("formId"); %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作流脚本</title>
<script type="text/javascript">
var formId="<%=formId%>";
function doInit(){
	var requestUrl = contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/getFormScriptAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{formId:formId},
		async:false,
		error:function(e){
		},
		success:function(data){
			$("#script").val(data.formscript);
		}
	});	
	
}
function commit(){
	var requestUrl = contextPath+"/tfd/system/workflow/form/act/WorkFlowFormAct/updateFormsScriptAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:{formId:formId,script:$("#script").val()},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data==1)
				{
				layer.msg("保存脚本成功!");
				}
				}
		});
}
</script>
</head>
<body style="margin:0px" onload="doInit()">
<textarea id="script" rows="15" style="height:100%;width:100%;border:0px"></textarea>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<% 
	String diaryId = request.getParameter("diaryId");
	String phone=request.getParameter("phone");
 %>
<title>工作日志</title>
<script type="text/javascript" src="/tfd/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script src="/tfd/system/jsall/jmobile/jquery.mobile-1.4.1.js"></script>
<link rel="stylesheet" href="/tfd/system/jsall/jmobile/jquery.mobile-1.4.1.css"></link>  

<script type="text/javascript" src="/tfd/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/tfd/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/tfd/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="/tfd/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="/tfd/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="/tfd/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript">
var diaryId = "<%=diaryId%>";
var phone="<%=phone%>";

$(function (){
	var url = "/tfd/tfd/system/mobile/diary/act/DiaryAct/getIdDiaryAct.act";
	var parm={diaryId:diaryId,phone:phone};
	$.ajax({
			url:url,
			type:'POST',
			dataType:"json",
			data:parm,
			async:false,
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                  
             },
			success:function(returndata){
				var data=returndata;
				var attachId=data.attachId;
				readAttachDiv(attachId,"attach");
				$("#diaryName").html(data.diaryName);
				if(data.diaryMold==0){
					$("#diaryMold").append("工作日志");
				}else{
					$("#diaryMold").append("个人日志");
				}
				$("#diaryTitletime").append(data.diaryTitletime);
				$("#diaryDatetime").append(data.diaryDatetime);
				$("#diaryContent").html(data.diaryContent);  
			}
	});
});

</script>
</head>
<body >
	<div data-role="page" id="pageone" >
		<div data-role="header" data-position="fixed" style="height: 40px; line-height: 23px;">
			<h1 id="diaryName"></h1>
		</div>
		<div data-role="content" style="height: 100%; padding: 0px;">
			<div id="diaryMold" style="color:#898989; line-height: 40px;"><span style="font-weight: bold;">日志类型：</span></div>
			<div style="width:100%;height: 1px;background-color: #B7B7B7;"></div>
			<div id="diaryTitletime" style="color:#898989; line-height: 40px;"><span style="font-weight: bold;">日志时间：</span></div>
			<div style="width:100%;height: 1px;background-color: #B7B7B7;"></div>
			<div id="diaryDatetime" style="color:#898989; line-height: 40px;"><span style="font-weight: bold;">最后修改时间：</span></div>
			<div style="width:100%;height: 1px;background-color: #B7B7B7;"></div>
			<div id="diaryContent" style="width:100%;color:#898989;word-break:break-all;word-wrap: break-word;"></div>
			<div id="attachDiv" name="attachDiv" style="margin-top: 30px;"></div>
	</div>
	</div>
</body>

</html>
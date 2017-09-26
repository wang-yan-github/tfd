<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String userName=session.getAttribute("USER_NAME").toString(); %>
<title>新建日志</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/diary/dateformat.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/diary/personaldiary/js/Newdiarylogic.js"></script>
<script type="text/javascript">
var userName="<%=userName%>";
</script>
</head>
<body>
<form id="diaryform"  action="" method="post" >
<div style="width: 90%;margin-left: 5%;">
<div class="list-group-item"  style="padding: 0px;cursor: auto;margin-left: 1%;width: 98%;">
<a style="cursor: auto;" class="list-group-item active">新建日志</a>
 <table class="table table-striped table-condensed">
 <tr>
	<td width="10%">日期：</td>
	<td>
	<div class="col-xs-5">
	<input id="diarytitleDatetime" name="diarytitleDatetime" type="text" readonly="readonly" style="cursor: pointer;"onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){evaluatediaryName();}});"  class="form-control"/>
	</div>
	</td>
</tr>
<tr>
	<td>标题：</td>
	<td>
	<div class="col-xs-5"><input id="diaryName" name="diaryName" class="form-control" type="text" ></div>
	</td>
</tr>
<tr>
	<td>共享人员：</td>
	<td>
	<div class="col-xs-5 form-group">
<textarea rows="3" cols="40" name="sharePriv" id="sharePriv" style="display: none;" ></textarea>
<textarea rows="3" cols="40" name="sharePrivName" id="sharePrivName" readonly="readonly" class="form-control"></textarea></div>
<div style="margin-top: 40px;">
<a href="javascript:void(0);" onclick="userinit(['sharePriv','sharePrivName'],'false');">添加人员</a>
</div>
	</td>
</tr>

<tr>
	<td>日志类型：</td>
	<td>
	<div class="col-xs-5">
	<select id="diaryMold" name="diaryMold"  class="form-control">
	<option value="0">工作日志</option>
	<option value="1">个人日志</option>
	</select>
	</div>
	</td>
</tr>
<tr>
<td colspan="2">日志内容：</td>
</tr>
<tr>
	<td colspan="2">
	<textarea  id="editor" name="editor"  >
	</textarea>
	<script type="text/javascript">CKEDITOR.replace('editor')</script>
	</td>
</tr>
<tr>
	<td>附件：</td>
	<td>
	<div id="attachDiv" name="attachDiv"></div>
	</td>
</tr>
<tr>
<td>附件选择:</td>
<td>	

<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('diary','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

</div>

</td>
<tr >
	<td id="addtd" colspan="2" align="right" >
	<input type="button" onclick="addDiary();" class="btn btn-primary" value="保存">
	<input type="button" onclick="history.back();" class="btn btn-default" value="返回">
	</td>
	</tr>
</table>  
</div>
</div>
</form>
<div id="modaldialog"></div>
</body>
</html>
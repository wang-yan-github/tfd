<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String folderNo = request.getParameter("folderNo");
	String folderName = request.getParameter("folderName");
	String folderId = request.getParameter("folderId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作资源设置</title>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/personfolder/manage/js/manage.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />

<style type="text/css">
	html,body{height:100%;}
	.f_li{height:100px;width:100px;float:left;margin-left:10px;list-style:none;border:#F5F5F5 1px solid;cursor:pointer;margin-top:5px;}
	.file_li{height:100px;width:100px;float:left;margin-left:10px;list-style:none;border:#F5F5F5 1px solid;cursor:pointer;margin-top:5px;text-align:center;}
	.lihover{background-color:#F1F7FD;border:#B8D6FB 1px solid;}
	.clickli{background-color:#CBE1FC;border:#7DA2CE 1px solid;}
	.clicklis{background-color:#CBE1FC;border:#7DA2CE 1px solid;}
</style>
<script type="text/javascript">
	var folderId = "<%=folderId%>";
	var folderName = "<%=folderName%>";
	var folderNo = "<%=folderNo%>";
</script>
</head>
<body>
	<div class="panel panel-info" style="height:100%;background-color:#F5F5F5;" >
	<div style="position:fixed;background-color:#F5F5F5;width:100%;" >
   <div class="panel-heading">
      <h3 class="panel-title" id="folderTitle" >
      </h3>
   </div>
   <table class="table table-striped table_btn" >
   	<tr style="background-color:#F5F5F5;">
   		<td><button id="btn_back" class="btn btn-info">返回</button>
   		<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">批量上传</button>
   		<button id="go_new" class="btn btn-primary">新建文件</button>
   		<button id="newChild" class="btn btn-primary">新建文件夹</button>
   		<button data-toggle="modal" data-target="#myModal_update" class="btn btn-success">编辑</button>
   		</td>
   	</tr>
   	<tr style="background-color:#F5F5F5;border-bottom:#CCC 1px solid;">
   		<td style="height:35px;line-height:35px;" >
   		<div style="width:80px;float:left;margin-left:30px;" >
   			<input type="checkbox" id="checkAll" />
   			<span style="margin-left:10px;">全选</span>
   		</div>
   		<div id="bottonList" style="folat:left;" >
   			<button id='btn_del' style="float:left;margin-left:5px;display:none;" class='btn btn-danger'>删除</button>
   			<button id='btn_copy' style="float:left;margin-left:5px;display:none;" class='btn btn-default'>复制</button>
   			<button id='btn_shera' style="float:left;margin-left:5px;display:none;"  class='btn btn-default'>剪切</button>
   		</div>
   		</td>
   	</tr>
</table>
</div>
<div id="myTable" style="background-color:#F5F5F5;min-height:50px;max-height:100%;margin-top:150px;"  >
   
</div>

<table class="MessageBox" style="display:none;margin-top:150px;" align="center" width="440" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
         <div class="msg-content"></div>
      </td>
      <td class="right"></td>
   </tr>
   <tr class="foot">
      <td class="left"></td>
      <td class="center"><b></b></td>
      <td class="right"></td>
   </tr>
   </tbody>
</table>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               批量上传
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
           <tr>
<td width="15%">附件:</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
<td>附件选择:</td>
<td>	

<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('news','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	<input type="hidden" id="attachName" name="attachName"/>
	
	
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

	<a class="add_swfupload" style="width:190px;" onclick="javascript:goOpen();"  href="javascript:void(0)">从文件柜和网络硬盘中选取附件</a>
	<input type="hidden" id="btn-open" data-toggle="modal" data-target="#attachModel" />
</div>

</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <button type="button" id="btn_uploads" class="btn btn-primary">提交上传</button>
            <button type="button" class="btn btn-default btn_close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>
<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑文件夹
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
           <tr>
<td width="15%">排序号:</td>
<td><input class="form-control " type="text" id="folderNo"  ><input class="form-control " type="hidden" id="folderId"  ></td>
</tr>
<tr>
<td>名称:</td>
<td><input class="form-control "  type="text" id="folderName"  >	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <button type="button" id="btn_update" class="btn btn-primary">确认</button>
            <button type="button" class="btn btn-default btn_close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<%
	String photoId=request.getParameter("photoId");
	String path = request.getParameter("path");
	String folderName=request.getParameter("folderName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>相册浏览</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<script type="text/javascript" src="<%=contextPath%>/system/jsall/layer/layer.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/photo/js/manage.logic.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/photo/photo.css"></link>

<style type="text/css">
.photo-img{max-height:120px;max-width:128px;overflow:hidden;}
.photo-div{height:128px;width:128px;text-align:center;line-height:128px;}
.photo-bg{float:left;margin-left:15px;margin-top:10px;border:#F5F5F5 1px solid;cursor:pointer;height:150px;}
.photo-bg:hover{background-color:#F1F7FD;border:#B8D6FB 1px solid;}
.photo-bg:hover .photo-info,.photo-bg:hover .good-ok-div,.photo-bg:hover .good-div,.photo-bg:hover .cover-div{display:block;}
.floder-div{background:url("<%=imgPath%>/filetype/folder.png");width:128px;height:120px;background-repeat:no-repeat;}
.good-ok-div,.good-div,.cover-div{background:url('<%=stylePath%>/frame/25-32.png');background-repeat:no-repeat;height:18px;width:24px;float:right;margin-right:5px;display:none;}
.cover-div{background-position:-414px -98px;}
.good-div{background-position:-360px -173px;}
.good-ok-div{background-position:-360px -148px;}
.photo-info{display:none;width:128px;height:100px;background-color:#FFF;border:1px solid #CCC;position:relative;font-size:12px;top:-150px;left:128px;z-index:98;}
</style>
<script type="text/javascript">
var photoId = "<%=photoId%>";
var folderName = "<%=folderName%>";
var path = "<%=path%>";
var paths;

</script>
</head>
<body>
<div id="content-body" >
	<div class="top_info" style="position:fixed;width:100%;height:85px;top:0px;z-index:99;"  >
	  <div class="top_info_left icontop-basic_hover">
		<span class="title_name" id="folderName" style="float:left;" ></span>
		</div>
	   <table class="table table-striped"  >
	   	<tr>
	   		<td><button id="btn_back" style="margin-left:5px;float:left;" class="btn btn-info ">返回</button>
	   		<button class="btn btn-primary" style="display:none;float:left;margin-left:5px;"  data-toggle="modal" id="go_upload" data-target="#myModal">批量上传</button>
   			<button type="button"  style="display:none;float:left;margin-left:5px;"  id="newFolder" data-toggle="modal" data-target="#myModal_new" class="btn btn-success">新建文件夹</button>
	   		</td>
	   	</tr>
	   	</table>
	</div>
   <div id="photosDemo" class="layer-photos-dome" style="margin-top:85px;" ></div>
   <table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
         <div class="msg-content">请选择相册进行浏览</div>
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
	<input type="file" onchange="fileUpLoad('photo','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	
	
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
          <button type="button"  onclick="uploadfiles()" class="btn btn-primary">提交上传</button>
            <button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>
<div class="modal fade" id="myModal_new" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建文件夹
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
<tr>
<td>名称:</td>
<td><input class="form-control "  type="text" id="newFloderName"  >	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <button type="button" onclick="javascript:createFolder();" class="btn btn-primary">确认</button>
            <button type="button" class="btn btn-default btn-close" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>
</body>
</html>
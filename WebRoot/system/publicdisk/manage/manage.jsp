<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String path = request.getParameter("path");
	if(path != null){
		path=path.replaceAll("\\\\", "/");
	}
    String orderBy = request.getParameter("orderBy");
    String ascDesc = request.getParameter("ascDesc");
    String diskId = request.getParameter("diskId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作资源设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/publicdisk/js/disk.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<link rel="stylesheet" type="text/css" href="<%=stylePath%>/publicdisk/publicdisk.css"></link>

<style type="text/css">
	#myTable tr:HOVER{background-color:#F5F5F5}
	#showPath{line-height:20px;}
	.menuClass{height:25px;line-height:25px;text-align: center;cursor:pointer;font-size:13px;}
.menuClass:hover{background-color:#83C1DE;color:#FFF;}
.editmenu{height:auto;width:100px;display:none;position: absolute;background-color:#F5F5F5;border:solid 1px #CCC;z-index:999999;}
.readmenu{height:auto;width:100px;display:none;position: absolute;background-color:#F5F5F5;border:solid 1px #CCC;z-index:999999;}
.buttons{float:left;margin-left:5px;display:none;}
</style>
<script type="text/javascript">
	var path = "<%=path%>";
	var orderBy = "<%=orderBy%>";
	var ascDesc = "<%=ascDesc%>";
	var diskId = "<%=diskId%>";
	var diskIds;
	$(function(){
		if(path=="null"){
			$(".MessageBox").show();
			$(".msg-content").html("请选择文件夹进行浏览");
			$(".child_content").hide();
		}else{
			filesUpLoad("publicdisk");
			fileUploadByAttach("publicdisk");
			if(path!="null"){
				searchDisk(orderBy,ascDesc);
				checkPriv();
				setFloderName();
			}
			$("#delFolder").click(function(){
				deleteFloder();
			});
		}
	});
	
</script>
<style>
body,html{height:100%;width:100%;padding:0px;margin:0px;}
.title{position:fixed;height:45px;width:100%;line-height:45px;background-color:#F5F5F5;padding-left:10px;z-index:99;margin-top:35px;}
</style>
</head>
<body>
<div class="child_content" >
   <div class="top_info" style="position:fixed;height:40px;width:100%;margin-top:-2px;"  >
	  <div class="top_info_left icontop-basic_hover" >
		<span class="title_name" id="folderName" style="float:left;" ></span>
		</div>
		</div>
   <div class="panel-body" style="border:none;min-height:200px;" >
   		<div class="title" >
   		<button class="btn btn-primary" style="display:none;float:left;margin-left:5px;margin-top:5px;"  data-toggle="modal" id="go_upload" data-target="#myModal">批量上传</button>
   		<button type="button"  style="display:none;float:left;margin-left:5px;margin-top:5px;"  id="newFolder" data-toggle="modal" data-target="#myModal_new" class="btn btn-success">新建文件夹</button>
   		<button type="button"  style="display:none;float:left;margin-left:5px;margin-top:5px;"  id="editFolder" data-toggle="modal" data-target="#myModal_update" class="btn btn-info">重命名此文件夹</button>
   		<button type="button"  style="display:none;float:left;margin-left:5px;margin-top:5px;"  id="delFolder" class="btn btn-danger">删除此文件夹</button>
   		<!-- <button type="button"  style="float:left;margin-left:5px;"  id="go_search" class="btn btn-default">搜索</button> -->
   		<div class="btn-group"  style="float:left;margin-left:5px;margin-top:5px;"  >
   <button type="button" class="btn btn-default dropdown-toggle" 
      data-toggle="dropdown">
      排序 <span class="caret"></span>
   </button>
   <ul class="dropdown-menu" style="z-index:101;" role="menu">
      <li><a id="sortName" onclick="changeSort(1)" >名称</a></li>
      <li><a id="sortType" onclick="changeSort(2)" >类型</a></li>
      <li><a id="sortSize" onclick="changeSort(3)" >大小</a></li>
      <li><a id="sortTime" onclick="changeSort(4)" >时间</a></li>
      <li class="divider"></li>
      <li><a id="sortAsc" onclick="changeSort(5)" >升序</a></li>
      <li><a id="sortDesc" onclick="changeSort(6)" >降序</a></li>
   </ul>
</div>
<div class="input-group" style="float:right;width:240px;margin-top:5px;margin-right:20px;">
               <input type="text" placeholder="请输入搜索内容" id="searchContent" class="form-control" style="width:200px;height:35px;" >
               <span class="input-group-btn">
                  <button id="search" onclick="searchDisk();" class="btn btn-default" type="button" style="line-height:22px;margin-bottom:15px;" >Go! </button>
               </span>
            </div>
  </div>
   <table style="height:30px;position:fixed;width:100%;margin-top:80px;z-index:98;background-color:#F5F5F5;border-top:#CCC 1px solid;border-bottom:#CCC 1px solid;"  >
   <tr style="height:40px;border-bottom:#CCC 1px solid;"><td  colspan="5" style="line-height:40px;" >
   		<span id="showPath" style="float:left;margin-left:5px;" ></span>
   		<button class="btn btn-primary btn-sm buttons" id="btn_copy" onclick="copyFile()" >复制</button>
   		<button class="btn btn-primary btn-sm buttons" id="btn_cut" onclick="cutFile()" >剪切</button>
   		<button class="btn btn-danger btn-sm buttons" id="btn_del" onclick="delFiles();" >删除</button>
   		<button class="btn btn-info btn-sm buttons" id="btn_download" onclick="downloadFiles();" >下载</button>
   		<button class="btn btn-success btn-sm buttons" id="btn_copy_paste" onclick="pasteCopyFile()" >粘贴</button>
   		<button class="btn btn-success btn-sm buttons" id="btn_cut_paste" onclick="pasteCutFile()" >粘贴</button>
   </td></tr>
   	<tr style="height:35px;" >
   	<td width='12%' style="padding-left:8px;"  ><input type='checkbox' id='checkAll' style='margin-left:30%;' onclick='javascript:checkAll()' /></td>
   	<td width='46%' style="padding-left:8px;">名称</td>
   	<td width='12%' style="padding-left:8px;">类型</td>
   	<td width='15%' style="padding-left:8px;">大小</td>
   	<td width='15%' style="padding-left:8px;">时间</td>
   	</tr>
</table>
<table class="table table-striped" id="myTable" style="margin-top:155px;" >
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
	<input type="file" onchange="fileUpLoad('publicdisk','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
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
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<td>名称:</td>
<td><input class="form-control "  type="text" id="folderName"  >	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <button type="button" id="btn_update" onclick="javascript:editFolder()" class="btn btn-primary">确认</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>
<table class="MessageBox" style="display:none;margin-top:200px;" align="center" width="440" cellpadding="0" cellspacing="0">
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
<div id="attachHtml" >

</div>
</body>
</html>
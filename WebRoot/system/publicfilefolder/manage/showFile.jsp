<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String fileId = request.getParameter("fileId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作资源设置</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<style type="text/css">
	#myTable tr:HOVER{background-color:#F5F5F5}
	#showPath{line-height:20px;}
</style>
<script type="text/javascript">
	var fileId = "<%=fileId%>";
	$(function(){
		if(fileId!="null"){
			doinit();
		}
		$('#btn_back').click(function(){
			history.go(-1);
			return false;
		});
		$('#btn_edit').click(function(){
			window.location =contextPath+"/system/publicfilefolder/manage/editFile.jsp?fileId="+encodeURIComponent(fileId);
		})
		$('#btn_del').click(function(){
			if(confirm("确定删除?")){
				var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/deleteFile.act';
				$.ajax({
					url:requestUrl,
					data:{fileId:fileId},
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
						alert(e.message);
					},
					success:function(data){
						if(data=='1'){
							top.layer.msg('删除成功');
						}
					}
				});	
			}
		})
	})
	function doinit(){
		var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/getFileById.act';
		$.ajax({
			url:requestUrl,
			data:{fileId:fileId,isPublic:'1'},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#fileName').html(data.fileName);
				$('#fileContent').html(data.fileContent);
				attachId=data.attachId;
				if(getFileEditPriv(data.folderId)){
					$('#btn_edit').css("display","block");
					readAttachDiv(attachId,"attach",4);
				}else if(getFileDownPriv(data.folderId)){
					readAttachDiv(attachId,"attach",2);
				}else{
					readAttachDiv(attachId,"attach",1);
					$('#btn_edit').css("display","none");
				}
				getFileDelPriv(data.folderId);
			}
		});	
		var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/getRecordListById.act';
		$.ajax({
			url:requestUrl,
			data:{fileId:fileId},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				$('#RecordList').html("");	
				$.each(data,function(index,data){
					if(data.recordNo == '0'){
						$('#firstRecord').html("<a onclick=\"javascript:showPersonal('"+data.accountId+"')\" href=\"javascript:void(0)\" >"+data.userName+"</a>");
						var str = "<p>"+data.recordTime+data.userName+"创建了文件</p><div id='attach"+data.recordNo+"Div' name='attach"+data.recordNo+"Div' ><div>";
						$('#RecordList').append(str);
						readAttachDiv(data.attachId,"attach"+data.recordNo,1);
					}else{
						var str = "<p>"+data.recordTime+data.accountId+"修改了文件</p><div id='attach"+data.recordNo+"Div' name='attach"+data.recordNo+"Div' ><div>";
						$('#RecordList').append(str);
						readAttachDiv(data.attachId,"attach"+data.recordNo,1);
					}
				})
			}
		});	
	}
	function getFileDelPriv(folderId){
		var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{folderId:encodeURIComponent(folderId),privType:'5'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					$('#btn_del').css("display","block");
				}else{
					$('#btn_del').css("display","none");
				}
			}
		});
	}
	function getFileEditPriv(folderId){
		var flag = false;
		var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{folderId:encodeURIComponent(folderId),privType:'2'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					flag = true;
				}else{
					flag = false;
				}
			}
		});
		return flag;
	}
	function getFileDownPriv(folderId){
		var flag = false;
		var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{folderId:encodeURIComponent(folderId),privType:'4'},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					flag = true;
				}else{
					flag = false;
				}
			}
		});
		return flag;
	}
</script>
</head>
<body>
	<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         查看文件
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped" >
   <tr>
   		<td align="center" colspan="2" id="fileName" ></td>
   	</tr>
   	<tr>
   		<td colspan="2" id="fileContent" style="min-height:300px;height:auto;!important;height:300px;" ></td>
   	</tr>
   	<tr>
<td width="15%">附件:</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
	<td>创建人：</td>
	<td><div id="firstRecord" ></div></td>
</tr>
<tr id="recordTr" >
	<td>修改记录：</td>
	<td><div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseOne">
          点击查看修订记录
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse">
      <div class="panel-body" id="RecordList" >
      </div>
    </div>
  </div></td>
</tr>
</table>
</div>
</div>
 <div align="right">
 <button type="button" id="btn_back" style="float:right;margin-right:10px;" class="btn btn-default">返回</button>
  	<button type="button" id="btn_del" style="display:none;float:right;margin-right:5px;" class="btn btn-danger">删除</button>
  	<button type="button" id="btn_edit" style="display:none;float:right;margin-right:5px;" class="btn btn-primary">编辑</button>
	
   </div>
</body>
</html>
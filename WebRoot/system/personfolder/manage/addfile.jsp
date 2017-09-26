<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String folderId = request.getParameter("folderId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作资源设置</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js"></script>


<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<style type="text/css">
	#myTable tr:HOVER{background-color:#F5F5F5}
	#showPath{line-height:20px;}
</style>
<script type="text/javascript">
var editor;
	var folderId = "<%=folderId%>";
	$(function(){
		filesUpLoad("personfolder");
		fileUploadByAttach("personfolder");
		if(folderId!="null"){
			editor = CKEDITOR.instances.editor;
		   
		}
		$('#btn_back').click(function(){
			history.go(-1);
			return false;
		});
		
	})
	function addFile(){
		var content = encodeURIComponent(editor.getData());
		var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/addFile.act';
 		$.ajax({
			url:requestUrl,
			data:{
				fileNo:$('#fileNo').val(),
				fileName:$('#fileName').val(),
				folderId:folderId,
				fileContent:content,
				attachId:$("#attachId").val()
			},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					top.layer.msg('添加成功');
				}
			}
 		});
	}
</script>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal" >
	<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         新建文件
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped" >
   <tr>
   		<td width="15%" >排序号:</td>
   		<td width="75%" ><div class="col-xs-8 form-group" style="padding:0px;" ><input class="form-control "  type="text" id="fileNo" name="fileNo" /></div></td>
   	</tr>
   	<tr>
   		<td width="15%" >文件名称:</td>
   		<td width="75%" ><div class="col-xs-8 form-group" style="padding:0px;" ><input class="form-control "  type="text" id="fileName" name="fileName"  /></div></td>
   	</tr>
   	<tr>
   		<td width="15%" >文件内容:</td>
   		<td width="75%" ><textarea id="editor" name="editor"  style="width:100%;height:200px;"></textarea>
   		<script type="text/javascript">CKEDITOR.replace('editor')</script></td>
   	</tr>
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
	<input type="file" onchange="fileUpLoad('personfolder','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
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
</div>
 <div align="right">
  <input type="submit" id="btn_ok" class="btn btn-success" value="确定" />
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
   </form>
</body>
<script>
$('#form1').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		fileNo: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '排序号不能为空'
                },
                integer: {
                	message: '排序号只能为整数'
                }
            }
        },
        fileName: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '文件名称不能为空'
                }
            }
        }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
		
     // Get the form instance
     var $form = $(e.target);

     // Get the BootstrapValidator instance
     var bv = $form.data('bootstrapValidator');
     addFile();
}); 
</script>
</html>
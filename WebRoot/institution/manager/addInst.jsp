<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制度中心</title>
</head>

<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/getAttach.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/institution/manager/js/addInst.logic.js"></script>

<style>
	html,body{
		width: 100%;
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	.top{
		width: 80%;
		height: 80px;
		position: absolute;
		padding-top: 15px;
		color: black;
		font-size: 17px;
		border-bottom: solid #ADD8E6 1px;
		padding-left: 8px;
	}
	.main{
		width: 80%;
		height:84%;
		position: absolute;
		margin-top: 65px;
		padding-left: 8px;
	}
	.moddle{
		width: 80%;
		height: 100%;
		margin: 0px;
		padding: 0px;
		border-right: solid #ADD8E6 1px;
	}
	#operation{
		position: absolute;
		bottom: 0%;
		right:3%;
		text-align: center;
	}
	.input_text{
		width: 200px;
		height: 24px;
		border: solid #CCC 1px;
		margin-left: 10px;
	}
	#dirContent{
		width: 300px;
		height: 300px;
		overflow: auto;
		display: none;
		position: absolute;
		border: solid #CCC 1px;
		background-color:white; 
		z-index: 999;
	}
</style>

<script type="text/javascript">

</script>
<body>
	<div id="dirContent" >
		<table class="TableBlock no-top-border" style="width: 100%;">
			<tr>
				<td class="TableData"><div><ul id="treeDemo" class="ztree"></ul></div></td>
			</tr>
		</table>
	</div>
	<div class="list-group" style="margin-bottom: 0px;width:90%;margin-left:5%;margin-top:20px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         制度信息
      </h5>
   </a>
	<div class="panel-body" style="border:none;box-shadow:none;" >
	<form id="form1" name="form1" class="form-horizontal" >
   <table class="table table-striped">
<tr>
<td style="width:100px;" >制度名称：</td>
<td><input type="hidden" id="sysMenuId" name="sysMenuId">
		<div class="col-xs-6 form-group" >	<input type="text" class="form-control " id="title" name="title"></input></div>
		</td>
</tr>
<tr>
<td style="width:100px;" >所属层级：</td>
<td><input type="hidden" id="directory_id" />
		<div class="col-xs-6 form-group" >	<input type="text" id="directory_Name" name="directory_Name" readonly="readonly" class="form-control "  onclick="showDir();"/></div>
		</td>
</tr>
<tr>
<td colspan="2" ><textarea id="editor" name="editor"  style="width:100%;height:200px;"></textarea>
<script type="text/javascript">CKEDITOR.replace('editor')</script></td>
</tr>
<tr>
<td>附件：</td>
<td><div id="attachDiv" name="attachDiv"></div></td>
</tr>
<tr>
<td>附件选择:</td>
<td>	

<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('institution','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	
	
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

	<a class="add_swfupload" style="width:190px;" onclick="javascript:goOpen();"  href="javascript:void(0)">从文件柜和网络硬盘中选取附件</a>
	<input type="hidden" id="btn-open" data-toggle="modal" data-target="#attachModel" />
</div>
</td>
</tr>
<tr>
<td colspan="2" align="right" ><input class="btn btn-success" type="submit" style="cursor: pointer;"  id="btn_ok" value="添加" />
				<input class="btn btn-default" type="button" style="cursor: pointer;" onclick="javascript:back()" id="btn_back" value="返回" /></td>
</tr>
</table>
</form>
</div>
</div>
<script type="text/javascript">
$('#form1').bootstrapValidator({
	message: '这不是一个有效的值',
	container: 'tooltip',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
	fields: {
		title: {
            validators: {
            	container: 'popover',
                notEmpty: {
                    message: '制度名称不能为空！'
                }
            }
        },
        directory_Name: {
		container: 'popover',
           validators: {
        	   notEmpty: {
                   message: '所属目录不能为空'
               }
           }
       }
	}
}).on('success.form.bv',function(e){
	 e.preventDefault();
     var $form = $(e.target);
     var bv = $form.data('bootstrapValidator');

     add();
});
</script>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%String formId=request.getParameter("formId"); %> 
<%String leavePath=request.getParameter("leavePath"); %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>表单设计器</title>
	<link type="text/css" rel="stylesheet" href="<%=contextPath%>/system/jsall/picexplore/picexplore.css"/>
	
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/picexplore/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/picexplore/picexplore.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/workflow/form/design/js/design.js"></script>
	<script>
		var formId ="<%=formId%>";
		var leavePath="<%=leavePath%>";
	</script>
	<style>
		html,body{width:100%;height:100%;margin:0px;padding:0px;}
    	html{overflow:hidden;}
        #body{width:100%;height:100%;}
        #east,#top,#center{position:absolute;background-color:#f2f2f2;}
        #top{left:10px;top:10px;right:10px;height:40px;line-height:40px;}
        #east{left:10px;top:55px;bottom:10px;width:150px;}
        #center{left:165px;top:55px;bottom:10px;right:10px;}
        #top-left,#top-right{margin:0px;padding:0px;}
        #top-right{text-align:right;}
        
        .btn-form-control{
        	height: 30px;
        }
        .form-control-icon{float:left;}
        
        #modal .modal-body{padding:0px;}
        .modal{top:0px !important;}
	</style>
</head>
<body>
	<div class="container-fluid" id="body">
       	<div id="top">
       		<div class="col-xs-6" id="top-left">
				<button class="btn btn-default" onclick="cssEdit()">
					<span class="glyphicon glyphicon-filter form-control-icon"></span>
					&nbsp;样式编辑器
				</button>
				<button class="btn btn-default" onclick="scriptEdit()">
					<span class="glyphicon glyphicon-globe form-control-icon"></span>
					&nbsp;脚本编辑器
				</button>
       		</div>
       		<div class="col-xs-6" id="top-right">
				<button class="btn btn-success" onclick="commit()">
					<span class="glyphicon glyphicon-floppy-save form-control-icon"></span>
					&nbsp;保存
				</button>
				<button class="btn btn-default" onclick="improtform();">
					<span class="glyphicon glyphicon-upload form-control-icon"></span>
					&nbsp;导入表单
				</button>
				<button class="btn btn-default" onclick="previewForm()">
					<span class="glyphicon glyphicon-eye-open form-control-icon"></span>
					&nbsp;预览版本
				</button>
       		</div>
        </div>
		<div id="east">
			<div class="btn-group-vertical btn-block">
				<button class="btn btn-primary active btn-form-control">表单控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xinput').exec();"><sapn class="glyphicon glyphicon-minus form-control-icon"></sapn>单行输入框</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xtextarea').exec();"><span class="glyphicon glyphicon-align-center form-control-icon"></span>多行文本框</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xtextuedit').exec();"><span class="glyphicon glyphicon-edit form-control-icon"></span>富文本框</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xselect').exec();"><span class="glyphicon glyphicon-chevron-down form-control-icon"></span>下拉菜单</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xradio').exec();"><span class="glyphicon glyphicon-record form-control-icon"></span>单选框</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xcheckbox').exec();"><span class="glyphicon glyphicon-check form-control-icon"></span>复选框</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xcalculate').exec();"><span class="glyphicon glyphicon-question-sign form-control-icon"></span>计算控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xfetch').exec();"><span class="glyphicon glyphicon-wrench form-control-icon"></span>选择器</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xlist').exec();"><span class="glyphicon glyphicon-list-alt form-control-icon"></span>列表控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xmacrotag').exec();"><span class="glyphicon glyphicon-star-empty form-control-icon" ></span>宏标记</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xmacro').exec();"><span class="glyphicon glyphicon-star form-control-icon" ></span>宏控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xdocnum').exec();"><span class="glyphicon glyphicon-sound-5-1 form-control-icon"></span>流程计数控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xworkflow').exec();"><span class="glyphicon glyphicon-random form-control-icon"></span>流程选择控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xsql').exec();"><span class="glyphicon glyphicon-flash form-control-icon"></span>SQL查询控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xiframe').exec();"><span class="glyphicon glyphicon-cloud form-control-icon"></span>嵌套窗体控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('ximg').exec();"><span class="glyphicon glyphicon-camera form-control-icon"></span>图片上传控件</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xupload').exec();"><sapn class="glyphicon glyphicon-paperclip form-control-icon"></sapn> 单个附件上传</button>
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xuploads').exec();"><sapn class="glyphicon glyphicon-paperclip form-control-icon"></sapn> 批量附件上传</button>
<!-- 				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xfeedback').exec();">会签控件</button> -->
				<button class="btn btn-default btn-form-control" onclick="editor.getCommand('xseal').exec();"><span class="glyphicon glyphicon-pushpin form-control-icon"></span>签章控件</button>
			</div>		
        </div>
        <div id="center">
        	<div id="editor1" name="editor1" ></div>
        </div>
    </div>

	<div class="modal fade" id="improtform">
	   <div class="modal-dialog">
	   <form id="form1" method="post"  enctype="multipart/form-data" 
					action="<%=contextPath%>/tfd/system/workflow/form/act/WorkFlowFormAct/importFormAct.act?formId=<%=formId %>&leavePath=<%=leavePath%>">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">×</button>
	            <h4 class="modal-title">表单导入</h4>
	         </div>
	         <div class="modal-body form-group" style="height: 60px;">
			        <div style="float:left;line-height: 30px;"> 请选择路径：</div>
		        	<input type="file" id="inputfile" name="inputfile" style="float:left;">
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">关闭</button>
	            <button type="submit" class="btn btn-primary"  id="impbut"  name="impbut">导入</button>
	         </div>
	      </div>
	      </form>
	   </div>
	</div>
	
	<div class="modal fade" id="modal">
	   <div class="modal-dialog" id="div-modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myModalLabel"></h4>
	         </div>
	         <div class="modal-body">
	            <iframe id="modaliframe" name="modaliframe" frameborder="0"></iframe>
	         </div>
	         <div class="modal-footer">
	         	<button type="button" class="btn btn-primary"  id="savedata">确定  </button>
	            <button type="button" class="btn btn-default"  data-dismiss="modal">关闭  </button>
	         </div>
	      </div>
		</div>
	</div>
</body>
</html>
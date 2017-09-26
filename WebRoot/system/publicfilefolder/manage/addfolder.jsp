<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<%
	String folderId = request.getParameter("folderId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建公共资源</title>
<style type="text/css">
	.add_content{height:80%;width:60%;position:absolute;top:50px;left:20%;}
</style>
</head>
<body>
<form id="form1" name="form1" class="form-horizontal" >
	<div class="add_content" >
   <div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
          新建文件夹<button type="button" id="addTable" style="font-weight:bolder;float:right;margin-top:-9px;" class="btn btn-default">+</button>
      </h5>
   </a>
   <div class="panel-body" style="border:none;box-shadow:none;" >
   <table class="table table-striped" id="myTable"  >
   <tr>
   	<td width="25%" align="center" >排序号</td>
   	<td width="60%"  align="center" >文件夹名称</td>
   	<td></td>
   </tr>
</table>
</div>
</div>
 <div align="right">
 <input type="submit" id="btn_ok" onclick="javascript:addFolders();" class="btn btn-success" value="确定" >
<button type="button" id="btn_back" class="btn btn-default">返回</button>
   </div>
</div>
</form>
</body>
<script type="text/javascript">
var folderId = "<%=folderId%>";
var i = 0;
$(function(){
	addTable();
 	
	$('#btn_back').click(function(){
		history.go(-1);
		return false;
	})
	$('#addTable').click(function(){
		addTable();
	})
});
function addTable(){
	var tableHtml = "<tr id='tr"+i+"' ><td align='center' width='25%'><div class=\"col-xs-12 form-group\" ><input class='form-control folderNo' type='text' name=\"folderNo\"   ></div></td><td width='60%'  align='center' ><div class=\"col-xs-11 form-group\" ><input class='form-control folderName' type='text' name=\"folderName\"  ></div></td><td align='center' ><button onclick=javascript:removeTable('"+i+"') style='font-weight:bolder;float:right;margin-top:-5px;' class='btn btn-default'>-</button></td></tr>";
	i++;
	$('#myTable').append(tableHtml);
	/* $('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
		fields: {
			folderNo: {
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
	        folderName: {
	            validators: {
	            	container: 'popover',
	                notEmpty: {
	                    message: '文件夹名称不能为空'
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
	     addFolders();
	}); */
}
function removeTable(i){
	$('#myTable #tr'+i).remove();
}
function addFolders(){
	var folderNo = $('.folderNo');
	var folderName = $('.folderName');
	var i = 0;
	for(var j = 0 ; j < folderNo.size() ; j ++ ){
		var no = folderNo[j].value;
		var name = folderName[j].value;
		var requestUrl= '<%=contextPath%>/tfd/system/folder/act/FolderAct/addFolder.act';
 		$.ajax({
			url:requestUrl,
			data:{
				folderPid:folderId,
				folderNo:no,
				folderName:name,
				isPublic:'1'
			},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					i++;
				}
			}
 		});
	}
	if(i==folderNo.size()){
		top.layer.msg("添加成功");
		parent["left"].location=contextPath+"/system/publicfilefolder/manage/folderDir.jsp";
		history.go(0);
		return false;
	}
}
</script>
</html>